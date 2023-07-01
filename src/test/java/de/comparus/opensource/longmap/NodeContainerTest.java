package de.comparus.opensource.longmap;

import org.junit.Assert;
import org.junit.Test;

public class NodeContainerTest {

  @Test
  public void testResize() {
    BucketContainer<String> nodeContainer = new BucketContainer<>(10);

    nodeContainer.resize(100);

    Assert.assertEquals(100, nodeContainer.bucketSize());
  }
}