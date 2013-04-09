package org.kslazarev.interfaces;

public interface FixedSizeCache<Key,Value> extends Cache<Key,Value> {

    void trimDownCacheSize(int size);

}
