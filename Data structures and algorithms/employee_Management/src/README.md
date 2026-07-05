# Exercise 4: Employee Management System

## What's implemented
- `Employee.java` — employeeId, name, position, salary
- `EmployeeManagement.java` — a plain `Employee[]` array (not ArrayList) with manual resizing, to show array mechanics directly; add/search/traverse/delete
- `Main.java` — demo (deliberately starts with capacity 2 to show a live resize happen) + printed analysis

## How to compile and run
```bash
javac *.java
java Main
```

## Time complexity
| Operation | Complexity | Why |
|---|---|---|
| add | O(1) amortized | O(n) only when the array must resize |
| search | O(n) | linear scan, no index by employeeId |
| traverse | O(n) | must visit every element |
| delete | O(n) | O(n) to find + O(n) to shift elements left |

See `output.txt` for the live resize message printed when the array fills up, and the full discussion of array limitations (fixed capacity, costly shifting) and when arrays are still the right choice.
