package mghmay.minesweeper;

import java.sql.SQLOutput;
import java.util.Arrays;

public class MineSweeper {
    private int hwGreaterThan;
    private int hwLessThan;
    private int diffLessThan;
    private int diffGreaterThan;

    public MineSweeper() {}

    public static void main(String[] args) {
        MineSweeper newMS = new MineSweeper();
        newMS.startGame();
    }

    public int getHwGreaterThan() {
        return this.hwGreaterThan;
    }
    public void setHwGreaterThan(int hwGreaterThan) {
        this.hwGreaterThan = hwGreaterThan;
    }
    public int getHwLessThan() {
        return this.hwLessThan;
    }
    public void setHwLessThan(int hwLessThan) {
        this.hwLessThan = hwLessThan;
    }
    public int getDiffLessThan() {
        return diffLessThan;
    }
    public void setDiffLessThan(int diffLessThan) {
        this.diffLessThan = diffLessThan;
    }
    public int getDiffGreaterThan() {
        return diffGreaterThan;
    }
    public void setDiffGreaterThan(int diffGreaterThan) {
        this.diffGreaterThan = diffGreaterThan;
    }

    public void startGame() {
        setHwGreaterThan(10);
        setHwLessThan(50);
        setDiffGreaterThan(1);
        setDiffLessThan(10);

        System.out.println("Welcome to Minesweeper \n");

        System.out.println("Pick a grid height");
        int height = Display.validateInteger(getHwGreaterThan(), getHwLessThan());

        System.out.println("Pick a grid width");
        int width = Display.validateInteger(getHwGreaterThan(), getHwLessThan());

        System.out.println("Pick a difficulty");
        int difficulty = Display.validateInteger(getDiffGreaterThan(), getDiffLessThan());

        Game myGame = new Game();
        Field myField = new Field(difficulty, height, width);


        boolean playing = true;
        while (playing) {
            myField.generateVisibleField();

            System.out.println("Row");
            int row = Display.validateInteger(0, myField.getHeight() - 1);

            System.out.println("Column");
            int col = Display.validateInteger(0, myField.getWidth() - 1);



            playing = myGame.makeMove(row, col, myField);
            if (myGame.checkWin(myField)) {
                myGame.displayHidden(myField);
                System.out.print("You win!");
            }
        }
    }
}