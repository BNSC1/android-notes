fun fibonacci(num: Int): Int {
    val n = abs(num) //absolute number for index and size
    val cache = IntArray((n+1).coerceAtLeast(2)) { -1 } //cache should have at least the size of 2, to contain base cases, -1 to indicate uninitialized
    cache[0] = 0
    cache[1] = 1
    for (i in 2 .. n) {
        cache[i] = cache[i-1] + cache[i-2]
    }
    return if (num % 2 == 0 && num < 0) { //fibonacci result will be negative when the input is even and is negative
        -cache[n]
    } else cache[n]
}
