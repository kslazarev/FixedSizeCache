package org.kslazarev.implementations;

public abstract class FIFOFixedSizeCache<Key, Value> extends BaseFixedSizeCache<Key, Value> {

    public FIFOFixedSizeCache(int size) {
        super(size, false);
    }
}
