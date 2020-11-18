import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;


public class SelectorTest {


    /**
     * Fixture initialization (common initialization
     * for all tests).
     **/
    // int[] a1 = {2, 8, 7, 3, 4};
    // int[] a2 = {5, 9, 1, 7, 3};
    // int[] a3 = {8, 7, 6, 5, 4};
    // int[] a4 = {2, 8, 8, 7, 3, 3, 4};

    class CompIntAscend implements Comparator<Integer>{

        @Override
        public int compare(Integer o, Integer t1) {
            return o.compareTo(t1);
        }
    }

    class CompIntDescend implements Comparator<Integer>{

        @Override
        public int compare(Integer o, Integer t1) {
            return t1.compareTo(o);
        }
    }


    class Pair {
        String one;
        Integer two;
    }
    class CompPairStringAscend implements Comparator<Pair>{

        @Override
        public int compare(Pair o, Pair t1) {
            return o.one.compareTo(t1.one);
        }
    }

    class CompPairIntegerAscend implements Comparator<Pair>{

        @Override
        public int compare(Pair o, Pair t1) {
            return o.two.compareTo(t1.two);
        }
    }

    @Before
    public void setUp() {

    }
    Comparator<Integer> ascend = new CompIntAscend();
    Comparator<Integer> descend = new CompIntDescend();

    // min section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testMinTypical1() {
        Integer expected = 1;
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        Integer actual = Selector.min(a, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testMinTypical2() {
        Integer expected = 11;
        List<Integer> a = List.of(5, 9, 1, 7, 11);
        //System.out.println(a.toString());
        Integer actual = Selector.min(a, descend);
        assertEquals(expected, actual);
    }

    @Test public void testMinTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;

        Comparator<Pair> ascend = new CompPairStringAscend();
        Pair expected = alpha;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.min(a, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testMinTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;

        Comparator<Pair> ascend = new CompPairIntegerAscend();
        Pair expected = epsilon;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.min(a, ascend);
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
    @Test public void testMinSpecial1() {
        Integer expected = 4;
        List<Integer> a = List.of(4, 4, 4, 4, 4);
        //System.out.println(a.toString());
        Integer actual = Selector.min(a, ascend);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = NoSuchElementException.class)
    public void testMinIllegal1() {
        Integer expected = 4;
        List<Integer> a = List.of();
        Integer actual = Selector.min(a, ascend);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMinIllegal2() {
        Integer expected = 4;
        List<Integer> a = List.of();
        Integer actual = Selector.min(null, ascend);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMinIllegal3() {
        Integer expected = 4;
        List<Integer> a = List.of();
        Integer actual = Selector.min(a, null);
    }


    // max section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testMaxTypical1() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer expected = 9;
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        Integer actual = Selector.max(a, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testMaxTypical2() {
        Comparator<Integer> descend = new CompIntDescend();
        Integer expected = 1;
        List<Integer> a = List.of(5, 9, 1, 7, 11);
        //System.out.println(a.toString());
        Integer actual = Selector.max(a, descend);
        assertEquals(expected, actual);
    }

    @Test public void testMaxTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;

        Comparator<Pair> ascend = new CompPairStringAscend();
        Pair expected = epsilon;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.max(a, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testMaxTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;

        Comparator<Pair> ascend = new CompPairIntegerAscend();
        Pair expected = alpha;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.max(a, ascend);
        assertEquals(expected, actual);
    }
    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders
    @Test public void testMaxSpecial1() {
        Integer expected = 4;
        List<Integer> a = List.of(4, 4, 4, 4, 4);
        //System.out.println(a.toString());
        Integer actual = Selector.max(a, ascend);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = NoSuchElementException.class)
    public void testMaxIllegal1() {
        Integer expected = 4;
        List<Integer> a = List.of();
        Integer actual = Selector.max(a, ascend);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxIllegal2() {
        Integer expected = 4;
        List<Integer> a = List.of();
        Integer actual = Selector.max(null, ascend);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxIllegal3() {
        Integer expected = 4;
        List<Integer> a = List.of();
        Integer actual = Selector.max(a, null);
    }



    // range section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testRangeTypical1() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 3;
        Integer high = 5;
        List<Integer> expected = List.of(5, 3);
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, ascend);
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        Assert.assertTrue(expected.equals(actual));
    }

    @Test public void testRangeTypical2() {
        Comparator<Integer> descend = new CompIntDescend();
        Integer low = 5;
        Integer high = 3;
        List<Integer> expected = List.of(5, 3);
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, descend);
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        Assert.assertTrue(expected.equals(actual));
    }

    @Test public void testRangeTypical2a() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 4;
        Integer high = 8;
        List<Integer> expected = List.of(8,7,6,5,4);
        List<Integer> a = List.of(8,7,6,5,4);
        //System.out.println(a.toString());
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, ascend);
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        Assert.assertTrue(expected.equals(actual));
    }

    @Test public void testRangeTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;

        Comparator<Pair> ascend = new CompPairStringAscend();

        List<Pair> a = List.of(alpha,beta,gamma);
        List<Pair> expected = List.of(beta,gamma);
        Pair low = beta;
        Pair high = gamma;
        //System.out.println(a.toString()
        List<Pair> actual = (List<Pair>)Selector.range(a, low, high, ascend);
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        Assert.assertTrue(expected.equals(actual));
    }

    @Test public void testRangeTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        Pair low = new Pair();
        low.one = "F";
        low.two = 4;
        Pair high = new Pair();
        high.one = "G";
        high.two = 7;

        Comparator<Pair> ascend = new CompPairIntegerAscend();
        List<Pair> a = List.of(alpha,beta,gamma);
        List<Pair> expected = List.of(alpha, beta);
        //System.out.println(a.toString()
        List<Pair> actual = (List<Pair>)Selector.range(a, low, high, ascend);
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        assertEquals(expected, actual);
    }
    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders

    @Test public void testRangeSpecial1() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 3;
        Integer high = 3;
        List<Integer> expected = List.of(3);
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, ascend);
        //System.out.println(expected.toString());
        //System.out.println(actual.toString());
        Assert.assertTrue(expected.equals(actual));
    }
    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = NoSuchElementException.class)
    public void testRangeIllegal0() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 20;
        Integer high = 10;
        List<Integer> expected = List.of();
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, ascend);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test (expected = NoSuchElementException.class)
    public void testRangeIllegal1() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 20;
        Integer high = 10;
        List<Integer> expected = List.of();
        List<Integer> a = List.of();
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, ascend);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRangeIllegal2() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 3;
        Integer high = 3;
        List<Integer> expected = List.of(3);
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        List<Integer> actual = (List<Integer>)Selector.range(null, low, high, ascend);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRangeIllegal3() {
        Comparator<Integer> ascend = new CompIntAscend();
        Integer low = 3;
        Integer high = 3;
        List<Integer> expected = List.of(3);
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        List<Integer> actual = (List<Integer>)Selector.range(a, low, high, null);
        Assert.assertTrue(expected.equals(actual));
    }

    // floor section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testFloorTypical1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 6;
        Integer expected = 4;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.floor(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testFloorTypical2() {
        Comparator<Integer> descend = new CompIntDescend();
        int key = 1;
        Integer expected = 1;
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        Integer actual = Selector.floor(a, key, descend);
        assertEquals(expected, actual);
    }

    @Test public void testFloorTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        Pair key = new Pair();
        key.one = "F";
        key.two = 0;

        Comparator<Pair> ascend = new CompPairStringAscend();
        Pair expected = epsilon;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.floor(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testFloorTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        Pair key = new Pair();
        key.one = "B";
        key.two = 9;


        Comparator<Pair> ascend = new CompPairIntegerAscend();
        Pair expected = alpha;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.floor(a, key, ascend);
        assertEquals(expected, actual);
    }
    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders
    @Test public void testFloorSpecial1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 6;
        Integer expected = 4;
        List<Integer> a = List.of(2,8,4,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.floor(a, key, ascend);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = NoSuchElementException.class)
    public void testFloorIllegal1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 0;
        Integer expected = 4;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.floor(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFloorIllegal2() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 6;
        Integer expected = 4;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.floor(null, key, ascend);
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFloorIllegal3() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 6;
        Integer expected = 4;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.floor(a, key, null);
        assertEquals(expected, actual);
    }


    // ceiling section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testCeilingTypical1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 1;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.ceiling(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testCeilingTypical2() {
        Comparator<Integer> descend = new CompIntDescend();
        int key = 7;
        Integer expected = 7;
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        Integer actual = Selector.ceiling(a, key, descend);
        assertEquals(expected, actual);
    }

    @Test public void testCeilingTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        Pair key = new Pair();
        key.one = "B";
        key.two = 9;

        Comparator<Pair> ascend = new CompPairStringAscend();
        Pair expected = beta;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.ceiling(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testCeilingTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        Pair key = new Pair();
        key.one = "F";
        key.two = 0;


        Comparator<Pair> ascend = new CompPairIntegerAscend();
        Pair expected = epsilon;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.ceiling(a, key, ascend);
        assertEquals(expected, actual);
    }

    // kmin section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testKminTypical1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 1;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testKminTypical2() {
        Comparator<Integer> descend = new CompIntDescend();
        int key = 2;
        Integer expected = 7;
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, descend);
        assertEquals(expected, actual);
    }

    @Test public void testKminTypical2a() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 3;
        Integer expected = 6;
        List<Integer> a = List.of(8,7,6,5,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }


    @Test public void testKminTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        int key = 4;
        Comparator<Pair> ascend = new CompPairStringAscend();
        Pair expected = delta;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testKminTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        int key = 2;
        Comparator<Pair> ascend = new CompPairIntegerAscend();
        Pair expected = delta;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    ///////////////////
    // Special cases //
    ///////////////////
    // duplicates
    // different orders
    @Test public void testKminSpecial1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 1;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,2,2,2,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testKminSpecial2() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 2;
        Integer expected = 3;
        List<Integer> a = List.of(3,7,3,3,1,9,1,1,1,5);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testKminSpecial3() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 3;
        Integer expected = 5;
        List<Integer> a = List.of(3,7,3,3,1,9,1,1,1,5);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }


    ///////////////////
    // Illegal cases //
    ///////////////////
    // null array
    // zero length array
    @Test (expected = NoSuchElementException.class)
    public void testKminIllegal1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 1;
        Integer expected = 2;
        List<Integer> a = List.of();
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test (expected = NoSuchElementException.class)
    public void testKminIllegal1a() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 6;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test (expected = NoSuchElementException.class)
    public void testKminIllegal1b() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 0;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }


    @Test (expected = NoSuchElementException.class)
    public void testKminIllegal1c() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 2;
        Integer expected = 2;
        List<Integer> a = List.of(-4,-4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, ascend);
        assertEquals(expected, actual);
    }


    @Test (expected = IllegalArgumentException.class)
    public void testKminIllegal2() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 10;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(null, key, ascend);
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testKminIllegal3() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 10;
        Integer expected = 2;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmin(a, key, null);
        assertEquals(expected, actual);
    }



    // kmax section
    ///////////////////
    // Typical cases //
    ///////////////////
    // random numbers
    @Test public void testKmaxTypical1() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 1;
        Integer expected = 8;
        List<Integer> a = List.of(2,8,7,3,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmax(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testKmaxTypical2() {
        Comparator<Integer> descend = new CompIntDescend();
        int key = 2;
        Integer expected = 3;
        List<Integer> a = List.of(5, 9, 1, 7, 3);
        //System.out.println(a.toString());
        Integer actual = Selector.kmax(a, key, descend);
        assertEquals(expected, actual);
    }

    @Test public void testKmaxTypical2a() {
        Comparator<Integer> ascend = new CompIntAscend();
        int key = 3;
        Integer expected = 6;
        List<Integer> a = List.of(8,7,6,5,4);
        //System.out.println(a.toString());
        Integer actual = Selector.kmax(a, key, ascend);
        assertEquals(expected, actual);
    }


    @Test public void testKmaxTypical3() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        int key = 4;
        Comparator<Pair> ascend = new CompPairStringAscend();
        Pair expected = beta;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.kmax(a, key, ascend);
        assertEquals(expected, actual);
    }

    @Test public void testKmaxTypical4() {
        Pair alpha = new Pair();
        alpha.one = "A";
        alpha.two = 5;
        Pair beta = new Pair();
        beta.one = "B";
        beta.two = 4;
        Pair gamma = new Pair();
        gamma.one = "C";
        gamma.two = 3;
        Pair delta = new Pair();
        delta.one = "D";
        delta.two = 2;
        Pair epsilon = new Pair();
        epsilon.one = "E";
        epsilon.two = 1;
        int key = 2;
        Comparator<Pair> ascend = new CompPairIntegerAscend();
        Pair expected = beta;
        List<Pair> a = List.of(alpha,beta,gamma,delta,epsilon);
        Pair actual = Selector.kmax(a, key, ascend);
        assertEquals(expected, actual);
    }


}