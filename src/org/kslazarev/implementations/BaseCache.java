package org.kslazarev.implementations;

import org.kslazarev.interfaces.Cache;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.*;

abstract public class BaseCache<Key, Value> implements Cache<Key,Value> {
    final Map<Key, Reference<Value>> map;
    private final List<Value> values = new LinkedList();

    BaseCache(boolean accessOrder){
        this.map = new LinkedHashMap<Key, Reference<Value>>(0, 0.75f, accessOrder);
    }

    public Value get(Key key) {
        Reference<Value> reference = map.get(key);
        return getValue(reference);
    }

    public Reference<Value> put(Key key, Value value) {
        values.add(value);
        return map.put(key, createReference(value));
    }

    public boolean remove(Key key) {
        Reference<Value> reference = map.remove(key);
        return values.remove(reference.get());
    }

    public Set<Key> getKeys() {
        return map.keySet();
    }

    public List<Value> getValues() {
        return values;
    }

    public void clear(){
        map.clear();
        values.clear();
    }

    Value getValue(Reference<Value> reference){
        Value value = null;
        if (reference != null) value = reference.get();
        return value;
    }

    Reference<Value> createReference(Value value) {
        return new WeakReference<Value>(value);
    }
}
