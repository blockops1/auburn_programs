import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci {

    static long[] Fib = null;
    static long n2 = 0;

    long NaiveFibonacci (int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            n2++;
            //System.out.println("adding one to n2 " + n2);
        }
    return (NaiveFibonacci(n-1) + (NaiveFibonacci(n-2)));
    }

    long MemFibonacci (int n) {
        Fib = new long[n+1];
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        Fib[0] = 0;
        Fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            Fib[i] = -1;
        }
        return Mem2Fibonacci(n);

    }
    long Mem2Fibonacci (int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (Fib[n] == -1) {
            Fib[n] = (Mem2Fibonacci(n-1) + (Mem2Fibonacci(n-2)));
        }
        return Fib[n];
        }

    long IterativeFibonacci (int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long number2 = 0;
        long number1 = 1;
        long number = 0;
        for (int i = 2; i <= n; i++) {
            number = number1 + number2;
            number2 = number1;
            number1 = number;
        }
        return number;
    }


    double timeNaiveFibonacci(int size) {
        //start timing
        long result;
        long time1 = System.nanoTime();
        result = NaiveFibonacci(size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        System.out.println("completed timeNaiveFibonacci:" + size + " result= " + result);
        return (time2 - time1);
    }

    double timeMemFibonacci(int size) {
        //start timing
        long result;
        long time1 = System.nanoTime();
        result = MemFibonacci(size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        System.out.println("completed timeMemFibonacci:" + size + " result= " + result);
        return (time2 - time1);
    }

    double timeIterativeFibonacci(int size) {
        //start timing
        long result;
        long time1 = System.nanoTime();
        result = IterativeFibonacci(size);
        //stop timing
        long time2 = System.nanoTime();
        // return time in milliseconds
        System.out.println("completed timeIterativeFibonacci:" + size + " result= " + result);
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
            buffWriter.write("size(n), Naive, Time1, Time1Last, t1n/t1n-1, n2, Memory, Time2, Time2Last, t2n/t2n-1, Iterative, Time3, Time3Last, t3n/t3n-1");
            buffWriter.newLine();
            buffWriter.close();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void writeToFile(String filename, int size, double time1, double time1last, double time2, double time2last, double time3
            , double time3last) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            BufferedWriter buffWriter = new BufferedWriter(myWriter);
            buffWriter.write(size + "," + "," + time1 + "," + time1last + "," + time1/time1last + "," + n2 + "," + "," + time2
                    + "," + time2last + "," + time2/time2last + "," + "," + time3  + "," + time3last + "," + time3/time3last);
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
        Fibonacci test = new Fibonacci();
        double time1 = 0;
        double time2 = 0;
        double time3 = 0;
        double time1last = 0;
        double time2last = 0;
        double time3last = 0;
        String filename = "outfile2.csv";
        test.createFile(filename);
        for (int n = 0; n <= 55; n++) {
            time1 = test.timeNaiveFibonacci(n);
            time2 = test.timeMemFibonacci(n);
            time3 = test.timeIterativeFibonacci(n);
            test.writeToFile(filename, n, time1, time1last, time2, time2last, time3, time3last);
            time1last = time1;
            time2last = time2;
            time3last = time3;
        }
    }
}
