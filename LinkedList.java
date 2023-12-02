import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList object class.
 *
 * @author Dhruv Sureka
 * @version 1.0
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * Constructor for a LinkedList without any arguments.
     *
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Constructor for a LinkedList without any arguments.
     *
     * @param data Array that will be turned into a linkedlist
     */
    public LinkedList(T[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot pass in a null array!");
        }
        for (T datum : data) {
            add(datum); // keep adding to end
        }
    }

    /**
     * Returns the head node of a LinkedList.
     *
     * @return The node representing the start of the LinkedList
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * This method converts a LinkedList into an array.
     *
     * @return An array representation of a Linkedlist
     */
    public T[] toArray() {

        T[] arr = (T[]) new Object[size];
        Iterator<T> iterator = iterator();

        int i = 0;
        while (iterator.hasNext()) {
            arr[i] = iterator.next();
            i++;
        }

        return arr;
    }

    /**
     * This method returns a string representation of a LinkedList.
     *
     * @return A string representing a LinkedList
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== LINKEDLIST %d =====\nisEmpty: %s\nsize: %d\nhead: %s\ndata: [",
                        hashCode(),
                        isEmpty(),
                        size(),
                        (head == null ? "null" : head.getData())));

        T[] data = toArray();
        if (data == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < data.length - 1; ++i) {
                sb.append(String.format("%s, ", data[i])); // append all but last value
            }
            if (data.length > 0) {
                sb.append(String.format("%s", data[data.length - 1])); // append last value
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    /**
     * This method adds a node with the specific element at the end of the list.
     *
     * @param element The element that will be assigned to the new node
     * @throws IllegalArgumentException Method may throw this error
     */
    @Override
    public void add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add a null node");
        }
        Node<T> newNode = new Node<T>(element);
        Node<T> tempNode = this.head;

        if (head == null || size() == 0) {
            head = newNode;
        } else {
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(newNode);
        }
        size++;

    }

    /**
     * This method adds a node with the specific element at a specific index.
     *
     * @param index   The index at which we want to add a new node at
     * @param element The element that will be assigned to the new node
     * @throws IndexOutOfBoundsException Method may throw this error
     * @throws IllegalArgumentException  Method may throw this error
     */
    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {

        if ((index > size() || index < 0) && element == null) {
            throw new IndexOutOfBoundsException("Invalid index and a null element");
        }
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("Cannot add a node to an invalid index.");
        }
        if (element == null) {
            throw new IllegalArgumentException("Cannot add a null node");
        }

        Node<T> newNode = new Node<T>(element);
        Node<T> tempNode = this.head;

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else if (index == size()) {
            add(element);
        } else {

            int position = 0;
            while (position != index - 1) {
                tempNode = tempNode.getNext();
                position++;
            }
            newNode.setNext(tempNode.getNext());
            tempNode.setNext(newNode);
        }

        size++;

    }

    /**
     * This method removes the first node in a list.
     *
     * @return The element that was stored by the removed node
     * @throws NoSuchElementException Method may throw this error
     */
    @Override
    public T remove() throws NoSuchElementException {

        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }
        Node<T> tempNode = head;
        head = tempNode.getNext();
        size--;
        return tempNode.getData();

    }

    /**
     * This method removes a node from the specefied index.
     *
     * @param index The index from which we want to remove the node
     * @return The element that was stored by the removed node
     * @throws NoSuchElementException    Method may throw this error
     * @throws IndexOutOfBoundsException Method may throw this error
     */
    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException {

        if (size() == 0 && (index >= size() || index < 0)) {
            throw new NoSuchElementException("The list is empty and index is invalid");
        }
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Entered an invalid index");
        }

        Node<T> removeNode;
        Node<T> tempNode = this.head;
        T returnVal;

        if (index == 0) {
            returnVal = remove();

        } else {

            int position = 0;
            while (position != index - 1) {
                tempNode = tempNode.getNext();
                position++;
            }

            removeNode = tempNode.getNext();
            tempNode.setNext(removeNode.getNext());
            size--;
            returnVal = removeNode.getData();
        }

        return returnVal;
    }

    /**
     * This method removes the first occurrence of the specified element from the
     * list and returns it.
     *
     * @param element The element that we are looking to remove
     * @return The element that is removed from the list
     * @throws NoSuchElementException    Method may throw this error
     * @throws IndexOutOfBoundsException Method may throw this error
     */
    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException {

        if (element == null) {
            throw new IllegalArgumentException("Passed in a null element!");
        }
        if (size == 0) {
            throw new NoSuchElementException("Empty list!");
        }

        Node<T> removeNode;
        Node<T> tempNode = this.head;
        T returnVal;

        if (tempNode.getData().equals(element)) {
            returnVal = remove();
        } else {

            int position = 0;

            while (!tempNode.getNext().getData().equals(element)) {
                tempNode = tempNode.getNext();
                position++;

                if (position == size() - 1) {
                    throw new NoSuchElementException("The passed in element is not in the list");
                }
            }

            removeNode = tempNode.getNext();
            tempNode.setNext(removeNode.getNext());
            size--;
            returnVal = removeNode.getData();

        }
        return returnVal;
    }

    /**
     * Replaces the element at a specific index with the passed in element.
     *
     * @param index   the index of the element to be replaced
     * @param element the element that should replace the existing element at the
     *                passed in index
     * @return the element that was replaced
     * @throws IndexOutOfBoundsException if the passed in index is invalid.
     *                                   In the event both arguments are invalid,
     *                                   this exception should be thrown.
     * @throws IllegalArgumentException  if the passed in element is null
     */
    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {

        if ((index < 0 || index >= size()) && element == null) {
            throw new IndexOutOfBoundsException("The index is invalid and the element is null.");
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        if (element == null) {
            throw new IllegalArgumentException("Passed in element is null");
        }

        T returnVal;
        Node<T> tempNode = this.head;

        if (index == 0) {
            returnVal = head.getData();
            head.setData(element);
        } else {

            int position = 0;
            while (position != index - 1) {
                tempNode = tempNode.getNext();
                position++;
            }
            returnVal = tempNode.getNext().getData();
            tempNode.getNext().setData(element);
        }
        return returnVal;

    }

    /**
     * Returns the element at the specified index.
     *
     * @param index the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the passed in index is invalid
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index of the element to get is invalid!");
        }

        Node<T> tempNode = this.head;

        int position = 0;

        if (index == 0) {
            return head.getData();
        } else {

            while (position != index - 1) {
                tempNode = tempNode.getNext();
                position++;
            }
            return tempNode.getNext().getData();

        }

    }

    /**
     * Checks if the list contains the specified element.
     * The list contains the specified element if there exists at least one element
     * in the list
     * that is equal to the passed in element according to how the equals method is
     * defined.
     *
     * @param element the element to search for in the list
     * @return whether the list contains the element
     * @throws IllegalArgumentException if the passed in element is null
     */
    @Override
    public boolean contains(T element) throws IllegalArgumentException {

        if (element == null) {
            throw new IllegalArgumentException("Cannot pass in null!");
        }
        boolean contains = false;
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                contains = true;
                break;
            }
            System.out.println("1");
        }
        return contains;
    }

    /**
     * Clears the list.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Checks if the list is empty.
     *
     * @return whether the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {

        return this.size;
        // int count = 0;
        //
        // if (isEmpty()) {
        // return 0;
        //
        // } else if (head.getNext() == null) {
        // return 1;
        //
        // } else {
        // count = 1;
        // Node<T> tempNode = this.head;
        // while (tempNode.getNext() != null) {
        // tempNode = tempNode.getNext();
        // count++;
        // }
        // }
        // return count;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }
}