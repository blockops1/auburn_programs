import java.util.Iterator;

public class RandomListMain {
    public static void main(String[] args) {
        RandomList<Integer> tester = new RandomList<>(0);
        tester.add(1);
        System.out.println(tester.sample());
        System.out.println(tester.remove());
        System.out.println();
        tester.add(1);
        tester.add(2);
        tester.add(3);
        tester.add(4);
        tester.add(5);
        tester.add(6);
        tester.add(7);
        Iterator<Integer> walker = tester.iterator();
        while (walker.hasNext()) {
            System.out.println("walker " + walker.next());
        }
        System.out.println();
        Iterator<Integer> jumper = tester.iterator();
        while (jumper.hasNext()) {
            System.out.println("jumper " + jumper.next());
        }
        System.out.println();
        System.out.println(tester.sample());
        System.out.println(tester.sample());
        System.out.println(tester.sample());
        System.out.println(tester.sample());
        System.out.println();
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
        System.out.println(tester.remove());
    }
}

