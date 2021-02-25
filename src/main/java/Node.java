public class Node<E> {
    private E value;
    private Node<E> leftChild;
    private Node<E> rightChild;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<E> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<E> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{value=" + value +
                ", leftChild=" +
                leftChild +
                ", rightChild=" +
                rightChild +
                '}';
    }
}
