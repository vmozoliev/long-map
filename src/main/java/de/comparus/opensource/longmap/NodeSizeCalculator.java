package de.comparus.opensource.longmap;

class NodeSizeCalculator {

  private static final double INCREASE_COEFFICIENT = 2.0d;

  private final double unLoadFactor;
  private final double loadFactor;

  private final int initialSize;

  public NodeSizeCalculator(int initialNodeSize, double unLoadFactor, double loadFactor) {
    this.unLoadFactor = unLoadFactor;
    this.loadFactor = loadFactor;
    this.initialSize = initialNodeSize;
  }

  public int calculateSize(long elementsCount, int actualNodeSize) {
    if (elementsCount >= actualNodeSize * loadFactor) {
      return maxInt((long) (elementsCount * INCREASE_COEFFICIENT));
    }

    if (elementsCount <= actualNodeSize * unLoadFactor) {
      if (elementsCount < initialSize) {
        return initialSize;
      }
      return maxInt(elementsCount);
    }
    return actualNodeSize;
  }

  private int maxInt(long value) {
    if (value >= Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    return (int) value;
  }
}
