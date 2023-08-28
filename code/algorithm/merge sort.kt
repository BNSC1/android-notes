    fun mergeSort(arr: IntArray,l: Int = 0, r: Int = arr.size - 1) {
        fun merge(arr: IntArray, l: Int, m: Int, r: Int) {
            // Create temp arrays
            val L = arr.clone().sliceArray(l .. m)
            val R = arr.clone().sliceArray(m+1..r)


            // Initial indices of first and second subarrays
            var i = 0
            var j = 0
            // Initial index of merged subarray array
            var k = l
            while (i < L.size && j < R.size) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i++]
                } else {
                    arr[k] = R[j++]
                }
                k++
            }
            // Copy remaining elements of L[] if any
            while (i < L.size) {
                arr[k++] = L[i++]
            }
            // Copy remaining elements of R[] if any
            while (j < R.size) {
                arr[k++] = R[j++]
            }
        }
        if (l < r) {
            val m = l + (r - l) / 2

            mergeSort(arr, l, m)
            mergeSort(arr, m + 1, r)
            merge(arr, l, m, r)
        }
    }