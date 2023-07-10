public class Book {
    private int id; // a unique identifier for the book.
    private String title; // title of the book.
    private Author author; // a reference variable for an Author class.
    public boolean borrowed; // a boolean variable indicates that the book is available or not.
    
    public Book(int id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    
    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public boolean isBorrowed(){
        return borrowed;
    }
    
    public boolean borrowed(){
        return false;
    }
    
    public boolean returned(){
        return false;
    }
    
    // this method returns information which contains book name and the author.
    public String toString(){
        return "Book name is " + getTitle() + ", Author is " + getAuthor();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
}