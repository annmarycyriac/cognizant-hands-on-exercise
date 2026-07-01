

import time
import sys

sys.setrecursionlimit(10_000)



# ---------------------------------------------------------------------
# 2 & 3. Setup + Implementation: Recursive future value calculation
# ---------------------------------------------------------------------
def future_value_recursive(present_value, growth_rate, years):
    if years == 0:
        return present_value
    return future_value_recursive(present_value, growth_rate, years - 1) * (1 + growth_rate)


def future_value_recursive_variable_rates(present_value, growth_rates):
    if not growth_rates:
        return present_value
    grown_so_far = future_value_recursive_variable_rates(present_value, growth_rates[:-1])
    return grown_so_far * (1 + growth_rates[-1])


from functools import lru_cache


@lru_cache(maxsize=None)
def future_value_memoized(present_value, growth_rate, years):
    if years == 0:
        return present_value
    return future_value_memoized(present_value, growth_rate, years - 1) * (1 + growth_rate)


def future_value_iterative(present_value, growth_rate, years):
    value = present_value
    for _ in range(years):
        value *= (1 + growth_rate)
    return value


def future_value_closed_form(present_value, growth_rate, years):
    return present_value * (1 + growth_rate) ** years

def timing_comparison(present_value, growth_rate, years):
    print(f"\nForecasting: PV=${present_value:,.2f}, rate={growth_rate*100:.1f}%, "
          f"years={years}")

    start = time.perf_counter()
    result_recursive = future_value_recursive(present_value, growth_rate, years)
    t_recursive = time.perf_counter() - start

    future_value_memoized.cache_clear()
    start = time.perf_counter()
    result_memo = future_value_memoized(present_value, growth_rate, years)
    t_memo_cold = time.perf_counter() - start

    start = time.perf_counter()
    future_value_memoized(present_value, growth_rate, years)
    t_memo_warm = time.perf_counter() - start

    start = time.perf_counter()
    result_iterative = future_value_iterative(present_value, growth_rate, years)
    t_iterative = time.perf_counter() - start

    start = time.perf_counter()
    result_closed = future_value_closed_form(present_value, growth_rate, years)
    t_closed = time.perf_counter() - start

    print(f"  Recursive        : ${result_recursive:,.2f}  ({t_recursive*1000:.4f} ms)")
    print(f"  Memoized (cold)   : ${result_memo:,.2f}  ({t_memo_cold*1000:.4f} ms)")
    print(f"  Memoized (warm)   : ${result_memo:,.2f}  ({t_memo_warm*1000:.4f} ms)  <- cached")
    print(f"  Iterative        : ${result_iterative:,.2f}  ({t_iterative*1000:.4f} ms)")
    print(f"  Closed-form (O(1)): ${result_closed:,.2f}  ({t_closed*1000:.4f} ms)")


if __name__ == "__main__":
    print("=" * 70)
    print("EXERCISE 7: FINANCIAL FORECASTING")
    print("=" * 70)

    print("\n--- Basic recursive forecast ---")
    pv = 10_000.00
    rate = 0.08  # 8% annual growth
    n_years = 10
    fv = future_value_recursive(pv, rate, n_years)
    print(f"Present Value: ${pv:,.2f}")
    print(f"Growth Rate  : {rate*100:.1f}% per year")
    print(f"Years        : {n_years}")
    print(f"Future Value : ${fv:,.2f}")

    print("\n--- Variable growth rates (realistic historical data) ---")
    pv2 = 5_000.00
    historical_rates = [0.05, 0.07, 0.03, 0.09, 0.04]  # e.g., 5 years of real data
    fv2 = future_value_recursive_variable_rates(pv2, historical_rates)
    print(f"Present Value: ${pv2:,.2f}")
    print(f"Yearly rates : {[f'{r*100:.0f}%' for r in historical_rates]}")
    print(f"Future Value : ${fv2:,.2f}")

    print("\n--- Performance comparison: Recursive vs Memoized vs Iterative vs Closed-form ---")
    timing_comparison(present_value=10_000.00, growth_rate=0.08, years=25)
    timing_comparison(present_value=10_000.00, growth_rate=0.08, years=900)  # deep recursion

    print("\n--- Demonstrating memoization benefit on REPEATED forecasts ---")
    future_value_memoized.cache_clear()
    start = time.perf_counter()
    for _ in range(1000):
        future_value_memoized(10_000.00, 0.08, 25)  # same args every time
    t_repeated_memo = time.perf_counter() - start

    start = time.perf_counter()
    for _ in range(1000):
        future_value_recursive(10_000.00, 0.08, 25)
    t_repeated_recursive = time.perf_counter() - start

    print(f"  1000x repeated identical forecast calls:")
    print(f"    Plain recursion : {t_repeated_recursive*1000:.4f} ms total")
    print(f"    Memoized        : {t_repeated_memo*1000:.4f} ms total "
          f"(~{t_repeated_recursive/max(t_repeated_memo,1e-9):.0f}x faster)")

    print("\n" + "=" * 70)
    print("ANALYSIS SUMMARY")
    print("=" * 70)
    print("""
Time Complexity of the recursive algorithm:
  O(n) time  -- one multiplication per year of forecasting
  O(n) space -- call stack grows by one frame per year

Optimizing the recursive solution:
  1. Memoization: eliminates repeated computation ONLY when the exact
     same forecast (same PV, rate, years) is requested multiple times
     -- common in a web app caching frequent queries. Does not reduce
     the cost of a single first-time computation.
  2. Iteration: converts O(n) recursive calls into an O(n) loop with
     O(1) space, removing call-stack overhead and stack-overflow risk
     for large numbers of years/periods.
  3. Closed-form formula (PV * (1+r)^n): the best optimization when
     the growth rate is constant -- reduces the whole computation to
     O(1), regardless of how many years are forecast.
  In practice: use the closed-form formula for constant-rate compound
  growth, and reserve recursion/iteration for cases with variable
  year-by-year rates or additional per-period logic (e.g., contributions,
  withdrawals, inflation adjustments) that a simple formula can't capture.
""")
