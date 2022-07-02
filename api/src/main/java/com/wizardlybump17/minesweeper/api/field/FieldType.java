package com.wizardlybump17.minesweeper.api.field;

import com.wizardlybump17.minesweeper.api.ClickType;
import com.wizardlybump17.minesweeper.api.Game;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public interface FieldType {

    FieldType NONE = new FieldType() {
        @Override
        public void onClick(Field field, Game game, ClickType clickType) {
            switch (clickType) {
                case LEFT_CLICK -> game.reveal(field.getX(), field.getY());
                case RIGHT_CLICK -> field.setFlag(true);
                case MIDDLE_CLICK -> field.setQuestion(true);
            }
        }

        @NotNull
        @Override
        public String getName() {
            return "NONE";
        }
    };
    FieldType MINE = new FieldType() {
        @Override
        public void onClick(Field field, Game game, ClickType clickType) {
            if (clickType == ClickType.LEFT_CLICK) {
                game.reveal(field.getX(), field.getY());
                game.gameOver();
            }
        }

        @NonNull
        @Override
        public String getName() {
            return "MINE";
        }
    };

    /**
     * Called when a field of this type is clicked
     * @param field the clicked field
     * @param game the game where the field is
     * @param clickType the click type
     */
    void onClick(Field field, Game game, ClickType clickType);

    @NotNull
    String getName();
}
