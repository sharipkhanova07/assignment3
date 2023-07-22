import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class bst<K extends Comparable<K>, V> implements Iterable<Node<K, V>> {
    // ... (previous BST code)


    @Override
    public Iterator<Node<K, V>> iterator() {
        Node<K, V> root = null;
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

}
