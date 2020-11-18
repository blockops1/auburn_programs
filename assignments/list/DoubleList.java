import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoubleList.java.
 * Implements Double Ended List
 *
 * @author Rolf Versluis (rzv0018@auburn.edu)
 * @version TODAY
 */

public class DoubleList<T> implements DoubleEndedList<T>{
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public DoubleList() {
        this.front = null;
        this.size = 0;
    }

    private Node<T> getFront() {
        return front;
    }

    private void setFront(Node<T> front) {
        this.front = front;
    }

    private Node<T> getRear() {
        return rear;
    }

    private void setRear(Node<T> rear) {
        this.rear = rear;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Adds element to the front of the list. If element is null,
     * this method throws an IllegalArgumentException.
     *
     * @param element
     */
    @Override
    public void addFirst(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (getSize() > 0 && (getFront() == null || getRear() == null))  {
            fixPointers();
        }
        Node<T> node = new Node<>(element, getFront(), null);
        if (getFront() != null) {
            getFront().setPrev(node);
        }
        setFront(node);
        setSize(getSize() + 1);
    }

    /**
     * Adds element to the end of the list. If element is null,
     * this method throws an IllegalArgumentException.
     *
     * @param element
     */
    @Override
    public void addLast(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (getSize() > 0 && (getFront() == null || getRear() == null))  {
            fixPointers();
        }
        Node<T> node = new Node<>(element, null, getRear());
        if (getRear() != null) {
            getRear().setNext(node);
        }
        setRear(node);
        setSize(getSize() + 1);
    }

    /**
     * Delete and return the element at the front of the list.
     * If the list is empty, this method returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (getSize() > 0 && (getFront() == null || getRear() == null))  {
            fixPointers();
        }
        Node<T> node = getFront();
        if (size > 1) {
            setFront(node.getNext());
            getFront().setPrev(null);
        } else {
            setFront(null);
        }
        setSize(getSize() - 1);
        if (getSize() == 0) {
            setRear(null);
            setFront(null);
        }
        return node.getElement();
    }

    /**
     * Delete and return the element at the end of the list.
     * If the list is empty, this method returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (getSize() > 0 && (getFront() == null || getRear() == null))  {
            fixPointers();
        }
        Node<T> node = getRear();
        if (size > 1) {
            setRear(node.getPrev());
            getRear().setNext(null);
        } else {
            setRear(null);
        }
        setSize(getSize() - 1);
        if (getSize() == 0) {
            setRear(null);
            setFront(null);
        }
        return node.getElement();
    }

    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        return getSize();
    }

    /**
     * Returns true if this list contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Creates and returns an iterator over the elements of this list.
     */
    @Override
    public Iterator<T> iterator() {
        if (getSize() > 0 && (getFront() == null || getRear() == null))  {
            fixPointers();
        }
        return new LinkedIterator<>(this);
    }

    private Node<T> getBottomFront() {
        Node<T> node = getFront();
        while (node.getNext() != null) {
            node = node.getNext();
        }
        return node;
    }

    private Node<T> getBottomRear() {
        Node<T> node = getRear();
        while (node.getPrev() != null) {
            node = node.getPrev();
        }
        return node;
    }

    private void fixPointers() {
        if (getFront() == null) {
            if (getRear() != null) {
                //traverse from rear until end and link the new front
                Node<T> node = getBottomRear();
                setFront(node);
            }
        }
        if (getRear() == null) {
            if (getFront() != null) {
            //traverse from front until end and link the new front
            Node<T> node = getBottomFront();
            setRear(node);
            }
        }
    }

    /**
     * Defines a doubly-linked node of generic type.
     */
    private class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        T getElement() {
            return element;
        }

        void setElement(T element) {
            this.element = element;
        }

        Node<T> getNext() {
            return next;
        }

        void setNext(Node<T> next) {
            this.next = next;
        }

        Node<T> getPrev() {
            return prev;
        }

        void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class LinkedIterator<T> implements Iterator<T> {
        DoubleList<T>.Node<T> current;

        LinkedIterator(DoubleList<T> list) {
            current = list.getFront();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = current.getElement();
            current = current.getNext();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
