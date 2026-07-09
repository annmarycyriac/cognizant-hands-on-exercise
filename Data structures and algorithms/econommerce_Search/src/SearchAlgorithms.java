/**
 * Exercise 2: E-commerce Platform Search Function
 * --------------------------------------------------

 */
public class SearchAlgorithms {

    
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) {
                return p;
            }
        }
        return null;
    }

    
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

   
    public static void sortByName(Product[] products) {
        java.util.Arrays.sort(products, (a, b) -> a.getProductName().compareToIgnoreCase(b.getProductName()));
    }
}
