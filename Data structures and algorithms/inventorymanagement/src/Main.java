/**
 * Exercise 1: Inventory Management System - Demo
 * -------------------------------------------------
 
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Inventory Management System Demo ===\n");

        InventoryManagement inventory = new InventoryManagement();

        // --- ADD ---
        System.out.println("-- Adding products --");
        inventory.addProduct(new Product(101, "Wireless Mouse", 50, 19.99));
        inventory.addProduct(new Product(102, "Mechanical Keyboard", 30, 79.99));
        inventory.addProduct(new Product(103, "USB-C Hub", 100, 24.50));
        System.out.println("Inventory size after adds: " + inventory.size());

        // Attempt duplicate add
        inventory.addProduct(new Product(101, "Duplicate Mouse", 10, 15.00));

        // --- SEARCH ---
        System.out.println("\n-- Searching for product ID 102 --");
        Product found = inventory.getProduct(102);
        System.out.println("Found: " + found);

        // --- UPDATE ---
        System.out.println("\n-- Updating product ID 103 (quantity=80, price=22.99) --");
        inventory.updateProduct(103, 80, 22.99);
        System.out.println("After update: " + inventory.getProduct(103));

        // --- DELETE ---
        System.out.println("\n-- Deleting product ID 101 --");
        inventory.deleteProduct(101);
        System.out.println("Inventory size after delete: " + inventory.size());

        // Attempt operations on non-existent product
        System.out.println("\n-- Operations on non-existent product ID 999 --");
        inventory.updateProduct(999, 5, 5.00);
        inventory.deleteProduct(999);

        System.out.println("\n-- Remaining products --");
        for (Product p : inventory.getAllProducts()) {
            System.out.println(p);
        }

        printAnalysis();
    }

    private static void printAnalysis() {
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Using a HashMap<Integer, Product> keyed by productId:");
        System.out.println("  addProduct()    : O(1) average, O(n) worst case (hash collisions/resize)");
        System.out.println("  updateProduct() : O(1) average (get + set fields)");
        System.out.println("  deleteProduct() : O(1) average (HashMap.remove)");
        System.out.println("  getProduct()    : O(1) average (HashMap.get)");
        System.out.println();
        System.out.println("Compare to an ArrayList<Product> (no direct key index):");
        System.out.println("  add()    : O(1) amortized (append to end)");
        System.out.println("  update() : O(n) - must scan to find the matching productId");
        System.out.println("  delete() : O(n) - must scan, then O(n) shift for array-backed removal");
        System.out.println("  search() : O(n) - linear scan");
        System.out.println();
        System.out.println("Optimization notes:");
        System.out.println("  - HashMap is the right choice here because lookups are keyed by a unique ID.");
        System.out.println("  - If we also need ordered traversal (e.g. by productId), a TreeMap gives");
        System.out.println("    O(log n) operations with sorted iteration as a middle ground.");
        System.out.println("  - If quantity-based range queries are common (e.g. 'find all products with");
        System.out.println("    qty < 10'), a secondary index (e.g. a TreeMap<Integer, List<Product>> keyed");
        System.out.println("    by quantity) could speed up that specific query at the cost of extra memory.");
    }
}
