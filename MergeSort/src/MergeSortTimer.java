import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.concurrent.ThreadLocalRandom;

public class MergeSortTimer {

    static int[] gArray;
    static int[] aArray;
    static int gSize = 5000000; // size of largest array tested
    static int testStart = 20000; // starting value for array length
    static int testStep = 5000; // amount to increase from current array length to next

    void createGArray(int size){
        gArray = new int[size + 1];
        System.out.println("Creating an array of size " + size);
        for (int i = 1; i <= size; i++) {
                gArray[i] = (ThreadLocalRandom.current().nextInt((Integer.MIN_VALUE + 1), (Integer.MAX_VALUE - 1)));
        }
    }

    void fillAArray(int size) {
        for (int i = 1; i <= size; i++) {
                aArray[i] = gArray[i];
        }
    }

    double timeTheSort(int size) {
        //start timing
        int buffer = 0;
        long time1 = System.nanoTime();
        mergeSort(aArray, 1, size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        //System.out.println("completed size:" + size);
        return (time2 - time1);
    }

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

    void createFile(String filename) {
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists, deleting data in it. Filename is " + myObj.getName());
            }
            FileWriter myWriter = new FileWriter(filename, false);
            BufferedWriter buffWriter = new BufferedWriter(myWriter);
            buffWriter.write("size(n),time(ns),time/n,time/lg(n),time/nlg(n)");
            buffWriter.newLine();
            buffWriter.close();
            myWriter.close();
            } catch (IOException e) {
            System.out.println("An error occurred.");
             e.printStackTrace();
        }
    }
    void writeToFile(String filename, double time, double size) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            BufferedWriter buffWriter = new BufferedWriter(myWriter);
            buffWriter.write(size + "," + time + "," + time/size + "," + time/(lg(size))
                    + "," + time/(size * lg(size)));
            buffWriter.newLine();
            buffWriter.close();
            myWriter.close();
            System.out.println("Successfully wrote to the file for test size: " + size);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static double lg(double x)
    {
        return (Math.log(x) / Math.log(2));
    }

    public static void main(String[] args) {
        MergeSortTimer test2 = new MergeSortTimer();
        // max heap size seems to be 17500
        test2.createGArray(gSize);
        String filename = "test2Output.csv";
        test2.createFile(filename);
        int testSize = testStart;
        double testTime = 0.0;
        aArray = new int[gSize + 1];
        while (testSize <= gSize) {
            test2.fillAArray(testSize);
            testTime = test2.timeTheSort(testSize);
            test2.writeToFile(filename, testTime, testSize);
            testSize += testStep;
        }
    }
}