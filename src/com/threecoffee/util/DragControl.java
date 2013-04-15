/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.util;

import java.awt.MouseInfo;

import com.threecoffee.anim.Sprite;

/**
 * Provides a simple interface for dragging a Sprite.
 * See the DragAdapter class, that is even simpler to use.
 * 
 * @author Divyansh Prakash
 */
public class DragControl extends Thread
{
	
	Sprite p;
	
	/**
	 * Creates a new DragControl for the given Sprite.
	 */
	public DragControl(Sprite p){
		this.p = p;
		start();
	}
	
	/**
	 * Returns the Sprite that this DragControl
	 * has control of.
	 */
	public Sprite getSprite(){
		return p;
	}
	
	/**
	 * Used by the class internally.
	 */
	final public void run(){
		int x = p.getLocation().x;
		int y = p.getLocation().y;
			
		int x1 = MouseInfo.getPointerInfo().getLocation().x;
		int y1 = MouseInfo.getPointerInfo().getLocation().y;
			
		while(true){
			int x2 = MouseInfo.getPointerInfo().getLocation().x;
			int y2 = MouseInfo.getPointerInfo().getLocation().y;
				
			p.setLocation(x+(x2-x1), y+(y2-y1));
			
			update();
		}
	}
	
	/**
	 * Called in every iteration of the
	 * drag loop.
	 */
	public void update(){
	}
}