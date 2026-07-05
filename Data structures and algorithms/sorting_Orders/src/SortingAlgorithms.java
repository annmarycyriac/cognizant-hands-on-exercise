/**
 * Exercise 3: Sorting Customer Orders
 * --------------------------------------
 * Implements Bubble Sort and Quick Sort, both sorting Order[] by totalPrice
 * in ascending order.
 */
public class SortingAlgorithms {

    /**
     * Bubble Sort: repeatedly swaps adjacent out-of-order elements.
     * Time complexity: O(n^2) worst/average case, O(n) best case (already sorted,
     * with the early-exit optimization below).
     */
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted, stop early
        }
    }

    /**
     * Quick Sort: picks a pivot, partitions the array around it, then
     * recursively sorts each partition.
     * Time complexity: O(n log n) average case, O(n^2) worst case (rare, only
     * with a poorly chosen pivot on already-sorted/reverse-sorted input).
     */
    public static void quickSort(Order[] orders) {
        quickSort(orders, 0, orders.length - 1);
    }

    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivotValue = orders[high].getTotalPrice(); // last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivotValue) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}
