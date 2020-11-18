import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.concurrent.ThreadLocalRandom;

public class TripleSort {

    static int[] gArray;
    static int[] aArray;
    static int gSize = 150000; // size of largest array tested
    static int testStart = 10000; // starting value for array length
    static int testStep = 1000; // amount to increase from current array length to next

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
            buffWriter.write("size(n),InsertSort, InsertSort time/n^2, QuickSort, QuickSort time/nlg(n), MergeSort, MergeSort time/nlg(n)");
            buffWriter.newLine();
            buffWriter.close();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void writeToFile(String filename,double insertTime,double quickTime, double mergeTime, double size) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            BufferedWriter buffWriter = new BufferedWriter(myWriter);
            buffWriter.write(size + "," + insertTime+ "," + insertTime/(size * size) + "," + quickTime + ","
                    + quickTime/(size * lg(size)) + "," + mergeTime + "," + mergeTime/(size * lg(size)));
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
        TripleSort test3 = new TripleSort();
        // max heap size seems to be 17500
        test3.createGArray(gSize);
        String filename = "test3Output.csv";
        test3.createFile(filename);
        int testSize = testStart;
        double insertTime = 0.0;
        double quickTime = 0.0;
        double mergeTime = 0.0;
        aArray = new int[gSize + 1];
        InsertSort insertTest = new InsertSort();
        QuickSort quickTest = new QuickSort();
        MergeSort mergeTest = new MergeSort();
        while (testSize <= gSize) {
            test3.fillAArray(testSize);
            insertTime = insertTest.timeTheSort(aArray, testSize);
            test3.fillAArray(testSize);
            quickTime = quickTest.timeTheSort(aArray, testSize);
            test3.fillAArray(testSize);
            mergeTime = mergeTest.timeTheSort(aArray, testSize);
            test3.writeToFile(filename, insertTime, quickTime, mergeTime, testSize);
            testSize += testStep;
        }
    }
}