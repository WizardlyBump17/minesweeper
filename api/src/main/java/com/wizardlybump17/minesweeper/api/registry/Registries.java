package com.wizardlybump17.minesweeper.api.registry;

import com.wizardlybump17.minesweeper.api.Difficulty;
import com.wizardlybump17.minesweeper.api.field.FieldType;

public final class Registries {

    public static final DifficultyRegistry DIFFICULTY = new DifficultyRegistry();
    public static final FieldTypeRegistry FIELD_TYPE = new FieldTypeRegistry();

    private Registries() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    static {
        DIFFICULTY.put("easy", Difficulty.EASY);
        DIFFICULTY.put("normal", Difficulty.NORMAL);
        DIFFICULTY.put("hard", Difficulty.HARD);
        DIFFICULTY.put("supreme", Difficulty.SUPREME);

        FIELD_TYPE.put("none", FieldType.NONE);
        FIELD_TYPE.put("mine", FieldType.MINE);
    }
}
