package mghmay.minesweeper;

import java.util.*;

public class Game {
    // make move
    public static boolean makeMove(int col, int row, Field myField) {

        int[][] hiddenField = myField.getHiddenField();
        int[][] visibleField = myField.getVisibleField();

        if (visibleField[col][row] != 0) {
            System.out.print("\nIncorrect Input!");
            return true;
        }
        if (hiddenField[col][row] == 100) {
            displayHidden(myField);
            System.out.print("You stood on a mine and were blasted into a million pieces. That's what you get for stepping on minefields.");
            return false;
        } else if (hiddenField[col][row] == 0) {
            makeVisible(col, row, myField);
//            makeVisibleNeighbour(col, row, myField);
//            floodFill(col, row, myField);
        } else {
            makeVisibleNeighbour(col, row, myField);
        }
        return true;
    }
    private static void makeVisible(int col, int row, Field myField) {
        myField.setVisibleField(col, row, 50);
        int[][] hiddenField = myField.getHiddenField();
        int[][] visibleField = myField.getVisibleField();

        int height = myField.getHeight();
        int width = myField.getWidth();
//        System.out.print("Triggered");
//        for (int i = 0; i < visibleField.length; i++) {
//            System.out.println("Visible Field:" + Arrays.toString(visibleField[i]));
//        }
//        for (int i = 0; i < visibleField.length; i++) {
//            System.out.print("Hidden Field: " + Arrays.toString(hiddenField[i]));
//        }

        //display top && top left
        if (col != 0) { // if not first col
            myField.setVisibleField(col - 1, row, hiddenField[col - 1][row]);   // display top
            if (visibleField[col - 1][row] == 0) myField.setVisibleField(col - 1, row, 50); // if top null display blank
            if (row != 0) { // if not first ro
                // w && not first col
                myField.setVisibleField(col - 1, row - 1, hiddenField[col - 1][row - 1]);   // display top left
                if (visibleField[col - 1][row - 1] == 0)  myField.setVisibleField(col - 1, row - 1, 50); // if top left null display blank
            }
        }

        // display bottom && bottom right
        if (col != height - 1) { // if not last col
            myField.setVisibleField(col + 1, row,  hiddenField[col + 1][row]); // display bottom
            if (visibleField[col + 1][row] == 0) myField.setVisibleField(col + 1, row, 50); // if bottom null display blank
            if (row != myField.getWidth() - 1) { // if not last row && not last col
                myField.setVisibleField(col + 1, row + 1, hiddenField[col + 1][row + 1]); // display bottom right
                if (visibleField[col + 1][row + 1] == 0) myField.setVisibleField(col + 1, row + 1, 50); // if bottom right null display blank
            }
        }

        // display left && bottom left
        if (row != 0) { // if not first row
            myField.setVisibleField(col, row - 1, hiddenField[col][row - 1]); // display left
            if (visibleField[col][row - 1] == 0) myField.setVisibleField(col, row - 1, 50); //if left null display blank
            if (col != height - 1) { // if not last col && not first row
                myField.setVisibleField(col + 1, row - 1, hiddenField[col + 1][row - 1]); // display bottom left
                if (visibleField[col + 1][row - 1] == 0) myField.setVisibleField(col + 1, row - 1, 50); //if bottom left null display blank
            }
        }

        // display right && top right
        if (row != width - 1) { // if not last row
            myField.setVisibleField( col, row + 1, hiddenField[col][row + 1]); // display right
            if (visibleField[col][row + 1] == 0) myField.setVisibleField(col, row + 1, 50); // if right null display blank
            if (col != 0) { // if not last row && not first col
                myField.setVisibleField(col - 1, row + 1, hiddenField[col - 1][row + 1]); //display top right
                if (visibleField[col - 1][row + 1] == 0) myField.setVisibleField(col - 1, row + 1, 50); // if top right null display blank
            }
        }

    }
    public static void makeVisibleNeighbour(int col, int row, Field myField) {
        Random random = new Random();
        int rand = random.nextInt(4) ;

        int[][] hiddenField = myField.getHiddenField();
        int[][] visibleField = myField.getVisibleField();

        myField.setVisibleField(col, row, hiddenField[col][row]);

        int width = myField.getWidth() - 1;
        int height = myField.getHeight() - 1;

        if (rand == 0) {
            //checkForBombs(row, col, notEquals, payload, myField)
            if (row != 0) { // if not first row
                if (hiddenField[col][row - 1] != 100) { // if top != bomb
                    myField.setVisibleField(col, row-1, hiddenField[col][row - 1]); // display row top
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if (visibleField[col][row - 1] == 0)  myField.setVisibleField(col, row - 1, 50);
                }
            } if (col != 0) { // if not first col
                if (hiddenField[col - 1][row] != 100) { // if left != bomb
                    myField.setVisibleField(col - 1, row, hiddenField[col - 1][row]); // display col left
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if (visibleField[row - 1][col] == 0) myField.setVisibleField(col - 1, row, 50);
                }
            } if (col != 0 && row != 0) { // if not first col and not first row
                if (hiddenField[col - 1][row - 1] != 100) { // if top left != bomb
                    myField.setVisibleField(col - 1, row - 1, hiddenField[col - 1][row - 1]); // display top left
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if (visibleField[col][row - 1] == 0) myField.setVisibleField(col - 1, row - 1, 50);
                }
            }
        } else if (rand == 1) {
            if (row != 0) { // if not first row
                if (hiddenField[col][row - 1] != 100) { // if top != bomb
                    myField.setVisibleField(col, row - 1,  hiddenField[col][row - 1]); // display top
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if (visibleField[col][row - 1] == 0)  myField.setVisibleField(col, row - 1, 50);
                }
            } if (col != width) {   // if not last col
                if (hiddenField[col + 1][row] != 100) { // if right != bomb
                    myField.setVisibleField(col + 1, row, hiddenField[col + 1][row]);  // display right
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if (visibleField[col + 1][row] == 0)  myField.setVisibleField(col + 1, row, 50);
                }
            } if (row != 0 && col != width) { // if not first row and not last col
                if (hiddenField[col + 1][row - 1] != 100) { // if top right != bomb
                    myField.setVisibleField(col + 1, row - 1, hiddenField[col + 1][row - 1]); // display top right
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if (visibleField[col+1][row-1] == 0) myField.setVisibleField(col + 1,row - 1, 50);
                }
            }
        } else if (rand == 2) {
            if( row != width) { // if not last row
                if(hiddenField[col + 1][row] != 100) { // if right != bomb
                    myField.setVisibleField(col + 1, row, hiddenField[col+1][row]); // display right
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if(visibleField[col + 1][row] == 0)  myField.setVisibleField(col + 1, row, 50);
                }
            }
            if( col != height) { // if not last col
                if(hiddenField[col][row + 1] != 100) { // if bottom != bomb
                    myField.setVisibleField(col, row + 1, hiddenField[col][row + 1]); // display bottom
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if(visibleField[col][row+1]==0)  myField.setVisibleField(col, row+1, 50);
                }

            }
            if( col != width && row != height) { // if not last col && not last row
                if(hiddenField[col + 1][row + 1] != 100) { // if bottom right != bomb
                    myField.setVisibleField(col + 1, row + 1, hiddenField[col + 1][row + 1]); // display bottom right
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if(visibleField[col + 1][row + 1] == 0)  myField.setVisibleField(col + 1, row + 1, 50);
                }
            }
        } else {
            if(row != height) { // if not last row
                if(hiddenField[col + 1][row] != 100) { // if right != bomb
                    myField.setVisibleField(col + 1, row, hiddenField[col + 1][row]); // display right
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if(visibleField[col + 1][row]==0)  myField.setVisibleField(col + 1, row, 50); //
                }
            } if(col != 0) { // if not first col
                if(hiddenField[col][row - 1] != 100) { // if  top != bomb
                    myField.setVisibleField(col, row - 1, hiddenField[col][row - 1]); // display top
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if(visibleField[col][row-1]==0)  myField.setVisibleField(col, row-1, 50);
                }

            } if(col != 0 && row != height) { // if not last row and not first col
                if(hiddenField[col - 1][row + 1] != 100) { // if left bottom != bomb
                    myField.setVisibleField(col - 1, row + 1, hiddenField[col - 1][row + 1]); // display left bottom
                    // if uncovered and not bomb and not num surrounding bombs -> display blank
                    if(visibleField[col - 1][row + 1] == 0)  myField.setVisibleField(col+1, row-1, 50);
                }
            }
        }
    }
    static void floodFill(int col, int row, Field myField) {
        int height = myField.getHeight() - 1;
        int width = myField.getWidth() - 1;
        if (myField.getHiddenField()[col][row] != 100 && col > 0 && col < height && row > 0 && row < width) {
            myField.setVisibleField(col, row, myField.getHiddenField()[row][col]);
            floodFill(col, row + 1, myField);
            floodFill(col, row - 1, myField);
            floodFill(col + 1, row, myField );
            floodFill(col - 1, row, myField);
        }else {
            return;
        }
    }
    public static void displayHidden(Field myField) {
        int[][] hiddenField = myField.getHiddenField();
        int width = myField.getWidth();
        int height = myField.getHeight();

        System.out.print("\t ");
        for(int col = 0; col < width; col++)
        {
            System.out.print(" " + col + "  ");
        }
        System.out.print("\n");
        for(int row = 0; row < height; row++)
        {
            System.out.print(row + "\t| ");
            for(int col=0; col<width; col++)
            {
                if(hiddenField[row][col] == 0)
                {
                    System.out.print(" ");
                }
                else if(hiddenField[row][col] == 100)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(hiddenField[row][col]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }
    public boolean checkWin(Field myField) {
        int width = myField.getWidth();
        int height = myField.getHeight();
        int[][] visibleField = myField.getVisibleField();
        int[][] hiddenFiled = myField.getHiddenField();

        for (int row = 0; row < height; row++) {
            for (int col = 0; row < width; col++) {
                if (visibleField[row][col] == 0) {
                    if (hiddenFiled[row][col] != 100) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}