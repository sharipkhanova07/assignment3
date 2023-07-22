class Node<K, V> {
    private K key;
    private V value;
    Node<K, V> left;
    Node<K, V> right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

