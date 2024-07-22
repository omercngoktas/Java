package com.omercngoktas.accounts.service;

import com.omercngoktas.accounts.dto.CustomerDto;

public interface IAccountsService {

    /*
     *
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);
}
