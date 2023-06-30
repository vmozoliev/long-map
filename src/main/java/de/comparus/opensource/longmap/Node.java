package de.comparus.opensource.longmap;

import java.util.Objects;

class Node<V> {

  private final long key;

  private V value;

  public Node(long key, V value) {
    this.key = key;
    this.value = value;
  }

  public long getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Node<?> node = (Node<?>) o;
    return key == node.key;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }
}
