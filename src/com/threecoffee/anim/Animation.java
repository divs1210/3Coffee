/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */


package com.threecoffee.anim;

import java.util.Vector;
import javax.swing.ImageIcon;

/**
 * A class to represent an animation created
 * using a series of images
 *  
 * @author Divyansh Prakash
 */
public class Animation extends Vector<ImageIcon> {
	
	String name;
	long delay;
	
	/**
	 * Sets the name of this animation.
	 */
	public void setName(String n){
		name = n;
	}
	
	/**
	 * Returns the name of this animation.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the time delay(in milliseconds) after
	 * which the update() method is called. 
	 */
	public void setDelay(long l){
		delay = l;
	}
	
	/**
	 * Returns the time delay(in milliseconds) after
	 * which the update() method is called. 
	 */
	public long getDelay(){
		return delay;
	}
	
}
