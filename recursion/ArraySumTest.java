import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ArraySumTest {


    /**
     * Fixture initialization (common initialization
     * for all tests).
     **/
    @Before
    public void setUp() {
    }

    // sum section
    ///////////////////
    // Typical cases //
    ///////////////////
    // test with 3 unique integers, 1, 2, 3
    @Test
    public void testSumTypical1() {
        int expected = 1;
        int[] a = {0, 0, 0, 0, 0, 0, 1};
        int actual = ArraySum.sum(a, 0, a.length - 1);
        assertEquals(expected, actual);
    }
    // test with 3 unique integers, 1, 2, 3
    @Test
    public void testSumTypical2() {
        int expected = 55;
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        int actual = ArraySum.sum(a, 0, a.length - 1);
        assertEquals(expected, actual);
    }
}

