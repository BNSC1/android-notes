fun fibonacci(num: Int): Int {
    val n = abs(num) //absolute number for index and size
    val cache = ArrayList<Int>()
    cache.add(0)
    cache.add(1)
    return if (num % 2 == 0 && num < 0) { //fibonacci result will be negative when the input is even and is negative
        -cacheOrCalculate(n, cache)
    }
    else {
        cacheOrCalculate(n, cache)
    }
}

fun cacheOrCalculate(n: Int, cache: ArrayList<Int>): Int {
    if (cache.size <= n) {
        cache.add(n,
                  cacheOrCalculate(n-2,cache) + cacheOrCalculate(n-1,cache)) //add to cache if not exist yet
    }
    return cache[n]
}
