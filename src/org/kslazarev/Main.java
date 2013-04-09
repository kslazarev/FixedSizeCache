package org.kslazarev;

import org.kslazarev.implementations.FIFOFixedSizeCache;
import org.kslazarev.implementations.LRUFixedSizeCache;
import org.kslazarev.interfaces.FixedSizeCache;

public class Main {
    static int cacheSize;

    public static void main(String[] args) {
        initializeParameters(args);

        FixedSizeCache<String, String> fifoCache = new FIFOFixedSizeCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String s) {
                return s.length() * 2;
            }
        };

        FixedSizeCache<String, String> lruCache = new LRUFixedSizeCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String s) {
                return s.length() * 2;
            }
        };

        fifoCache.put("first", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        fifoCache.put("second", "Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s.");
        fifoCache.put("third", "Check tests");
        fifoCache.clear();
    }

    private static void initializeParameters(String[] args){
        cacheSize = 500;
        if (args.length != 0) { cacheSize = Integer.parseInt(args[0]);}
    }
}
