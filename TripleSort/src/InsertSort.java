public class InsertSort {
    void insertSort(int[] sortArray, int size) {
        int i = 0;
        for (int j = 2; j <= size; j++) {
            int key = sortArray[j];
            i = j - 1;
            while (i > 0 && sortArray[i] > key) {
                sortArray[i+1] = sortArray[i];
                i--;
            }
            sortArray[i+1] = key;
        }
    }

    double timeTheSort(int[] aArray, int size) {
        //start timing
        int buffer = 0;
        long time1 = System.nanoTime();
        insertSort(aArray, size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        //System.out.println("completed size:" + size);
        return (time2 - time1);
    }

}
