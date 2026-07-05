/**
 * Exercise 6: Library Management System - Demo
 * ----------------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Library Management System Demo ===\n");

        Book[] books = {
            new Book(1, "The Hobbit", "J.R.R. Tolkien"),
            new Book(2, "Dune", "Frank Herbert"),
            new Book(3, "1984", "George Orwell"),
            new Book(4, "Brave New World", "Aldous Huxley"),
            new Book(5, "Fahrenheit 451", "Ray Bradbury"),
            new Book(6, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(7, "Moby Dick", "Herman Melville"),
        };

        String target = "Fahrenheit 451";

        System.out.println("-- Linear Search for \"" + target + "\" (original order) --");
        Book foundLinear = LibrarySearch.linearSearchByTitle(books, target);
        System.out.println("Result: " + foundLinear);

        Book[] sortedBooks = books.clone();
        LibrarySearch.sortByTitle(sortedBooks);

        System.out.println("\n-- Books sorted alphabetically by title (for Binary Search) --");
        for (Book b : sortedBooks) {
            System.out.println("  " + b);
        }

        System.out.println("\n-- Binary Search for \"" + target + "\" (sorted array) --");
        Book foundBinary = LibrarySearch.binarySearchByTitle(sortedBooks, target);
        System.out.println("Result: " + foundBinary);

        System.out.println("\n-- Searching for a book that doesn't exist (\"The Odyssey\") --");
        System.out.println("Linear result: " + LibrarySearch.linearSearchByTitle(books, "The Odyssey"));
        System.out.println("Binary result: " + LibrarySearch.binarySearchByTitle(sortedBooks, "The Odyssey"));

        printAnalysis();
    }

    private static void printAnalysis() {
        System.out.println("\n=== Analysis ===");
        System.out.println("Linear Search: O(n) worst/average case, O(1) best case.");
        System.out.println("  Works on any order (sorted or not) - no pre-processing required.");
        System.out.println();
        System.out.println("Binary Search: O(log n) worst/average case, O(1) best case.");
        System.out.println("  Requires the collection to be sorted by title first (O(n log n) one-time");
        System.out.println("  cost if not already sorted).");
        System.out.println();
        System.out.println("When to use each, based on catalog size and order:");
        System.out.println("  - Small library (a few dozen books) or a catalog that changes constantly:");
        System.out.println("    linear search's simplicity outweighs binary search's setup cost.");
        System.out.println("  - Large, mostly-static library catalog (thousands+ of books) that's already");
        System.out.println("    sorted or rarely updated: binary search's O(log n) is dramatically faster");
        System.out.println("    - e.g. finding a title among 100,000 books takes ~17 comparisons with");
        System.out.println("    binary search versus up to 100,000 with linear search.");
        System.out.println("  - If users search by multiple fields (title OR author), you'd need a");
        System.out.println("    separate sorted array/index per field, or a HashMap-based index, since");
        System.out.println("    one sort order only speeds up searches on that one field.");
    }
}
