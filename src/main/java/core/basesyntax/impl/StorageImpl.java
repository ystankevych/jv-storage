package core.basesyntax.impl;

import core.basesyntax.Pair;
import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int STORAGE_CAPACITY = 10;
    private final Pair<K, V>[] pairs;
    private int index;

    public StorageImpl() {
        pairs = new Pair[STORAGE_CAPACITY];
        index = 0;
    }

    @Override
    public void put(K key, V value) {
        if (canBeAdded()) {
            int keyIndex = getKeyIndex(key);
            if (keyIndex >= 0) {
                pairs[keyIndex].setValue(value);
            } else {
                pairs[index++] = new Pair<>(key, value);
            }
        }
    }

    @Override
    public V get(K key) {
        int keyIndex = getKeyIndex(key);
        return keyIndex >= 0 ? pairs[keyIndex].getValue() :
                null;
    }

    @Override
    public int size() {
        return index;
    }

    private int getKeyIndex(K key) {
        for (int i = 0; i < size(); i++) {
            K current = pairs[i].getKey();
            if ((current != null && current.equals(key))
                    || (current == null && key == null)) {
                return i;
            }
        }
        return -1;
    }

    private boolean canBeAdded() {
        return size() < STORAGE_CAPACITY;
    }
}
