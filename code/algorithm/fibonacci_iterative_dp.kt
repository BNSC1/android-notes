fun fibonacci(num: Int): Int {
    val n = abs(num) //absolute number for index and size
    val cache = ArrayList<Int>()
    cache.add(0)
    cache.add(1)
    for (i in 2 .. n) {
        cache.add(i,cache[i-1] + cache[i-2])
    }
    return if (num % 2 == 0 && num < 0) { //fibonacci result will be negative when the input is even and is negative
        -cache[n]
    } else cache[n]
}
