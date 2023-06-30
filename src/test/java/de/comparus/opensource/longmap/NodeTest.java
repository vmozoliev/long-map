package de.comparus.opensource.longmap;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

  @Test
  public void testEquals() {
    Node<String> node1 = new Node<>(100, "test");
    Node<String> node2 = new Node<>(99, "test");

    Assert.assertNotEquals(node1, node2);

    Node<String> node3 = new Node<>(44, "test");
    Node<String> node4 = new Node<>(44, "test");
    Assert.assertEquals(node3, node4);
  }
}