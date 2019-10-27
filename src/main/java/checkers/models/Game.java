package checkers.models;



/**
 * Game
 */
public class Game {

	private Turn turn;
	private Dashboard dashboard;

    public Game(){

    }

	public Token getToken(Coordinate coordinate) {
		return this.dashboard.getTokenInPosition(coordinate);
	}

	public Turn getTurn() {
		return turn;
	}

	public Error move(Coordinate origin, Coordinate target) {
		return null;
	}

	public Turn nextTurn() {
		return null;
	}


}