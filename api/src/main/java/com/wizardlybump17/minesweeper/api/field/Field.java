package com.wizardlybump17.minesweeper.api.field;

import lombok.Data;

@Data
public class Field {

    private final FieldType type;
    private final int x;
    private final int y;
    private boolean revealed;
    private boolean question;
    private boolean flag;
    private int nearbyMines;

    public void addNearbyMines(int mines) {
        nearbyMines += mines;
    }

    public boolean hasMinesNearby() {
        return nearbyMines > 0;
    }
}
