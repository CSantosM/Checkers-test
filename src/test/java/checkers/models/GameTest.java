package checkers.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private static Game game;

    public GameTest() {
        game = new Game();
    }

    @BeforeEach
    public void newGame() {

    }

    private static Error moveToken(int origin_i, int origin_j, int target_i, int target_j) {
        Coordinate origin = new Coordinate(origin_i, origin_j);
        Coordinate target = new Coordinate(target_i, target_j);
        return game.move(origin, target);
    }

    @Test
    public void isWhiteTurnRequireNotError() {
        assertEquals(game.getTurn().getColor(), Color.WHITE);
    }

    @Test
    public void whiteTokenIsInFirstPositionRequireNotError() {
        Coordinate origin = new Coordinate(0, 0);
        Token token = game.getToken(origin);
        assertNotNull(token);
        assertEquals(token.getColor(), Color.WHITE);
    }

    @Test
    public void nonWhiteTokenIsInLastPositionRequireNotError() {
        Coordinate origin = new Coordinate(0, 7);
        assertNull(game.getToken(origin));
    }

    @Test
    public void blackTokenIsInFirstPositionRequireNotError() {
        Coordinate origin = new Coordinate(7, 7);
        Token token = game.getToken(origin);
        assertNotNull(token);
        assertEquals(token.getColor(), Color.BLACK);
    }

    @Test
    public void nonBlackTokenIsInLastPositionRequireNotError() {
        Coordinate origin = new Coordinate(7, 0);
        assertNull(game.getToken(origin));
    }

    @Test
    public void givenPlayControllerWhenMovementRequireCorrectThenNotError() {
        Coordinate origin = new Coordinate(2, 0);
        Coordinate target = new Coordinate(3, 1);
        assertNull(game.move(origin, target));
        assertNull(game.getToken(origin));
        Token tokenTarget = game.getToken(target);
        assertNotNull(tokenTarget);
        assertEquals(tokenTarget.getColor(), Color.WHITE);
    }

    @Test
    public void moveTokenToSamePositionRequireError() {
        assertNotNull(moveToken(2, 0, 2, 0));
    }

    @Test
    public void moveTokenWithHorizontalMovementRequireError() {
        assertNotNull(moveToken(2, 0, 2, 1));
    }

    @Test
    public void moveTokenWithVerticalMovementRequireError() {
        assertNotNull(moveToken(2, 0, 3, 0));
    }

    @Test
    public void moveTokenWithBackMovementRequireError() {
        assertNull(moveToken(2, 0, 3, 1));
        assertNotNull(moveToken(3, 1, 2, 0));
    }

    @Test
    public void moveWrongTokenRequireError() {
        assertNotNull(moveToken(5, 1, 4, 0));
    }

    @Test
    public void moveTokenToWrongCoordinateRequireError() {
        assertNotNull(moveToken(2, 0, 3, 12));
    }

    @Test
    public void moveDoubleSquareRequireError() {
        assertNotNull(moveToken(2, 2, 4, 4));
    }

    @Test
    public void eatTokenInMovementRequireNotError() {
        assertNull(moveToken(2, 2, 3, 3));
        assertNull(game.getToken(new Coordinate(2, 2)));

        Token whiteToken = game.getToken(new Coordinate(3, 3));
        assertNotNull(whiteToken);
        assertEquals(whiteToken.getColor(), Color.WHITE);

        assertEquals(game.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(5, 1, 4, 2));

        assertNull(game.getToken(new Coordinate(5, 1)));
        Token blackToken = game.getToken(new Coordinate(4, 2));
        assertNotNull(blackToken);
        assertEquals(blackToken.getColor(), Color.BLACK);

        assertEquals(game.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(3, 3, 5, 1));
        assertNull(game.getToken(new Coordinate(3, 3)));
        whiteToken = game.getToken(new Coordinate(5, 1));
        assertNotNull(whiteToken);
        assertEquals(whiteToken.getColor(), Color.WHITE);
        assertNull(game.getToken(new Coordinate(4, 2)));
    }

    @Test
    public void changeToLadyRequireNotError() {
        // Wrong way, must not use test in other test
        this.eatTokenInMovementRequireNotError();

        assertEquals(game.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(5, 3, 4, 2));
        assertEquals(game.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(2, 0, 3, 1));
        assertEquals(game.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(6, 2, 5, 3));
        assertEquals(game.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(1, 1, 2, 0));
        assertEquals(game.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(7, 3, 6, 2));
        assertEquals(game.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(5, 1, 7, 3)); // CHANGE TO LADY
        assertNull(game.getToken(new Coordinate(6, 2)));
        assertEquals(game.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(4, 2, 3, 3));
        assertEquals(game.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(7, 3, 4, 0)); // lONG MOVEMENT
    }

}