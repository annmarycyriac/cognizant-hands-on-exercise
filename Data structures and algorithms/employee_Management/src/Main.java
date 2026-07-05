/**
 * Exercise 4: Employee Management System - Demo
 * ----------------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System Demo (Array-Based) ===\n");

        // Start with a deliberately small capacity to show resizing in action.
        EmployeeManagement em = new EmployeeManagement(2);

        System.out.println("-- Adding employees (initial capacity = 2) --");
        em.addEmployee(new Employee(1, "Alice Johnson", "Engineer", 85000));
        em.addEmployee(new Employee(2, "Bob Smith", "Designer", 72000));
        em.addEmployee(new Employee(3, "Carla Diaz", "Manager", 95000)); // triggers resize
        em.addEmployee(new Employee(4, "David Lee", "Engineer", 88000));
        System.out.println("Size: " + em.size() + ", Capacity: " + em.capacity());

        System.out.println("\n-- Traversing all employees --");
        em.traverseEmployees();

        System.out.println("\n-- Searching for employee ID 3 --");
        Employee found = em.searchEmployee(3);
        System.out.println("Found: " + found);

        System.out.println("\n-- Searching for non-existent employee ID 99 --");
        System.out.println("Found: " + em.searchEmployee(99));

        System.out.println("\n-- Deleting employee ID 2 (Bob Smith) --");
        em.deleteEmployee(2);
        System.out.println("After delete, size: " + em.size());
        System.out.println("Remaining employees:");
        em.traverseEmployees();

        printAnalysis();
    }

    private static void printAnalysis() {
        System.out.println("\n=== Analysis ===");
        System.out.println("Time complexity of each operation on an array-backed store:");
        System.out.println("  add()       : O(1) amortized. Occasionally O(n) when the backing array");
        System.out.println("                must be resized (as shown above when adding the 3rd employee).");
        System.out.println("  search()    : O(n) - no ordering/index by employeeId, so we must scan.");
        System.out.println("  traverse()  : O(n) - visiting every element is inherently linear.");
        System.out.println("  delete()    : O(n) - O(n) to find + O(n) to shift remaining elements left.");
        System.out.println();
        System.out.println("Limitations of arrays:");
        System.out.println("  - Fixed size: growing requires allocating a new array and copying every");
        System.out.println("    element (O(n)), unlike a linked list which can grow in O(1).");
        System.out.println("  - Costly deletion/insertion in the middle: every element after the gap");
        System.out.println("    must shift, giving O(n) even though only one logical item changed.");
        System.out.println("  - No direct key-based lookup (unlike a HashMap), so searching by an");
        System.out.println("    attribute (not index) is always O(n) unless the array is kept sorted");
        System.out.println("    and binary search is used (O(log n), but insert/delete then costs more).");
        System.out.println();
        System.out.println("When to use arrays:");
        System.out.println("  - When the number of employees is known/bounded and mostly static.");
        System.out.println("  - When fast index-based access (O(1) by position) matters more than");
        System.out.println("    fast key-based search or frequent insert/delete.");
        System.out.println("  - For a growing employee roster with frequent adds/removes and lookups by");
        System.out.println("    ID, a HashMap<Integer, Employee> (as used in Exercise 1) would be a");
        System.out.println("    better real-world choice than a raw array.");
    }
}
