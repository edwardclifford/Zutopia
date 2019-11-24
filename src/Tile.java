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
	public static final int IMG_WIDTH = 40;
	/**
	 * The height of the image.
	 */
	public static final int IMG_HEIGHT = 40;
	/**
	 * The position of the image y.
	 */
	public double posY = 0;
	/**
	 * The position of the image X.
	 */
	public double posX = 0;
    /**
     * The location of the image file
     */
	public String URL_IMG = "";

    public ImageView imageView;
    /**
     * Impliments a tile class
     */ 
	Tile(String url, int centerX, int centerY) {
		URL_IMG = url;
		posX = centerX;
		posY = centerY;
        try {
            Image image = openImage();
            imageView = new ImageView(image); 

            //Setting the position of the image 
            imageView.setX(posX - (IMG_WIDTH / 2));
            imageView.setY(posY - (IMG_HEIGHT /2)); 

            //setting the fit height and width of the image view 
            imageView.setFitHeight(IMG_HEIGHT); 
            imageView.setFitWidth(IMG_WIDTH); 
        }
        catch (FileNotFoundException e) {
            System.out.println(URL_IMG + " not found");
        }
	}
	/**
	 * gets the image from path
     * @return the imageview
	 */
	public ImageView getImageView () {
		//Creating an image 
        return imageView; 
	}

    public Image openImage () throws FileNotFoundException {
        return new Image(new FileInputStream(URL_IMG));
    }

	/**
	 * this function gets the top Y value
	 */
	public double getTop () {
		return posY - (IMG_HEIGHT / 2);
	}
	
	/**
	 * This function gets the bottom Y value
	 */
	public double getBottom () {
		return posY + (IMG_HEIGHT / 2);
	}
	
	/**
	 * This function gets the left X value
	 */
	public double getLeft () {
		return posX - (IMG_WIDTH / 2);
	}
	
	/**
	 * This function gets the right x value
	 */
	public double getRight () {
		return posX + (IMG_WIDTH / 2);
	}	
	
}
