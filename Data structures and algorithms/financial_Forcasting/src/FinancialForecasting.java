import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 7: Financial Forecasting
 * -------------------------------------

 */
public class FinancialForecasting {

   
    public static double futureValueRecursive(double presentValue, double growthRate, int periods) {
        if (periods == 0) {
            return presentValue; // base case: no growth periods left
        }
        return futureValueRecursive(presentValue, growthRate, periods - 1) * (1 + growthRate);
    }

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
