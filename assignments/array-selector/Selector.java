import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   YOUR NAME (YOU@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int minimum = Integer.MAX_VALUE;
      for (int i = 0; i < a.length; i++) {
         if (a[i] < minimum) {
            minimum = a[i];
         }
      }
      return minimum;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int maximum = Integer.MIN_VALUE;
      for (int i = 0; i < a.length; i++) {
         if (a[i] > maximum) {
            maximum = a[i];
         }
      }
      return maximum;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) throws IllegalArgumentException {
      if (a == null || a.length == 0 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int[] minArray = new int [k];
      Arrays.fill(minArray, Integer.MIN_VALUE);
      int minimum = Integer.MAX_VALUE;
      for (int j = 0; j < k; j++) {
         for (int i = 0; i < a.length; i++) {
            if (j == 0) {
               if (a[i] < minimum) {
                  minimum = a[i];
               }
            } else if ((a[i] < minimum) && a[i] > minArray[j - 1]) {
               minimum = a[i];
            }
         }
         //System.out.println("Minimum:" + minimum);
         minArray[j] = minimum;
         minimum = Integer.MAX_VALUE;
         //System.out.println("MinArray[" + j + "] = " + minArray[j]);
      }
      if (minArray[k - 1] != Integer.MAX_VALUE) {
         return minArray[k - 1];
      } else {
         throw new IllegalArgumentException();
      }
   }



   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) throws IllegalArgumentException {
      if (a == null || a.length == 0 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int[] maxArray = new int [k];
      Arrays.fill(maxArray, Integer.MAX_VALUE);
      int maximum = Integer.MIN_VALUE;
      for (int j = 0; j < k; j++) {
         for (int i = 0; i < a.length; i++) {
            if (j == 0) {
               if (a[i] > maximum) {
                  maximum = a[i];
               }
            } else if ((a[i] > maximum) && a[i] < maxArray[j - 1]) {
               maximum = a[i];
            }
         }
         //System.out.println("Maximum:" + maximum);
         maxArray[j] = maximum;
         maximum = Integer.MIN_VALUE;
         //System.out.println("MaxArray[" + j + "] = " + maxArray[j]);
      }
      if (maxArray[k - 1] != Integer.MIN_VALUE) {
         return maxArray[k - 1];
      } else {
         throw new IllegalArgumentException();
      }
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int counter = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            counter++;
         }
      }
      int[] rangeSolution = new int[counter];
      counter = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            rangeSolution[counter] = a[i];
            counter++;
         }
      }
      return rangeSolution;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int ceilingNum = 0;
      int bestDiff = Integer.MAX_VALUE;
      int diff = 0;
      for (int i = 0; i < a.length; i++) {
         diff = a[i] - key;
         if ((diff >= 0 ) && (diff < bestDiff)) {
            ceilingNum = a[i];
            bestDiff = diff;
         }
      }
      if (bestDiff == Integer.MAX_VALUE) {
         throw new IllegalArgumentException();
      } else {
         return ceilingNum;
      }
   }

   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int floorNum = 0;
      int bestDiff = Integer.MAX_VALUE;
      int diff = 0;
      for (int i = 0; i < a.length; i++) {
         diff = key - a[i];
         if ((diff >= 0 ) && (diff < bestDiff)) {
            floorNum = a[i];
            bestDiff = diff;
         }
      }
      if (bestDiff == Integer.MAX_VALUE) {
         throw new IllegalArgumentException();
      } else {
         return floorNum;
      }
   }
}
