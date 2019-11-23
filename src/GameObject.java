import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

interface GameObject {
	/**
     * Returns the y value of the top of the bounding hitbox
	 * @return the y value of the top segment of the hitbox 
	 */
	int getTop ();

	/**
     * Returns the x value of the right of the bounding hitbox
	 * @return the x value of the right segment of the hitbox 
	 */
	int getRight ();

	/**
     * Returns the y value of the bottom of the bounding hitbox
	 * @return the y value of the bottom segment of the hitbox 
	 */
	int getBottom ();

	/**
     * Returns the x value of the left of the bounding hitbox
	 * @return the x value of the left segment of the hitbox 
	 */
	int getLeft ();
}
