/**
 * Exercise 2: E-commerce Platform Search Function
 * --------------------------------------------------
 * Implements Linear Search (works on any order) and Binary Search
 * (requires the array to be sorted by productName).
 */
public class SearchAlgorithms {

    /**
     * Linear Search: scans every element until a match is found.
     * Works on an unsorted (or sorted) array.
     * Time complexity: O(n) worst/average case, O(1) best case.
     */
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Binary Search: repeatedly halves the search range.
     * REQUIRES the array to already be sorted by productName.
     * Time complexity: O(log n) worst/average case, O(1) best case.
     */
    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;   // target is alphabetically after mid
            } else {
                high = mid - 1;  // target is alphabetically before mid
            }
        }
        return null; // not found
    }

    /** Simple sort helper (by productName) so binarySearch's precondition holds. */
    public static void sortByName(Product[] products) {
        java.util.Arrays.sort(products, (a, b) -> a.getProductName().compareToIgnoreCase(b.getProductName()));
    }
}
