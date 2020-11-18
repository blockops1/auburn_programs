import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException  {
      if (coll == null || comp == null ) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      T minimum = null;
      for (T item : coll) {
         if (minimum == null) {
            minimum = item;
         }
         if (comp.compare(item,minimum) < 0 ) {
            minimum = item;
         }
      }
      return minimum;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException  {
      if (coll == null || comp == null ) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      T maximum = null;
      for (T item : coll) {
         if (maximum == null) {
            maximum = item;
         }
         if (comp.compare(item,maximum) > 0 ) {
            maximum = item;
         }
      }
      return maximum;
   }



   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {

      if (coll == null || comp == null ) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0 || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      List<T> kSolution = new ArrayList<>(coll);
      java.util.Collections.sort(kSolution, comp);
      int counter = 0;
      T previous = null;
      for (T item: kSolution) {
         if (previous == null) {
            counter++;
            previous = item;
         }
         if (!item.equals(previous)) {
            counter++;
            previous = item;
         }
         System.out.println(counter + " k:" + k);
         if (counter == k) {
            return item;
         }
      }
      throw new NoSuchElementException();
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {

      if (coll == null || comp == null ) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0 || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      List<T> kSolution = new ArrayList<>(coll);
      java.util.Collections.sort(kSolution, comp);
      java.util.Collections.reverse(kSolution);
      int counter = 0;
      T previous = null;
      for (T item: kSolution) {
         if (previous == null) {
            counter++;
            previous = item;
         }
         if (!item.equals(previous)) {
            counter++;
            previous = item;
         }
         if (counter == k) {
            return item;
         }
      }
      throw new NoSuchElementException();
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) throws IllegalArgumentException,
                                         NoSuchElementException {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      Collection<T> rangeSolution = new ArrayList<>();
      for (T item : coll) {
         //System.out.println(item + " " + low + " " + high + " " + comp.compare(item, low) + " " + comp.compare(item, high));
         if ((comp.compare(item, low) >= 0) && (comp.compare(item, high) <= 0)) {
            rangeSolution.add(item);
            //System.out.println(item.toString());
         }
      }
      if (rangeSolution.size() == 0) {
         throw new NoSuchElementException();
      }
      return rangeSolution;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total ordjjjjjjjjjjjjjjjjjjjjer on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException  {
      if (coll == null || comp == null ) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      T ceilingValue = null;
      for (T item : coll) {
         if (ceilingValue == null && comp.compare(item,key) >= 0 ) {
            ceilingValue = item;
         } else if (comp.compare(item,key) >= 0 && comp.compare(item,ceilingValue) < 0 ) {
            ceilingValue = item;
         }
      }
      if (ceilingValue == null) {
         throw new NoSuchElementException();
      }
      return ceilingValue;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException  {
      if (coll == null || comp == null ) {
         throw new IllegalArgumentException();
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      T floorValue = null;
      for (T item : coll) {
         if (floorValue == null && comp.compare(item,key) <= 0 ) {
            floorValue = item;
         } else if (comp.compare(item,key) <= 0 && comp.compare(item,floorValue) > 0 ) {
            floorValue = item;
         }
      }
      if (floorValue == null) {
         throw new NoSuchElementException();
      }
      return floorValue;
   }

}
