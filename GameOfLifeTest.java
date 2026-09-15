/**
 * Lightweight tests for GameOfLife.
 *
 * No JUnit setup is required. Run this file like any other Java program.
 */
public class GameOfLifeTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        testConstructorAndBasicMethods();
        testNeighborCountInMiddle();
        testNeighborCountAtEdge();
        testBlinkerUpdate();
        testOverpopulation();
        testBirth();
        testSurvival();
        testIsolation();
        testStablePatterns();
        testChangingPatterns();
        testNoNeighbors();
        testSeveralNeighbors();
        testEightNeighbors();
        testNoWrapAround();

        System.out.println();
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        if (failed == 0) {
            System.out.println("All starter tests passed!");
        }
        else {
            System.out.println("Keep going - failing tests show what still needs work.");
        }
    }

    private static void testConstructorAndBasicMethods() {
        GameOfLife game = new GameOfLife(5, 8);

        check("constructor: correct row count",
                game.numberOfRows() == 5);

        check("constructor: correct column count",
                game.numberOfColumns() == 8);

        check("new board starts empty",
                !game.cellAt(2, 3));

        game.growCellAt(2, 3);
        check("growCellAt makes a cell alive",
                game.cellAt(2, 3));

        game.killCellAt(2, 3);
        check("killCellAt makes a cell dead",
                !game.cellAt(2, 3));
    }

    private static void testNeighborCountInMiddle() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(1, 1);
        game.growCellAt(1, 2);
        game.growCellAt(2, 1);

        check("neighborCount: middle cell counts 3 neighbors",
                game.neighborCount(2, 2) == 3);
    }

    private static void testNeighborCountAtEdge() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(0, 1);
        game.growCellAt(1, 0);
        game.growCellAt(1, 1);

        check("neighborCount: corner counts only in-bounds neighbors",
                game.neighborCount(0, 0) == 3);
    }

    private static void testOverpopulation() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(2, 2);
        game.growCellAt(1, 1);
        game.growCellAt(1, 2);
        game.growCellAt(1, 3);
        game.growCellAt(2, 1);

        game.update();

        check("update: live cell with 4 neighbors dies",
                !game.cellAt(2, 2));
    }

    private static void testBirth() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(1, 1);
        game.growCellAt(1, 2);
        game.growCellAt(1, 3);

        game.update();

        check("update: dead cell with 3 neighbors is born",
                game.cellAt(2, 2));
    }

    private static void testSurvival() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(2, 2);
        game.growCellAt(1, 1);
        game.growCellAt(1, 2);

        game.update();

        check("update: live cell with 2 neighbors survives",
                game.cellAt(2, 2));
    }

    private static void testIsolation() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(2, 2);

        game.update();

        check("update: live cell with 0 neighbors dies",
                !game.cellAt(2, 2));
    }

    private static void testStablePatterns() {
        GameOfLife game = new GameOfLife(5, 5);

        // Block pattern (2x2 square)
        game.growCellAt(1, 1);
        game.growCellAt(1, 2);
        game.growCellAt(2, 1);
        game.growCellAt(2, 2);

        game.update();

        check("update: stable block pattern remains unchanged",
                game.cellAt(1, 1) && game.cellAt(1, 2) &&
                game.cellAt(2, 1) && game.cellAt(2, 2));
    }

    private static void testChangingPatterns() {
        GameOfLife game = new GameOfLife(5, 5);

        // Blinker pattern (horizontal line of 3)
        game.growCellAt(2, 1);
        game.growCellAt(2, 2);
        game.growCellAt(2, 3);

        game.update();

        check("update: blinker pattern changes orientation",
                game.cellAt(1, 2) && game.cellAt(2, 2) && game.cellAt(3, 2) &&
                !game.cellAt(2, 1) && !game.cellAt(2, 3));
    }

    private static void testNoNeighbors() {
        GameOfLife game = new GameOfLife(5, 5);

        check("neighborCount: cell with no neighbors returns 0",
                game.neighborCount(2, 2) == 0);
    }

    private static void testSeveralNeighbors() {
        GameOfLife game = new GameOfLife(5, 5);

        game.growCellAt(1, 1);
        game.growCellAt(1, 2);
        game.growCellAt(2, 1);
        game.growCellAt(2, 3);
        game.growCellAt(3, 2);

        check("neighborCount: cell with several neighbors returns correct count",
                game.neighborCount(2, 2) == 5);
    }

    private static void testEightNeighbors() {
        GameOfLife game = new GameOfLife(5, 5);

        // Fill all neighbors around (2, 2)
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) continue; // Skip the center cell
                game.growCellAt(i, j);
            }
        }

        check("neighborCount: cell with 8 neighbors returns 8",
                game.neighborCount(2, 2) == 8);
    }

    private static void testNoWrapAround() {
        GameOfLife game = new GameOfLife(5, 5);

        // This cell is diagonally opposite (0,0).
        // It must NOT count as a neighbor.
        game.growCellAt(4, 4);

        check("neighborCount: board does not wrap around",
                game.neighborCount(0, 0) == 0);
    }

    private static void testBlinkerUpdate() {
        GameOfLife game = new GameOfLife(5, 5);

        // Horizontal blinker
        game.growCellAt(2, 1);
        game.growCellAt(2, 2);
        game.growCellAt(2, 3);

        game.update();

        boolean correct =
                game.cellAt(1, 2) &&
                game.cellAt(2, 2) &&
                game.cellAt(3, 2) &&
                !game.cellAt(2, 1) &&
                !game.cellAt(2, 3);

        check("update: horizontal blinker becomes vertical",
                correct);
    }

    private static void check(String testName, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + testName);
        }
        else {
            failed++;
            System.out.println("FAIL: " + testName);
        }
    }
}
