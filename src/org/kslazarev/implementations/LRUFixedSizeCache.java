package org.kslazarev.implementations;

public abstract class LRUFixedSizeCache<Key, Value> extends BaseFixedSizeCache<Key, Value> {

    public LRUFixedSizeCache(int size) {
        super(size, true);
    }
}
