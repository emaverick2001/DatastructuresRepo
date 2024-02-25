package hw7;

import hw7.hashing.ChainingHashMap;
import hw7.hashing.OpenAddressingHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SuppressWarnings("All")
public abstract class MapTest {

  protected Map<String, String> map;

  @BeforeEach
  public void setup() {
    map = createMap();
  }

  protected abstract Map<String, String> createMap();

  @Test
  public void newMapIsEmpty() {
    assertEquals(0, map.size());
  }

  @Test
  public void insertOneElement() {
    map.insert("key1", "value1");
    assertEquals(1, map.size());
    assertTrue(map.has("key1"));
    assertEquals("value1", map.get("key1"));
  }

  @Test
  public void insertMultipleElement() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    assertEquals(3, map.size());
    assertTrue(map.has("key1"));
    assertTrue(map.has("key2"));
    assertTrue(map.has("key3"));
    assertEquals("value1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("value3", map.get("key3"));
  }

  @Test
  public void insertMultipleElementDifferentKeyName() {
    map.insert("1111", "value1");
    map.insert("2222", "value2");
    map.insert("3333", "value3");
    assertEquals(3, map.size());
    assertTrue(map.has("1111"));
    assertTrue(map.has("2222"));
    assertTrue(map.has("3333"));
    assertEquals("value1", map.get("1111"));
    assertEquals("value2", map.get("2222"));
    assertEquals("value3", map.get("3333"));
  }

  //TODO test growing hashtable if loadfactor reached
  @Test
  public void OpenAddressingloadFactorReachesLimit() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    assertEquals(4, map.size());
    OpenAddressingHashMap test = (OpenAddressingHashMap) map;
    assertEquals(11,test.getCapacity());
    assertTrue(map.has("key1"));
    assertTrue(map.has("key2"));
    assertTrue(map.has("key3"));
    assertTrue(map.has("key4"));
    assertEquals("value1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("value3", map.get("key3"));
    assertEquals("value4", map.get("key4"));
  }

  @Test
  public void OpenAddressingIndexCollisionDifferentKeyNames() {
    map.insert("1111", "value1");
    map.insert("2222", "value2");
    map.insert("3333", "value3");
    map.insert("44444", "value4");
    assertEquals(4, map.size());
    OpenAddressingHashMap test = (OpenAddressingHashMap) map;
    assertEquals(11,test.getCapacity());
    assertTrue(map.has("1111"));
    assertTrue(map.has("2222"));
    assertTrue(map.has("3333"));
    assertTrue(map.has("44444"));
    assertEquals("value1", map.get("1111"));
    assertEquals("value2", map.get("2222"));
    assertEquals("value3", map.get("3333"));
    assertEquals("value4", map.get("44444"));
  }

  @Test
  public void OpenAddressingIndexCollisionWithTSRightAfter() {
    map.insert("1111", "value1");
    map.insert("2222", "value2");
    map.insert("3333", "value3");
    map.remove("1111");
    map.insert("44444", "value4");
    assertEquals(3, map.size());
    assertFalse(map.has("1111"));
    assertTrue(map.has("2222"));
    assertTrue(map.has("3333"));
    assertTrue(map.has("44444"));
    assertEquals("value2", map.get("2222"));
    assertEquals("value3", map.get("3333"));
    assertEquals("value4", map.get("44444"));
  }

  @Test
  public void OpenAddressingIndexCollisionWithTS() {
    map.insert("1111", "value1");
    map.insert("2222", "value2");
    map.insert("3333", "value3");
    map.remove("2222");
    map.insert("44444", "value4");
    assertEquals(3, map.size());
    OpenAddressingHashMap test = (OpenAddressingHashMap) map;
    // grows since TS counts as entry
    assertEquals(11,test.getCapacity());
    assertTrue(map.has("1111"));
    assertFalse(map.has("2222"));
    assertTrue(map.has("3333"));
    assertTrue(map.has("44444"));
    assertEquals("value1", map.get("1111"));
    assertEquals("value3", map.get("3333"));
    assertEquals("value4", map.get("44444"));
  }

  @Test
  public void ChainingloadFactorReachesLimit() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    assertEquals(4, map.size());
    ChainingHashMap test = (ChainingHashMap) map;
    assertEquals(11,test.getCapacity());
    assertTrue(map.has("key1"));
    assertTrue(map.has("key2"));
    assertTrue(map.has("key3"));
    assertTrue(map.has("key4"));
    assertEquals("value1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("value3", map.get("key3"));
    assertEquals("value4", map.get("key4"));
  }

  @Test
  public void ChainingloadFactorReachesLimitTwice() {
    ChainingHashMap test = (ChainingHashMap) map;
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    assertEquals(4, map.size());
    assertEquals(11,test.getCapacity());
    map.insert("key5", "value5");
    map.insert("key6", "value6");
    map.insert("key7", "value7");
    map.insert("key8", "value8");
    map.insert("key9", "value9");
    assertEquals(9, map.size());
    assertEquals(23,test.getCapacity());
    assertTrue(map.has("key1"));
    assertTrue(map.has("key2"));
    assertTrue(map.has("key3"));
    assertTrue(map.has("key4"));
    assertTrue(map.has("key5"));
    assertTrue(map.has("key6"));
    assertTrue(map.has("key7"));
    assertTrue(map.has("key8"));
    assertTrue(map.has("key9"));
    assertEquals("value1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("value3", map.get("key3"));
    assertEquals("value4", map.get("key4"));
    assertEquals("value5", map.get("key5"));
    assertEquals("value6", map.get("key6"));
    assertEquals("value7", map.get("key7"));
    assertEquals("value8", map.get("key8"));
    assertEquals("value9", map.get("key9"));
  }

  @Test
  public void insertDuplicatedKey() {
    try {
      map.insert("key1", "value1");
      map.insert("key1", "value2");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void insertDuplicatedKeyAfterRemovingSameKey() {
    map.insert("key1", "value1");
    map.remove("key1");
    map.insert("key1", "value1");
    assertEquals("value1", map.get("key1"));
  }

  @Test
  public void insertNullKey() {
    try {
      map.insert(null, "value1");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void insertDuplicatedValue() {
    map.insert("key1", "value1");
    map.insert("key2", "value1");
    assertEquals(2, map.size());
  }

  // insert edge case -> probing? Array list size?
  @Test
  public void insertMultipleDuplicatedValues() {
    map.insert("key1", "value1");
    map.insert("k1", "value1");
    map.insert("key3", "value1");
    map.insert("kkkk","value2");
    assertEquals(4, map.size());
    map.remove("kkkk");
    map.insert("whwy","value3");
    map.insert("hiii","value4");
    map.insert("byeeee","value5");
    assertEquals(6, map.size());
    map.insert("woaaha","value6");
    map.insert("nooooooo","value7");
    map.insert("zzzzzz","value8");
  }

  @Test
  public void insertAndRemovingMultipleDuplicatedValues() {
    map.insert("key1", "value1");
    map.insert("k1", "value1");
    map.insert("key3", "value1");
    map.insert("kkkk","value2");
    assertEquals(4, map.size());
    map.insert("whwy","value3");
    map.insert("hiii","value4");
    map.insert("byeeee","value5");
    assertEquals(7, map.size());
    map.insert("woaaha","value6");
    map.insert("nooooooo","value7");
    map.insert("zzzzzz","value8");
  }

  //TODO do null strings work as key names?
  @Test
  public void insertNull() {
    map.insert("null", null);
    assertEquals(1, map.size());
  }

  @Test
  public void removeOneElement() {
    map.insert("key1", "value1");
    assertEquals("value1", map.remove("key1"));
    assertEquals(0, map.size());
  }

  @Test
  public void ChainingremoveFirstElementInBucketWithElementsAfterFirst() {
    map.insert("key1", "value1");
    map.insert("kkkk","value2");
    assertEquals("value1", map.remove("key1"));
    assertEquals(1, map.size());
  }

  @Test
  public void ChainingremoveEntryInMiddleOfBucket() {
    map.insert("key1", "value1");
    map.insert("kkkk","value2");
    map.insert("whwy","value3");
    assertEquals("value2", map.remove("kkkk"));
    assertEquals(2, map.size());
  }

  @Test
  public void ChainingremoveLastEntryInBucket() {
    map.insert("key1", "value1");
    map.insert("kkkk","value2");
    map.insert("whwy","value3");
    assertEquals("value3", map.remove("whwy"));
    assertEquals(2, map.size());
  }

  @Test
  public void removeMultipleElements() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    assertEquals("value1", map.remove("key1"));
    assertEquals("value3", map.remove("key3"));
    assertEquals(1, map.size());
    assertFalse(map.has("key1"));
    assertTrue(map.has("key2"));
    assertFalse(map.has("key3"));
    assertEquals("value2", map.get("key2"));
  }

  @Test
  public void removeNull() {
    try {
      map.remove(null);
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeNoSuchElement() {
    try {
      map.remove("key1");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void updateValue() {
    map.insert("key1", "value1");
    map.put("key1", "value2");
    assertEquals(1, map.size());
    assertEquals("value2", map.get("key1"));
  }

  @Test
  public void updateMultipleValues() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.put("key1", "updated1");
    map.put("key3", "updated3");
    assertEquals(3, map.size());
    assertEquals("updated1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("updated3", map.get("key3"));
  }

  @Test
  public void updateMultipleTimes() {
    map.insert("key1", "value1");
    map.put("key1", "value2");
    map.put("key1", "value3");
    map.put("key1", "value4");
    assertEquals(1, map.size());
    assertEquals("value4", map.get("key1"));
  }

  @Test
  public void updateNullValue() {
    map.insert("key1", "value1");
    map.put("key1", null);
    assertEquals(1, map.size());
    assertNull(map.get("key1"));
  }

  @Test
  public void updateNullKey() {
    try {
      map.put(null, "value");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void updateKeyNotMapped() {
    try {
      map.put("key", "value");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void getKeyNull() {
    try {
      map.get(null);
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void getAllKeysAfterRemove() {
    map.insert("key1", "value1");
    map.insert("kkkk","value2");
    map.insert("whwy","value3");
    assertEquals("value2", map.remove("kkkk"));
    assertEquals("value3",map.get("whwy"));
    assertEquals(2, map.size());
  }

  @Test
  public void getKeysAfterGrow () {
    map.insert("key1", "value1");
    map.insert("kkkk","value2");
    map.insert("whwy","value3");
    map.insert("hiii","value4");
    map.insert("byeeee","value5");
    assertEquals("value1",map.get("key1"));
    assertEquals("value2",map.get("kkkk"));
    assertEquals("value3",map.get("whwy"));
    assertEquals("value4",map.get("hiii"));
    assertEquals("value5",map.get("byeeee"));
  }

  @Test
  public void getKeysAfterGrowTwiceBecauseApparentlyTheresStillAnEdgeCase() {
    map.insert("key1", "value1");
    map.insert("kkkk","value2");
    map.insert("whwy","value3");
    map.insert("hiii","value4");
    map.insert("byeeee","value5");
    map.insert("wooooooooooah","value6");
    map.insert("o","null");
    assertEquals(7, map.size());
    assertEquals("value1",map.get("key1"));
    assertEquals("value2",map.get("kkkk"));
    assertEquals("value3",map.get("whwy"));
    assertEquals("value4",map.get("hiii"));
    assertEquals("value5",map.get("byeeee"));
    assertEquals("value6",map.get("wooooooooooah"));
  }

  @Test
  public void iteratorEmptyMap() {
    for (String key : map) {
      fail("Empty map!");
    }
  }

  @Test
  public void iteratorMultipleElements() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    int counter = 0;
    for (String key : map) {
      counter++;
      assertTrue(map.has(key));
    }
    assertEquals(3, counter);
  }


  // Ideally we should also check for "Keys must be immutable"
  // This is not trivial; check out
  // https://github.com/MutabilityDetector/MutabilityDetector
}