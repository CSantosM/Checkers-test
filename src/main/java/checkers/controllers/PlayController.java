package checkers.controllers;

import checkers.models.Coordinate;
import checkers.models.Game;
import checkers.models.Token;
import checkers.models.Turn;

/**
 * PlayController
 */
public class PlayController {
    private Game game;

    public PlayController(Game game) {

    }

    public Error move(Coordinate origin, Coordinate target) {
        return null;

    }

    public Token getToken(Coordinate origin) {
        return null;
    }

    public Turn nextTurn() {
        return this.game.nextTurn();
    }
}