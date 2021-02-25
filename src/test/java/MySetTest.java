import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import java.util.HashSet;
import java.util.Set;

public class MySetTest extends Assert {

    private final Set<Integer> set = new MySet<>();

    @Before
    public void setUp() {
        set.addAll(Arrays.asList(10, 2, 6, 17, 3, 20, 30, 4, 7, 87));
    }

    @Test
    public void addInSet() {
        assertTrue(set.add(9));
        assertFalse(set.add(9));
    }

    @Test
    public void containsInSet() {
        assertTrue(set.contains(17));
        assertFalse(set.contains(9));
    }

    @Test
    public void isEmptyOfSet() {
        assertFalse(set.isEmpty());
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void sizeOfSet() {
        assertEquals(10, set.size());
        set.add(1);
        assertEquals(11, set.size());
        set.remove(1);
        assertEquals(10, set.size());
    }

    @Test
    public void iteratorOfSet() {
        var size = 0;
        for (var item : set) {
            assertTrue(set.contains(item));
            size++;
        }
        assertEquals(size, set.size());
    }

    @Test
    public void ToArrayFromSet() {
        var array = set.toArray();
        assertEquals(array.length, set.size());
        for (var item : array) {
            assertTrue(item.toString(),set.contains(item));
        }
    }

    @Test
    public void removeFromSet() {
        assertFalse(set.remove(99));
        var array = set.toArray();
        for (var item : array) {
            assertTrue(item.toString(),set.remove(item));
        }
        assertEquals(0, set.size());
    }

    @Test
    public void addAllInSet() {
        assertTrue(set.addAll(Arrays.asList(22, 33, 88, 99, 111)));
        assertFalse(set.addAll(Arrays.asList(22, 33, 88, 99, 111)));
        assertTrue(set.addAll(Arrays.asList(222, 111)));

    }

    @Test
    public void SetToClear() {
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    public void RemoveAllFromSet() {
        assertTrue(set.removeAll(Arrays.asList(12, 30, 5, 6)));
        assertFalse(set.removeAll(Arrays.asList(33, 88)));
    }

    @Test
    public void retainAllFromSet() {
        var list = Arrays.asList(20, 30, 4);
        assertTrue(set.retainAll(list));
        assertEquals(set.size(), list.size());
        assertFalse(set.retainAll(list));
    }

    @Test
    public void containsAllInSet() {
        assertTrue(set.containsAll(Arrays.asList(20, 30, 4)));
        assertFalse(set.containsAll(Arrays.asList(20, 33)));
    }

    @Test
    public void toArrayWithArrFromSet() {
        var arr = new Integer[5];
        Integer[] array = set.toArray(arr);
        assertEquals(array.length, set.size());
        for (var item : array) {
            assertTrue(set.contains(item));
        }

        array = set.toArray(new Integer[17]);
        assertEquals(17, array.length);
        var countNull =0;
        for (var item : array) {
            if (item != null)
                assertTrue(set.contains(item));
            else
                countNull++;
        }
        assertEquals(countNull, array.length - set.size());

    }


    @After
    public void tearDown(){
        set.clear();
    }

}
