import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 7: Financial Forecasting
 * -------------------------------------
 * Predicts a future value from a present value and a per-period growth
 * rate, using the compound growth formula:
 *
 *     FV = PV * (1 + r)^n
 *
 * implemented RECURSIVELY rather than with a direct Math.pow call, since
 * the exercise is about understanding recursion itself.
 *
 * RECURSION CONCEPT:
 * Recursion solves a problem by expressing it in terms of a smaller version
 * of itself, plus a base case that stops the recursion. Here, the future
 * value after n periods is just "the future value after (n-1) periods,
 * grown by one more period's rate" - so:
 *     futureValue(PV, r, n) = futureValue(PV, r, n-1) * (1 + r)
 *     futureValue(PV, r, 0) = PV   <-- base case
 * This mirrors how compound growth actually works period by period, which
 * is why recursion "simplifies" the problem: the code directly matches the
 * real-world definition instead of us having to derive a closed-form loop.
 */
public class FinancialForecasting {

    /**
     * Naive recursive future value calculation.
     * Time complexity: O(n) - one recursive call per period, each doing O(1) work.
     * Space complexity: O(n) - the call stack holds one frame per period.
     */
    public static double futureValueRecursive(double presentValue, double growthRate, int periods) {
        if (periods == 0) {
            return presentValue; // base case: no growth periods left
        }
        return futureValueRecursive(presentValue, growthRate, periods - 1) * (1 + growthRate);
    }

    /**
     * Optimized version using memoization to avoid recomputation when the
     * SAME (growthRate, periods) pair is requested multiple times (e.g. in
     * a forecasting tool that repeatedly asks "what's the value at year 5?"
     * "year 5?" again for different scenarios sharing sub-results).
     * Still O(n) for a single fresh call, but O(1) for any repeated call
     * with a periods value already computed and cached.
     */
    public static double futureValueMemoized(double presentValue, double growthRate, int periods,
                                              Map<Integer, Double> cache) {
        if (periods == 0) {
            return presentValue;
        }
        if (cache.containsKey(periods)) {
            return cache.get(periods);
        }
        double result = futureValueMemoized(presentValue, growthRate, periods - 1, cache) * (1 + growthRate);
        cache.put(periods, result);
        return result;
    }

    /**
     * Optimized version using "exponentiation by squaring" - avoids
     * recursion depth of O(n) entirely by halving the exponent each call.
     * Time complexity: O(log n). This is the standard technique for
     * optimizing a recursive power computation.
     */
    public static double futureValueFastPow(double presentValue, double growthRate, int periods) {
        return presentValue * powFast(1 + growthRate, periods);
    }

    private static double powFast(double base, int exponent) {
        if (exponent == 0) {
            return 1.0; // base case: anything ^ 0 = 1
        }
        double half = powFast(base, exponent / 2);
        double result = half * half;
        if (exponent % 2 != 0) {
            result *= base; // odd exponent: one extra factor of base
        }
        return result;
    }
}
