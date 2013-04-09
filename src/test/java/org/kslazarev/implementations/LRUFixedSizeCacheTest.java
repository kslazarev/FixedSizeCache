package test.java.org.kslazarev.implementations;


import org.junit.*;
import org.kslazarev.implementations.LRUFixedSizeCache;
import org.kslazarev.interfaces.FixedSizeCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LRUFixedSizeCacheTest {
    private static final String FIXTURE_FILE = "./src/test/resources/fixtures_examples.txt";
    private static final List<String[]> fixtures = new ArrayList<String[]>();

    private final int CACHE_SIZE = 600;
    private final int CORRECT_SIZE = 8;

    private final FixedSizeCache<String, String> cache = new LRUFixedSizeCache<String, String>(CACHE_SIZE) {
        @Override
        protected int sizeOf(String s) {
            return s.length() * 2;
        }
    };

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(FIXTURE_FILE));
        scanner.useDelimiter("\n");
        while (scanner.hasNextLine()) {
            fixtures.add(scanner.nextLine().split(", "));
        }
        scanner.close();
    }

    @Before
    public void setUp() {
        for (String[] strings : fixtures) {
            cache.put(strings[0], strings[1]);
            for (Integer i = 1; i < 4; i++) {
                cache.get(i.toString());
            }
        }
    }

    @After
    public void tearDown() {
        cache.clear();
    }

    @Test
    public void testElementsCount() {
        String errorMessage = "Wrong elements count in cache";
        org.junit.Assert.assertTrue(errorMessage, CORRECT_SIZE == cache.getKeys().size());
    }

    @Test
    public void testContainsKeys() {
        List<String> actual = new ArrayList<String>(cache.getKeys());
        List<String> expected = new ArrayList<String>(Arrays.asList(
                "296", "297", "298", "299", "300", "1", "2", "3"
        ));
        org.junit.Assert.assertEquals(actual, expected);
    }
}