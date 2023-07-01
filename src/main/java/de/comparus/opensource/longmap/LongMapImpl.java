package de.comparus.opensource.longmap;

public class LongMapImpl<V> implements LongMap<V> {

    private static final int BUCKET_INITIAL_SIZE = 10;

    private static final double LOAD_FACTOR = 0.75d;

    private static final double UNLOAD_FACTOR = 0.25d;

    private final BucketContainer<V> bucketContainer = new BucketContainer<>(BUCKET_INITIAL_SIZE);

    private final BucketSizeCalculator bucketSizeCalculator = new BucketSizeCalculator(BUCKET_INITIAL_SIZE, UNLOAD_FACTOR, LOAD_FACTOR);

    public V put(long key, V value) {
        V newValue = bucketContainer.put(key, value);
        resize();
        return newValue;
    }

    public V get(long key) {
        return bucketContainer.get(key);
    }

    public V remove(long key) {
        V newValue = bucketContainer.remove(key);
        resize();
        return newValue;
    }

    public boolean isEmpty() {
        return bucketContainer.elementsSize() == 0;
    }

    public boolean containsKey(long key) {
        return bucketContainer.containsKey(key);
    }

    public boolean containsValue(V value) {
        return bucketContainer.containsValue(value);
    }

    public long[] keys() throws ArraySizeLimitationException {
        return bucketContainer.keys();
    }

    public V[] values() throws ArraySizeLimitationException {
        return bucketContainer.values();
    }

    public long size() {
        return bucketContainer.elementsSize();
    }

    public void clear() {
        bucketContainer.clear();
    }

    private void resize() {
        bucketContainer.resize(bucketSizeCalculator.calculateSize(bucketContainer.elementsSize(), bucketContainer.bucketSize()));
    }
}
