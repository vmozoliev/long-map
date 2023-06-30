package de.comparus.opensource.longmap;

public class LongMapImpl<V> implements LongMap<V> {

    private static final int NODE_INITIAL_SIZE = 10;

    private static final double LOAD_FACTOR = 0.75d;

    private static final double UNLOAD_FACTOR = 0.25d;

    private final NodeContainer<V> nodeContainer = new NodeContainer<>(NODE_INITIAL_SIZE);

    private final NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(NODE_INITIAL_SIZE, UNLOAD_FACTOR, LOAD_FACTOR);

    public V put(long key, V value) {
        V newValue = nodeContainer.put(key, value);
        resize();
        return newValue;
    }

    public V get(long key) {
        return nodeContainer.get(key);
    }

    public V remove(long key) {
        V newValue = nodeContainer.remove(key);
        resize();
        return newValue;
    }

    public boolean isEmpty() {
        return nodeContainer.elementsSize() == 0;
    }

    public boolean containsKey(long key) {
        return nodeContainer.containsKey(key);
    }

    public boolean containsValue(V value) {
        return nodeContainer.containsValue(value);
    }

    public long[] keys() {
        return nodeContainer.keys();
    }

    public V[] values() {
        return nodeContainer.values();
    }

    public long size() {
        return nodeContainer.elementsSize();
    }

    public void clear() {
        nodeContainer.clear();
    }

    private void resize() {
        nodeContainer.resize(nodeSizeCalculator.calculateSize(nodeContainer.elementsSize(), nodeContainer.nodeSize()));
    }
}
