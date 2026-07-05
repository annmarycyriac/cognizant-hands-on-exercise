/**
 * Exercise 5: Task Management System
 * --------------------------------------
 * A hand-rolled singly linked list of Tasks (not java.util.LinkedList,
 * to show the actual node-and-pointer mechanics).
 *
 * SINGLY LINKED LIST vs DOUBLY LINKED LIST:
 * A singly linked list node holds data plus one reference: to the NEXT
 * node. Traversal is one-directional (head -> tail only). A doubly linked
 * list node holds two references (next AND previous), allowing traversal
 * in both directions and O(1) removal of a node once you have a direct
 * reference to it (no need to re-walk from the head to find its
 * predecessor). The trade-off is a doubly linked list uses more memory
 * per node (an extra pointer) and slightly more bookkeeping on every
 * insert/delete to keep both directions consistent.
 */
public class TaskLinkedList {

    /** A single node in the list, holding a Task and a reference to the next node. */
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    /** Add a task to the end of the list. O(n) since we must walk to the tail. */
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Search for a task by ID. O(n) - must walk from head until found. */
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    /** Traverse and print every task in order. O(n). */
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println("  " + current.task);
            current = current.next;
        }
    }

    /**
     * Delete a task by ID. O(n): must walk to find the node AND keep a
     * reference to its predecessor so we can re-link around it.
     */
    public boolean deleteTask(int taskId) {
        if (head == null) return false;

        // Special case: deleting the head.
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            size--;
            return true;
        }

        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                previous.next = current.next; // unlink current
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false; // not found
    }

    public int size() {
        return size;
    }
}
