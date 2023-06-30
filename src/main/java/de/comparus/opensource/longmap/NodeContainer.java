package de.comparus.opensource.longmap;

import java.util.Optional;

class NodeContainer<V> {
  private NodeList<V>[] nodes;
  private long size = 0;

  private final int initialNodeSize;

  public NodeContainer(int initialNodeSize) {
    this.initialNodeSize = initialNodeSize;
    nodes = initialize(initialNodeSize);
  }

  public V put(long key, V value) {
    int index = (int) (key % nodes.length);

    if (nodes[index].put(new Node<>(key, value))) {
      size++;
    }
    return value;
  }

  public V get(long key) {
    int index = (int) (key % nodes.length);

    Optional<Node<V>> optionals = nodes[index].getNode(key);
    return optionals.map(Node::getValue).orElse(null);
  }

  public V remove(long key) {
    int index =(int) (key % nodes.length);

    V value = nodes[index].remove(key);
    if (value != null) {
      size--;
    }
    return value;
  }

  public boolean containsKey(long key) {
    int index =(int) (key % nodes.length);

    return nodes[index].containsKey(key);
  }

  public boolean containsValue(V value) {
    for (NodeList<V> list : nodes) {
      boolean isContained = list.containsValue(value);
      if (isContained) {
        return true;
      }
    }
    return false;
  }

  public long[] keys() {
    if (size > Integer.MAX_VALUE) {
      throw new IllegalStateException();
    }
    long[] keys = new long[(int) size];
    final int[] index = {0};
    for (NodeList<V> list : nodes) {
      list.forEach(node -> {
        keys[index[0]] = node.getKey();
        index[0]++;
      });
    }
    return keys;
  }

  public V[] values() {
    if (size > Integer.MAX_VALUE) {
      throw new IllegalStateException();
    }
    V[] values = (V[]) new Object[(int) size];
    final int[] index = {0};
    for (NodeList<V> list : nodes) {
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
    nodes = initialize(initialNodeSize);
    size = 0;
  }

  public int nodeSize() {
    return nodes.length;
  }

  public void resize(int newNodeSize) {
    if (nodes.length == newNodeSize) {
      return;
    }

    NodeList<V>[] newNodes = initialize(newNodeSize);

    for (NodeList<V> list : nodes) {
      list.forEach(node -> {
        int index = (int) (node.getKey() % newNodes.length);
        newNodes[index].put(node);
      });
    }

    nodes = newNodes;
  }

  private static <V> NodeList<V>[] initialize(int nodeSize) {
    NodeList<V>[] newNodes = (NodeList<V>[]) new NodeList[nodeSize];

    for (int i = 0; i < newNodes.length; i++) {
      newNodes[i] = new NodeList<>();
    }
    return newNodes;
  }
}
