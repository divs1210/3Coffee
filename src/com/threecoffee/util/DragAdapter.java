/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.threecoffee.anim.Sprite;

/**
 * Simple class to enable mouse-dragging a Sprite.
 * 
 * @author Divyansh Prakash
 */
public class DragAdapter {
	
	Sprite sprite;
	
	/**
	 * Enables dragging the given Sprite.
	 */
	public DragAdapter(final Sprite s){
		sprite = s;
		s.addMouseListener(new MouseAdapter(){
			
			DragControl dc;
			
			public void mousePressed(MouseEvent e){
				pressed();
				dc = new DragControl(s);
			}
			
			public void mouseReleased(MouseEvent e){
				try{
					dc.stop();
				}catch(Exception e1){
					e1.printStackTrace();
					Logger.log(e1);
				}
				released();
			}
			
		});
	}

	/**
	 * Called when the mouse has been released.
	 */
	public void released() {
	}
	
	/**
	 * Called when the mouse has been pressed.
	 */
	public void pressed() {
	}
	
	/**
	 * Returns the Sprite that this DragAdapter
	 * has control of.
	 */
	public Sprite getSprite(){
		return sprite;
	}
	
}
