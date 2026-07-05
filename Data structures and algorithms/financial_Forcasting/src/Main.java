import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 7: Financial Forecasting - Demo
 * -----------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Demo (Recursive Future Value) ===\n");

        double presentValue = 10000.0; // starting investment
        double growthRate = 0.07;      // 7% growth per period (e.g. per year)
        int periods = 10;              // forecast 10 years ahead

        System.out.printf("Present Value: %.2f%n", presentValue);
        System.out.printf("Growth Rate  : %.2f%%%n", growthRate * 100);
        System.out.println("Periods      : " + periods + "\n");

        double fv = FinancialForecasting.futureValueRecursive(presentValue, growthRate, periods);
        System.out.printf("Future Value after %d periods (naive recursion): %.2f%n", periods, fv);

        // Show the year-by-year progression for transparency.
        System.out.println("\n-- Year-by-year forecast --");
        for (int year = 0; year <= periods; year++) {
            double valueAtYear = FinancialForecasting.futureValueRecursive(presentValue, growthRate, year);
            System.out.printf("  Year %2d: %.2f%n", year, valueAtYear);
        }

        // Demonstrate the memoized version giving the same answer.
        Map<Integer, Double> cache = new HashMap<>();
        double fvMemo = FinancialForecasting.futureValueMemoized(presentValue, growthRate, periods, cache);
        System.out.printf("%nFuture Value (memoized version)   : %.2f%n", fvMemo);

        // Demonstrate the O(log n) fast-power version giving the same answer.
        double fvFast = FinancialForecasting.futureValueFastPow(presentValue, growthRate, periods);
        System.out.printf("Future Value (O(log n) fast power): %.2f%n", fvFast);

        // Stress test: naive recursion on a large number of periods to show
        // the linear call-stack growth (and that it still works correctly).
        System.out.println("\n-- Testing with a larger horizon (periods = 1000) --");
        double bigFv = FinancialForecasting.futureValueFastPow(1000, 0.05, 1000);
        System.out.printf("Future value after 1000 periods at 5%% growth: %.4e%n", bigFv);
        System.out.println("(Using the O(log n) version here - naive O(n) recursion works too,");
        System.out.println(" but risks a StackOverflowError on very large period counts.)");

        printAnalysis();
    }

    private static void printAnalysis() {
        System.out.println("\n=== Analysis ===");
        System.out.println("Naive recursive futureValueRecursive():");
        System.out.println("  Time complexity  : O(n) - exactly one recursive call per period.");
        System.out.println("  Space complexity : O(n) - each call adds a frame to the call stack until");
        System.out.println("                     the base case, so very large period counts risk a");
        System.out.println("                     StackOverflowError.");
        System.out.println();
        System.out.println("Optimization 1 - Memoization:");
        System.out.println("  Caches results per 'periods' value. Doesn't speed up a single fresh call");
        System.out.println("  (still O(n) the first time), but makes repeated calls asking for the same");
        System.out.println("  or smaller period counts O(1), which matters in a forecasting tool that");
        System.out.println("  re-queries overlapping projections often.");
        System.out.println();
        System.out.println("Optimization 2 - Exponentiation by squaring (fast power):");
        System.out.println("  Rewrites (1+r)^n by halving n each recursive call instead of decrementing");
        System.out.println("  by 1, giving O(log n) time and O(log n) stack depth - a real complexity");
        System.out.println("  class improvement, not just caching. This is the standard technique for");
        System.out.println("  optimizing any recursive power/repeated-multiplication computation, and is");
        System.out.println("  the right choice when forecasting far into the future (large n) or when");
        System.out.println("  performance matters more than mirroring the year-by-year growth story.");
    }
}
