/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */


package com.threecoffee.anim;

import java.util.Vector;

import javax.swing.ImageIcon;

/**
 * Class to represent complex characters with multiple animations.
 * 
 * @author Divyansh Prakash
 */
public class Actor extends Sprite {

	Vector<Animation> anims;
	int n;
	
	/**
	 * Instantiates a new Actor
	 */
	public Actor(){
		anims = new Vector<Animation>();
	}
	
	/**
	 * Adds an animation to this actor
	 */
	public void addAnimation(Animation a){
		anims.add(a);
	}
	
	/**
	 * Returns a set of all the Animations of this Actor
	 */
	public Vector<Animation> getAllAnimations(){
		return anims;
	}
	
	/**
	 * Sets the current animation being played by this Actor
	 */
	public void setCurrentAnimation(int i){
		n=i;
		
		if(images == null)
			images = new Vector<ImageIcon>(1, 1);
		else
			images.removeAllElements();
		
		images.addAll(anims.get(i));
		setSize(images.get(0).getIconWidth(), images.get(0).getIconHeight());
		delay = anims.get(i).getDelay();
		
		//spriteNo = 0;
		//sprite = images.get(0);
	}
	
	/**
	 * Returns the animation being played by this Actor
	 */
	public Animation getCurrentAnimation(){
		return anims.get(n);
	}
}