import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


    /**
     * Fixture initialization (common initialization
     * for all tests).
     **/
     // int[] a1 = {2, 8, 7, 3, 4};
     // int[] a2 = {5, 9, 1, 7, 3};
     // int[] a3 = {8, 7, 6, 5, 4};
     // int[] a4 = {2, 8, 8, 7, 3, 3, 4};

    @Before
    public void setUp() {

    }

    // min section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testMinTypical1() {
        int expected = 1;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.min(a2);
        assertEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    // test with three max integer objects Integer.MAX_VALUE
    @Test public void testMinBoundary1() {
        int expected = 4;
        int[] a3 = {8, 7, 6, 5, 4};
        int actual = Selector.min(a3);
        assertEquals(expected, actual);
    }

    @Test public void testMinBoundary2() {
        int expected = 2;
        int[] a1 = {2, 8, 7, 3, 4};
        int actual = Selector.min(a1);
        assertEquals(expected, actual);
    }

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
    @Test (expected = IllegalArgumentException.class)
    public void testMinIllegal1() {
        int[] a5 = null;
        int actual = Selector.min(a5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMinIllegal2() {
        int[] a5 = {};
        int actual = Selector.min(a5);
    }

    // max section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testMaxTypical1() {
        int expected = 9;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.max(a2);
        assertEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    @Test public void testMaxBoundary1() {
        int expected = 8;
        int[] a3 = {8, 7, 6, 5, 4};
        int actual = Selector.max(a3);
        assertEquals(expected, actual);
    }

    @Test public void testMaxBoundary2() {
        int expected = 8;
        int[] a1 = {2, 8, 7, 3, 4};
        int actual = Selector.max(a1);
        assertEquals(expected, actual);
    }

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
    @Test (expected = IllegalArgumentException.class)
    public void testMaxIllegal1() {
        int[] a5 = null;
        int actual = Selector.max(a5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxIllegal2() {
        int[] a5 = {};
        int actual = Selector.max(a5);
    }

    // range section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testRangeTypical1() {
        int[] expected = {5, 7, 3};
        int[] a2 = {5, 9, 1, 7, 3};
        int[] actual = Selector.range(a2, 3, 7);
        assertArrayEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    @Test public void testRangeBoundary1() {
        int[] expected = {8, 7, 6, 5, 4};
        int[] a3 = {8, 7, 6, 5, 4};
        int[] actual = Selector.range(a3, 4, 8);
        assertArrayEquals(expected, actual);
    }

    @Test public void testRangeBoundary2() {
        int[] expected = {};
        int[] a1 = {2, 8, 7, 3, 4};
        int[] actual = Selector.range(a1, 15, 25);
        assertArrayEquals(expected, actual);
    }

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
    @Test (expected = IllegalArgumentException.class)
    public void testRangeIllegal1() {
        int[] a5 = null;
        int[] actual = Selector.range(a5, 3, 5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRangeIllegal2() {
        int[] a5 = {};
        int[] actual = Selector.range(a5, 5, 6);
    }
    // floor section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testCeilingTypical1() {
        int expected = 7;
        int key = 5;
        int[] a4 = {2, 8, 8, 7, 3, 3, 4};
        int actual = Selector.ceiling(a4, key);
        assertEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    @Test public void testCeilingBoundary1() {
        int expected = 4;
        int key = 0;
        int[] a3 = {8, 7, 6, 5, 4};
        int actual = Selector.ceiling(a3, key);
        assertEquals(expected, actual);
    }

    @Test public void testCeilingBoundary2() {
        int expected = 9;
        int key = 8;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.ceiling(a2, key);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders
    @Test (expected = IllegalArgumentException.class)
    public void testCeilingSpecial1() {
        int key = 10;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.ceiling(a2, key);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = IllegalArgumentException.class)
    public void testCeilingIllegal1() {
        int[] a5 = null;
        int actual = Selector.ceiling(null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCeilingIllegal2() {
        int[] a5 = {};
        int actual = Selector.ceiling(a5, 0);
    }
    // floor section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testFloorTypical1() {
        int expected = 4;
        int key = 5;
        int[] a4 = {2, 8, 8, 7, 3, 3, 4};
        int actual = Selector.floor(a4, key);
        assertEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    @Test public void testFloorBoundary1() {
        int expected = 8;
        int key = 9;
        int[] a3 = {8, 7, 6, 5, 4};
        int actual = Selector.floor(a3, key);
        assertEquals(expected, actual);
    }

    @Test public void testFloorBoundary2() {
        int expected = 1;
        int key = 1;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.floor(a2, key);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders
    @Test (expected = IllegalArgumentException.class)
    public void testFloorSpecial1() {
        int key = -1;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.floor(a2, key);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = IllegalArgumentException.class)
    public void testFloorIllegal1() {
        int[] a5 = null;
        int actual = Selector.floor(null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFloorIllegal2() {
        int[] a5 = {};
        int actual = Selector.floor(a5, 0);
    }


    // kmin section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testKMinTypical1() {
        int expected = 2;
        int key = 1;
        int[] a1 = {2, 8, 7, 3, 4};
        int actual = Selector.kmin(a1, key);
        assertEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    @Test public void testKMinBoundary1() {
        int expected = 8;
        int key = 5;
        int[] a3 = {8, 7, 6, 5, 4};
        int actual = Selector.kmin(a3, key);
        assertEquals(expected, actual);
    }

    @Test public void testKMinBoundary2() {
        int expected = 5;
        int key = 3;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.kmin(a2, key);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders
    @Test (expected = IllegalArgumentException.class)
    public void testKMinSpecial1() {
        int key = 7;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.kmin(a2, key);
    }
    @Test public void testKMinSpecial2() {
        int expected = 4;
        int key = 3;
        int[] a4 = {2, 8, 8, 7, 3, 3, 4};
        int actual = Selector.kmin(a4, key);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = IllegalArgumentException.class)
    public void testKMinIllegal1() {
        int[] a5 = null;
        int actual = Selector.kmin(null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKMinIllegal2() {
        int[] a5 = {};
        int actual = Selector.kmin(a5, 0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testKMinIllegal4() {
        int key = 2;
        int[] a2 = {-4, -4, -4, -4, -4, -4, -4};
        int actual = Selector.kmin(a2, key);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKMinIllegal5() {
        int key = 2;
        int[] a2 = {-4, -4};
        int actual = Selector.kmin(a2, key);
    }

    // kmax section

    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testKMaxTypical1() {
        int expected = 8;
        int key = 1;
        int[] a1 = {2, 8, 7, 3, 4};
        int actual = Selector.kmax(a1, key);
        assertEquals(expected, actual);
    }

    ////////////////////
    // Boundary cases //
    ////////////////////
    // min first
    // min last
    @Test public void testKMaxBoundary1() {
        int expected = 4;
        int key = 5;
        int[] a3 = {8, 7, 6, 5, 4};
        int actual = Selector.kmax(a3, key);
        assertEquals(expected, actual);
    }

    @Test public void testKMaxBoundary2() {
        int expected = 5;
        int key = 3;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.kmax(a2, key);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders

    @Test public void testKMaxSpecial2() {
        int expected = 3;
        int key = 4;
        int[] a4 = {2, 8, 8, 7, 3, 3, 4};
        int actual = Selector.kmax(a4, key);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = IllegalArgumentException.class)
    public void testKMaxIllegal1() {
        int[] a5 = null;
        int actual = Selector.kmax(null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKMaxIllegal2() {
        int[] a5 = {};
        int actual = Selector.kmax(a5, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKMaxIllegal3() {
        int key = 7;
        int[] a2 = {5, 9, 1, 7, 3};
        int actual = Selector.kmax(a2, key);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKMaxIllegal4() {
        int key = 2;
        int[] a2 = {-4, -4, -4, -4, -4, -4, -4};
        int actual = Selector.kmax(a2, key);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKMaxIllegal5() {
        int key = 2;
        int[] a2 = {-4, -4};
        int actual = Selector.kmax(a2, key);
    }
}
