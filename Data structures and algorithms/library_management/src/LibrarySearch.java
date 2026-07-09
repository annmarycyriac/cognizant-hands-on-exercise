import java.util.Arrays;

/**
 * Exercise 6: Library Management System
 * ------------------------------------------

 */
public class LibrarySearch {

    
    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(targetTitle)) {
                return b;
            }
        }
        return null;
    }

    
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

   
    public static void sortByTitle(Book[] books) {
        Arrays.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }
}
