# Exercise 7: Financial Forecasting (Recursion)

## What's implemented
- `FinancialForecasting.java` — three versions of the compound-growth future value formula `FV = PV * (1+r)^n`:
  1. `futureValueRecursive()` — naive O(n) recursion (one call per period)
  2. `futureValueMemoized()` — caches results per period count
  3. `futureValueFastPow()` — O(log n) recursion via exponentiation by squaring
- `Main.java` — demo (year-by-year forecast, all 3 versions cross-checked to agree, plus a 1000-period stress test) + printed analysis

## How to compile and run
```bash
javac *.java
java Main
```

## Time complexity
| Version | Time | Space (call stack) |
|---|---|---|
| Naive recursion | O(n) | O(n) - risks StackOverflowError for very large n |
| Memoized | O(n) first call, O(1) repeats | O(n) cache |
| Fast power (exp. by squaring) | O(log n) | O(log n) |

See `output.txt` for the full year-by-year forecast and the written explanation of both optimization techniques.
