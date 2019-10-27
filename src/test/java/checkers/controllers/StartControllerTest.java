package checkers.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.models.Game;
import checkers.models.State;
import checkers.models.StateValue;


public class StartControllerTest {

    private static Game game;
    private static State state;
    private static StartController startController;



    public StartControllerTest(){

    }

    @BeforeEach
    public void initialize() {
        game = new Game();
        startController = new StartController(game, state);
    }

    @Test
    public void isInitialDashboardRequireNotError() {

        startController.start();
        assertEquals(state.getStateValue(), StateValue.IN_GAME);

    }
}