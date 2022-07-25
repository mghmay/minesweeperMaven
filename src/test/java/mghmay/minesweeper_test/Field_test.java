package mghmay.minesweeper_test;
import mghmay.minesweeper.Field;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Field_test {
    static int baseWidth = 10;
    static int baseHeight = 10;
    static double baseDifficulty = 5;
    private static Field myField;

    @BeforeAll
    static void beforeAll() {
        myField = new Field(baseDifficulty, baseHeight, baseWidth);
    }

    @Test
    public void getWidth_test() {
        int width = 10;
        int myFieldWidth = myField.getWidth();
        assertEquals(width, myFieldWidth, "The width of the field does not equal the input");
    }

    @Test
    public void getHeight_test() {
        int height = 10;
        int myFieldHeight = myField.getHeight();
        assertEquals(height, myFieldHeight, "The height of the field does not equal the input");
    }

    @Test
    public void getDifficulty_test() {
        double difficulty = 5;
        double myFieldDifficulty = myField.getDifficulty();
        assertEquals(difficulty, myFieldDifficulty, "The difficulty of the field does not equal the input");
    }

    @Test
    public void getHiddenField_test() {
        double difficulty = 5;
        double myFieldDifficulty = myField.getDifficulty();
        assertEquals(difficulty, myFieldDifficulty, "The difficulty of the field does not equal the input");
    }

    @Test
    public void getVisibleField_test() {
        double difficulty = 5;
        double myFieldDifficulty = myField.getDifficulty();
        assertEquals(difficulty, myFieldDifficulty, "The difficulty of the field does not equal the input");
    }
    // hello blah blah blah

    @Test
    public void generateField_test() {
        double difficulty = 5;
        int[][] myFieldDifficulty = myField.generateVisibleField();

        assertEquals(difficulty, myFieldDifficulty, "The difficulty of the field does not equal the input");
    }
    @AfterAll
    static void afterAll() {
        myField = null;
        assertNull(myField);
    }
}
