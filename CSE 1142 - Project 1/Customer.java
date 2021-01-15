
public class Customer extends Person{
    private String address;
    public Book borrowedBook; // stores the book which is borrowed by the customer.
    public boolean borrowABook = false;
    
    public Customer(String name, String birthPlace, int birthDate, String address){
        super(name, birthDate, birthPlace);
        this.address = address;
    }
    
    public Customer(String name, int birthDate, String address){
        super(name, birthDate);
        this.address = address;
    }
    
    public Customer(String name, String address){
        super(name);
        this.address = address;
    }
    
    public Customer(String name, int birthDate){
        super(name, birthDate);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public boolean isBorrowABook() {
        return borrowABook;
    }

    public void setBorrowABook(boolean borrowABook) {
        this.borrowABook = borrowABook;
    }
    
    // this method checks given information if they are available then it returns available values, if they are null, it prints "-".
    @Override
    public String toString(){
        
        if ((getBirthDate() == null) && (getBirthPlace() == null) && (getAddress() == null)){
            return "Name: " + getName() + ", Birth Date: -, Birth Place: -\nAddress: -";
        }
        
        else if ((getBirthDate() == null) && (getAddress() == null)){
            return "Name: " + getName() + ", Birth Date: -, Birth Place: " + getBirthPlace() + "\nAddress: -";
        }
        
        else if ((getBirthPlace() == null) && (getAddress() == null)){
            return "Name: " + getName() + ", Birth Date: " + getBirthDate() + ", Birth Place: -\nAddress: -";
        }
        
        else if ((getBirthDate() == null) && (getBirthPlace() == null)){
            return "Name: " + getName() + ", Birth Date: -, Birth Place: -\nAddress: " + getAddress();
        }
        
        else if (getBirthDate() == null){
            return "Name: " + getName() + ", Birth Date: -, Birth Place: " + getBirthPlace() + "\nAddress: " + getAddress();
        }
        
        else if (getBirthPlace() == null){
            return "Name: " + getName() + ", Birth Date: " + getBirthDate() + ", Birth Place: -\nAddress: " + getAddress();
        }
        
        else if (getAddress() == null){
            return "Name: " + getName() + ", Birth Date: " + getBirthDate() + ", Birth Place: " + getBirthPlace() + "\nAddress: -";
        }
        
        else{
            return "Name: " + getName() + ", Birth Date: " + getBirthDate() + ", Birth Place: " + getBirthPlace() + "\nAddress: " + getAddress();
        }
        
    }
}
