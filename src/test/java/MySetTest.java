import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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
        assertTrue(set.size() == 10);
        set.add(1);
        assertTrue(set.size() == 11);
        set.remove(1);
        assertTrue(set.size() == 10);
    }

    @Test
    public void iteratorOfSet() {
        var size = 0;
        for (var item : set) {
            assertTrue(set.contains(item));
            size++;
        }
        assertTrue(size == set.size());
    }

    @Test
    public void ToArrayFromSet() {
        var array = set.toArray();
        assertTrue(array.length == set.size());
        for (var item : array) {
            assertTrue(set.contains(item));
        }
    }

    @Test
    public void removeFromSet() {
        assertFalse(set.remove(99));
        var array = set.toArray();
        for (var item : array) {
            assertTrue(set.remove(item));
        }
        assertTrue(set.size() == 0);
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
        assertTrue(set.size() == 0);
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
        assertTrue(set.size() == list.size());
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
        assertTrue(array.length == set.size());
        for (var item : array) {
            assertTrue(set.contains(item));
        }

        array = set.toArray(new Integer[17]);
        assertTrue(array.length == 17);
        var countNull =0;
        for (var item : array) {
            if (item != null)
                assertTrue(set.contains(item));
            else
                countNull++;
        }
        assertTrue(countNull == array.length -set.size());

    }


    @After
    public void tearDown(){
        set.clear();
    }

}
