package de.comparus.opensource.longmap;

import java.util.Optional;

class BucketContainer<V> {
  private Bucket<V>[] buckets;
  private long size = 0;

  private final int initialBucketSize;

  public BucketContainer(int initialBucketSize) {
    this.initialBucketSize = initialBucketSize;
    buckets = initialize(initialBucketSize);
  }

  public V put(long key, V value) {
    int index = (int) (key % buckets.length);

    if (buckets[index].put(new Node<>(key, value))) {
      size++;
    }
    return value;
  }

  public V get(long key) {
    int index = (int) (key % buckets.length);

    Optional<Node<V>> optionals = buckets[index].getNode(key);
    return optionals.map(Node::getValue).orElse(null);
  }

  public V remove(long key) {
    int index =(int) (key % buckets.length);

    V value = buckets[index].remove(key);
    if (value != null) {
      size--;
    }
    return value;
  }

  public boolean containsKey(long key) {
    int index =(int) (key % buckets.length);

    return buckets[index].containsKey(key);
  }

  public boolean containsValue(V value) {
    for (Bucket<V> list : buckets) {
      boolean isContained = list.containsValue(value);
      if (isContained) {
        return true;
      }
    }
    return false;
  }

  public long[] keys() throws ArraySizeLimitationException {
    if (size > Integer.MAX_VALUE) {
      throw new ArraySizeLimitationException();
    }
    long[] keys = new long[(int) size];
    final int[] index = {0};
    for (Bucket<V> list : buckets) {
      list.forEach(node -> {
        keys[index[0]] = node.getKey();
        index[0]++;
      });
    }
    return keys;
  }

  public V[] values() throws ArraySizeLimitationException {
    if (size > Integer.MAX_VALUE) {
      throw new ArraySizeLimitationException();
    }
    V[] values = (V[]) new Object[(int) size];
    final int[] index = {0};
    for (Bucket<V> list : buckets) {
      list.forEach(node -> {
        values[index[0]] = node.getValue();
        index[0]++;
      });
    }
    return values;
  }

  public long elementsSize() {
    return size;
  }

  public void clear() {
    buckets = initialize(initialBucketSize);
    size = 0;
  }

  public int bucketSize() {
    return buckets.length;
  }

  public void resize(int newBucketSize) {
    if (buckets.length == newBucketSize) {
      return;
    }

    Bucket<V>[] newBuckets = initialize(newBucketSize);

    for (Bucket<V> list : buckets) {
      list.forEach(node -> {
        int index = (int) (node.getKey() % newBuckets.length);
        newBuckets[index].put(node);
      });
    }

    buckets = newBuckets;
  }

  private static <V> Bucket<V>[] initialize(int nodeSize) {
    Bucket<V>[] newBuckets = (Bucket<V>[]) new Bucket[nodeSize];

    for (int i = 0; i < newBuckets.length; i++) {
      newBuckets[i] = new Bucket<>();
    }
    return newBuckets;
  }
}
