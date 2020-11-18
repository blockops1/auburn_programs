import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * RandomList.java.
 * Implements Random List
 *
 * @author Rolf Versluis (rzv0018@auburn.edu)
 * @version TODAY
 */

public class RandomList<T> implements RandomizedList<T> {
    private T[] elements;
    private int rear;

    @SuppressWarnings("unchecked")
    public RandomList() {
        elements = (T[]) new Object[8];
        rear = 0;
    }

    @SuppressWarnings("unchecked")
    public RandomList(int size) {
        if (size < 8){
            size = 8;
        }
        elements = (T[]) new Object[size];
        rear = 0;
    }

    T[] getElements() {
        return elements;
    }

    void setElements(T[] elements) {
        this.elements = elements;
    }

    int getRear() {
        return rear;
    }

    void setRear(int rear) {
        this.rear = rear;
    }

    /**
     * Adds the specified element to this list. If the element is null, this
     * method throws an IllegalArgumentException.
     *
     * @param element
     */
    @Override
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(getElements().length * 2);
        }
        getElements()[getRear()] = element;
        setRear(getRear() + 1);
    }

    /**
     * Selects and removes an element selected uniformly at random from the
     * elements currently in the list. If the list is empty this method returns
     * null.
     */
    @Override
    public T remove() {
        if (getRear() <= getElements().length / 2) {
            resize(getElements().length / 2);
        }
        if (!isEmpty()) {
            int index = randomChoose(getRear());
            T item = getElements()[index];
            getElements()[index] = getElements()[getRear() - 1];
            setRear(getRear() - 1);
            return item;
        }
        return null;
    }

    /**
     * Selects but does not remove an element selected uniformly at random from
     * the elements currently in the list. If the list is empty this method
     * return null.
     */
    @Override
    public T sample() {
        if (!isEmpty()) {
            return getElements()[randomChoose(getRear())];
        }
        return null;
    }


    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        return getRear();
    }

    /**
     * Returns true if this list contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return getRear() == 0;
    }

    /**
     * Creates and returns an iterator over the elements of this list.
     */
    @Override
    public Iterator<T> iterator() {
        return new RandomIterator<>(this);
    }

    private boolean isFull() {
        return getElements().length == getRear();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newLength) {
        T[] newList = (T[]) new Object[newLength];
        for (int i = 0; i < getRear(); i++) {
            newList[i] = getElements()[i];
        }
        setElements(newList);
        //System.out.println("new size " + newLength);
    }

    private int randomChoose(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    private class RandomIterator<T> implements Iterator<T> {
        int current;
        RandomList<T> copyList;

        RandomIterator(RandomList<T> list) {
            copyList = new RandomList<T>(list.size());
            for (int i = 0; i < list.getRear(); i++) {
                copyList.add(list.getElements()[i]);
            }
            if (!copyList.isEmpty()) {
                current = randomChoose(copyList.getRear());
            } else {
                current = 0;
            }
        }

        @Override
        public boolean hasNext() {
            return !copyList.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = copyList.remove();
            if (copyList.getRear() > 0) {
                current = randomChoose(copyList.getRear());
            } else {
                current = 0;
            }
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
