import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class Matrix {

    int[][] gMatrix;
    int[][] aMatrix;
    int gSize;

    void createGMatrix(int size){
        gMatrix = new int[size][size];
        System.out.println("Creating a matrix of size " + size + " by " + size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                gMatrix[i][j] = (int)Math.round(Math.random()*100);
            }
        }
    }

    void fillAMatrix(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                aMatrix[i][j] = gMatrix[i][j];
            }
        }
    }

    double transposeAMatrix(int size) {
        //start timing
        int buffer = 0;
        long time1 = System.nanoTime();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                // swap A[i] and A[j]
                buffer = aMatrix[i][j];
                aMatrix[i][j] = aMatrix[j][i];
                aMatrix[j][i] = buffer;
            }
        }
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        return (time2 - time1);
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
            buffWriter.write("size,time(ns),time/size,time/(size^2),time/(size^3),time/(size^3 * log2(size))");
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
            double fudge = Math.log(size)/Math.log(2);
            FileWriter myWriter = new FileWriter(filename, true);
            BufferedWriter buffWriter = new BufferedWriter(myWriter);
            buffWriter.write(size + "," + time + "," + time/size + "," + time/(size * size)
                    + "," + time/(size * size * size) + "," + time/((size * size * size) * (fudge)));
            buffWriter.newLine();
            buffWriter.close();
            myWriter.close();
            System.out.println("Successfully wrote to the file for test size: " + size);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Matrix test1 = new Matrix();
        // max heap size seems to be 17500
        test1.gSize = 20000;
        test1.createGMatrix(test1.gSize);
        String filename = "test1Output.csv";
        test1.createFile(filename);
        int testSize = 100;
        double testTime = 0.0;
        test1.aMatrix = new int[test1.gSize][test1.gSize];
        while (testSize <= test1.gSize) {
            test1.fillAMatrix(testSize);
            testTime = test1.transposeAMatrix(testSize);
            test1.writeToFile(filename, testTime, testSize);
            testSize += 100;
        }
    }
}