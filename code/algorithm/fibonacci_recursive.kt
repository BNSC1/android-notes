fun fibonacci(num: Int): Int {
    return when {
        num == 1 -> 1
        num == 0 -> 0
        num > 1 -> {
            fibonacci(num-1) + fibonacci(num-2)
        }
        else -> {
            val sign =
                if (num%2 == 0) {
                    -1 //when the num is negative and is even, it becomes a negative number
                } else {
                    1 //when the num is negative but is odd, it remains positive
                }
            return (fibonacci(abs(num)-1) + fibonacci(abs(num)-2)) * sign
        }
    }
}
