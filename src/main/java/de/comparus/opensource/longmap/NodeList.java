package de.comparus.opensource.longmap;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

class NodeList<V> {
  private final List<Node<V>> list = new LinkedList<>();

  public boolean put(Node<V> newNode) {
    Optional<Node<V>> node = getNode(newNode.getKey());
    if (node.isPresent()) {
      node.get().setValue(newNode.getValue());
      return false;
    }
    list.add(newNode);
    return true;
  }

  public Optional<Node<V>> getNode(long key) {
    return list.stream().filter(node -> node.getKey() == key).findFirst();
  }

  public boolean containsValue(V value) {
    return list.stream().anyMatch(node -> Objects.equals(node.getValue(), value));
  }

  public boolean containsKey(long key) {
    return list.contains(new Node<V>(key, null));
  }

  public V remove(long key) {
    Optional<Node<V>> removedNode = list.stream().filter(node -> node.getKey() == key).findFirst();
    if (removedNode.isPresent()) {
      list.remove(removedNode.get());
      return removedNode.get().getValue();
    }
    return null;
  }

  public void forEach(Consumer<Node<V>> consumer) {
    list.forEach(consumer);
  }

}