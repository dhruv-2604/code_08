/**
 * Node object class.
 *
 * @author Dhruv Sureka
 * @version 1.0
 * @param <T> the type of elements in this list
 */
public class Node<T> {

    private T data;
    private Node<T> next;

    /**
     * Constructor for a Node.
     *
     * @param data Data that will be assigned to the node
     * @param next Refrence to the next node that will be stored
     */
    public Node(T data, Node<T> next) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot set the value of data to null!");
        }
        this.data = data;
        this.next = next;
    }

    /**
     * Constructor for a Node.
     *
     * @param data The data that will be stored by the Node
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * Returns the data from a Node.
     *
     * @return Data value of node
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the next node refrence.
     *
     * @return Next node refrence
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setter method to set the data value of a node.
     *
     * @param data The data that will be set
     */
    public void setData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot set the value of data to null!");
        }
        this.data = data;
    }

    /**
     * Setter method to set the next node refrence.
     *
     * @param next The next node to be refrenced
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

}
