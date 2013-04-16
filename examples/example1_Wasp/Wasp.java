/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package example1_Wasp;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.imgscalr.Scalr;

import com.threecoffee.anim.Sprite;
import com.threecoffee.control.GameWindow;

/**
 * A simple animation using a spritesheet
 * 
 * @author Divyansh Prakash
 */
class Wasp{

	Wasp(){
		//Create new window with given width and height
		GameWindow g = new GameWindow(150, 150);
		
		//Load spritesheet
		BufferedImage spritesheet=null;
		try {
			spritesheet = ImageIO.read(new File("media/wasp/wasp.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//Create new sprite
		Sprite wasp = new Sprite();
		//Crop images from spritesheet, and add them to the sprite
		for(int i=0; i<8; i++)
			wasp.addImage(new ImageIcon( Scalr.crop(spritesheet, 100*i, 0, 100, 100) ));
		
		//Add sprite to GameWindow, set its location on screen,
		//and play it.
		wasp.addTo(g);
		wasp.setLocation(0, 0);
		wasp.play();
		
		//Make window visible on screen
		g.setVisible(true);
	}
	
	public static void main(String[] args){
		new Wasp();
	}
	
}