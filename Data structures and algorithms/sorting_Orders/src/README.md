# Exercise 3: Sorting Customer Orders

## What's implemented
- `Order.java` — orderId, customerName, totalPrice
- `SortingAlgorithms.java` — Bubble Sort and Quick Sort, both sorting by totalPrice ascending
- `Main.java` — demo, real timing comparison on 5,000 random orders, and printed analysis

## How to compile and run
```bash
javac *.java
java Main
```

## Measured performance (5,000 random orders, see output.txt)
```
Bubble Sort time: 33.23 ms
Quick Sort time : 3.70 ms
```
Quick Sort ran roughly 9x faster in this run — and the gap grows further as the dataset size increases, consistent with O(n log n) vs O(n^2).

## Time complexity
| Case | Bubble Sort | Quick Sort |
|---|---|---|
| Best | O(n) | O(n log n) |
| Average | O(n^2) | O(n log n) |
| Worst | O(n^2) | O(n^2) (rare, poor pivot choice) |
