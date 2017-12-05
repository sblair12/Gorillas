package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Score {
	private int player1, player2;

	public Score() {
		this.player1 = 0;
		this.player2 = 0;
	}

	/**
	 * Obtain which Player won the point, display who got the point, and update the score for that player
	 * @param player1 True for Player 1 score, false for Player 2 score
	 */
	public void updateScore(boolean player1) {
		if (player1 == true) {
			this.player1 = this.player1 + 1;
		}
		else {
			this.player2 = this.player2 + 1;
		}
	}

	/**
	 * Get the score for Player 1
	 * @return Player 1 score
	 */
	public int getPlayer1() {
		return this.player1;
	}

	/**
	 * Get the score for Player 2
	 * @return Player 2 score
	 */
	public int getPlayer2() {
		return this.player2;
	}

	/**
	 * Redraw Player score-board on top of screen
	 */
	public void redraw() {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.07, 0.95, Integer.toString(this.player1));
		StdDraw.text(0.93, 0.95, Integer.toString(this.player2));
	}
}