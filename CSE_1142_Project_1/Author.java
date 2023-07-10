public class Author extends Person{
    private String publisher;
    
    public Author(String name, String publisher, int birthDate){
        super(name, birthDate);
        this.publisher = publisher;
    }
    
    // this method returns author's name.
    @Override
    public String toString(){
        return getName();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
