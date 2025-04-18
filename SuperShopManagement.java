import java.util.*;


public class SuperShopManagement {


    public static void main(String[] args) {
        ShopManager manager = new ShopManager();
        manager.start();
    }
}


class ShopManager {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Product> products = new ArrayList<>();
    private final List<Sale> sales = new ArrayList<>();


    public void start() {
        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice: ");
            choice = getValidIntInput();
            handleChoice(choice);
        } while (choice != 6);
    }


    private void showMenu() {
        System.out.println("\n===== Super Shop Management System =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Make a Sale");
        System.out.println("5. View Sales");
        System.out.println("6. Exit");
    }


    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> viewProducts();
            case 3 -> updateProduct();
            case 4 -> makeSale();
            case 5 -> viewSales();
            case 6 -> System.out.println("Exiting the system. Goodbye!");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        System.out.print("Enter product price: ");
        double price = getValidDoubleInput();

        products.add(new Product(name, price));
        System.out.println("Product added successfully!");
    }

    private void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\n===== Product List =====");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
    }


    private void updateProduct() {
        viewProducts();
        if (products.isEmpty()) return;

        System.out.print("Enter product number to update: ");
        int index = getValidIntInput() - 1;

        if (index < 0 || index >= products.size()) {
            System.out.println("Invalid product number.");
            return;
        }

        Product product = products.get(index);
        System.out.print("Enter new name (current: " + product.getName() + "): ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        System.out.print("Enter new price (current: " + product.getPrice() + "): ");
        double newPrice = getValidDoubleInput();

        product.setName(newName);
        product.setPrice(newPrice);
        System.out.println("Product updated successfully!");
    }

    private void makeSale() {
        viewProducts();
        if (products.isEmpty()) return;

        System.out.print("Enter product number to sell: ");
        int index = getValidIntInput() - 1;

        if (index < 0 || index >= products.size()) {
            System.out.println("Invalid product number.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = getValidIntInput();

        Product product = products.get(index);
        double total = product.getPrice() * quantity;
        sales.add(new Sale(product.getName(), quantity, total));

        System.out.println("Sale successful! Total: " + total);
    }

    private void viewSales() {
        if (sales.isEmpty()) {
            System.out.println("No sales recorded.");
            return;
        }
        System.out.println("\n===== Sales List =====");
        for (Sale sale : sales) {
            System.out.println(sale);
        }
    }

    private int getValidIntInput() {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                if (input <= 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private double getValidDoubleInput() {
        double input;
        while (true) {
            try {
                input = scanner.nextDouble();
                scanner.nextLine();
                if (input <= 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: " + price;
    }
}


class Sale {
    private final String productName;
    private final int quantity;
    private final double totalAmount;

    public Sale(String productName, int quantity, double totalAmount) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Product: " + productName + ", Quantity: " + quantity + ", Total: " + totalAmount;
    }
}


