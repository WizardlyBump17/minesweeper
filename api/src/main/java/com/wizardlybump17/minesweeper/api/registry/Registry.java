package com.wizardlybump17.minesweeper.api.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Registry<K, V> {

    private final Map<K, V> registry = getInitialMap();

    public void put(K key, V value) {
        registry.put(key, value);
    }

    public void remove(K key) {
        registry.remove(key);
    }

    public boolean has(K key) {
        return registry.containsKey(key);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(registry.get(key));
    }

    public Collection<V> getAll() {
        return registry.values();
    }

    protected Map<K, V> getInitialMap() {
        return new HashMap<>();
    }
}
