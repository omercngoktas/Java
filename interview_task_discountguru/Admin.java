import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;




public class Admin extends Person {
    private String role;

    public Admin(String username, String password, String firstName, String lastName, String email, String phone, String role) {
        super(username, password, firstName, lastName, email, phone);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static List<Admin> loadAdminsFromJson(String filename) {
        List<Admin> admins = new ArrayList<>();

        try {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(new FileReader(filename)).getAsJsonObject();
            JsonArray jsonAdmins = jsonObject.getAsJsonArray("admins");

            for (JsonElement jsonElement : jsonAdmins) {
                Admin admin = gson.fromJson(jsonElement, Admin.class);
                admins.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admins;
    }



    public void writeUsersToJson(String filename, List<User> users) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // create Gson instance
            String json = gson.toJson(users); // convert list to JSON
            FileWriter writer = new FileWriter(filename); // create FileWriter for the given filename
            writer.write(json); // write JSON to file
            writer.close(); // close the writer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to add a product
    public void addProduct(Product product) {
        Gson gson = new Gson();
        List<Product> products = Product.loadProductsFromJson();
        products.add(product);
        try (FileWriter writer = new FileWriter("products.json")) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    // method to remove a product
    public void removeProduct(Product product) {
        List<Product> products = Product.loadProductsFromJson();
        products.remove(product);
        // code to write the updated products list back to the JSON file
    }

    // method to update a product
    public void updateProduct(Product product) {
        List<Product> products = Product.loadProductsFromJson();
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p = product; // assuming Product class has proper setters
                break;
            }
        }
        // code to write the updated products list back to the JSON file
    }

    // method to list all users
    public void listAllUsers() {
        List<User> users = User.loadUsersFromJson("users.json");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Membership Duration: " + user.getMembershipDuration());
            System.out.println("Affiliate: " + user.getAffiliate());
            System.out.println("Card: " + user.getCard());
            System.out.println("---------------------------");
            }

    }

    // method to get user details
    public void  getAdminDetails() {
        System.out.println("-------------------------------------");
        System.out.println("Username: " + getUsername());
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Role: " + getRole());
        System.out.println("-------------------------------------");
    }

    // method to update user
    public void updateUser(User userToUpdate) {
    // Load all users
    List<User> users = User.loadUsersFromJson("users.json");

    // Find and update the user
    for (User user : users) {
        if (user.getUsername().equals(userToUpdate.getUsername())) {
            // Assuming the userToUpdate object has the updated information
            user.setPassword(userToUpdate.getPassword());
            user.setFirstName(userToUpdate.getFirstName());
            user.setLastName(userToUpdate.getLastName());
            user.setEmail(userToUpdate.getEmail());
            user.setPhone(userToUpdate.getPhone());
            user.setMembershipDuration(userToUpdate.getMembershipDuration());
            user.setAffiliate(userToUpdate.getAffiliate());
            user.setCard(userToUpdate.getCard());
            break;
        }
    }

    // Write the updated users back to the JSON file
    // writeUsersToJson(users, "users.json");
}

    //method to delete user
    public void deleteUser(String username, List<User> users) {
        boolean userDeleted = users.removeIf(user -> user.getUsername().equals(username));
        if (userDeleted) {
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User not found or not deleted");
        }
        writeUsersToJson("users.json", users);
    }

    public static boolean isValidCredentials(String username, String password, Admin currentAdmin) {
    if (currentAdmin.getUsername().equals(username) && currentAdmin.getPassword().equals(password)) {
        return true;
    }
    return false;
}
}