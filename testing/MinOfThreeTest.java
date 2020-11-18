import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   // min1 section
   ///////////////////
   // Typical cases //
   ///////////////////
   // test with 3 unique integers, 1, 2, 3
   @Test public void testMin1Typical1() {
      int expected = 1;
      int actual = MinOfThree.min2(1,2,3);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 1, 3, 2
   @Test public void testMin1Typical2() {
      int expected = 1;
      int actual = MinOfThree.min2(1,3,2);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 2, 1, 3
   @Test public void testMin1Typical3() {
      int expected = 1;
      int actual = MinOfThree.min1(2,1,3);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 2, 3, 1
   @Test public void testMin1Typical4() {
      int expected = 1;
      int actual = MinOfThree.min1(2,3,1);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 3, 1, 2
   @Test public void testMin1Typical5() {
      int expected = 1;
      int actual = MinOfThree.min1(3,1,2);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 3, 2, 1
   @Test public void testMin1Typical6() {
      int expected = 1;
      int actual = MinOfThree.min1(3,2,1);
      assertEquals(expected, actual);
   }

   ////////////////////
   // Boundary cases //
   ////////////////////
   // test with three max integer objects Integer.MAX_VALUE
   @Test public void testMin1Boundary1() {
      int expected = Integer.MAX_VALUE;
      int actual = MinOfThree.min1(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
      assertEquals(expected, actual);
   }

   //test with three min integer objects Integer.MIN_VALUE
   @Test public void testMin1Boundary2() {
      int expected = Integer.MIN_VALUE;
      int actual = MinOfThree.min1(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
      assertEquals(expected, actual);
   }

   //test with min/max range integer objects Integer.MIN_VALUE and Integer.MAX_VALUE
   @Test public void testMin1Boundary3() {
      int expected = Integer.MIN_VALUE;
      int actual = MinOfThree.min1(Integer.MIN_VALUE,0,Integer.MAX_VALUE);
      assertEquals(expected, actual);
   }


   ///////////////////
   // Special cases //
   ///////////////////
   // test with 3 integers, all matching, 1, 1, 1
   @Test public void testMin1Special1() {
      int expected = 1;
      int actual = MinOfThree.min1(1,1,1);
      assertEquals(expected, actual);
   }

   // test with 2 integers matching 1, 1, 2
   @Test public void testMin1Special2() {
      int expected = 1;
      int actual = MinOfThree.min1(1,1,2);
      assertEquals(expected, actual);
   }

   // test with 2 integers matching 1, 2, 1
   @Test public void testMin1Special3() {
      int expected = 1;
      int actual = MinOfThree.min1(1,2,1);
      assertEquals(expected, actual);
   }

   // test with 2 integers matching 2, 1, 1
   @Test public void testMin1Special4() {
      int expected = 1;
      int actual = MinOfThree.min1(2,1,1);
      assertEquals(expected, actual);
   }

   // min2 section
   ///////////////////
   // Typical cases //
   ///////////////////
   // test with 3 unique integers, 1, 2, 3
   @Test public void testMin2Typical1() {
      int expected = 1;
      int actual = MinOfThree.min2(1,2,3);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 1, 3, 2
   @Test public void testMin2Typical2() {
      int expected = 1;
      int actual = MinOfThree.min2(1,3,2);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 2, 1, 3
   @Test public void testMin2Typical3() {
      int expected = 1;
      int actual = MinOfThree.min2(2,1,3);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 2, 3, 1
   @Test public void testMin2Typical4() {
      int expected = 1;
      int actual = MinOfThree.min2(2,3,1);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 3, 1, 2
   @Test public void testMin2Typical5() {
      int expected = 1;
      int actual = MinOfThree.min2(3,1,2);
      assertEquals(expected, actual);
   }

   // test with 3 unique integers, 3, 2, 1
   @Test public void testMin2Typical6() {
      int expected = 1;
      int actual = MinOfThree.min2(3,2,1);
      assertEquals(expected, actual);
   }

   ////////////////////
   // Boundary cases //
   ////////////////////
   // test with three max integer objects Integer.MAX_VALUE
   @Test public void testMin2Boundary1() {
      int expected = Integer.MAX_VALUE;
      int actual = MinOfThree.min2(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
      assertEquals(expected, actual);
   }

   //test with three min integer objects Integer.MIN_VALUE
   @Test public void testMin2Boundary2() {
      int expected = Integer.MIN_VALUE;
      int actual = MinOfThree.min2(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
      assertEquals(expected, actual);
   }

   //test with min/max range integer objects Integer.MIN_VALUE and Integer.MAX_VALUE
   @Test public void testMin2Boundary3() {
      int expected = Integer.MIN_VALUE;
      int actual = MinOfThree.min2(Integer.MIN_VALUE,0,Integer.MAX_VALUE);
      assertEquals(expected, actual);
   }


   ///////////////////
   // Special cases //
   ///////////////////
   // test with 3 integers, all matching, 1, 1, 1
   @Test public void testMin2Special1() {
      int expected = 1;
      int actual = MinOfThree.min2(1,1,1);
      assertEquals(expected, actual);
   }

   // test with 2 integers matching 1, 1, 2
   @Test public void testMin2Special2() {
      int expected = 1;
      int actual = MinOfThree.min2(1,1,2);
      assertEquals(expected, actual);
   }

   // test with 2 integers matching 1, 2, 1
   @Test public void testMin2Special3() {
      int expected = 1;
      int actual = MinOfThree.min2(1,2,1);
      assertEquals(expected, actual);
   }

   // test with 2 integers matching 2, 1, 1
   @Test public void testMin2Special4() {
      int expected = 1;
      int actual = MinOfThree.min2(2,1,1);
      assertEquals(expected, actual);
   }

}
