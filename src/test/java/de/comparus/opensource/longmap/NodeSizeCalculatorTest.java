package de.comparus.opensource.longmap;

import org.junit.Assert;
import org.junit.Test;

public class NodeSizeCalculatorTest {

  @Test
  public void testCalculateSize_100_70() {
    NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(10, 0.25, 0.75);

    int size = nodeSizeCalculator.calculateSize(100, 70);

    Assert.assertEquals(200, size);
  }

  @Test
  public void testCalculateSize_10_70() {
    NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(10, 0.25, 0.75);

    int size = nodeSizeCalculator.calculateSize(11, 70);

    Assert.assertEquals(11, size);
  }

  @Test
  public void testCalculateSize_5_70() {
    NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(10, 0.25, 0.75);

    int size = nodeSizeCalculator.calculateSize(5, 70);

    Assert.assertEquals(10, size);
  }

  @Test
  public void testCalculateSize_MaxInt_10000() {
    NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(10, 0.25, 0.75);

    int size = nodeSizeCalculator.calculateSize((long) Integer.MAX_VALUE + 1000, 10000);

    Assert.assertEquals(Integer.MAX_VALUE, size);
  }

  @Test
  public void testCalculateSizeIncreaseCase() {
    NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(10, 0.25, 0.75);

    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(1, 10));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(2, 10));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(3, 10));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(4, 10));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(5, 10));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(6, 10));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(7, 10));
    Assert.assertEquals(16, nodeSizeCalculator.calculateSize(8, 10));
    Assert.assertEquals(16, nodeSizeCalculator.calculateSize(9, 16));
    Assert.assertEquals(16, nodeSizeCalculator.calculateSize(10, 16));
    Assert.assertEquals(16, nodeSizeCalculator.calculateSize(11, 16));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(12, 16));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(13, 24));
  }

  @Test
  public void testCalculateSizeDecreaseCase() {
    NodeSizeCalculator nodeSizeCalculator = new NodeSizeCalculator(10, 0.25, 0.75);

    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(13, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(12, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(11, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(10, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(9, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(8, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(7, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(10, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(9, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(9, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(8, 24));
    Assert.assertEquals(24, nodeSizeCalculator.calculateSize(7, 24));
    Assert.assertEquals(10, nodeSizeCalculator.calculateSize(6, 24));
  }
}