package com.wizardlybump17.minesweeper.api;

import com.wizardlybump17.minesweeper.api.field.Field;
import com.wizardlybump17.minesweeper.api.field.FieldType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Game {

    private final int width;
    private final int height;
    private final Field[][] fields;
    private final Difficulty difficulty;
    private boolean gameOver;
    private final int mines;

    public Game(int xSize, int ySize, Difficulty difficulty) {
        fields = new Field[width = xSize][height = ySize];
        this.difficulty = difficulty;
        mines = setupFields();
        setupNearbyMines();
    }

    private int setupFields() {
        int mines = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.random() > difficulty.getMineChance()) {
                    fields[x][y] = new Field(FieldType.NONE, x, y);
                    continue;
                }

                fields[x][y] = new Field(FieldType.MINE, x, y);
                mines++;
            }
        }

        if (mines < difficulty.getMinMines(this))
            return setupFields();

        return mines;
    }

    private void setupNearbyMines() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Field field = fields[x][y];
                if (field.getType() == FieldType.MINE)
                    addNearbyMines(x, y);
            }
        }
    }

    private void addNearbyMines(int x, int y) {
        if (x + 1 < width)
            fields[x + 1][y].addNearbyMines(1);
        if (x - 1 >= 0)
            fields[x - 1][y].addNearbyMines(1);
        if (y + 1 < height)
            fields[x][y + 1].addNearbyMines(1);
        if (y - 1 >= 0)
            fields[x][y - 1].addNearbyMines(1);
        if (x + 1 < width && y + 1 < height)
            fields[x + 1][y + 1].addNearbyMines(1);
        if (x + 1 < width && y - 1 >= 0)
            fields[x + 1][y - 1].addNearbyMines(1);
        if (x - 1 >= 0 && y + 1 < height)
            fields[x - 1][y + 1].addNearbyMines(1);
        if (x - 1 >= 0 && y - 1 >= 0)
            fields[x - 1][y - 1].addNearbyMines(1);
    }

    public void click(int x, int y, ClickType type) {
        if (gameOver)
            return;

        checkBounds(x, y);

        Field field = fields[x][y];
        field.getType().onClick(field, this, type);

        if (isGameWon())
            System.out.println("You Won!!!");
    }

    public void gameOver() {
        gameOver = true;
        System.out.println("Game Over!");
    }

    public void reveal(int x, int y) {
        if (gameOver)
            return;

        checkBounds(x, y);
        Field field = fields[x][y];

        if (field.isRevealed())
            return;

        field.setRevealed(true);
        if (field.hasMinesNearby() || field.getType() == FieldType.MINE)
            return;

        revealNeighbors(x, y);
    }

    private void revealNeighbors(int x, int y) {
        if (x + 1 < width)
            reveal(x + 1, y);
        if (x - 1 >= 0)
            reveal(x - 1, y);

        if (y + 1 < height)
            reveal(x, y + 1);
        if (y - 1 >= 0)
            reveal(x, y - 1);
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= fields.length || y < 0 || y >= fields[x].length)
            throw new ArrayIndexOutOfBoundsException("Invalid x or y");
    }

    @NotNull
    public Field getField(int x, int y) {
        checkBounds(x, y);
        return fields[x][y];
    }

    public boolean isGameWon() {
        int nonRevealedMines = 0;

        for (Field[] fields : fields)
            for (Field field : fields)
                if (!field.isRevealed())
                    nonRevealedMines++;

        return nonRevealedMines == mines;
    }
}
