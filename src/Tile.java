import java.awt.*;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  


/**
 * This class create a tile object
 * @author Gabi
 *
 */

public class Tile implements GameObject {
	/**
	 * The width of the image
	 */
	public static final int IMG_WIDTH = 24;
	/**
	 * The height of the image.
	 */
	public static final int IMG_HEIGHT = 24;
	/**
	 * The position of center of the image.
	 */
	public static double CENTER_Y = 0;
	/**
	 * The of center of the image X.
	 */
	public static double CENTER_X = 0;
	
	public static String URL_IMG = "";

	Tile(String url, int centerX, int centerY) {
		URL_IMG = url;
		CENTER_X = centerX;
		CENTER_Y = centerY;
	}
	/**
	 * gets the image from path
	 * @throws FileNotFoundException 
	 */
	public void getImage () throws FileNotFoundException {
		//Creating an image 
		Image image = new Image(new FileInputStream(URL_IMG));  

		//Setting the image view 
		ImageView imageView = new ImageView(image); 

		//Setting the position of the image 
		imageView.setX(CENTER_X);
		imageView.setY(CENTER_Y); 

		//setting the fit height and width of the image view 
		imageView.setFitHeight(IMG_HEIGHT); 
		imageView.setFitWidth(IMG_WIDTH); 
	}

	/**
	 * this function gets the top Y value
	 */
	public int getTop () {
		return (int) (CENTER_Y - (0.5 * IMG_HEIGHT));
		
	}
	
	/**
	 * This function gets the bottom Y value
	 */
	public int getBottom () {
		return  (int) (CENTER_Y + (0.5 * IMG_HEIGHT));
	}
	
	/**
	 * This function gets the left X value
	 */
	public int getLeft () {
		return  (int) (CENTER_X - (0.5 * IMG_WIDTH));
		
	}
	
	/**
	 * This function gets the right x value
	 */
	public int getRight () {
		return  (int) (CENTER_X + (0.5 * IMG_WIDTH));
		
	}	
	
}
