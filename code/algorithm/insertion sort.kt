fun insertionSort(arr: IntArray) {
    for (r in 1 until arr.size) {
        val key = arr[r]
        var l = r - 1

        while (l >= 0 && arr[l] > key) {
            arr[l+1] = arr[l--]
        }
        arr[l+1] = key
    }
}