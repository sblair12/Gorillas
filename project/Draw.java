package project;

import java.awt.Color;

import cse131.ArgsProcessor;
import sedgewick.StdDraw;

public class Draw {

	private GameBoard g;
	private Score s;
	private Player p1, p2;
	private ArgsProcessor ap;

	public Draw(GameBoard g, Player p1, Player p2, Score s, String[] args) {
		this.g = g;
		this.s = s;
		this.p1 = p1;
		this.p2 = p2;
		this.ap = new ArgsProcessor(args);
	}

	/**
	 * Show start screen, get amount of points to win, and show player positions on screen
	 * @return number of points to win
	 */
	public int startScreen() {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.5, 0.5, "GORILLAS");
		StdDraw.pause(1500);
		StdDraw.clear();
		int points = -1;
		while (points <= 0) {
			points = this.ap.nextInt("How many points to win?");
		}

		redrawAll();
		StdDraw.setPenColor(Color.RED);
		StdDraw.text(0.10, 0.80, "Player 1");
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.text(0.90, 0.80, "Player 2");
		StdDraw.pause(1500);
		redrawAll();
		return points;
	}

	/**
	 * Redraw GameBoard, Players, and Score
	 */
	public void redrawAll() {
		StdDraw.clear();
		g.redraw();
		p1.redraw();
		p2.redraw();
		s.redraw();
	}

	/**
	 * Redraw GameBoard, Players, and Score in preparation for Projectile throw
	 */
	public void redrawProjectile() {
		StdDraw.show(5);
		redrawAll();
		StdDraw.show();
	}

	/**
	 * Draw Player label above respective position
	 * @param player true for Player 1, false for Player 2
	 */
	public void player(boolean player) {
		if (player == true) {
			StdDraw.setPenColor(Color.RED);
			StdDraw.text(0.10, 0.80, "Player 1");
		}
		if (player == false) {
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.text(0.90, 0.80, "Player 2");
		}
	}

	/**
	 * Animate label designating winner of round
	 * @param player true for Player 1, false for Player 2
	 */
	public void hitAnimation(boolean player) {
		for (int i = 0; i < 5; i++) {
			StdDraw.show(0);
			redrawAll();
			StdDraw.show();
			if (player == true) {
				StdDraw.setPenColor(Color.RED);
				StdDraw.text(0.5, 0.95, "Player 1 POINT");
			}
			else {
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.text(0.5, 0.95, "Player 2 POINT");
			}
			StdDraw.pause(200);
			StdDraw.show(0);
			redrawAll();
			StdDraw.show();
			StdDraw.pause(200);
		}
	}

	/**
	 * Reset GameBoard and Players objects to new ones for new round
	 * @param g new round GameBoard
	 * @param p new round Players
	 */
	public void reset(GameBoard g, Player p1, Player p2) {
		this.g = g;
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Draw Game Over screen when point limit reached
	 */
	public void endScreen(boolean player) {
		StdDraw.clear();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.5, 0.5, "GAME OVER");
		if (player == true) {
			StdDraw.setPenColor(Color.RED);
			StdDraw.text(0.5, 0.3, "Player 1 WINS");
		}
		if (player == false) {
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.text(0.5, 0.3, "Player 2 WINS");
		}
	}
}