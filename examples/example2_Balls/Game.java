/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package example2_Balls;

import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;

import com.threecoffee.anim.Sprite;
import com.threecoffee.control.GameWindow;
import com.threecoffee.media.ImageControl;
import com.threecoffee.util.Logger;

/**
 * This is the main class for Balls.
 * It is a GameWindow with different Actors and Sprites
 * working together.
 * 
 * @author Divyansh Prakash
 */
public class Game extends GameWindow{
	
	public static GameWindow gw;
	public static boolean ended;
	
	public static Date init, curr, pstart, pend;
	public static int time, ptime;
	
	public Game() {
		super(600, 400);
		gw = this;
		ended = false;
		init=curr=null;
		ptime=time=0;
		
		Random r = new Random();
		int bg = r.nextInt(3)+1;
		
		Sprite background = new Sprite();
		background.addImage(ImageControl.resize(new ImageIcon("media/balls/sprites/background/bg"+bg+".jpg"), 600, 400));
		background.setLocation(0, 0);
		
		Ball b = new Ball();
		b.setLocation(r.nextInt(getWidth()-b.getWidth()-5)+5, r.nextInt(getHeight()/2)+5);
		b.yvel = -1;
		b.addTo(this);
		
		Ball b2 = new Ball();
		b2.setLocation(r.nextInt(getWidth()-b.getWidth()-5)+5, r.nextInt(getHeight()/2)+5);
		while(b.getVel()>=2.2 && b2.getVel()>=2.2)
			b2.setRandomVel();
		b2.yvel = -1;
		b2.addTo(this);
		
		Ball b3 = new Ball();
		b3.setLocation(r.nextInt(getWidth()-b.getWidth()-5)+5, r.nextInt(b.getHeight()/2)+5);
 		while((b.getVel()>=2.2 || b2.getVel()>=2.2) && b3.getVel()>=2.2)
			b3.setRandomVel();
		b3.yvel = -1;
		b3.addTo(this);
		
		Cloud c= new Cloud(bg);
		c.setLocation(0, 0);
		c.addTo(this);
		
		Cloud c1= new Cloud(bg);
		c1.setLocation(300, 70);
		c1.addTo(this);
		
		StickMan sm = new StickMan();
		sm.setLocation(0, getHeight()-sm.getHeight()-30);
		sm.addTo(this);
		
		b.addCollider(sm);
		b2.addCollider(sm);
		b3.addCollider(sm);
		
		background.addTo(this);
		
		background.play();
		b.play();
		b2.play();
		b3.play();
		sm.play();
		c.play();
		c1.play();
		
		init = new Date();
		
		setVisible(true);
	}
	
	public void update(){
		if(GameWindow.isKeyDown(KeyEvent.VK_SPACE) && !ended){
			if(!this.isPaused())
				pstart = new Date();
			else{
				pend = new Date();
				ptime += (pend.getTime()-pstart.getTime())/1000;
			}
			
			pause(!this.isPaused());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Logger.log(e);
			}
		}
	}

	public static void main(String[] args){
		Game g = new Game();
	}
	
}
