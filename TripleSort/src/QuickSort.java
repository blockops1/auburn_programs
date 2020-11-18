public class QuickSort {
    void quickSort(int[] sortArray, int p, int r) {
        int q;
        if (p < r) {
            q = partition(sortArray, p, r);
            //System.out.println("quicksort p = " + p + " q = " + q + " r = " + r);
            quickSort(sortArray, p, q - 1);
            quickSort(sortArray, q+1, r);
        }
    }

    int partition(int[] sortArray, int p, int r) {
        int x = sortArray[r];
        int i = p - 1;
        int temp = 0;
        for (int j = p; j <= (r - 1); j++) {
            if (sortArray[j] <= x) {
                i++;
                //swap i with j
                temp = sortArray[(int)i];
                sortArray[i] = sortArray[j];
                sortArray[j] = temp;
            }
        }
        //swap i+1 with r
        temp = sortArray[(int)i+1];
        sortArray[(int)i+1] = sortArray[(int)r];
        sortArray[(int)r] = temp;
        return i + 1;
    }

    double timeTheSort(int[] aArray, int size) {
        //start timing
        int buffer = 0;
        long time1 = System.nanoTime();
        quickSort(aArray, 1, size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        //System.out.println("completed size:" + size);
        return (time2 - time1);
    }
}
