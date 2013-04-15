/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.media;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Provides static methods for Image manipulation.
 * 
 * @author Divyansh Prakash
 */
public class ImageControl
{
	/**
	 * Resizes the given Image to the given width and height.
	 */
    public static Image resize(Image orig, int width, int height)
    {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(orig, 0, 0, width, height, null);
        g.dispose();
        return Toolkit.getDefaultToolkit().createImage(resizedImage.getSource());
    }
    
    /**
	 * Resizes the given ImageIcon to the given width and height.
	 */
    public static ImageIcon resize(ImageIcon orig, int width, int height){
    	BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(orig.getImage(), 0, 0, width, height, null);
        g.dispose();
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(resizedImage.getSource()));
    }
}
