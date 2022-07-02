package com.wizardlybump17.minesweeper.api;

public interface Difficulty {

    Difficulty EASY = new Difficulty() {
        @Override
        public double getMineChance() {
            return 0.2;
        }

        @Override
        public String getName() {
            return "EASY";
        }

        @Override
        public int getMinMines(Game game) {
            return game.getWidth();
        }
    };
    Difficulty NORMAL = new Difficulty() {
        @Override
        public double getMineChance() {
            return 0.3;
        }

        @Override
        public String getName() {
            return "NORMAL";
        }

        @Override
        public int getMinMines(Game game) {
            return game.getWidth();
        }
    };
    Difficulty HARD = new Difficulty() {
        @Override
        public double getMineChance() {
            return 0.4;
        }

        @Override
        public String getName() {
            return "HARD";
        }

        @Override
        public int getMinMines(Game game) {
            return game.getWidth();
        }
    };
    Difficulty SUPREME = new Difficulty() {
        @Override
        public double getMineChance() {
            return 0.5;
        }

        @Override
        public String getName() {
            return "SUPREME";
        }

        @Override
        public int getMinMines(Game game) {
            return game.getWidth();
        }
    };

    double getMineChance();

    String getName();

    int getMinMines(Game game);

    static Difficulty custom(double chance) {
        return new Difficulty() {
            @Override
            public double getMineChance() {
                return chance;
            }

            @Override
            public String getName() {
                return "CUSTOM";
            }

            @Override
            public int getMinMines(Game game) {
                return game.getWidth();
            }
        };
    }
}
