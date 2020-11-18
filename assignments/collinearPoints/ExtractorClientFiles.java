import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.SortedSet;

/**
 * ExtractorClient.java.
 * Demonstrates feature extraction on sample data.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-02-27
 */
public class ExtractorClientFiles {


   /** 
    * Drives execution.
    */
   public static void main(String[] args) throws FileNotFoundException {
      String filename = "/home/rolf/java/cpsc1233/assignments/collinearPoints/test_data/input4a.txt";
      Extractor cl = new Extractor(filename);
      System.out.println("completed reading file");
      SortedSet<Line> bruteLines = cl.getLinesBrute();
      System.out.println("Brute: ");
      printLines(bruteLines);
   
      SortedSet<Line> fastLines = cl.getLinesFast();
      System.out.println("Fast: ");
      printLines(fastLines);
   }
   
   /**
    * Prints an enumeration of the given set of lines.
    */
   private static void printLines(SortedSet<Line> lines) {
      int count = 0;
      for (Line line : lines) {
         System.out.println(++count + ": " + line);
      }
      System.out.println();
   }
}


/*

RUNTIME OUTPUT

Brute:
1: (1, 0) -> (1, 1) -> (1, 2) -> (1, 3)
2: (1, 0) -> (1, 1) -> (1, 2) -> (1, 4)
3: (3, 0) -> (3, 1) -> (3, 2) -> (3, 3)
4: (3, 0) -> (3, 1) -> (3, 2) -> (3, 4)
5: (1, 1) -> (1, 2) -> (1, 3) -> (1, 4)
6: (3, 1) -> (3, 2) -> (3, 3) -> (3, 4)
7: (30, 5) -> (31, 5) -> (32, 5) -> (33, 5)
8: (30, 5) -> (31, 5) -> (32, 5) -> (34, 5)
9: (31, 5) -> (32, 5) -> (33, 5) -> (34, 5)
10: (30, 6) -> (31, 6) -> (32, 6) -> (33, 6)
11: (30, 6) -> (31, 6) -> (32, 6) -> (34, 6)
12: (31, 6) -> (32, 6) -> (33, 6) -> (34, 6)
13: (15, 9) -> (16, 10) -> (17, 11) -> (18, 12)
14: (15, 9) -> (16, 10) -> (17, 11) -> (19, 13)
15: (19, 9) -> (18, 10) -> (17, 11) -> (16, 12)
16: (19, 9) -> (18, 10) -> (17, 11) -> (15, 13)
17: (16, 10) -> (17, 11) -> (18, 12) -> (19, 13)
18: (18, 10) -> (17, 11) -> (16, 12) -> (15, 13)

Fast:
1: (1, 0) -> (1, 1) -> (1, 2) -> (1, 3) -> (1, 4)
2: (3, 0) -> (3, 1) -> (3, 2) -> (3, 3) -> (3, 4)
3: (30, 5) -> (31, 5) -> (32, 5) -> (33, 5) -> (34, 5)
4: (30, 6) -> (31, 6) -> (32, 6) -> (33, 6) -> (34, 6)
5: (15, 9) -> (16, 10) -> (17, 11) -> (18, 12) -> (19, 13)
6: (19, 9) -> (18, 10) -> (17, 11) -> (16, 12) -> (15, 13)



 */

