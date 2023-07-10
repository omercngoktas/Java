import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Product {
    private int id;
    private String name;
    private String category;
    private double price;

    public Product(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

        public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static List<Product> loadProductsFromJson() {
        List<Product> productList = null;

        try (Reader reader = new FileReader("products.json")) {
            Gson gson = new Gson();
            Type productListType = new TypeToken<List<Product>>() {}.getType();
            productList = gson.fromJson(reader, productListType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public boolean isDiscountable() {
        return !this.name.equalsIgnoreCase("phone");
    }

    public void listProductData() {
        System.out.println("Product ID: " + getId());
        System.out.println("Product Name: " + getName());
        System.out.println("Product Category: " + getCategory());
        System.out.println("Product Price: " + getPrice());
        System.out.println("---------------------------");
    }
    
    public void listProductWithName(String productName){
        if(getName().toLowerCase().contains(productName.toLowerCase())) {
            listProductData();
        }
    }

    public void updateProduct(String newName, String newCategory, int newPrice) {
        setName(newName);
        setCategory(newCategory);
        setPrice(newPrice);
    }

    //This function allows the user to select a product from a list of products and returns the chosen product. If an invalid selection is made, it prints an error message and returns null.
    public static Product buyProduct(List<Product> products) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice());
        }

        System.out.print("Select a product to add to the basket: ");
        int productIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (productIndex >= 1 && productIndex <= products.size()) {
            return products.get(productIndex - 1);
        } else {
            System.out.println("Invalid product selection.");
            return null;
        }
    }
    
    //This function searches for products in the productsBasket list whose names contain the searchString (case-insensitive), and if found, it prints their IDs and names; otherwise, it prints a message indicating no matching product was found.
    public static void searchProducts(String searchString, List<Product> productsBasket) {
        List<Product> matchingProducts = new ArrayList<>();
        boolean found = false;
        for (Product product : productsBasket) {
            if (product.getName().toLowerCase().contains(searchString.toLowerCase())) {
                matchingProducts.add(product);
                found = true;
            }
        }
        if(found) {
            for (Product foundProduct : matchingProducts) {
                System.out.println(foundProduct.getId() + "- " + foundProduct.getName());
            }
        } else {
            System.out.println("There is no found product with the given name.");
        }
    }

    //this function converts the products list to JSON format using Gson library, writes the JSON to a file specified by the filename, and handles any exceptions that may occur during the file writing process.
    public static void writeProductsToJson(String filename, List<Product> products) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // create Gson instance
            String json = gson.toJson(products); // convert list to JSON
            FileWriter writer = new FileWriter(filename); // create FileWriter for the given filename
            writer.write(json); // write JSON to file
            writer.close(); // close the writer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
