import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MySet<E extends Comparable<E>> implements Set<E> {
    private Node<E> rootNode;
    private int size;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return rootNode == null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        var value = (E) o;
        var currentNode = rootNode;
        if (rootNode == null)
            return false;
        while (!value.equals(currentNode.getValue())) {
            if (value.compareTo(currentNode.getValue()) < 0)
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();

            if (currentNode == null)
                return false;
        }
        return true;
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> currentNode = rootNode;
            final Stack<Node<E>> stack = new Stack<>();

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                var value = currentNode.getValue();
                var left = currentNode.getLeftChild();
                var right = currentNode.getRightChild();
                if (right != null)
                    stack.push(right);
                if (left != null)
                    currentNode = left;
                else if (!stack.isEmpty())
                    currentNode = stack.pop();
                else
                    currentNode = null;
                return value;
            }
        };
    }


    @Override
    public Object[] toArray() {
        var array = new Object[size];
        var iterator = iterator();
        var i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }

    @Override
    public boolean add(E value) {
        var newNode = new Node<E>();
        newNode.setValue(value);
        if (rootNode == null) {
            //если дерево пустое
            rootNode = newNode;
            size++;
            return true;
        } else {
            var currentNode = rootNode;
            Node<E> parentNode;

            while (true) {
                parentNode = currentNode;
                if (value.equals(currentNode.getValue())) {
                    //если добавляемый элемент уже существует
                    return false;
                } else if (value.compareTo(currentNode.getValue()) < 0) {
                    //если добовляем элемент меньше текущего то уходим в левое поддерево
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        size++;
                        parentNode.setLeftChild(newNode);
                        return true;
                    }
                } else {
                    // если доавляемый элемент больше текущего по уходим в правое поддерево
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        size++;
                        parentNode.setRightChild(newNode);
                        return true;
                    }
                }
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        var value = (E) o;
        var currentNode = rootNode;
        Node<E> parentNode = null;

        //поиск удаляемого элемента
        while (!value.equals(currentNode.getValue())) {
            parentNode = currentNode;
            if (value.compareTo(currentNode.getValue()) < 0)
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();
            //выход если элемент не найдет
            if (currentNode == null)
                return false;
        }

        if (currentNode.getRightChild() == null) {
            //обработка случая когда у удаляемого элемента нет правого поддерева
            if (rootNode == currentNode)
                rootNode = currentNode.getLeftChild();
            else {
                if (parentNode.getLeftChild() == currentNode)
                    parentNode.setLeftChild(currentNode.getLeftChild());
                else
                    parentNode.setRightChild(currentNode.getLeftChild());
            }
        } else {
            var leftMost = currentNode.getRightChild();
            parentNode = null;

            //поиска самого левого элемента в правом поддереве
            while (leftMost.getLeftChild() != null) {
                parentNode = leftMost;
                leftMost = leftMost.getLeftChild();
            }
            //удаление ссылки на "самый левый" элемент к черту этих радикалов =)))
            if (parentNode != null)
                parentNode.setLeftChild(leftMost.getRightChild());
             else
                currentNode.setRightChild(leftMost.getRightChild());

            //замена значения в удаляемом элементе
            currentNode.setValue(leftMost.getValue());
        }
        size--;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        var len = size;
        for (var item : collection)
            add(item);
        return size > len;
    }

    @Override
    public void clear() {
        for (var item : toArray()) {
            remove(item);
        }
    }

    @Override
    public boolean removeAll(Collection collection) {
        var len = size;
        for (var item : collection)
            remove(item);
        return size < len;
    }

    @Override
    public boolean retainAll(@NotNull Collection collection) {
        var len = size;
        for (var item : toArray()) {
            if (!collection.contains(item))
                remove(item);
        }
        return size < len;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (var item : collection)
            if (!contains(item))
                return false;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] ts) {
        if (ts.length < size)
            ts = Arrays.copyOf(ts, size);
        var iterator = iterator();
        for (var i = 0; i < ts.length; i++) {
            if (iterator.hasNext())
                ts[i] = (T) iterator.next();
            else
                ts[i] = null;
        }
        return ts;
    }
}

