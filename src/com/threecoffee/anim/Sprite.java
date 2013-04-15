/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */


package com.threecoffee.anim;

import java.awt.Point;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.threecoffee.control.GameWindow;
import com.threecoffee.util.Gravity;
import com.threecoffee.util.Logger;

/**
 * Simple class to represent an image or a simple animation consisting of a group of images.
 * 
 * @author Divyansh Prakash
 */
public class Sprite extends JLabel implements Runnable, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Vector<ImageIcon> images;
	Vector<Sprite> colliders;
	
	long delay;
	int spriteNo;
	boolean gstop, paused;
	double prevel;
	
	String name;
	ImageIcon sprite;
	Thread t;
	Gravity g;
	Point prev;
	Sprite other;
	GameWindow gw;
	
	/**
	 * Creates a new animated sprite with no image
	 */
	public Sprite() {
		images = new Vector<ImageIcon>(1, 1);
		colliders = new Vector<Sprite>(1, 1);
		delay = 50;
		gstop=false;
		paused = false;
		name = "";
	}
	
	/**
	 * Creates a new Sprite from the given Animation
	 */
	public Sprite(Animation anim){
		images = new Vector<ImageIcon>(1, 1);
		images.addAll(anim);
		setSize(anim.get(0).getIconWidth(), anim.get(0).getIconHeight());
		colliders = new Vector<Sprite>(1, 1);
		delay = anim.getDelay();
		gstop = paused = false;
		name = anim.getName();
	}
	
	/**
	 * Returns the Animation Object that this 
	 * Sprite displays
	 */
	public Animation getAnimation(){
		Animation a = new Animation();
		a.addAll(images);
		a.setName(name);
		a.setDelay(delay);
		
		return a;
	}
	
	/**
	 * Sets the name of this Sprite
	 * to the given string.
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Returns the name of this Sprite.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a new image to the sprite.
	 * All images added will be played
	 * in a continuous loop.
	 */
	public void addImage(ImageIcon ico){
		images.add(ico);
		setSize(ico.getIconWidth(), ico.getIconHeight());
	}
	
	/**
	 * Returns all the images that this
	 * sprite contains.
	 */
	public Vector<ImageIcon> getImages() {
		return images;
	}
	
	/**
	 * Sets the given images as the ones that
	 * this Sprite displays.
	 */
	public void setImages(Vector<ImageIcon> i) {
		images = i;
		sprite = i.get(0);
		spriteNo = 0;
	}
	
	/**
	 * Checks for collision with the 
	 * given Sprite, and calls the 
	 * collided(Sprite) method of
	 * BOTH THE SPRITES if collision 
	 * is detected.
	 */
	public void addCollider(Sprite s){
		colliders.add(s);
	}
	
	/**
	 * Returns all the Sprites that this
	 * Sprite can collide with.
	 */
	public Vector<Sprite> getColliders(){
		return colliders;
	}
	
	/**
	 * Sets the time in milliseconds between
	 * two consecutive frames.
	 */
	public void setDelay(long l) {
		delay = l;
	}
	
	/**
	 * Returns the time in milliseconds 
	 * between two successive frames.
	 */
	public long getDelay() {
		return delay;
	}
	
	/**
	 * Sets the image being displayed
	 * to the given image. The other 
	 * images adjust accordingly.
	 */
	public void setCurrentImage(ImageIcon ico) {
		int c=0;
		for(ImageIcon i : images){
			if(ico.equals(i)){
				sprite = i;
				spriteNo = c;
				break;
			}
			c++;
		}
	}
	
	/**
	 * Returns the ImageIcon being
	 * displayed by the Sprite.
	 */
	public ImageIcon getCurrentImage() {
		return sprite;
	}

	/**
	 * Sets the image being displayed
	 * to the image at the given index. 
	 * The other images adjust accordingly.
	 */
	public void setCurrentSpriteIndex(int i) {
		spriteNo = i;
		sprite = images.get(i);
	}

	/**
	 * Returns the index of the image being
	 * displayed currently by this sprite
	 */
	public int getCurrentSpriteIndex() {
		return spriteNo;
	}
	
	/**
	 * Adds/Removes gravity to/from the
	 * Sprite.
	 */
	public void setGravity(boolean b){
		if(!b && g!=null){
			g.stop();
			g=null;
		}else if(b){
			g = new Gravity(this, 0.85){
				public void update(){
					checkCollisions();
				}
			};
			g.start();
		}
	}
	
	/**
	 * Sets the given Gravity object as this
	 * Sprite's default Gravity.
	 */
	public void setGravity(Gravity g1){
		if(g!=null)
			g.stop();
		g = g1;
	}
	
	/**
	 * Returns whether the Sprite is 
	 * affected by Gravity.
	 */
	public boolean hasGravity(){
		if(g == null)
			return false;
		return true;
	}
	
	/**
	 * Returns the Gravity object acting
	 * on the Sprite. Returns null if
	 * the Sprite is not affected by gravity.
	 */
	public Gravity getGravity(){
		return g;
	}
	
	/**
	 * Returns true if the Sprite is being
	 * displayed on the screen.
	 */
	public boolean inView(){
		boolean b=true;
		if(isVisible()){
			if(getX()>getParent().getWidth() || getX()+getWidth()<0)
				b = false;
			if(getY()>getParent().getHeight() || getY()+getHeight()<0)
				b = false;
		}
		return (b && isVisible());
	}
	
	/**
	 * Returns the previous location of the sprite.
	 */
	public Point getPreviousLocation(){
		return prev;
	}
	
	/**
	 * Used by the system internally. See the
	 * play() method instead.
	 */
	public void run() {
		init();
		
		while (true) {
			if(!paused) {
				for(spriteNo = 0; spriteNo < images.size(); spriteNo++) {
					if (isVisible()) {
						sprite = images.get(spriteNo);
						setIcon(sprite);
						
						prev = getLocation();

						checkCollisions();
						
						update();
						
						repaint();
						
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
							Logger.log(e);
						}
					}
				}
			}
			else{
				checkCollisions();
			}
		}
	}
	
	/**
	 * Used by the system to check if this
	 * Sprite has collided with a collider
	 * Object.
	 */
	public void checkCollisions(){
		
		int x1=getX(), y1=getY(), w1=getWidth(), h1=getHeight();
		int x2, y2, w2, h2;
		
		for(Sprite s : colliders){
			
			x2 = s.getX();
			y2 = s.getY();
			w2 = s.getWidth();
			h2 = s.getHeight();
				
			if ( s.isVisible() &&
			   ( s.hasPoint(x1, y1) || s.hasPoint(x1+w1, y1) || s.hasPoint(x1+w1, y1+h1) || s.hasPoint(x1, y1+h1) ||
				   hasPoint(x2, y2) ||   hasPoint(x2+w2, y2) ||   hasPoint(x2+w2, y2+h2) ||   hasPoint(x2, y2+h2) ) ){

				collided(s);
				s.collided(this);
				
				if(hasGravity() && g.getVel()>=0 && y1+h1>=y2 && y1+h1<=y2+h2)
				{
					setLocation(getX(), s.getY()-getHeight()+1);
					g.bounce();
				}
				
				return;
			}
		}
	}
	
	/**
	 * Returns true if the Sprite contains
	 * the given location on the screen.
	 */
	public boolean hasPoint(int x, int y){
		return (x>=getX() && x<=getX()+getWidth() && y>=getY() && y<=getY()+getHeight());
	}
	
	/**
	 * Returns true if the Sprite contains
	 * the given location on the screen.
	 */
	public boolean hasPoint(Point p){
		return hasPoint(p.x, p.y);
	}
	
	/**
	 * Forces the Sprite to go back to it's
	 * previous location.
	 */
	public void undo(){
		setLocation(prev);
	}
	
	/**
	 * Call this method to start displaying
	 * the Sprite on the screen.
	 */
	public void play() {
		t = new Thread(this);
		t.start();
	}
	
	/**
	 * Called by the system when the play() method 
	 * is called, just before the Sprite starts playing.
	 */
	public void init(){
	}
	
	/**
	 * This method is called again and again
	 * unless the Sprite is paused or stopped.
	 * Override this method to animate your sprite.
	 */
	public void update() {
	}
	
	/**
	 * Use this method to pause/resume the Sprite.
	 */
	public void pause(boolean b){
		paused = b;
	}
	
	/**
	 * Returns true if the Sprite is paused.
	 */
	public boolean isPaused(){
		return paused;
	}
	
	/**
	 * Moves the sprite x pixels to the right, and y pixels down.
	 */
	public void moveSprite(int x, int y){
		setLocation(getX()+x, getY()+y);
	}
	
	/**
	 * Called whenever this Sprite collides with
	 * another Sprite.
	 */
	public void collided(Sprite s){
	}
	
	/**
	 * Stops playing the current Sprite.
	 */
	public void stop() {
		try {
			t.stop();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log(e);
		}
	}
	
	/**
	 * Add this Sprite to the given GameWindow.
	 */
	public void addTo(GameWindow gaw) {
		gw=gaw;
		gw.getContentPane().add(this);
	}
	
	/**
	 * Returns the GameWindow that this Sprite
	 * is attached to.
	 */
	public GameWindow getGameWindow(){
		return gw;
	}
}