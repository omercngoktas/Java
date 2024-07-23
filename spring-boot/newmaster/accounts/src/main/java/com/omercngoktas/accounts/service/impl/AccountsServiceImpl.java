package com.omercngoktas.accounts.service.impl;

import org.springframework.stereotype.Service;

import com.omercngoktas.accounts.constants.AccountsConstants;
import com.omercngoktas.accounts.dto.AccountsDto;
import com.omercngoktas.accounts.dto.CustomerDto;
import com.omercngoktas.accounts.entity.Accounts;
import com.omercngoktas.accounts.entity.Customer;
import com.omercngoktas.accounts.exception.CustomerAlreadyExistsException;
import com.omercngoktas.accounts.exception.ResourceNotFoundException;
import com.omercngoktas.accounts.mapper.AccountsMapper;
import com.omercngoktas.accounts.mapper.CustomerMapper;
import com.omercngoktas.accounts.repository.AccountsRepository;
import com.omercngoktas.accounts.repository.CustomerRepository;
import com.omercngoktas.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber: "
                    + customer.getMobileNumber());
        } else {
            Customer savedCustomer = customerRepository.save(customer);
            Accounts accountsToSave = createNewAccout(savedCustomer);
            accountsRepository.save(accountsToSave);
        }
    }

    private Accounts createNewAccout(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(mobileNumber);
        if (optionalCustomer.isPresent()) {
            Optional<Accounts> optionalAccount = accountsRepository
                    .findByCustomerId(optionalCustomer.get().getCustomerId());
            if (optionalAccount.isPresent()) {
                CustomerDto customerDto = CustomerMapper.mapToCustomerDto(optionalCustomer.get(), new CustomerDto());
                customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(optionalAccount.get(), new AccountsDto()));
                return customerDto;
            } else {
                throw new ResourceNotFoundException("Account", "customerId",
                        optionalCustomer.get().getCustomerId().toString());
            }
        } else {
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        }
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Optional<Accounts> optionalAccounts = accountsRepository.findById(accountsDto.getAccountNumber());
            if (optionalAccounts.isPresent()) {
                Accounts accounts = optionalAccounts.get();
                AccountsMapper.mapToAccounts(accountsDto, accounts);
                accounts = accountsRepository.save(accounts);
                Long customerId = accounts.getCustomerId();
                Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
                if (optionalCustomer.isPresent()) {
                    Customer customer = optionalCustomer.get();
                    CustomerMapper.mapToCustomer(customerDto, customer);
                    customerRepository.save(customer);
                    isUpdated = true;
                } else {
                    throw new ResourceNotFoundException(
                            "Customer",
                            "CustomerID",
                            customerId.toString());
                }
            } else {
                throw new ResourceNotFoundException(
                        "Account",
                        "AccountNumber",
                        accountsDto.getAccountNumber().toString());
            }
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted = false;
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(mobileNumber);
        if (optionalCustomer.isPresent()) {
            accountsRepository.deleteByCustomerId(optionalCustomer.get().getCustomerId());
            customerRepository.deleteById(optionalCustomer.get().getCustomerId());
            isDeleted = true;
        } else {
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        }
        return isDeleted;
    }
}
