import java.util.Arrays;

/**
 * Exercise 4: Employee Management System
 * ------------------------------------------
 * Stores employees in a plain Java array (Employee[]) rather than a
 * collection like ArrayList, to directly illustrate how arrays work:
 * fixed capacity, contiguous memory, and manual resizing.
 *
 * ARRAY REPRESENTATION IN MEMORY:
 * A Java array is a block of contiguous memory. For an object array like
 * Employee[], each slot holds a *reference* (pointer) to an Employee object
 * elsewhere on the heap - the array itself stores fixed-size references,
 * not the objects' full data. Because slots are contiguous and index-based,
 * accessing any element by index is O(1) (direct memory offset calculation).
 * The trade-off is that the array's size is fixed at creation time; growing
 * it requires allocating a new, larger array and copying every reference
 * over - which is exactly what resizeIfNeeded() below does.
 */
public class EmployeeManagement {

    private Employee[] employees;
    private int size; // number of employees actually stored (<= employees.length)

    public EmployeeManagement(int initialCapacity) {
        employees = new Employee[initialCapacity];
        size = 0;
    }

    /** Add an employee at the end of the array. O(1) amortized (O(n) when resizing). */
    public void addEmployee(Employee employee) {
        resizeIfNeeded();
        employees[size] = employee;
        size++;
    }

    /** Search for an employee by ID. O(n) - must scan since array isn't sorted/indexed by ID. */
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    /** Traverse and print all employees. O(n) - must visit every element. */
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println("  " + employees[i]);
        }
    }

    /**
     * Delete an employee by ID. O(n): O(n) to find it, then O(n) to shift
     * all subsequent elements left by one to close the gap (arrays don't
     * support removing a "hole" in the middle without shifting).
     */
    public boolean deleteEmployee(int employeeId) {
        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1) {
            return false; // not found
        }

        // Shift every subsequent element left by one.
        for (int i = indexToRemove; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[size - 1] = null; // clear the now-unused last slot
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return employees.length;
    }

    /** Doubles the backing array's capacity when it's full. O(n) copy cost. */
    private void resizeIfNeeded() {
        if (size == employees.length) {
            int newCapacity = employees.length == 0 ? 1 : employees.length * 2;
            employees = Arrays.copyOf(employees, newCapacity);
            System.out.println("  [internal] Array resized to capacity " + newCapacity);
        }
    }
}
