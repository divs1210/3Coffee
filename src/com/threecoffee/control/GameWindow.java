/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.threecoffee.anim.Sprite;
import com.threecoffee.util.Logger;

/**
 * A Window to which Sprites can be added to make a game.
 * 
 * @author Divyansh Prakash
 */
public class GameWindow extends JFrame {
	static boolean[] keys = new boolean[300];
	boolean paused = false;
	/**
	 * Creates a new GameWindow with the given
	 * width and height.
	 */
	public GameWindow(int width, int height){
		setSize(width, height);
		setTitle("3Coffee Game Engine");
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setIconImage(new ImageIcon("media/engine/ic.lp").getImage());
		
		Container container = getContentPane();
		container.setLayout(null);
		container.setBackground(Color.WHITE);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		showLoadScreen();
		
		try{
			Thread.sleep(1000);
			new Thread(){
				public void run(){
					while(true)
						update();
				}
			}.start();
		}catch(Exception e){
			e.printStackTrace();
			Logger.log(e);
		}
		
		addKeyListener(new KA());
	}
	
	/**
	 * Shifts the whole screen, along with the components,
	 * by the given pixels.
	 */
	public void shiftScreen(double x, double y){
		for(Component c : this.getContentPane().getComponents()){
			try{
				c.setLocation((int)(c.getX()+x), (int)(c.getY()+y));
			}catch(Exception e){
				e.printStackTrace();
				Logger.log(e);
			}
		}
	}
	
	/**
	 * Method to check if a key is pressed.
	 * Takes the KeyEvent.keyCode as input parameter.
	 */
	public static boolean isKeyDown(int key){
		return keys[key];
	}
	
	/**
	 * Method to check if the game is paused.
	 */
	public boolean isPaused(){
		return paused;
	}
	
	/**
	 * Method to pause or resume the game.
	 */
	public void pause(boolean flag){
		paused = flag;
		for(Component c : this.getContentPane().getComponents()){
			try{
				Sprite s = (Sprite)c;
				s.pause(flag);
			}catch(Exception e){
				e.printStackTrace();
				Logger.log(e);
			}
		}
	}
	
	/**
	 * This method is called again and again by the system.
	 * Override to implement functionality.
	 */
	public void update(){
	}
	
	void showLoadScreen(){
		new Thread(){
			public void run(){
				ImageIcon ico = new ImageIcon("media/engine/sp.lp");
				JLabel lbl = new JLabel(ico);
				lbl.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
				
				JFrame f = new JFrame();
				f.setSize(lbl.getSize());
				f.setUndecorated(true);
				f.getContentPane().add(lbl);
				f.setLocationRelativeTo(null);
				f.setDefaultCloseOperation(EXIT_ON_CLOSE);
				f.setVisible(true);
				
				while(!isVisible());
				f.setVisible(false);
				
				f.dispose();
			}
		}.start();
	}
	
	class KA extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			keys[e.getKeyCode()] = true;
		}
		
		public void keyTyped(KeyEvent e){
			keys[e.getKeyCode()] = true;
		}
		
		public void keyReleased(KeyEvent e){
			keys[e.getKeyCode()] = false;
		}
	}
	
}
