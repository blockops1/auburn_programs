public class MergeSort {

    void mergeSort(int[] sortArray, int p, int r) {
        int q;
        if (p < r) {
            q = (p+r)/2;
            //System.out.println("mergesort p = " + p + " q = " + q + " r = " + r);
            mergeSort(sortArray, p, q);
            mergeSort(sortArray, q+1, r);
            merge(sortArray, p, q, r);
        }
    }

    void merge(int[] sortArray, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int [] lArray = new int[n1 + 2];
        int [] rArray = new int[n2 + 2];
        for (int i = 1; i <= n1; i++) {
            lArray[i] = sortArray[p+i-1];
        }
        for (int j = 1; j <= n2; j++) {
            rArray[j] = sortArray[q+j];
        }
        //lArray[n1+1] = 0xffffffff;
        //rArray[n2+1] = 0xffffffff;
        lArray[n1+1] = Integer.MAX_VALUE;
        rArray[n2+1] = Integer.MAX_VALUE;
        int i = 1;
        int j = 1;
        int k = p;
        //while (i <= n1 && j <= n2) {
        for (k = p; k <= r; k++) {
            //System.out.println("  merge n1 = " + n1 + " n2 = " + n2 + " k = " + k + " p = " + p + " r = " + r + " i = " + i + " j = " + j);
            if (lArray[i] <= rArray[j]) {
                sortArray[k] = lArray[i];
                i++;
            } else {
                sortArray[k] = rArray[j];
                j++;
            }
            //k++;
        }

    }

    double timeTheSort(int[] aArray, int size) {
        //start timing
        int buffer = 0;
        long time1 = System.nanoTime();
        mergeSort(aArray, 1, (int)size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        //System.out.println("completed size:" + size);
        return (time2 - time1);
    }
}
