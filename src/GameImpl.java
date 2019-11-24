import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import java.io.FileNotFoundException; 
import java.util.*;

public class GameImpl extends Pane implements Game {
	/**
	 * Defines different states of the game.
	 */
	public enum GameState {
		WON, LOST, ACTIVE, NEW
	}

	// Constants
	/**
	 * The width of the game board.
	 */
	public static final int WIDTH = 400;
	/**
	 * The height of the game board.
	 */
	public static final int HEIGHT = 600;

	// Instance variables
	private Ball ball;
	private Paddle paddle;
    private List<Tile> tiles;
    private int bottomCollisions = 0;
	/**
	 * Constructs a new GameImpl.
	 */
	public GameImpl () {
		setStyle("-fx-background-color: white;");

		restartGame(GameState.NEW);
	}

	public String getName () {
		return "Zutopia";
	}

	public Pane getPane () {
		return this;
	}

	private void restartGame (GameState state) {
		getChildren().clear();  // remove all components from the game
        bottomCollisions = 0;

		// Create animals
        tiles = new ArrayList<Tile>();
        for (int i = 0; i < 16; i++) {

            // Get an (x, y) for each value of i 
            int x = WIDTH / 8 + (i % 4) * (WIDTH / 4);
            int y = (HEIGHT / 8 + (i / 4) * (HEIGHT / 4)) / 2;

            // Alternate between all 3 animals
            String animal;
            if (i % 3 == 0) animal = "duck.jpg";
            else if ((i + 1) % 3 == 0) animal = "goat.jpg";
            else animal = "horse.jpg";
            
            // Add new tiles to list and display them on screen 
            Tile tile = new Tile(animal, x, y);
            tiles.add(tile);
            getChildren().add(tile.getImageView());
        }

		// Create and add ball
		ball = new Ball();
		getChildren().add(ball.getCircle());  // Add the ball to the game board

		// Create and add paddle
		paddle = new Paddle();
		getChildren().add(paddle.getRectangle());  // Add the paddle to the game board

		// Add start message
		final String message;
		if (state == GameState.LOST) {
			message = "Game Over\n";
		} else if (state == GameState.WON) {
			message = "You won!\n";
		} else {
			message = "";
		}
		final Label startLabel = new Label(message + "Click mouse to start");
		startLabel.setLayoutX(WIDTH / 2 - 50);
		startLabel.setLayoutY(HEIGHT / 2 + 100);
		getChildren().add(startLabel);

		// Add event handler to start the game
		setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
				GameImpl.this.setOnMouseClicked(null);

				// As soon as the mouse is clicked, remove the startLabel from the game board
				getChildren().remove(startLabel);
				run();
			}
		});

		// Add another event handler to steer paddle...
        setOnMouseMoved(new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
                paddle.moveTo(e.getX(), e.getY());
            }
		});


	}

	/**
	 * Begins the game-play by creating and starting an AnimationTimer.
	 */
	public void run () {
		// Instantiate and start an AnimationTimer to update the component of the game.
		new AnimationTimer () {
			private long lastNanoTime = -1;
			public void handle (long currentNanoTime) {
				if (lastNanoTime >= 0) {  // Necessary for first clock-tick.
					GameState state;
					if ((state = runOneTimestep(currentNanoTime - lastNanoTime)) != GameState.ACTIVE) {
						// Once the game is no longer ACTIVE, stop the AnimationTimer.
						stop();
						// Restart the game, with a message that depends on whether
						// the user won or lost the game.
						restartGame(state);
					}
				}
				// Keep track of how much time actually transpired since the last clock-tick.
				lastNanoTime = currentNanoTime;
			}
		}.start();
	}

	/**
	 * Updates the state of the game at each timestep. In particular, this method should
	 * move the ball, check if the ball collided with any of the animals, walls, or the paddle, etc.
	 * @param deltaNanoTime how much time (in nanoseconds) has transpired since the last update
	 * @return the current game state
	 */
	public GameState runOneTimestep (long deltaNanoTime) {
        setOnMouseMoved(new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
                paddle.moveTo(e.getX(), e.getY());
            }
		});

        tileCollisions(deltaNanoTime); 
        wallCollisions(deltaNanoTime);
        paddleCollisions(deltaNanoTime);

		ball.updatePosition(deltaNanoTime);

        if (tiles.size() == 0) {
            return GameState.WON;
        }

        if (bottomCollisions == 5) {
            return GameState.LOST;
        }
		return GameState.ACTIVE;
	}

    public void tileCollisions (long deltaNanoTime) {
        final double currentBallX = ball.x;  
        final double currentBallY = ball.y;
        final double[] nextBall = ball.getNextPosition(deltaNanoTime);
        final double nextBallX = nextBall[0];
        final double nextBallY = nextBall[1];
        final double speedIncreaseX = ball.INITIAL_VX / 5;
        final double speedIncreaseY = ball.INITIAL_VY / 5;

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);

            if (tile.contains(nextBallX, nextBallY)) {
                ball.vx = ball.vx * 1.1;
                ball.vy = ball.vy * 1.1; 
                getChildren().remove(tile.getImageView());
                tiles.remove(i);
                return;
            }
            /*
            //https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
            // Check for left bounds
            if (ball.vx > 0) {
                if (doesIntersect(currentBallX + ball.BALL_RADIUS, currentBallY, 
                        nextBallX + ball.BALL_RADIUS, nextBallY, 
                        tile.getLeft(), tile.getTop() - 1, tile.getLeft(), tile.getBottom() + 1)) {
                            // Left edge collision, flip ball vx
                            System.out.println("Left col");
                            ball.vx = -1 * (Math.abs(ball.vx) + speedIncreaseX); 
                            getChildren().remove(tile.getImageView());
                            tiles.remove(i);
                            return;
                }
            }
            // Check for right bounds
            else {
                if (doesIntersect(currentBallX - ball.BALL_RADIUS, currentBallY, 
                        nextBallX - ball.BALL_RADIUS, nextBallY, 
                        tile.getRight(), tile.getTop() - 1, tile.getRight(), tile.getBottom() + 1)) {
                            // Right edge collision, flip ball vx
                            System.out.println("Right col");
                            ball.vx = Math.abs(ball.vx) + speedIncreaseX; 
                            getChildren().remove(tile.getImageView());
                            tiles.remove(i);
                            return;
                }
            }
            // Check for top bounds
            if (ball.vy > 0) {
                if (doesIntersect(currentBallX, currentBallY + ball.BALL_RADIUS, 
                        nextBallX, nextBallY + ball.BALL_RADIUS, 
                        tile.getRight() + 1, tile.getTop(), tile.getLeft() - 1, tile.getTop())) {
                            // Top edge collision, flip ball vy
                            System.out.println("Top col");
                            ball.vy = -1 * (Math.abs(ball.vy) + speedIncreaseY); 
                            getChildren().remove(tile.getImageView());
                            tiles.remove(i);
                            return;
                }
            }
            else {
                if (doesIntersect(currentBallX, currentBallY - ball.BALL_RADIUS,
                        nextBallX, nextBallY - ball.BALL_RADIUS, 
                        tile.getRight() + 1, tile.getBottom(), tile.getLeft() - 1, tile.getBottom())) {
                            // Bottom edge collision, flip ball vy
                            System.out.println("Bottom col");
                            ball.vy = Math.abs(ball.vy) + speedIncreaseY; 
                            getChildren().remove(tile.getImageView());
                            tiles.remove(i);
                            return;
                }
            }
            */
        }
    }

    public void wallCollisions (Long deltaNanoTime) {
    
        // Check for collision on right wall
        if (ball.x + ball.BALL_RADIUS > WIDTH) {
            ball.vx = -1 *Math.abs(ball.vx);
            return;
        }
        // Check for collision on left wall
        else if (ball.x - ball.BALL_RADIUS < 0) {
            ball.vx = Math.abs(ball.vx);
            return;
        }
        
        // Check for collision on top wall
        if (ball.y + ball.BALL_RADIUS > HEIGHT) {
            ball.vy = -1 * Math.abs(ball.vy);
            bottomCollisions++;
            return;
        }
        // Check for collision bottom wall
        else if (ball.y - ball.BALL_RADIUS < 0) {
            ball.vy = Math.abs(ball.vy);
            return;
        }
    }

    public void paddleCollisions(long deltaNanoTime) {
        final double currentBallX = ball.x;
        final double currentBallY = ball.y;
        final double[] nextBall = ball.getNextPosition(deltaNanoTime);
        final double nextBallX = nextBall[0];
        final double nextBallY = nextBall[1];

        if (ball.vy > 0) {
            if (doesIntersect(currentBallX, currentBallY + ball.BALL_RADIUS,
                        nextBallX, nextBallY + ball.BALL_RADIUS,
                        paddle.getLeft(), paddle.getTop(), paddle.getRight(), paddle.getTop())) {
                ball.vy = -1 * Math.abs(ball.vy);
            }
        }
        else {
            if (doesIntersect(currentBallX, currentBallY - ball.BALL_RADIUS,
                        nextBallX, nextBallY - ball.BALL_RADIUS,
                        paddle.getLeft(), paddle.getBottom(), paddle.getRight(), paddle.getBottom())) {
                ball.vy = Math.abs(ball.vy);
            }
        }
    }
    /**
     * Checks for a collision between two line segments. Uses the orientation
     * of 3 points on a line to determine if the lines intersect. Can ignore
     * special cases because ball will always be traveling at a 45 degree angle and
     * can never lie parallel to the edges.
     * @param x1 the first x value for the first line
     * @param y1 the first y value for the first line
     * @param x2 the second x value for the first line
     * @param y2 the second y value for the first line
     * @param x3 the first x value for the second line
     * @param y3 the first y value for the second line
     * @param x4 the second x value for the second line
     * @param y4 the second y value for the second line   
     * @return true if the lines intersect, false otherwise.
     */
    public boolean doesIntersect (double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double orientation1 = orientation(x1, y1, x2, y2, x3, y3);
        double orientation2 = orientation(x1, y1, x2, y2, x4, y4);
        double orientation3 = orientation(x3, y3, x4, y4, x1, y1);
        double orientation4 = orientation(x3, y3, x4, y4, x2, y2);

        return (orientation1 != orientation2 && orientation3 != orientation4);
    }

    /**
     * Determines the orientation for 3 points in space. See this slide for reference:
     * http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf (slide 10)
     * @param x1 the x value of the first point
     * @param y1 the y value of the first point
     * @param x2 the x value of the second point
     * @param y2 the y value of the second point
     * @param x3 the x value of the third point
     * @param y3 the y value of the third point
     * @return 1 if clockwise case or 2 if counterclockwise case
     */
    public double orientation (double x1, double y1, double x2, double y2, double x3, double y3) {
        double slopeComparison = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);

        if (slopeComparison > 0) return 1;
        else return 2;
    }
}
