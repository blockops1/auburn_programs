public class ActivityMax {

    static int[] start_times = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
    static int[] finish_times = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
    static int[] max_count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int n = 10;


    int Max(int[] s, int[] f, int i, int j) {
        if (i >= j) return 0;
        //if (f[i] > s[j]) return 0;
        int k = (i + j)/2;
        System.out.println("before looops i: " + i + " k: " + k +" j: " + j);
        int left = 0;
        for (int m = i; m < k; m++){
            // see if any of the activities end before j starts
            if (f[m] <= s[k]) {
                System.out.println("i: " + i + " m: " + m +" k: " + k);
                System.out.println("left");
                left = Max(s, f, i, k) + 1;
            }
        }
        int right = 0;
        for (int m = k + 1; m <= j; m++) {
            // see if any of the activities start before i finishes
            if (f[k] <= s[m]) {
                System.out.println("k: " + k + " m: " + m +" j: " + j);
                System.out.println("right");
                right = Max(s, f, k, j) + 1;
            }
        }
        return left + right;
    }

    int IterateMax (int k, int left, int right) {
        // this finds the best solution with k in it from i to j and saves it in max_count
        if ((k < left) || (k > right)) {
            System.out.println("out of bounds");
            return -1;
        }
        // boolean array to keep track if activity is in current solution
        boolean[] inSolution = new boolean[n];
        inSolution[k] = true;
        // loop to check each of the items to the left and put in solution
        int
        for (int i = left; i < k; i++ ) {
            //check furthest to left and see if it fits
            if ((finish_times[i] <= start_times[k])) {
                inSolution[i] = true;
                //update the working start and finish times
            }
        }


    }

    public static void main(String[] args) {
        int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        ActivityMax counter = new ActivityMax();
        int count = counter.Max(s, f, 1, 11);
        System.out.println(count);
    }
}
