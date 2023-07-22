import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class bst<K extends Comparable<K>, V> implements Iterable<bst.Node<K, V>> {
    private Node<K, V> root;
    private int size;

    public static class Node<K, V> {
        K key;
        V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public bst() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        Node<K, V> node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node<K, V> temp = node;
                node = min(temp.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
            }
        }
        return node;
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

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

        for (Node<Integer, String> node : bst) {
            System.out.println("Key is " + node.key + " and value is " + node.value);
        }
    }
}
