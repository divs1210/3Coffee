/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.util;

import java.awt.Container;

import javax.swing.JLabel;

import com.threecoffee.anim.Sprite;

/**
 * Simple class to imitate the action of Gravity.
 * 
 * @author Divyansh Prakash
 */
public class Gravity extends Thread
{
	
	Sprite s;
	Container c;
	
	boolean paused, gpaused;
	double bounce, vel, acc;
	long delay;
	
	/**
	 * Sets gravity on the given Sprite.
	 * For normal purposes, use the 
	 * Sprite.setGravity(boolean) method.
	 * 
	 * @param bounce A value typically between 0 and 1.
	 */
	public Gravity(Sprite s, double bounce){
		this.s = s;
		this.bounce = bounce;
		gpaused=paused = false;
		vel=0;
		acc=1;
		delay=15;
	}
	
	/**
	 * Sets the time difference in ms
	 * between successive Gravity updates. 
	 */
	public void setDelay(long d){
		delay = d;
	}
	
	/**
	 * Returns the time difference in ms
	 * between successive Gravity updates.
	 */
	public long getDelay(){
		return delay;
	}
	
	/**
	 * Returns the fraction of energy
	 * maintained after every bounce.
	 */
	public double getBounce(){
		return bounce;
	}
	
	/**
	 * Sets the fraction of energy
	 * maintained after every bounce.
	 */
	public void setBounce(double b){
		bounce = b;
	}
	
	/**
	 * Returns the velocity of the
	 * Sprite due to gravity.
	 */
	public double getVel(){
		return vel;
	}
	
	/**
	 * Sets the vertical velocity of the
	 * Sprite controlled by this Gravity object.
	 */
	public void setVel(double v){
		vel = v;
	}
	
	/**
	 * Sets the vertical acceleration of the 
	 * Sprite due to this Gravity object.
	 */
	public void setAcceleration(double a){
		acc = a;
	}
	
	/**
	 * Returns the acceleration of the
	 * Sprite due to gravity.
	 */
	public double getAcceleration(){
		return acc;
	}
	
	/**
	 * Called by the System internally.
	 * Call the start() method to start
	 * this Gravity object. 
	 */
	final public void run(){
		vel = 0;
		
		while(true){
			if(!paused && !gpaused){
				
				for(int i=0; i<=Math.abs(vel); i++){
					s.setLocation(s.getX(), s.getY()+(vel<0?-1:1));
					update();
				}
				
				try {
					sleep(delay);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.log(e);
				}
				
				vel+=acc;
			}
		}
			
	}
	
	/**
	 * Called in every iteration of the
	 * Gravity loop.
	 */
	public void update() {
	}
	
	/**
	 * Bounces the Sprite vertically.
	 */
	public void bounce(){
		vel *= -bounce;
	}
	
	/**
	 * Pauses this Gravity object.
	 */
	public void pause(boolean b){
		paused = b;
	}
	
	/**
	 * Returns true if this Gravity object is paused.
	 */
	public boolean isPaused(){
		return paused;
	}
	
	/**
	 * Used by the System internally. Use pause()
	 * method instead.
	 */
	public void toggleGravity() {
		gpaused = !gpaused;
	}
	
}