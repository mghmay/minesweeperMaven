package mghmay.minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Field {
    private final double difficultyModifier = 0.02;
    private final int width;
    private final int height;
    private final double difficulty;
    // 100 = bomb
    // 50 = blank space (visible)
    // 1 to 8 = numSurroundingBombs
    // 0 = null (not-visible)
    private final int[][] hiddenField;
    private final int[][] visibleField;

    public Field(double difficulty, int height, int width) {
        this.difficulty = difficulty;
        this.height = height;
        this.width = width;
        this.hiddenField = new int[this.height][this.width];
        this.visibleField = new int[this.height][this.width];

        generateHiddenField(this.height, this.width);
    }

    public double getDifficultyModifier() {
        return difficultyModifier;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int[][] getVisibleField() {
        return this.visibleField;
    }
    public void setVisibleField(int col, int row, int payload) {
        this.visibleField[col][row] = payload;
    }
    public int[][] getHiddenField() {
        return this.hiddenField;
    }

    private int[][] generateHiddenField(int height, int width) {
        int[][] hiddenField = new int[height][width];
        int numOfMines = generateNumOfMines();
        System.out.println(numOfMines);
        while (numOfMines > 0) {
            Random random = new Random();
            int row = random.nextInt(this.width);
            int col = random.nextInt(this.height);
//            System.out.print("x: " + x + " y: " + y);
            this.hiddenField[col][row] = 100;
            numOfMines--;
        }
        generateFieldNumbers();
        return hiddenField;
    }
    private void generateFieldNumbers() {
        for (int col = 0; col < this.height; col++) {
            for (int row = 0; row < this.width; row++) {
                int mineCount = 0;
                if(this.hiddenField[col][row] != 100) {
                    if (col != 0) {
                        if (this.hiddenField[col - 1][row] == 100) mineCount++;
                        if (row != 0) {
                            if (this.hiddenField[col - 1][row - 1] == 100) mineCount++;
                        }
                    }
                    if (col != this.height - 1) {
                        if (this.hiddenField[col + 1][row] == 100) mineCount++;
                        if (row != this.width - 1) {
                            if (this.hiddenField[col + 1][row + 1] == 100) mineCount++;
                        }
                    }
                    if (row != 0) {
                        if (this.hiddenField[col][row - 1] == 100) mineCount++;
                        if (col != this.height - 1) {
                            if (this.hiddenField[col + 1][row - 1] == 100) mineCount++;
                        }
                    }
                    if (row != this.width - 1) {
                        if (this.hiddenField[col][row + 1] == 100) mineCount++;
                        if (col != 0) {
                            if (this.hiddenField[col - 1][row + 1] == 100) mineCount++;
                        }
                    }
                    this.hiddenField[col][row] = mineCount;
                }
            }
        }
    }
    private int generateNumOfMines() {
        int area = this.height * this.width;
        double numOfMines = area * (this.difficulty * this.difficultyModifier);

        return (int) numOfMines;
    }
    public int[][] generateVisibleField() {
        System.out.println("\t ");
        for (int row = 0; row < this.width; row++) {
            if (row == 0) {
                System.out.print("    ");
            }
            System.out.print("  " + row + " ");
        }
        System.out.print("\n");
        for (int col = 0; col < this.height; col++) {
            System.out.print(col + "\t| ");
            for (int row = 0; row < this.width; row++) {
                if (this.visibleField[col][row] == 0) {
                    System.out.print("#");
                } else if (this.visibleField[col][row] == 50) {
                    System.out.print(" ");
                } else {
                    System.out.print((this.visibleField[col][row]));
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
//        for (int i = 0; i < this.height; i++) {
//            System.out.print(Arrays.toString(this.visibleField[i]));
//        }
        return this.visibleField;
    }
}

