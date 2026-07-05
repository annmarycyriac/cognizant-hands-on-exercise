/**
 * Exercise 5: Task Management System - Demo
 * ------------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Task Management System Demo (Singly Linked List) ===\n");

        TaskLinkedList tasks = new TaskLinkedList();

        System.out.println("-- Adding tasks --");
        tasks.addTask(new Task(1, "Design database schema", "In Progress"));
        tasks.addTask(new Task(2, "Write unit tests", "Not Started"));
        tasks.addTask(new Task(3, "Deploy to staging", "Not Started"));
        tasks.addTask(new Task(4, "Code review", "In Progress"));
        System.out.println("Total tasks: " + tasks.size());

        System.out.println("\n-- Traversing tasks (head to tail) --");
        tasks.traverseTasks();

        System.out.println("\n-- Searching for task ID 3 --");
        System.out.println("Found: " + tasks.searchTask(3));

        System.out.println("\n-- Searching for non-existent task ID 99 --");
        System.out.println("Found: " + tasks.searchTask(99));

        System.out.println("\n-- Deleting the head task (ID 1) --");
        tasks.deleteTask(1);
        System.out.println("Remaining tasks:");
        tasks.traverseTasks();

        System.out.println("\n-- Deleting a middle task (ID 3) --");
        tasks.deleteTask(3);
        System.out.println("Remaining tasks:");
        tasks.traverseTasks();
        System.out.println("Total tasks: " + tasks.size());

        printAnalysis();
    }

    private static void printAnalysis() {
        System.out.println("\n=== Analysis ===");
        System.out.println("Time complexity on a singly linked list:");
        System.out.println("  addTask()    : O(n) to reach the tail (would be O(1) if we kept a tail");
        System.out.println("                 pointer, a common optimization).");
        System.out.println("  searchTask() : O(n) - no random access, must walk node by node.");
        System.out.println("  traverse()   : O(n) - visiting every node is inherently linear.");
        System.out.println("  deleteTask() : O(n) - must walk to find the node and its predecessor.");
        System.out.println();
        System.out.println("Advantages of linked lists over arrays for dynamic data:");
        System.out.println("  - O(1) insertion/removal at the head (or anywhere, given a direct node");
        System.out.println("    reference) with no shifting of other elements - unlike an array, which");
        System.out.println("    needs O(n) shifting to open/close a gap.");
        System.out.println("  - No fixed capacity or costly resize-and-copy step: a linked list grows");
        System.out.println("    one node at a time in O(1), while an array must reallocate and copy");
        System.out.println("    everything (O(n)) once it's full.");
        System.out.println("  - Memory is allocated exactly as needed per task, with no wasted");
        System.out.println("    pre-allocated capacity sitting unused.");
        System.out.println();
        System.out.println("  The trade-off: linked lists lose O(1) random access by index (must walk");
        System.out.println("  from the head every time) and use extra memory per node for the 'next'");
        System.out.println("  pointer. For a task list where tasks are added/removed far more often than");
        System.out.println("  they're accessed by position, a linked list is the more natural fit.");
    }
}
