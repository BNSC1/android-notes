fun quickSort(arr: IntArray, l: Int = 0, r: Int = arr.size - 1) {
    fun swap(i: Int, j: Int) {
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }
    fun partition(): Int {
        val pivot = arr[r]

        var i = l
        for (j in l until r) {
            if (arr[j] < pivot) {
                swap(i++, j)
            }
        }
        swap(i, r)
        return i
    }
    if (l < r) {
        val pi = partition()
        quickSort(arr, l, pi-1)
        quickSort(arr, pi+1, r)
    }
}