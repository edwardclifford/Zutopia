import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that implements a ball with a position and velocity.
 */
public class Ball implements GameObject {
	// Constants
	/**
	 * The radius of the ball.
	 */
	public static final int BALL_RADIUS = 8;
	/**
	 * The initial velocity of the ball in the x direction.
	 */
	public static final double INITIAL_VX = 1e-7;
	/**
	 * The initial velocity of the ball in the y direction.
	 */
	public static final double INITIAL_VY = 1e-7;

	// Instance variables
	// (x,y) is the position of the center of the ball.
	public double x, y;
	public double vx, vy;
	private Circle circle;

	/**
	 * @return the Circle object that represents the ball on the game board.
	 */
	public Circle getCircle () {
		return circle;
	}

	/**
	 * Constructs a new Ball object at the centroid of the game board
	 * with a default velocity that points down and right.
	 */
	public Ball () {
		x = GameImpl.WIDTH/2;
		y = GameImpl.HEIGHT/2;
		vx = INITIAL_VX;
		vy = INITIAL_VY;

		circle = new Circle(BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
		circle.setLayoutX(x - BALL_RADIUS);
		circle.setLayoutY(y - BALL_RADIUS);
		circle.setFill(Color.BLACK);
	}

	/**
	 * Updates the position of the ball, given its current position and velocity,
	 * based on the specified elapsed time since the last update.
	 * @param deltaNanoTime the number of nanoseconds that have transpired since the last update
	 */
	public void updatePosition (long deltaNanoTime) {
		double dx = vx * deltaNanoTime;
		double dy = vy * deltaNanoTime;
		x += dx;
		y += dy;

		circle.setTranslateX(x - (circle.getLayoutX() + BALL_RADIUS));
		circle.setTranslateY(y - (circle.getLayoutY() + BALL_RADIUS));
	}

    /**
     * Calculates the next x, y position for a given unit of time and its current velocity.
     * @param deltaNanoTime the number of nanoseconds that have transpired since the last update
     * @return an array of 2 length containing the x pos in the 0th element and the y pos in the 1st element
     */
    public double[] getNextPosition (long deltaNanoTime) {
        double dx = vx * deltaNanoTime;
        double dy = vy * deltaNanoTime;
        
        return new double[] { dx + x, dy + y };
    }

    /**
     * Return the y value of the top of the ball
     * @return the y value of the top of the ball
     */
    public double getTop () {
        return y - BALL_RADIUS;
    }

    /**
     * Return the x value of the right of the ball
     * @return the x value of the right of the ball
     */
    public double getRight () {
        return x + BALL_RADIUS;
    }

    /**
     * Return the y value of the bottom of the ball
     * @return the y value of the bottom of the ball
     */
    public double getBottom () {
        return y + BALL_RADIUS;
    }

    /**
     * Return the x value of the left of the ball
     * @return the x value of the left of the ball
     */
    public double getLeft () {
        return x + BALL_RADIUS;
    }
}
