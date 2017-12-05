package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Player implements DrawableObject {
	private double x, y;
	private Color color;

	public Player(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		StdDraw.setPenColor(this.color);
		StdDraw.filledSquare(this.x, this.y, 0.01);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	/**
	 * Redraw player scores
	 */
	public void redraw() {
		StdDraw.setPenColor(this.color);
		StdDraw.filledSquare(this.x, this.y, 0.01);
	}

	/**
	 * Take in x-coordinate and y-coordinate of projectile and determine if it has collided with the Player or not
	 * @param x x-coordinate of projectile
	 * @param y y-coordinate of projectile
	 * @return True for collision, false for no collision
	 */
	public boolean checkCollision(double x, double y, Player p) {
		if ((x >= (p.getX() - 0.01)) && (x <= (p.getX() + 0.01)) && (y >= (p.getY() - 0.01)) && (y <= (p.getY() + 0.01))) {
			return true;
		}
		else {
			return false;
		}
	}
}