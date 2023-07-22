import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class BST<K extends Comparable<K>, V> implements Iterable<BST.Node<K, V>> {
    private Node<K, V> root;
    private int size;

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BST() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void put(Node<K, V> root, K key, V value) {
        put(this.root, key, value);
    }

    // ... (rest of the BST methods)

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<Node<K, V>> {
        private Node<K, V> current;
        private LinkedList<Node<K, V>> stack;

        public BSTIterator(Node<K, V> root) {
            current = root;
            stack = new LinkedList<>();
            fillStack(current);
        }

        private void fillStack(Node<K, V> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> nextNode = stack.pop();
            fillStack(nextNode.right);
            return nextNode;
        }
    }

    public static void main(String[] args) {
        bst<Integer, String> bst = new bst<>();
        bst.put(3, "Three");
        bst.put(1, "One");
        bst.put(5, "Five");
        bst.put(2, "Two");
        bst.put(4, "Four");

        for (bst.Node<Integer, String> node : bst) {
            System.out.println("Key is " + node.key + " and value is " + node.value);
        }
    }
}
