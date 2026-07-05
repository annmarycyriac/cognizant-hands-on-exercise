# Exercise 1: Inventory Management System

## What's implemented
- `Product.java` — productId, productName, quantity, price
- `InventoryManagement.java` — stores products in a `HashMap<Integer, Product>` keyed by productId; add/update/delete/search
- `Main.java` — demo + printed time-complexity analysis

## Why a HashMap?
Warehouse inventory is looked up/updated/deleted by productId far more often than iterated in order, so a HashMap gives O(1) average time for all four core operations versus O(n) for an ArrayList (which needs a linear scan to find a matching ID).

## How to compile and run
```bash
javac *.java
java Main
```

## Time complexity (see output.txt for the full printed analysis)
| Operation | HashMap | ArrayList (for comparison) |
|---|---|---|
| add | O(1) avg | O(1) amortized |
| update | O(1) avg | O(n) |
| delete | O(1) avg | O(n) |
| search | O(1) avg | O(n) |
