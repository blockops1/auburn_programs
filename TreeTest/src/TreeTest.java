import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TreeTest {
    class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
            right = null;
            left = null;
            parent = null;
        }

        Node(int key, Node parent) {
            this.key = key;
            right = null;
            left = null;
            this.parent = parent;
        }
    }

    class BinaryTree {
        Node root;
    }

    void TreeInsert(BinaryTree testTree, Node z) {
        Node y = null;
        Node x = testTree.root;
        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            testTree.root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    int maxDepth(BinaryTree testTree, Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(testTree, node.left);
            int rightDepth = maxDepth(testTree, node.right);
            if (leftDepth > rightDepth) {
                return (leftDepth + 1);
            } else {
                return (rightDepth + 1);
            }
        }
    }

    double CollectData(int n) {
        int sumHeight = 0;
        double avgHeight = 0;
        int p = 0;
        int treeHeight = 0;
        Random random = new Random();
        BinaryTree testTree = new BinaryTree();
        for (int j = 1; j <= 10; j++) {
            for (int i = 1; i <= n; i++) {
                p = random.nextInt(n) + 1;
                Node z = new Node(p);
                TreeInsert(testTree, z);
            }
            treeHeight = maxDepth(testTree, testTree.root) - 1;
            testTree = new BinaryTree();
            sumHeight = sumHeight + treeHeight;
            //System.out.println(n + " " + treeHeight);
        }
        avgHeight = sumHeight/10;
        return avgHeight;
        //System.out.println(n + " " + avgHeight + " " + (int)(log(n)/log(2)));
        //write data to file of n and avgHeight
    }

    static double lg(double x)
    {
        return (Math.log(x) / Math.log(2));
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
            buffWriter.write("size(n), Height(n)/lg n, Height(n)/n");
            buffWriter.newLine();
            buffWriter.close();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void writeToFile(String filename, int size, double avgHeight) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            BufferedWriter buffWriter = new BufferedWriter(myWriter);
            buffWriter.write(size + "," + avgHeight/lg(size)+ "," + avgHeight/size);
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
        TreeTest test = new TreeTest();
        double avgHeight = 0;
        String filename = "outfile2.csv";
        test.createFile(filename);
        for (int n = 500; n <= 20000; n = n + 500) {
            avgHeight = test.CollectData(n);
            test.writeToFile(filename, n, avgHeight);
        }
    }
}
