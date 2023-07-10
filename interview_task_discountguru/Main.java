import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<User> users = User.loadUsersFromJson("users.json");
        List<Product> products = Product.loadProductsFromJson();
        List<Admin> admins = Admin.loadAdminsFromJson("admins.json");

        User loggedUser = null;
        Admin loggedAdmin = null;
        Scanner scanner = new Scanner(System.in);
        int menuChoice;
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            // Prompt the user for input
            System.out.print("Enter your user ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            for(User currentUser : users) {
                if(currentUser.isValidCredentials(userId, password, currentUser)) {
                    loggedUser = currentUser;
                    isLoggedIn = true;
                    break;
                }
            }

            for(Admin currentAdmin : admins) {
                if(currentAdmin.isValidCredentials(userId, password, currentAdmin)) {
                    loggedAdmin = currentAdmin;
                    isLoggedIn = true;
                    break;
                }
            }

            if (!isLoggedIn) {
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        // Successfully logged in as user
        if (isLoggedIn && loggedUser != null) {
            System.out.println("Logged in as user. Welcome, " + loggedUser.getFirstName() + "!");
            // User menu options
            while (true) {
                System.out.println("MENU:");
                System.out.println("1. Add product to cart");
                System.out.println("2. Remove product from cart");
                System.out.println("3. View cart");
                System.out.println("4. Search products");
                System.out.println("5. View membership details");
                System.out.println("6. Complete shopping");
                System.out.println("7. Logout");
                System.out.print("Enter your choice: ");
                menuChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (menuChoice) {
                    case 1:
                        // add a product to basket
                        Product productToBuy = Product.buyProduct(products);
                        loggedUser.addToBasket(productToBuy);
                        System.out.println("Product added successfully.");
                        break;

                    case 2:
                        // Remove product from basket
                        if (loggedUser.getProductsBasket() == null) {
                            System.out.println("The basket is empty.");
                        } else {
                            System.out.println("Basket Contents:");
                            loggedUser.viewBasket();
                            
                            try {
                                System.out.print("Enter the order number of the product to remove: ");
                                int orderNumberToRemove = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                        
                                boolean isRemoved = loggedUser.removeFromBasket(orderNumberToRemove);
                                if (isRemoved) {
                                    System.out.println("Product removed from the basket.");
                                } else {
                                    System.out.println("Invalid order number. Failed to remove the product from the basket.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid order number.");
                                scanner.nextLine(); // Clear the input
                            }
                        }
                        break;

                    case 3:
                        // View basket
                        loggedUser.viewBasket();
                        break;

                    case 4:
                        System.out.print("Enter string for product to search: ");
                        String productToSearch = scanner.nextLine();
                        Product.searchProducts(productToSearch, products);
                        break;

                    case 5:
                        // View order history
                        loggedUser.viewMembershipDetails();
                        break;

                    case 6:
                        // Complete shopping
                        if (loggedUser.getProductsBasket() == null) {
                            System.out.println("Your basket is empty. Please add products before completing shopping.");
                        } else {
                            double totalPrice = 0.0;
                            double discountPercentage = loggedUser.getDiscountPercentage();
                            double discountAmount = 0.0;
                            for(Product boughtProduct : loggedUser.getProductsBasket()) {
                                if(boughtProduct.isDiscountable()) {
                                    double handledDiscount = boughtProduct.getPrice() - (boughtProduct.getPrice() * discountPercentage / 100);
                                    totalPrice += handledDiscount;
                                    discountAmount += (boughtProduct.getPrice() * discountPercentage / 100);
                                } else {
                                    totalPrice += boughtProduct.getPrice();
                                }
                            }
                            int discountNumber200 = (int)totalPrice / 200;
                            totalPrice = totalPrice - discountNumber200 * 5;
                            discountAmount += discountNumber200 * 5;
                            // Display the discount information and final price
                            System.out.println("Discount(applies to eligible products): " + discountPercentage + "%");
                            System.out.println("Discount amount: $" + discountAmount);
                            System.out.println("Final price: $" + totalPrice);
                            // Clear the user's basket
                            loggedUser.getProductsBasket().clear();
                            System.out.println("Thank you for shopping! Your order has been completed.");
                        }
                        break;

                    case 7:
                        // Logout
                        System.out.println("Logged out.");
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        else if (isLoggedIn && loggedAdmin != null) {
            System.out.println("Logged in as Admin. Welcome, " + loggedAdmin.getFirstName() + "!");
            while (true) {
                System.out.println("MENU:");
                System.out.println("1. Add new product");
                System.out.println("2. Remove product");
                System.out.println("3. Update product");
                System.out.println("4. List All Products");
                System.out.println("5. List Products With Name");
                System.out.println("6. List All Users");
                System.out.println("7. List Users With Username");
                System.out.println("8. Update users");
                System.out.println("9. Delete Users");
                System.out.println("10. Get Admins Detail");
                System.out.println("11. Logout");

                System.out.print("Enter your choice: ");
                menuChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (menuChoice) {
                    case 1:
                        // adding new product
                        System.out.print("Enter a name for new product: ");
                        String newProductName = scanner.nextLine();
                        System.out.print("Enter the category of the new product: ");
                        String newProductCategory = scanner.nextLine();
                        System.out.print("Enter the price of the new product: ");
                        double newProductPrice = scanner.nextDouble();
                        int newProductID = 0;
                        for(Product product : products) {
                            newProductID = product.getId();
                        }
                        newProductID++;
                        Product newProduct = new Product(newProductID, newProductName, newProductCategory,  newProductPrice);
                        products.add(newProduct);
                        
                        Product.writeProductsToJson("products.json", products);
                        break;

                    case 2:
                        // Remove product
                        System.out.print("Enter a name to remove a product: ");
                        String productNameRemove = scanner.nextLine();
                        List<Product> tempProducts = new ArrayList<>();

                        for(Product productToRemove : products) {
                            if(productToRemove.getName().toLowerCase().contains(productNameRemove.toLowerCase())) {
                                tempProducts.add(productToRemove);
                            }
                        }

                        if(tempProducts.size() != 0) {
                            int i = 1;
                            for(Product tProduct : tempProducts) {
                                System.out.println(i + "- " + tProduct.getName());
                                i++;
                            }
                            System.out.println(i + "- Cancel");
                            System.out.print("Select product to remove: ");
                            int productIdToRemove = scanner.nextInt();
                            if(productIdToRemove == i) { break; }

                            try {
                                boolean isRemoved = products.remove(tempProducts.get(productIdToRemove - 1));
                                if (isRemoved) {
                                    System.out.println("Product removed successfully.");
                                    Product.writeProductsToJson("products.json", products);
                                } else {
                                    System.out.println("Product not found. Nothing was removed.");
                                }
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Invalid product ID. Please enter a valid ID.");
                            }
                        }

                        else if(tempProducts.size() == 0) {
                            System.out.println("There is no found product with this name.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter product name to update: ");
                        String productNameToUpdate = scanner.nextLine();
                        for(Product productToSearch : products) {
                            productToSearch.listProductWithName(productNameToUpdate);
                        }
                        System.out.print("From the listed products enter product ID to update (0 for cancel): ");
                        int productIdToUpdate = scanner.nextInt();
                        if(productIdToUpdate == 0)  { break; }
                        scanner.nextLine();
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();
                        System.out.print("Enter new price: ");
                        int newPrice = scanner.nextInt();
                        products.get(productIdToUpdate).updateProduct(newName, newCategory, newPrice);
                        System.out.println("Product updated successfully!\n-------------------------------------");
                        Product.writeProductsToJson("products.json", products);
                        break;
                    case 4:
                        // list all products
                        for(Product productToList : products) {
                            productToList.listProductData();
                        }
                        break;
                    case 5:
                        System.out.print("Enter product name to search: ");
                        String productName = scanner.nextLine();
                        for(Product productToSearch : products) {
                            productToSearch.listProductWithName(productName);
                        }
                        break;
                    case 6:
                        // list all users
                        for(User userToList : users) {
                            userToList.viewMembershipDetails();
                        }
                        break;
                    case 7:
                        System.out.print("Enter Username to search: ");
                        String userName = scanner.nextLine();
                        for(User userToSearch : users){
                            userToSearch.listUsersWithUsername(userName);
                        }
                        break;
                        
                    case 8:
                        System.out.print("Enter Username to search: ");
                        String userNametoUpdate = scanner.nextLine();
                        for(User userToSearch : users){
                            userToSearch.listUsersWithUsername(userNametoUpdate);
                        }
                        System.out.print("From the listed Usernames enter username (exit for cancel) :");
                        String userNamestoUpdate = scanner.nextLine();
                        if(userNamestoUpdate.equals("exit"))  { break; }
                        User userToUpdate = users.stream()
                            .filter(user -> user.getUsername().equals(userNamestoUpdate))
                            .findFirst()
                            .orElse(null);
                        if (userToUpdate != null) {
                            System.out.print("Enter new Username: ");
                            String newUsername = scanner.nextLine();
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.nextLine();
                            System.out.print("Enter new First Name: ");
                            String newFirstName = scanner.nextLine();
                            System.out.print("Enter new Last Name: ");
                            String newLastName = scanner.nextLine();
                            System.out.print("Enter new email: ");
                            String newEmail = scanner.nextLine();
                            System.out.print("Enter new phone: ");
                            String newPhone = scanner.nextLine();
                            System.out.print("Enter new membership duration: ");
                            String newMembershipDuration = scanner.nextLine();
                            System.out.print("Is the user an affiliate? (true/false): ");
                            boolean newAffiliate = scanner.nextBoolean();
                            scanner.nextLine(); // Consume newline left-over
                            System.out.print("Enter new card type: ");
                            String newCard = scanner.nextLine();
                            userToUpdate.updateUser(newUsername, newPassword, newFirstName, newLastName, newEmail, newPhone, newMembershipDuration, newAffiliate, newCard);
                            System.out.println("User updated successfully!\n-------------------------------------");
                            User.writeUsersToJson("users.json", users);
                            break;
                        }
                    case 9:
                        System.out.print("Enter a username to remove a user: ");
                        String usernameToRemove = scanner.nextLine();
                        List<User> tempUsers = new ArrayList<>();
                        
                        for(User userToRemove : users) {
                            if(userToRemove.getUsername().toLowerCase().contains(usernameToRemove.toLowerCase())) {
                                tempUsers.add(userToRemove);
                            }
                        }
                        
                        if(tempUsers.size() != 0) {
                            int i = 1;
                            for(User tUser : tempUsers) {
                                System.out.println(i + "- " + tUser.getUsername());
                                i++;
                            }
                            System.out.println(i + "- Cancel");
                            System.out.print("Select user to remove: ");
                            int userIndexToRemove = scanner.nextInt();
                            scanner.nextLine(); // To consume newline left-over
                            if(userIndexToRemove == i) { return; }
                        
                            try {
                                User removedUser = tempUsers.get(userIndexToRemove - 1);
                                users.remove(removedUser);
                                System.out.println("User removed successfully.");
                                User.writeUsersToJson("users.json", users);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Invalid user ID. Please enter a valid ID.");
                            }
                        } else {
                            System.out.println("No user found with the given username.");
                        }
                        break;
                    case 10:
                        loggedAdmin.getAdminDetails();
                        break;
                    case 11:
                        System.out.println("Logged out.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        scanner.close();    
    }
}




