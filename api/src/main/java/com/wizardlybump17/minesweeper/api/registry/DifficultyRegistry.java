package com.wizardlybump17.minesweeper.api.registry;

import com.wizardlybump17.minesweeper.api.Difficulty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class DifficultyRegistry extends Registry<String, Difficulty> {

    @Override
    public void put(String key, Difficulty value) {
        super.put(key.toLowerCase(), value);
    }

    @Override
    public Optional<Difficulty> get(String key) {
        return super.get(key.toLowerCase());
    }

    @Override
    public boolean has(String key) {
        return super.has(key.toLowerCase());
    }

    @Override
    public void remove(String key) {
        super.remove(key.toLowerCase());
    }

    @Override
    protected Map<String, Difficulty> getInitialMap() {
        return new LinkedHashMap<>();
    }
}
