import java.io.ObjectOutput;
import java.util.Iterator;

public class DoubleListMain {

    /**
     * Call a method to perform example operations on nodes.
     */
    public static void main(String[] args) {
        DoubleList<Object> tester = new DoubleList<>();
        Iterator<Object> walker = tester.iterator();
        // test remove extra from front
        Object front;
        tester.addLast("one");
        tester.addLast("two");
        tester.addLast("three");
        tester.addLast("four");
        System.out.println("one through four");
        for (Object item : tester) {
            System.out.print(item + " ");
        }
        System.out.println();
        front = tester.removeFirst();
        front = tester.removeFirst();
        front = tester.removeFirst();
        front = tester.removeFirst();
        front = tester.removeFirst();
        System.out.println("blank list");
        for (Object item : tester) {
            System.out.print(item + " ");
        }
        System.out.println();
        tester.addLast("one");
        tester.addLast("two");
        tester.addLast("three");
        tester.addLast("four");
        front = tester.removeLast();
        System.out.println(front);
        for (Object item : tester) {
            System.out.print(item + " ");
        }
        System.out.println();
        front = tester.removeFirst();
        System.out.println(front);
        for (Object item : tester) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

}
