package org.kslazarev.implementations;

import org.kslazarev.interfaces.FixedSizeCache;

import java.lang.ref.Reference;
import java.util.*;


public abstract class BaseFixedSizeCache<Key, Value> extends BaseCache<Key,Value> implements FixedSizeCache<Key, Value> {
    private final int sizeLimit;
    private int cacheSize;

    BaseFixedSizeCache(int sizeLimit, boolean accessOrder) {
        super(accessOrder);
        this.sizeLimit = sizeLimit;
        this.cacheSize = 0;
    }

    public Reference<Value> put(Key key, Value value) {
        int valueSize = sizeOf(value);
        trimDownCacheSize(valueSize);
        cacheSize += valueSize;
        return super.put(key,value);
    }

    public boolean remove(Key key) {
        int valueSize = sizeOf(get(key));
        cacheSize -= valueSize;
        return super.remove(key);
    }

    public void trimDownCacheSize(int size) {
        while (sizeLimit < cacheSize + size) { removeNext(); }
    }

    void removeNext() {
        Map.Entry<Key, Reference<Value>> entry = map.entrySet().iterator().next();
        remove(entry.getKey());
    }

    protected abstract int sizeOf(Value value);
}