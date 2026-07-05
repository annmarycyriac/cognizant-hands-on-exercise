import java.util.Arrays;
import java.util.Random;

/**
 * Exercise 3: Sorting Customer Orders - Demo
 * -----------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sorting Customer Orders Demo ===\n");

        Order[] smallSample = {
            new Order(1, "Alice", 250.75),
            new Order(2, "Bob", 89.99),
            new Order(3, "Carla", 512.40),
            new Order(4, "David", 15.20),
            new Order(5, "Eva", 300.00),
        };

        System.out.println("-- Original orders --");
        printOrders(smallSample);

        Order[] forBubble = smallSample.clone();
        SortingAlgorithms.bubbleSort(forBubble);
        System.out.println("\n-- After Bubble Sort (by totalPrice ascending) --");
        printOrders(forBubble);

        Order[] forQuick = smallSample.clone();
        SortingAlgorithms.quickSort(forQuick);
        System.out.println("\n-- After Quick Sort (by totalPrice ascending) --");
        printOrders(forQuick);

        // Performance comparison on a larger random dataset
        System.out.println("\n-- Performance comparison on 5,000 random orders --");
        Order[] largeBubble = generateRandomOrders(5000);
        Order[] largeQuick = largeBubble.clone();

        long start = System.nanoTime();
        SortingAlgorithms.bubbleSort(largeBubble);
        long bubbleTime = System.nanoTime() - start;

        start = System.nanoTime();
        SortingAlgorithms.quickSort(largeQuick);
        long quickTime = System.nanoTime() - start;

        System.out.println("Bubble Sort time: " + (bubbleTime / 1_000_000.0) + " ms");
        System.out.println("Quick Sort time : " + (quickTime / 1_000_000.0) + " ms");
        System.out.println("Both sorted correctly: " + isSortedAscending(largeBubble) + " / " + isSortedAscending(largeQuick));

        printAnalysis();
    }

    private static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    private static Order[] generateRandomOrders(int count) {
        Random rand = new Random(42); // fixed seed for reproducibility
        Order[] orders = new Order[count];
        for (int i = 0; i < count; i++) {
            double price = 5 + rand.nextDouble() * 995; // between 5 and 1000
            orders[i] = new Order(i, "Customer" + i, price);
        }
        return orders;
    }

    private static boolean isSortedAscending(Order[] orders) {
        for (int i = 0; i < orders.length - 1; i++) {
            if (orders[i].getTotalPrice() > orders[i + 1].getTotalPrice()) return false;
        }
        return true;
    }

    private static void printAnalysis() {
        System.out.println("\n=== Analysis ===");
        System.out.println("Bubble Sort:");
        System.out.println("  Best case  : O(n)   - already sorted, with early-exit optimization");
        System.out.println("  Average    : O(n^2)");
        System.out.println("  Worst case : O(n^2) - reverse sorted input");
        System.out.println("  Space      : O(1) extra (in-place)");
        System.out.println();
        System.out.println("Quick Sort:");
        System.out.println("  Best case  : O(n log n)");
        System.out.println("  Average    : O(n log n)");
        System.out.println("  Worst case : O(n^2) - poor pivot choice on already sorted/reverse data");
        System.out.println("               (mitigated in practice with random or median-of-three pivots)");
        System.out.println("  Space      : O(log n) extra for the recursion stack (in-place partitioning)");
        System.out.println();
        System.out.println("Why Quick Sort is generally preferred:");
        System.out.println("  For large order lists, O(n log n) vastly outperforms O(n^2) - e.g. sorting");
        System.out.println("  10,000 orders is roughly 10,000 x slower with Bubble Sort's n^2 growth than");
        System.out.println("  Quick Sort's n log n growth. The timing above on 5,000 random orders shows");
        System.out.println("  this gap directly. Bubble Sort is only reasonable for small or nearly-sorted");
        System.out.println("  datasets where its simplicity and low overhead can be acceptable.");
    }
}
