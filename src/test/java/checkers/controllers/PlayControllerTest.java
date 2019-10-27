package checkers.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.models.Color;
import checkers.models.Coordinate;
import checkers.models.Game;
import checkers.models.Token;

public class PlayControllerTest {

    private static Game game;
    private static PlayController playController;

    private static Error moveToken(int origin_i, int origin_j, int target_i, int target_j) {
        Coordinate origin = new Coordinate(origin_i, origin_j);
        Coordinate target = new Coordinate(target_i, target_j);
        return playController.move(origin, target);
    }

    @BeforeEach
    public void createGame() {
        game = new Game();
        playController = new PlayController(game);
    }

    @Test
    public void givenPlayControllerWhenMovementRequireCorrectThenNotError() {
        Coordinate origin = new Coordinate(2, 0);
        Coordinate target = new Coordinate(3, 1);
        assertNull(playController.move(origin, target));
        assertNull(playController.getToken(origin));
        Token tokenTarget = playController.getToken(target);
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
        assertNull(playController.getToken(new Coordinate(2, 2)));

        Token whiteToken = playController.getToken(new Coordinate(3, 3));
        assertNotNull(whiteToken);
        assertEquals(whiteToken.getColor(), Color.WHITE);

        assertEquals(playController.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(5, 1, 4, 2));

        assertNull(playController.getToken(new Coordinate(5, 1)));
        Token blackToken = playController.getToken(new Coordinate(4, 2));
        assertNotNull(blackToken);
        assertEquals(blackToken.getColor(), Color.BLACK);

        assertEquals(playController.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(3, 3, 5, 1));
        assertNull(playController.getToken(new Coordinate(3, 3)));
        whiteToken = playController.getToken(new Coordinate(5, 1));
        assertNotNull(whiteToken);
        assertEquals(whiteToken.getColor(), Color.WHITE);
        assertNull(playController.getToken(new Coordinate(4, 2)));
    }

    @Test
    public void changeToLadyRequireNotError() {
        // Wrong way, must not use test in other test
        this.eatTokenInMovementRequireNotError();

        assertEquals(playController.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(5, 3, 4, 2));
        assertEquals(playController.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(2, 0, 3, 1));
        assertEquals(playController.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(6, 2, 5, 3));
        assertEquals(playController.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(1, 1, 2, 0));
        assertEquals(playController.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(7, 3, 6, 2));
        assertEquals(playController.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(5, 1, 7, 3)); // CHANGE TO LADY
        assertNull(playController.getToken(new Coordinate(6, 2)));
        assertEquals(playController.nextTurn().getColor(), Color.BLACK);
        assertNull(moveToken(4, 2, 3, 3));
        assertEquals(playController.nextTurn().getColor(), Color.WHITE);
        assertNull(moveToken(7, 3, 4, 0)); // lONG MOVEMENT
    }

}