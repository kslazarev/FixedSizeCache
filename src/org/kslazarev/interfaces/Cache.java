package org.kslazarev.interfaces;

import java.lang.ref.Reference;
import java.util.List;
import java.util.Set;

public interface Cache<Key, Value> {

    Value get(Key key);

    Reference<Value> put(Key key, Value value);

    boolean remove(Key key);

    void clear();

    Set<Key> getKeys();

    List<Value> getValues();
}
