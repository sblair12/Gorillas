package project;

import java.awt.Color;

import cse131.ArgsProcessor;
import sedgewick.StdDraw;

public class Initializer {
	private int points;
	private double[] heights;
	private ArgsProcessor ap;
	private GameBoard g;
	private Score s;
	private Player p1, p2;
	private Draw d;

	public Initializer(String[] args) {
		this.ap = new ArgsProcessor(args);
		this.g = new GameBoard();
		this.heights = this.g.getStartHeights();
		this.p1 = new Player(0.05, this.heights[0] + 0.01, Color.RED);
		this.p2 = new Player(0.95, this.heights[1] + 0.01, Color.BLUE);
		this.s = new Score();
		this.d = new Draw(g, p1, p2, s, args);
		StdDraw.clear();
		this.points = d.startScreen();
	}

	/**
	 * Start Projectile throw based on Player throwing and push Projectile object and Player indicator to the throw loop
	 * @param player true for Player 1, false for Player 2
	 * @return indicator for if a player reaches point limit, received by throwLoop
	 */
	public int startThrow(boolean player) {
		double angle = 360;
		double velocity = -1;
		int result = 1;
		this.d.redrawAll();
		if (player == true) {
			this.d.player(true);
			while (angle > 90 || angle < -90) {
				angle = this.ap.nextDouble("Player 1: Angle");
			}
			while (velocity < 0) {
				velocity = this.ap.nextDouble("Player 1: Velocity");
			}
			Projectile r = new Projectile(angle, velocity);
			result = throwLoop(r, 0.07, this.heights[0] + 0.02, true);
		}
		if (player == false) {
			this.d.player(false);
			while (angle > 90 || angle < -90) {
				angle = this.ap.nextDouble("Player 2: Angle");
			}
			while (velocity < 0) {
				velocity = this.ap.nextDouble("Player 2: Velocity");
			}
			Projectile r = new Projectile(angle, velocity);
			result = throwLoop(r, 0.93, this.heights[1] + 0.02, false);
		}
		return result;
	}

	/**
	 * Loop the thrown Projectile based on Player throwing until it hits the GameBoard or the other Player
	 * @param r Projectile created by startThrow
	 * @param initX initial x-coordinate for Projectile
	 * @param player true for Player 1, false for Player 2
	 * @return indicator for if a player reaches point limit (1 if not yet reached, 0 if reached)
	 */
	public int throwLoop(Projectile r, double initX, double initY, boolean player) {
		int turn = 1;
		while (turn == 1) {
			double [] coord = new double [2];
			if (player == true) {
				coord = r.throwP(true, initX, initY);
			}
			if (player == false) {
				coord = r.throwP(false, initX, initY);
			}
			initX = coord[0];
			initY = coord[1];
			this.d.redrawProjectile();
			boolean collisionP1 = this.p1.checkCollision(initX, initY, p2);
			boolean collisionP2 = this.p2.checkCollision(initX, initY, p1);
			boolean collisionG = this.g.checkCollision(initX, initY);

			if (collisionG == true) {
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.text(0.5, 0.95, "Miss!");
				turn = 2;
				StdDraw.pause(1000);
			}
			if (collisionP1 == true || collisionP2 == true) {
				if (player == true) {
					this.s.updateScore(true);
					this.d.hitAnimation(true);
				}
				if (player == false) {
					this.s.updateScore(false);
					this.d.hitAnimation(false);
				}
				turn = 2;
				if (this.s.getPlayer1() != this.points && this.s.getPlayer2() != this.points) {
					this.g = new GameBoard();
					this.heights = g.getStartHeights();
					this.p1 = new Player(0.05, this.heights[0] + 0.01, Color.RED);
					this.p2 = new Player(0.95, this.heights[1] + 0.01, Color.BLUE);
					this.d.reset(g, p1, p2);
				}
			}
			if (this.s.getPlayer1() == this.points || this.s.getPlayer2() == this.points) {
				return 0;
			}
		}
		return 1;
	}

	/**
	 * Call to Draw to show Game Over screen
	 */
	public void endScreen() {
		if (this.s.getPlayer1() == this.points) {
			this.d.endScreen(true);
		}
		if (this.s.getPlayer2() == this.points) {
			this.d.endScreen(false);
		}
	}
}