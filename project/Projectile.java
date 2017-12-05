package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Projectile {
	private double velocityX, velocityY, dtime;

	public Projectile(double angle, double velocity) {
		this.velocityX = velocity/10 * Math.cos(angle * (Math.PI/180));
		this.velocityY = velocity/10 * Math.sin(angle * (Math.PI/180));
		this.dtime = 0.003;
	}

	/**
	 * Update the x-coordinate according to the x-direction velocity for Player 1
	 * @param x Old x-coordinate
	 * @return New x-coordinate
	 */
	public double updateXOne(double x) {
		return x + (velocityX*dtime);
	}

	/**
	 * Update the x-coordinate according to the negative x-direction velocity for Player 2
	 * @param x Old x-coordinate
	 * @return New x-coordinate
	 */
	public double updateXTwo(double x) {
		return x + (-velocityX*dtime);
	}

	/**
	 * Update the y-coordinate according to the y-direction velocity, taking into account gravity
	 * @param y Old y-coordinate
	 * @return New y-coordinate
	 */
	public double updateY(double y) {
		this.velocityY = this.velocityY - (9.81*dtime);
		return y + (this.velocityY*dtime);
	}

	/**
	 * Determine which player is being thrown for and update the x and y coordinates
	 * @param player1 True for Player 1, false for Player 2
	 * @param initX Initial x-coordinate
	 * @param initY Initial y-coordinate
	 * @return Array of new x-coordinate and y-coordinate
	 */
	public double [] throwP(boolean player1, double initX, double initY) {
		if (player1 == true) {
			StdDraw.setPenColor(Color.RED);
			StdDraw.filledCircle(initX, initY, 0.004);
			double next [] = {updateXOne(initX), updateY(initY)};
			return next;
		}
		else {
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.filledCircle(initX, initY, 0.004);
			double next [] = {updateXTwo(initX), updateY(initY)};
			return next;
		}
	}
}