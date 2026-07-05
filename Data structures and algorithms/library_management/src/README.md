# Exercise 6: Library Management System

## What's implemented
- `Book.java` — bookId, title, author
- `LibrarySearch.java` — linear search by title (any order) and binary search by title (requires sorted array)
- `Main.java` — demo + printed analysis

## How to compile and run
```bash
javac *.java
java Main
```

## Time complexity
| Case | Linear Search | Binary Search |
|---|---|---|
| Best | O(1) | O(1) |
| Average | O(n) | O(log n) |
| Worst | O(n) | O(log n) |

See `output.txt` for the full run and the discussion of when to use each approach based on catalog size and how often it changes.
