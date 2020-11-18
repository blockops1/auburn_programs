import org.junit.Assert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;


public class PointTest {

   @Before
   public void setUp() {


   }


   ///////////////////
   // Typical cases //
   ///////////////////
   // random numbers
   @Test
   public void testSlopeToTypical1() {
      Point p0 = new Point(5, 5);
      Point p1 = new Point(0, 0);
      Point p2 = new Point(10, 10);
      Integer expected = p0.slopeOrder.compare(p1, p2);
      //System.out.println(a.toString());
      Integer actual = -p0.slopeOrder.compare(p1, p2);
      assertEquals(expected, actual);
   }

   @Test
   public void testSlopeToTypical2() {
      Point p0 = new Point(2, 2);
      Point p1 = new Point(2, 7);
      Point p2 = new Point(2, 12);
      Integer expected = 0;
      //System.out.println(a.toString());
      Integer actual = p0.slopeOrder.compare(p1, p2);
      assertEquals(expected, actual);
   }

   @Test
   public void testSlopeToTypical3() {
      Point p0 = new Point(1, 1);
      Point p1 = new Point(1, 6);
      Point p2 = new Point(1, 11);
      Integer expected = 0;
      //System.out.println(a.toString());
      Integer actual = p0.slopeOrder.compare(p1, p2);
      assertEquals(expected, actual);
   }



   ////////////////////
   // Boundary cases //
   ////////////////////


   ///////////////////
   // Special cases //
   ///////////////////
   // duplicates
   // different orders

   ///////////////////
   // Illegal cases //
   ///////////////////
   // null array
   // zero length array
   @Test(expected = IllegalArgumentException.class)
   public void testToSlopeIllegal1() {
      Point p0 = new Point(-1, 0);
      Point p1 = new Point(1, 1);
      Double expected = 1.0;
      //System.out.println(a.toString());
      Double actual = p0.slopeTo(p1);
      assertEquals(expected, actual);
   }

}
