
import time
import random


class Product:
   

    def __init__(self, product_id, product_name, category):
        self.product_id = product_id
        self.product_name = product_name
        self.category = category

    def __repr__(self):
        return f"Product(id={self.product_id}, name='{self.product_name}', category='{self.category}')"



def linear_search(products, target_id):
    
    comparisons = 0
    for index, product in enumerate(products):
        comparisons += 1
        if product.product_id == target_id:
            return index, comparisons
    return -1, comparisons


def binary_search(sorted_products, target_id):
    
    comparisons = 0
    low, high = 0, len(sorted_products) - 1

    while low <= high:
        mid = (low + high) // 2
        comparisons += 1
        mid_id = sorted_products[mid].product_id

        if mid_id == target_id:
            return mid, comparisons
        elif mid_id < target_id:
            low = mid + 1
        else:
            high = mid - 1

    return -1, comparisons



def build_sample_catalog(n):
    
    categories = ["Electronics", "Books", "Clothing", "Home", "Toys", "Sports"]
    products = [
        Product(i, f"Product-{i}", random.choice(categories))
        for i in range(1, n + 1)
    ]
    random.shuffle(products)
    return products


def compare_algorithms(catalog_sizes):
    print(f"{'N (catalog size)':<20}{'Linear (comparisons)':<25}{'Binary (comparisons)':<25}")
    print("-" * 70)

    results = []
    for n in catalog_sizes:
        unsorted_catalog = build_sample_catalog(n)
        sorted_catalog = sorted(unsorted_catalog, key=lambda p: p.product_id)

       
        target_id = n - 1 if n > 1 else n

        _, lin_comparisons = linear_search(unsorted_catalog, target_id)
        _, bin_comparisons = binary_search(sorted_catalog, target_id)

        results.append((n, lin_comparisons, bin_comparisons))
        print(f"{n:<20}{lin_comparisons:<25}{bin_comparisons:<25}")

    return results


def timing_demo(n=200_000):
    
    unsorted_catalog = build_sample_catalog(n)
    sorted_catalog = sorted(unsorted_catalog, key=lambda p: p.product_id)
    target_id = n - 5  # a value near the end -> near worst-case for linear

    start = time.perf_counter()
    linear_search(unsorted_catalog, target_id)
    linear_time = time.perf_counter() - start

    start = time.perf_counter()
    binary_search(sorted_catalog, target_id)
    binary_time = time.perf_counter() - start

    print(f"\nTiming demo with N = {n:,} products (searching near-worst-case target):")
    print(f"  Linear search took : {linear_time*1000:.4f} ms")
    print(f"  Binary search took : {binary_time*1000:.4f} ms")
    if binary_time > 0:
        print(f"  Binary search was ~{linear_time/binary_time:.1f}x faster")



if __name__ == "__main__":
    print("=" * 70)
    print("EXERCISE 2: E-COMMERCE PLATFORM SEARCH FUNCTION")
    print("=" * 70)

    print("\n--- Basic correctness check ---")
    catalog = build_sample_catalog(10)
    sorted_catalog = sorted(catalog, key=lambda p: p.product_id)

    print("Unsorted catalog sample:", catalog[:3], "...")
    print("Sorted catalog sample:  ", sorted_catalog[:3], "...")

    target = 7
    idx_lin, cmp_lin = linear_search(catalog, target)
    idx_bin, cmp_bin = binary_search(sorted_catalog, target)

    print(f"\nSearching for product_id={target}")
    print(f"  Linear Search -> found at index {idx_lin} in unsorted list, "
          f"{cmp_lin} comparisons")
    print(f"  Binary Search -> found at index {idx_bin} in sorted list, "
          f"{cmp_bin} comparisons")

    print("\n--- Comparison across catalog sizes (near-worst-case target) ---")
    compare_algorithms([10, 100, 1_000, 10_000, 100_000, 1_000_000])

    print("\n--- Empirical timing demo ---")
    timing_demo(200_000)

    print("\n" + "=" * 70)
    print("ANALYSIS SUMMARY")
    print("=" * 70)
    print("""
Time Complexity:
  Linear Search : O(n)      -- must check up to every element
  Binary Search : O(log n)  -- halves the search space every step

Which is more suitable for an e-commerce platform?
  Binary Search is far more suitable for large catalogs (thousands to
  millions of products) BECAUSE:
    1. Product catalogs are relatively static (updated in batches,
       reindexed periodically), so the one-time cost of keeping the
       list sorted (O(n log n)) is paid rarely, while searches happen
       constantly.
    2. O(log n) scales extremely well: even at 1,000,000 products,
       binary search needs only ~20 comparisons versus up to 1,000,000
       for linear search.
  Linear Search is still useful when:
    - The catalog is small or frequently changing (re-sorting cost
      isn't worth it).
    - Searching on an unindexed/unsorted field (e.g., free-text search
      on product_name), where binary search cannot apply directly.
  In production, real e-commerce platforms typically go further and use
  hash maps / indexes (O(1) average lookup by ID) or full-text search
  engines (e.g., Elasticsearch) for search-by-keyword -- but binary
  search is the right "next step up" from linear search taught here.
""")
