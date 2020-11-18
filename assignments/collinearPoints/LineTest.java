import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;


public class LineTest {

   @Before
   public void setUp() {


   }


   ///////////////////
   // Typical cases //
   ///////////////////
   // random numbers
   @Test
   public void testLineTypical1() {
      Line line0 = new Line();
      line0.add(new Point(0,0));
      line0.add(new Point(1,1));
      boolean expected = false;
      boolean actual = line0.add(new Point(1,2));
      assertEquals(expected, actual);
   }

   @Test
   public void testLineTypical2() {
      Line line0 = new Line();
      line0.add(new Point(0,0));
      line0.add(new Point(1,1));
      boolean expected = true;
      boolean actual = line0.add(new Point(2,2));
      assertEquals(expected, actual);
   }

   @Test
   public void testLineTypical3() {
      Point p0 = new Point(1, 1);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Set<Point> lineSet = Set.of(p0, p1, p2);
      Line line0 = new Line(lineSet);
      Point expected = p0;
      Point actual = line0.first();
      assertEquals(expected, actual);
   }

   @Test
   public void testLineTypical4() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      Point expected = p1;
      Point actual = line0.first();
      assertEquals(expected, actual);
   }

   @Test
   public void testLineTypical5() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      Point expected = p0;
      Point actual = line0.last();
      assertEquals(expected, actual);
   }



   ////////////////////
   // Boundary cases //
   ////////////////////
   @Test
   public void testLineEmpty1() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      int expected = -1;
      int actual = line0.compareTo(line1);
      assertEquals(expected, actual);
   }

   @Test
   public void testLineEmpty2() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      int expected = 1;
      int actual = line1.compareTo(line0);
      assertEquals(expected, actual);
   }


   @Test
   public void testLineEmpty3() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      Line line2 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      int expected = 0;
      int actual = line1.compareTo(line2);
      assertEquals(expected, actual);
   }

   @Test
   public void testLineEmpty4() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      Line line2 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      line1.add(p2);
      int expected = -1;
      int actual = line0.compareTo(line1);
      assertEquals(expected, actual);
   }

   @Test
   public void testLineEmpty5() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      Line line2 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      line1.add(p2);
      int expected = 1;
      int actual = line1.compareTo(line0);
      assertEquals(expected, actual);
   }

   @Test
   public void testLineEmpty6() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      line0.add(p0);
      int expected = -1;
      int actual = line0.compareTo(line1);
      assertEquals(expected, actual);
   }

   @Test
   public void testLineEmpty7() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Line line0 = new Line();
      Line line1 = new Line();
      line0.add(p0);
      int expected = 1;
      int actual = line1.compareTo(line0);
      assertEquals(expected, actual);
   }


   @Test
   public void testLineCompare1() {
      Point p0 = new Point(20, 20);
      Point p1 = new Point(21, 20);
      Point p2 = new Point(22, 20);
      Point p3 = new Point(23, 20);
      Point p4 = new Point(24, 20);
      Line line0 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      line0.add(p3);
      line0.add(p4);
      Point p5 = new Point(20, 21);
      Point p6 = new Point(21, 21);
      Point p7 = new Point(22, 21);
      Point p8 = new Point(23, 21);
      Point p9 = new Point(24, 21);
      Line line1 = new Line();
      line1.add(p5);
      line1.add(p6);
      line1.add(p7);
      line1.add(p8);
      line1.add(p9);
      int expected = -1;
      int actual = line0.compareTo(line1);
      assertEquals(expected, actual);
   }




   ///////////////////
   // Special cases //
   ///////////////////
   // duplicates
   // different orders
   @Test
   public void testLineAdding1() {
      Point p0 = new Point(4, 4);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Point p3 = new Point(3, 0);
      Line line0 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      line0.add(p3);
      int expected = 3;
      int actual = line0.length();
      assertEquals(expected, actual);
   }

   @Test
   public void testLineAdd2() {
      Point p0 = new Point(1, 1);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(2, 2);
      Point p3 = new Point(2, 2);
      Line line0 = new Line();
      line0.add(p0);
      line0.add(p1);
      line0.add(p2);
      line0.add(p3);
      int expected = 2;
      int actual = line0.length();
      assertEquals(expected, actual);
   }


   @Test
   public void testLineSetAdd1() {
      Point p0 = new Point(1, 1);
      Point p1 = new Point(2, 2);
      Point p2 = new Point(3, 3);
      Point p3 = new Point(3, 2);
      SortedSet<Point> lineSet = new TreeSet<>();
      lineSet.add(p0);
      lineSet.add(p1);
      lineSet.add(p2);
      lineSet.add(p3);
      Line line0 = new Line(lineSet);
      int expected = 3;
      int actual = line0.length();
      assertEquals(expected, actual);
   }

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
