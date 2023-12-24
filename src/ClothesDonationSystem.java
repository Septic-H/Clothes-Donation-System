import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.awt.Desktop;
import java.net.URI;

/**
 * This class implements the Clothes Donation System.
 * It allows users to donate clothes and view donated clothes and donation organizations.
 * <p>
 *     The system stores the data in text files.
 *     The usersList.txt file stores the list of users.
 *     The clothesList.txt file stores the list of clothes donated by the users.
 *     The donationCompaniesList.txt file stores the list of donation organizations.
 *     The data is loaded from the files to ArrayLists when the system is initialized.
 *     The data is saved to the files when the system is closed.
 *     The system also checks if a user already exists in the system.
 *     If the user exists, it displays the previous clothes donated by the user.
 *     If the user does not exist, it creates a new user ID for the user.
 *     The system also allows users to donate clothes.
 *     The system also allows users to view donated clothes and donation organizations.
 *     The system also allows users to exit the application.
 * </p>
 *
 * @version 1.0
 */

public class ClothesDonationSystem {
    private static final String USERS_FILE = "usersList.txt";
    private static final String CLOTHES_FILE = "clothesList.txt";
    private static final String DONATION_COMPANIES_FILE = "donationCompaniesList.txt";

    private static final File userFilePath = new File(USERS_FILE);
    private static final File clothesFilePath = new File(CLOTHES_FILE);
    private static final File donationCompaniesFilePath = new File(DONATION_COMPANIES_FILE);

    private ArrayList<String> usersList;
    private ArrayList<String> clothesList;
    private ArrayList<String> donationCompaniesList;

    private final Scanner scanner = new Scanner(System.in);

    private ClothesDonationSystem() {
        // Initialize ArrayLists
        usersList = new ArrayList<>();
        clothesList = new ArrayList<>();
        donationCompaniesList = new ArrayList<>();

        // Create files if they don't exist
        createUsersList();
        createClothesList();
        createDonationCompaniesList();

        // Load data from files to ArrayLists
        loadUsersData();
        loadClothesData();
        loadDonationCompaniesData();
    }

    private void createUsersList() {
        if (!userFilePath.exists()) {
            try {
                userFilePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createClothesList() {
        if (!clothesFilePath.exists()) {
            try {
                clothesFilePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createDonationCompaniesList() {
        if (!donationCompaniesFilePath.exists()) {
            try {
                donationCompaniesFilePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadUsersData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                usersList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadClothesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CLOTHES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clothesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDonationCompaniesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DONATION_COMPANIES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                donationCompaniesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String username = getUsername();

    private String getUsername() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        return username;
    }

    private void checkUser() {
        if (usersList.contains(username)) {
            System.out.println("This username already exists in the system. Accessing the user's previous donations.");
        } else {
            System.out.println("Creating a new user ID for " + username + ".");
            // Add the new user to the system
            usersList.add(username);
            // Save the updated usersList to the file
            saveUsersData();
        }
    }

    private void saveUsersData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (String user : usersList) {
                writer.write(user);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Donate Clothes");
        System.out.println("2. View All Previously Donated Clothes");
        System.out.println("3. View Donation Organizations");
        System.out.println("4. Search for Previously Donated Items");
        System.out.println("5. Exit the Application");

        int choice = 0;

        try {
            System.out.print("Enter your choice (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> donateClothes();
                case 2 -> displayPreviousDonations(username);
                case 3 -> viewDonationOrganizations();
                case 4 -> searchItems();
                case 5 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
        }

        // Recursive call to display the main menu again
        displayMainMenu();
    }

    private void donateClothes() {

        System.out.println("Donation Menu:");
        System.out.println("1. Uppers");
        System.out.println("2. Lowers");

        int choice = 0;
        try {
            System.out.print("Pick a type (1 or 2): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> donateUppers();
                case 2 -> donateLowers();
                default -> System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
        }

        // Recursive call to display the donation menu again
        donateClothes();
    }

    private void donateUppers() {

        System.out.println("Uppers Menu:");
        System.out.println("1. Shirts");
        System.out.println("2. Hoodies");
        System.out.println("3. Jackets");

        int choice = 0;
        try {
            System.out.print("Pick a category (1, 2, or 3): ");
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 3) {
                String category = switch (choice) {
                    case 1 -> "Shirt";
                    case 2 -> "Hoodie";
                    case 3 -> "Jacket";
                    default -> "Unknown";
                };

                // Input size with check
                String size = sizeForClothingItem();

                // Input quality with check
                String quality = qualityForClothingItem();

                // Input gender with check
                String gender = genderForClothingItem();

                // Generate a random price based on the quality
                double price = generateRandomPriceForClothingItem(quality);

                // Store the information in clothesList.txt
                String donationInfo = username + ", " + category + ", " + size + ", " + quality + ", " + gender + ", $" + price;
                clothesList.add(donationInfo);
                saveClothesData();

                System.out.println("Item donated successfully! Estimated worth: $" + price);

                // Ask if the user wants to donate more items or go back to the main menu
                askForMoreDonations();
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
        }

        // Recursive call to display the uppers menu again
        donateUppers();
    }

    private void donateLowers() {

        System.out.println("Lowers Menu:");
        System.out.println("1. Pants");
        System.out.println("2. Trousers");

        int choice = 0;
        try {
            System.out.print("Pick a category (1 or 2): ");
            choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                String category = (choice == 1) ? "Pant" : "Trouser";

                // Input size with check
                String size = sizeForClothingItem();

                // Input quality with check
                String quality = qualityForClothingItem();

                // Input gender with check
                String gender = genderForClothingItem();

                // Generate a random price based on the quality
                double price = generateRandomPriceForClothingItem(quality);

                // Store the information in clothesList.txt
                String donationInfo = username + ", " + category + ", " + size + ", " + quality + ", " + gender + ", $" + price;
                clothesList.add(donationInfo);
                saveClothesData();

                System.out.println("Item donated successfully! Estimated worth: $" + price);

                // Ask if the user wants to donate more items or go back to the main menu
                askForMoreDonations();
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
        }

        // Recursive call to display the lowers menu again
        donateLowers();
    }

    private String sizeForClothingItem() {
        String size;

        do {
            System.out.print("Enter size (XS, S, M, L, XL): ");
            size = scanner.next().trim().toUpperCase();

            if (!"XS".equals(size) && !"S".equals(size) && !"M".equals(size) && !"L".equals(size) && !"XL".equals(size)) {
                System.out.println("Invalid size. Please enter XS, S, M, L, or XL.");
            }

        } while (!size.matches("XS|S|M|L|XL"));

        return size;
    }


    private String qualityForClothingItem() {
        String quality;

        do {
            System.out.print("Enter quality (L for Low, M for Medium, H for High): ");
            quality = scanner.next().trim().toUpperCase();

            if (!"L".equals(quality) && !"M".equals(quality) && !"H".equals(quality)) {
                System.out.println("Invalid quality. Please enter L, M, or H.");
            }

        } while (!quality.matches("L|M|H"));

        return switch (quality) {
            case "L" -> "Low";
            case "M" -> "Medium";
            case "H" -> "High";
            default -> throw new IllegalStateException("Unexpected value: " + quality);
        };
    }

    private String genderForClothingItem() {
        String gender;

        do {
            System.out.print("Enter gender (M for Male, F for Female, U for Unisex): ");
            gender = scanner.next().trim().toUpperCase();

            if (!"M".equals(gender) && !"F".equals(gender) && !"U".equals(gender)) {
                System.out.println("Invalid gender. Please enter M, F, or U.");
            }

        } while (!gender.matches("M|F|U"));

        return switch (gender) {
            case "M" -> "Male";
            case "F" -> "Female";
            case "U" -> "Unisex";
            default -> throw new IllegalStateException("Unexpected value: " + gender);
        };
    }

    private double generateRandomPriceForClothingItem(String quality) {
        Random random = new Random();
        double basePrice = switch (quality.toLowerCase()) {
            case "low" -> 20.0;
            case "medium" -> 30.0;
            case "high" -> 40.0;
            default -> 0.0; // Default to 0 if quality is not recognized
        };

        // Generate a random value within the specified range with two decimal places
        double randomValue = basePrice + (random.nextDouble() * 10.0);
        return Math.round(randomValue * 100.0) / 100.0;
    }

    private void askForMoreDonations() {
        System.out.print("Do you want to donate more items? (yes/no): ");
        String choice = scanner.next().toLowerCase();

        if ("yes".equals(choice)) {
            // Continue donating items
            donateClothes();
        } else if ("no".equals(choice)) {
            // Go back to the main menu
            displayMainMenu();
        } else {
            System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            // Recursive call to ask for more donations again
            askForMoreDonations();
        }
    }

    private void saveClothesData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLOTHES_FILE))) {
            for (String item : clothesList) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPreviousDonations(String username) {
        try {
            if (clothesList == null || clothesList.isEmpty()) {
                throw new Exception("Clothes list is empty or null.");
            }

            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid username provided.");
            }

            username = username.trim(); // Trimming the username to remove leading/trailing spaces

            System.out.println("Previous donations by '" + username + "':");

            int itemCount = 0;
            double totalEstimatedWorth = 0;
            boolean found = false;
            for (String item : clothesList) {
                String[] itemDetails = item.split(", ");
                String donorUsername = itemDetails[0].trim();
                if (donorUsername.equals(username)) {
                    if (!found) {
                        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n",
                                "Sr.", "Category", "Size", "Quality", "Gender", "Estimated Price");
                        found = true;
                    }

                    // Print item number
                    System.out.printf("%-5d ", itemCount + 1);
                    itemCount++;

                    // Print details from the second element onward (first element is the username)
                    for (int i = 1; i < itemDetails.length; i++) {
                        System.out.printf("%-15s ", itemDetails[i]);
                    }

                    // Calculate total estimated worth
                    String price = itemDetails[itemDetails.length - 1].trim();
                    price = price.substring(1); // Remove the $ sign
                    totalEstimatedWorth += Double.parseDouble(price);

                    System.out.println();
                }
            }

            if (found) {
                System.out.printf("Total estimated worth of all donations: $%.2f\n", totalEstimatedWorth);
            } else {
                System.out.println("No previous donations found for '" + username + "'.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchItems() {
        System.out.println("Search Menu:");
        System.out.println("1. Search by Gender");
        System.out.println("2. Search by Size");
        System.out.println("3. Search by Quality");
        System.out.println("4. Go back to Main Menu");

        int choice = 0;

        try {
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> searchByGender(username);
                case 2 -> searchBySize(username);
                case 3 -> searchByQuality(username);
                case 4 -> {
                    displayMainMenu();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
        }

        // Recursive call to display the search menu again
        searchItems();
    }

    private void searchByGender(String username) {
        try {
            String gender;
            do {
                System.out.print("Enter gender (M for Male, F for Female, U for Unisex): ");
                gender = scanner.next().toUpperCase();

                if (!gender.matches("M|F|U")) {
                    System.out.println("Invalid gender. Please enter M, F, or U.");
                }
            } while (!gender.matches("M|F|U"));

            gender = switch (gender) {
                case "M" -> "Male";
                case "F" -> "Female";
                case "U" -> "Unisex";
                default -> throw new IllegalStateException("Unexpected value: " + gender);
            };

            int itemCount = 0;
            boolean found = false;
            System.out.println("Search results based on gender for '" + username + "':");

            for (String item : clothesList) {
                String[] itemDetails = item.split(", ");

                if (itemDetails.length >= 5 && itemDetails[4].equals(gender) && itemDetails[0].equals(username)) {
                    if (!found) {
                        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n",
                                "Sr.", "Category", "Size", "Quality", "Gender", "Estimated Price");
                        found = true;
                    }

                    // Print item number
                    System.out.printf("%-5d ", itemCount + 1);
                    itemCount++;

                    // Print details from the second element onward (first element is the username)
                    for (int i = 1; i < itemDetails.length; i++) {
                        System.out.printf("%-15s ", itemDetails[i]);
                    }
                    System.out.println();
                }
            }

            if (!found) {
                System.out.println("No items found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid gender.");
        }

        // Go back to the search menu
        askForSearchAgain();
    }

    private void searchBySize(String username) {
        try {
            String size;
            do {
                System.out.print("Enter size (XS/S/M/L/XL): ");
                size = scanner.next().toUpperCase();

                if (!size.matches("XS|S|M|L|XL")) {
                    System.out.println("Invalid size. Please enter XS, S, M, L, or XL.");
                }
            } while (!size.matches("XS|S|M|L|XL"));

            int itemCount = 0;
            boolean found = false;
            System.out.println("Search results based on size for '" + username + "':");

            for (String item : clothesList) {
                String[] itemDetails = item.split(", ");

                if (itemDetails.length >= 3 && itemDetails[2].equalsIgnoreCase(size) && itemDetails[0].equals(username)) {
                    if (!found) {
                        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n",
                                "Sr.", "Category", "Size", "Quality", "Gender", "Estimated Price");
                        found = true;
                    }

                    // Print item number
                    System.out.printf("%-5d ", itemCount + 1);
                    itemCount++;

                    // Print details from the second element onward (first element is the username)
                    for (int i = 1; i < itemDetails.length; i++) {
                        System.out.printf("%-15s ", itemDetails[i]);
                    }
                    System.out.println();
                }
            }

            if (!found) {
                System.out.println("No items found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid size.");
        }

        // Go back to the search menu
        askForSearchAgain();
    }


    private void searchByQuality(String username) {
        String quality;

        try {
            do {
                System.out.print("Enter quality (L for Low, M for Medium, H for High): ");
                quality = scanner.next().toUpperCase();

                if (!quality.matches("L|M|H")) {
                    System.out.println("Invalid quality. Please enter L, M, or H.");
                }
            } while (!quality.matches("L|M|H"));

            quality = switch (quality) {
                case "L" -> "Low";
                case "M" -> "Medium";
                case "H" -> "High";
                default -> throw new IllegalStateException("Unexpected value: " + quality);
            };

            int itemCount = 0;
            boolean found = false;
            System.out.println("Search results based on quality for '" + username + "':");

            for (String item : clothesList) {
                String[] itemDetails = item.split(", ");
                if (itemDetails.length >= 4 && itemDetails[3].equalsIgnoreCase(quality) && itemDetails[0].equals(username)) {
                    if (!found) {
                        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n",
                                "Sr.", "Category", "Size", "Quality", "Gender", "Estimated Price");
                        found = true;
                    }

                    // Print item number
                    System.out.printf("%-5d ", itemCount + 1);
                    itemCount++;

                    // Print details from the second element onward (first element is the username)
                    for (int i = 1; i < itemDetails.length; i++) {
                        System.out.printf("%-15s ", itemDetails[i]);
                    }
                    System.out.println();
                }
            }

            if (!found) {
                System.out.println("No items found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid quality.");
        }

        // Go back to the search menu
        askForSearchAgain();
    }

    private void askForSearchAgain() {
        System.out.print("Do you want to search again? (yes/no): ");
        String choice = scanner.next().toLowerCase();

        if ("yes".equals(choice)) {
            // Continue searching items
            searchItems();
        } else if ("no".equals(choice)) {
            // Go back to the main menu
            displayMainMenu();
        } else {
            System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            // Recursive call to ask for search again
            askForSearchAgain();
        }
    }

    private void viewDonationOrganizations() {
        System.out.println("Donation Organizations:");
        System.out.println("1. AkhuwatUK");
        System.out.println("2. Go back to Main Menu");

        int choice = 0;
        String url;
        try {
            System.out.print("Enter your choice (1 or 2): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    url = donationCompaniesList.get(0);
                    openURL(url);
                }
                case 2 -> {
                    displayMainMenu();
                }
                default -> System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
        }

        // Recursive call to display the donation organizations menu again
        viewDonationOrganizations();
    }

    private void openURL(String url) {
        try {
            System.out.println("Opening " + url + " in your browser...");
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClothesDonationSystem donationSystem = new ClothesDonationSystem();
        donationSystem.checkUser();
        donationSystem.displayMainMenu();
    }
}
