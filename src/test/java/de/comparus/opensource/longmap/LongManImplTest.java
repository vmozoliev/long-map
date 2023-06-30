package de.comparus.opensource.longmap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LongManImplTest {

  @Test
  public void testPutGet() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test");

    Assert.assertEquals("test", map.get(45));
    Assert.assertEquals(1, map.size());
  }

  @Test
  public void testPutRemove() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test");

    map.remove(45);

    Assert.assertNull(null, map.get(45));
    Assert.assertEquals(0, map.size());
    Assert.assertNull(map.remove(100));
  }

  @Test
  public void testSize() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test2");
    map.put(45, "test1");
    map.put(46, "test4");

    Assert.assertEquals(2, map.size());
  }

  @Test
  public void testClear() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test2");
    map.put(45, "test1");
    map.put(46, "test4");

    map.clear();

    map.put(48, "test4");

    Assert.assertEquals(1, map.size());
  }


  @Test
  public void testEmptyCases() {
    LongMap<String> map = new LongMapImpl<>();

    Assert.assertTrue(map.isEmpty());

    map.put(45, "test2");

    Assert.assertFalse(map.isEmpty());

    map.clear();

    Assert.assertTrue(map.isEmpty());

    map.put(45, "test2");

    map.remove(45);

    Assert.assertTrue(map.isEmpty());
  }

  @Test
  public void testContainsKey() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test2");

    Assert.assertTrue(map.containsKey(45));
    Assert.assertFalse(map.containsKey(46));
  }

  @Test
  public void testContainsValue() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test2");

    Assert.assertTrue(map.containsValue("test2"));
    Assert.assertFalse(map.containsValue("test1"));
  }


  @Test
  public void testKeys() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test2");
    map.put(46, "test1");
    map.put(47, "test");

    Assert.assertArrayEquals(new long[] {45, 46, 47}, map.keys());
  }

  @Test
  public void testValues() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, "test2");
    map.put(46, "test1");
    map.put(47, "test");

    Assert.assertArrayEquals(new String[] {"test2", "test1", "test"}, map.values());
  }

  @Test
  public void testPutNull() {
    LongMap<String> map = new LongMapImpl<>();

    map.put(45, null);

    Assert.assertNull(map.get(45));
  }

  @Test
  public void testPut10000() {
    LongMap<String> map = new LongMapImpl<>();

    for (long i = 0; i < 10000; i++) {
      map.put(i, String.valueOf(i));
    }

    Assert.assertEquals(10000, map.size());

    for (long i = 0; i < 10000; i++) {
      Assert.assertEquals(String.valueOf(i), map.get(i));
    }
  }

  @Test
  @Ignore
  //provide more memory
  public void testPutMaxInt() {
    LongMap<String> map = new LongMapImpl<>();

    for (long i = 0; i < (long) Integer.MAX_VALUE + 1000; i++) {
      map.put(i, String.valueOf(i));
    }

    Assert.assertEquals((long) Integer.MAX_VALUE + 1000, map.size());

    for (long i = 0; i < (long) Integer.MAX_VALUE + 1000; i++) {
      Assert.assertEquals(String.valueOf(i), map.get(i));
    }
  }

}
