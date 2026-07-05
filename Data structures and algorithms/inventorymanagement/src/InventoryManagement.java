import java.util.HashMap;
import java.util.Collection;

/**
 * Exercise 1: Inventory Management System
 * ------------------------------------------
 * Stores products keyed by productId in a HashMap.
 *
 * WHY A HASHMAP?
 * A warehouse inventory is looked up, updated, and deleted by productId
 * far more often than it is iterated in order. A HashMap gives average
 * O(1) time for add/update/delete/search by key, versus O(n) for an
 * ArrayList (which would require scanning to find a matching productId).
 * The trade-off is that HashMap does not preserve insertion order and
 * uses more memory per entry (hash buckets) than a plain array/ArrayList.
 */
public class InventoryManagement {

    private final HashMap<Integer, Product> inventory = new HashMap<>();

    /** Add a new product. O(1) average case. */
    public boolean addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product ID " + product.getProductId() + " already exists. Use updateProduct() instead.");
            return false;
        }
        inventory.put(product.getProductId(), product);
        return true;
    }

    /** Update an existing product's quantity and price. O(1) average case. */
    public boolean updateProduct(int productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product ID " + productId + " not found. Cannot update.");
            return false;
        }
        product.setQuantity(newQuantity);
        product.setPrice(newPrice);
        return true;
    }

    /** Delete a product by ID. O(1) average case. */
    public boolean deleteProduct(int productId) {
        Product removed = inventory.remove(productId);
        if (removed == null) {
            System.out.println("Product ID " + productId + " not found. Cannot delete.");
            return false;
        }
        return true;
    }

    /** Look up a product by ID. O(1) average case. */
    public Product getProduct(int productId) {
        return inventory.get(productId);
    }

    /** Returns all products (unordered). */
    public Collection<Product> getAllProducts() {
        return inventory.values();
    }

    public int size() {
        return inventory.size();
    }
}
