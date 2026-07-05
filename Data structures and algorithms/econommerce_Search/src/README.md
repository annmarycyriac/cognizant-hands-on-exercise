# Exercise 2: E-commerce Platform Search Function

## What's implemented
- `Product.java` — productId, productName, category
- `SearchAlgorithms.java` — linear search (any order) and binary search (requires sorted array)
- `Main.java` — demo, timing comparison, and printed analysis

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

Binary search needs the array sorted first (O(n log n) one-time cost if not already sorted). See `output.txt` for real measured timings on the sample data and the full written analysis of which approach fits a real e-commerce search bar.
