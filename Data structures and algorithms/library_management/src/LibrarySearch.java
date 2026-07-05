import java.util.Arrays;

/**
 * Exercise 6: Library Management System
 * ------------------------------------------
 * Implements Linear Search (by title, any order) and Binary Search
 * (by title, requires the array to be sorted alphabetically by title first).
 */
public class LibrarySearch {

    /**
     * Linear Search by title: checks every book until a match is found.
     * Time complexity: O(n) worst/average case, O(1) best case.
     */
    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(targetTitle)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Binary Search by title: requires books[] to already be sorted by title.
     * Time complexity: O(log n) worst/average case, O(1) best case.
     */
    public static Book binarySearchByTitle(Book[] sortedBooks, String targetTitle) {
        int low = 0;
        int high = sortedBooks.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = sortedBooks[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (cmp == 0) {
                return sortedBooks[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    /** Sorts a copy of books[] alphabetically by title (precondition for binary search). */
    public static void sortByTitle(Book[] books) {
        Arrays.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }
}
