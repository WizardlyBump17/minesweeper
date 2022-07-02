package com.wizardlybump17.minesweeper.api.registry;

import com.wizardlybump17.minesweeper.api.field.FieldType;

import java.util.Optional;

public class FieldTypeRegistry extends Registry<String, FieldType> {

    @Override
    public void put(String key, FieldType value) {
        super.put(key.toLowerCase(), value);
    }

    @Override
    public void remove(String key) {
        super.remove(key.toLowerCase());
    }

    @Override
    public boolean has(String key) {
        return super.has(key.toLowerCase());
    }

    @Override
    public Optional<FieldType> get(String key) {
        return super.get(key.toLowerCase());
    }
}
