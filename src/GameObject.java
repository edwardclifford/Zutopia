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
     * Returns the y value of the top of the bounding hitbox
	 * @return the y value of the top segment of the hitbox 
	 */
	int getRight ();

	/**
     * Returns the y value of the top of the bounding hitbox
	 * @return the y value of the top segment of the hitbox 
	 */
	int getBottom ();

	/**
     * Returns the y value of the top of the bounding hitbox
	 * @return the y value of the top segment of the hitbox 
	 */
	int getLeft ();
}
