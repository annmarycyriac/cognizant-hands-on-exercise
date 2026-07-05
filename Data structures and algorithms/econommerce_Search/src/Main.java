/**
 * Exercise 2: E-commerce Platform Search Function - Demo
 * -----------------------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-commerce Search Function Demo ===\n");

        Product[] products = {
            new Product(1, "Wireless Mouse", "Electronics"),
            new Product(2, "Bluetooth Speaker", "Electronics"),
            new Product(3, "Running Shoes", "Footwear"),
            new Product(4, "Yoga Mat", "Fitness"),
            new Product(5, "Coffee Maker", "Kitchen"),
            new Product(6, "Desk Lamp", "Home"),
            new Product(7, "Backpack", "Accessories"),
            new Product(8, "Water Bottle", "Fitness"),
        };

        String target = "Yoga Mat";

        // --- Linear search on the unsorted array ---
        System.out.println("-- Linear Search for \"" + target + "\" (unsorted array) --");
        long start = System.nanoTime();
        Product foundLinear = SearchAlgorithms.linearSearch(products, target);
        long linearTime = System.nanoTime() - start;
        System.out.println("Result: " + foundLinear);
        System.out.println("Time taken: " + linearTime + " ns\n");

        // --- Binary search requires sorting first ---
        Product[] sortedProducts = products.clone();
        SearchAlgorithms.sortByName(sortedProducts);

        System.out.println("-- Array sorted by productName for Binary Search --");
        for (Product p : sortedProducts) {
            System.out.println("  " + p);
        }

        System.out.println("\n-- Binary Search for \"" + target + "\" (sorted array) --");
        start = System.nanoTime();
        Product foundBinary = SearchAlgorithms.binarySearch(sortedProducts, target);
        long binaryTime = System.nanoTime() - start;
        System.out.println("Result: " + foundBinary);
        System.out.println("Time taken: " + binaryTime + " ns\n");

        // Search for something that doesn't exist
        System.out.println("-- Searching for a non-existent product (\"Laptop Stand\") --");
        System.out.println("Linear result: " + SearchAlgorithms.linearSearch(products, "Laptop Stand"));
        System.out.println("Binary result: " + SearchAlgorithms.binarySearch(sortedProducts, "Laptop Stand"));

        printAnalysis();
    }

    private static void printAnalysis() {
        System.out.println("\n=== Analysis ===");
        System.out.println("Big O notation describes how an algorithm's running time (or space) grows");
        System.out.println("relative to input size n, focusing on the dominant term as n gets large,");
        System.out.println("so it lets us compare algorithms independent of hardware or specific input.");
        System.out.println();
        System.out.println("Linear Search:");
        System.out.println("  Best case  : O(1)   - target is the first element");
        System.out.println("  Average    : O(n)   - target is somewhere in the middle, ~n/2 comparisons");
        System.out.println("  Worst case : O(n)   - target is last, or not present (scan everything)");
        System.out.println();
        System.out.println("Binary Search (array must be sorted):");
        System.out.println("  Best case  : O(1)   - target is exactly the middle element");
        System.out.println("  Average    : O(log n)");
        System.out.println("  Worst case : O(log n) - halves the search space each comparison");
        System.out.println();
        System.out.println("Which is more suitable for an e-commerce search bar?");
        System.out.println("  Binary search is far faster for large, static or infrequently-changing");
        System.out.println("  catalogs (O(log n) vs O(n)) - e.g. searching 1,000,000 products takes");
        System.out.println("  ~20 comparisons with binary search vs up to 1,000,000 with linear search.");
        System.out.println("  However, binary search requires the data to be sorted and re-sorted on");
        System.out.println("  every insert/update, which costs O(n log n) per sort. In practice, real");
        System.out.println("  e-commerce search uses inverted indexes / search engines (e.g. Elasticsearch)");
        System.out.println("  rather than either of these, but binary search is the right in-memory choice");
        System.out.println("  when the catalog is sorted and mostly static (e.g. sorted product ID lookups).");
    }
}
