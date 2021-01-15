
import java.util.ArrayList;

public class Library {
    private String address;
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private static final String openingTime = "9 am";
    private static final String closingTime = "5 pm.";
    
    public Library(String address){
        this.address = address;
    }
    
    // it prints the opening hours of the library.
    public static void printOpeningHours(){
        System.out.println("Libraries are open daily from " + openingTime + " to " + closingTime);
    }
    
    // it prints the addrees of the library.
    public void printAddress(){
        System.out.println(address);
    }
    
    // it adds the given book to the books (ArrayList<Book>).
    public boolean addBook(Book book){
        return books.add(book);
    }
    
    // it adds the given customer to the customers (ArrayList<Customer>) after checking length of customer name.
    public void addCustomer(Customer customer){
        try{
            if (customer.getName().length() > 3){
                customers.add(customer);
            }
            else {
                throw new StringIndexOutOfBoundsException(customer.getName() + " is lesser than 3.");
            }
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(customer.getName() + " is lesser than 3.");
        }
    }
    
    // this method checks if person is exists, if he exists then it checks the book and if all the information is correct, it gives the book to the customer.
    public void borrowBook(String bookName, String personName){
        
        int ifCustomerFound = 0;
        for (Customer currentCustomer : customers){
            if (currentCustomer.getName().equals(personName)){
                if (ifCustomerFound == 0){
                    ifCustomerFound = 1; // this determines personName is in customers list.
                }
                if (!currentCustomer.borrowABook){
                    ifCustomerFound = 2; // this determines current user has not borrowed a book.
                    
                }
            }
        }
        
        switch (ifCustomerFound) {
            case 0:
                System.out.println("Sorry, " + personName + " is not a customer");
                break;
            case 1:
                System.out.println("Sorry, " + personName + " already borrowed a book");
                break;
            case 2: // this case checks books if in library after that if it borrowed already if not it borrows by personName.
                int ifFound = 0;
                for (Book currentBook : books){
                    if (currentBook.getTitle().equals(bookName)){
                        if (ifFound == 0){
                            ifFound = 1;
                        }
                        if (!currentBook.isBorrowed()){
                            
                            ifFound = 2;
                            break;
                        }
                    }
                }
                
                switch (ifFound) {
                    case 0:
                        System.out.println("Sorry, this book is not in our catalog");
                        break;
                    case 1:
                        System.out.println("Sorry, this book is already borrowed");
                        break;
                    case 2:
                        for (Customer currentCustomer : customers){
                            if (currentCustomer.getName().equals(personName)){
                                currentCustomer.borrowABook = true;
                                break;
                            }
                            
                        }
                        for (Book currentBook : books){
                            if (currentBook.getTitle().equals(bookName) && currentBook.borrowed != true){
                                currentBook.borrowed = true;
                                break;
                            }
                            
                        }
                        System.out.println(personName + " successfully borrowed " + bookName + ".");
                        
                        for (Customer currentCustomer : customers){ // this for loop determines the current user and current book after that it calls setBorrowedBook()
                            if (currentCustomer.getName().equals(personName)){
                                for (Book currentBook : books){
                                    if (currentBook.getTitle().equals(bookName)){
                                        currentCustomer.setBorrowedBook(currentBook);
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                
                break;
            default:
                break;
        }
    }
    
    // this method checks if user exists, then it checks if book is available and then it checks if user borrow any book then it returns.
    public void returnBook(String personName){
        
        int checkUser = 0;
        for (Customer currentCustomer : customers){
            if (currentCustomer.getName().equals(personName)){
                if (checkUser == 0){
                    checkUser = 1;
                }
                if (currentCustomer.getBorrowedBook() != null){
                    checkUser = 2;
                }
                else if (currentCustomer.getBorrowedBook() == null){
                    checkUser = 3;
                }
            }
        }
        
        switch(checkUser){
            case 0:
                System.out.println("Sorry, " + personName + " is not a customer.");
                break;
            case 1:
                System.out.println("Sorry, " + personName + " did not barrow a book");
                break;
            case 2:
                for (Customer currentUser : customers){
                    if (currentUser.getName().equals(personName)){
                        System.out.println(personName + " successfully returned " + currentUser.borrowedBook.getTitle());
                        currentUser.borrowABook = false;
                        currentUser.borrowedBook.borrowed = false;
                        break;
                    }
                }
                break;
            case 3:
                System.out.println("Sorry, " + personName + " did not barrow a book");
                break;
        }
    }
    
    // this method prints available books which are in libraries.
    public void printAvailableBooks(){
        boolean currentBooks = false;
        for (Book availableBooks : books){
            if (!availableBooks.isBorrowed()){
                System.out.println(availableBooks.toString());
                currentBooks = true;
            }
        }
        if (!currentBooks){
            System.out.println("No book in catalog.");
        }
    }
    
    ArrayList<Book> getBooks(){
        return books;
    }
    
    ArrayList<Customer> getCustomers(){
        return customers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
