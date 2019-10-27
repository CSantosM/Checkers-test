package checkers.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkers.models.Game;
import checkers.models.State;
import checkers.models.StateValue;

public class ResumeControllerTest {

    private static Game game;
    private static State state;
    private static ResumeController resumeController;

    public ResumeControllerTest() {

    }

    @BeforeEach
    public void initialize() {
        game = new Game();
        resumeController = new ResumeController(game, state);
    }

    @Test
    public void changeStateToFinalRequireNotError() {
        resumeController.resume(false);
        assertEquals(state.getStateValue(), StateValue.FINAL);

    }

    @Test
    public void changeStateToInitialRequireNotError() {
        resumeController.resume(true);
        assertEquals(state.getStateValue(), StateValue.INITIAL);
    }
}