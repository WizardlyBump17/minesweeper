package com.wizardlybump17.minesweeper;

import com.wizardlybump17.minesweeper.api.ClickType;
import com.wizardlybump17.minesweeper.api.Difficulty;
import com.wizardlybump17.minesweeper.api.Game;
import com.wizardlybump17.minesweeper.api.field.Field;
import com.wizardlybump17.minesweeper.api.field.FieldType;
import com.wizardlybump17.minesweeper.api.registry.Registries;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose a difficulty (" + Registries.DIFFICULTY.getAll().stream().map(Difficulty::getName).collect(Collectors.joining(", ")) + " or a custom)");

            Difficulty difficulty = askDifficulty(scanner);

            System.out.println("Type the height and width separated by comma (10,10). If it does not have comma, the height and width will be the same.");

            int[] size = askSize(scanner);
            Game game = new Game(size[0], size[1], difficulty);
            System.out.println("Game started! To reveal a mine, type the mine location separated by comma (10,10).");
            printGame(game);

            gameLogic(game, scanner, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Difficulty askDifficulty(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("stop"))
                throw new RuntimeException("stop");

            Optional<Difficulty> optional = Registries.DIFFICULTY.get(line);
            if (optional.isPresent())
                return optional.get();

            try {
                double chance = Double.parseDouble(line);
                if (chance <= 0) {
                    System.out.println("Invalid difficulty. Try again.");
                    continue;
                }

                return Difficulty.custom(chance);
            } catch (NumberFormatException e) {
                System.out.println("Invalid difficulty. Try again.");
            }
        }
    }

    private static int[] askSize(Scanner scanner) {
        while (true) {
            try {
                int height;
                int width;

                String line = scanner.nextLine();

                if (line.equalsIgnoreCase("stop"))
                    throw new RuntimeException("stop");

                String[] data = line.split(",");
                if (data.length == 1)
                    height = width = Integer.parseInt(line);
                else {
                    height = Integer.parseInt(data[0].trim());
                    width = Integer.parseInt(data[1].trim());
                }

                if (height < 2) {
                    System.out.println("Invalid height. Min height is 2.");
                    continue;
                }

                if (width < 2) {
                    System.out.println("Invalid width. Min width is 2.");
                    continue;
                }

                return new int[]{height, width};
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers. Try again.");
            }
        }
    }

    private static void gameLogic(Game game, Scanner scanner, String[] args) {
        while (scanner.hasNextLine()) {
            int x;
            int y;
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("stop")) {
                System.out.println("Game Over!");
                main(args);
                return;
            }

            String[] data = line.split(",");
            if (data.length == 1) {
                System.out.println("Invalid location.");
                continue;
            }

            try {
                x = Integer.parseInt(data[0].trim());
                y = Integer.parseInt(data[1].trim());

                game.click(x, y, ClickType.LEFT_CLICK);
                printGame(game);

                if (game.isGameOver() || game.isGameWon()) {
                    main(args);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid location.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printGame(Game game) {
        Field[][] fields = game.getFields();
        for (Field[] value : fields) {
            System.out.print("|");
            for (int y = 0; y < fields.length; y++) {
                Field field = value[y];
                String string = field.isRevealed() ? " " : "x";

                if (field.isQuestion())
                    string = "?";
                else if (field.isFlag())
                    string = "!";
                else if (field.hasMinesNearby() && field.getType() == FieldType.NONE && field.isRevealed())
                    string = String.valueOf(field.getNearbyMines());
                else if (field.getType() == FieldType.MINE && !field.isQuestion() && !field.isFlag() && field.isRevealed())
                    string = "*";

                System.out.print(string + "|");
            }
            System.out.println();
        }
    }
}
