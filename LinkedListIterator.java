import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedListIterator object class.
 *
 * @author Dhruv Sureka
 * @version 1.0
 * @param <T> the type of elements in this list
 */
public class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> next;

    /**
     * Constructor for a LinkedListIterator.
     *
     * @param list List that needs to be iterated over
     * @throws IllegalArgumentException Constructor may throw this error
     */
    public LinkedListIterator(LinkedList<T> list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("Cannot pass in a null LinkedList");
        }
        next = list.getHead();

    }

    /**
     * This method checks if there's another node to be iterated over.
     *
     * @return a boolean that represents if there's another node to be iterated over
     */
    public boolean hasNext() {

        return next != null;
    }

    /**
     * This method returns the element from the node that is being iterated over.
     *
     * @return The element that was stored by the node
     * @throws NoSuchElementException Constructor may throw this error
     */
    public T next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the LinkedList");
        }
        T temp = next.getData();
        if (next.getNext() != null) {
            next = next.getNext();
        } else {
            next = null;
        }

        return temp;

    }

}
