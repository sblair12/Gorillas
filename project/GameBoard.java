package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class GameBoard implements DrawableObject {
	private double [] height;
	private int [] colors;
	private Color [] palette;

	public GameBoard() {
		double [] height = new double [5];
		int [] colors = new int [5];
		this.height = height;
		this.colors = colors;
		Color [] palette = {StdDraw.BLACK, StdDraw.CYAN, StdDraw.GRAY, StdDraw.GREEN, StdDraw.ORANGE};
		this.palette = palette;
		for (int i = 0; i < this.height.length; i++) {
			this.height[i] = Math.random()*0.75;
			this.colors[i] = (int)(Math.random() * 5);
			StdDraw.setPenColor(this.palette[this.colors[i]]);
			StdDraw.filledRectangle((i * 0.2) + 0.1, this.height[i]/2, 0.1, this.height[i]/2);
		}
	}

	/**
	 * Obtain the heights of both Player platforms
	 * @return Array of heights for Player 1 and Player 2 platforms
	 */
	public double [] getStartHeights() {
		double [] heights = {this.height[0], this.height[4]};
		return heights;
	}

	/**
	 * Redraw GameBoard skyline
	 */
	public void redraw() {
		for (int i = 0; i < this.height.length; i++) {
			StdDraw.setPenColor(this.palette[this.colors[i]]);
			StdDraw.filledRectangle((i * 0.2) + 0.1, this.height[i]/2, 0.1, this.height[i]/2);
		}
	}

	/**
	 * Take in x-coordinate and y-coordinate of projectile and determine if it has collided with the GameBoard or not
	 * @param x x-coordinate of projectile
	 * @param y y-coordinate of projectile
	 * @return True for collision, false for no collision
	 */
	public boolean checkCollision(double x, double y) {
		if (x > 1.0 || x < 0.0 || y < 0.0){
			return true;
		}
		else {
			for (int i = 0; i < this.height.length; i++) {
				double left = (0.2 * i);
				double right = ((0.2 * i) + 0.2);
				double top = this.height[i];
				if (x >= left && x < right && y <= top ) {
					return true;
				}
			}
		}
		return false;
	}
}