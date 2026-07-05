# Exercise 5: Task Management System (Singly Linked List)

## What's implemented
- `Task.java` — taskId, taskName, status
- `TaskLinkedList.java` — a hand-rolled singly linked list (own Node class, not `java.util.LinkedList`) with add/search/traverse/delete
- `Main.java` — demo (including deleting the head node and a middle node) + printed analysis

## How to compile and run
```bash
javac *.java
java Main
```

## Time complexity
| Operation | Complexity | Why |
|---|---|---|
| add | O(n) | must walk to the tail (O(1) if a tail pointer were kept) |
| search | O(n) | no random access, walk node by node |
| traverse | O(n) | must visit every node |
| delete | O(n) | must walk to find the node and its predecessor |

See `output.txt` for the full run (including deleting the head vs. a middle task) and the written comparison of linked lists vs. arrays for dynamic data.
