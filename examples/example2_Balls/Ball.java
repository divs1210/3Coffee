/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package example2_Balls;

import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.threecoffee.anim.Actor;
import com.threecoffee.anim.Sprite;

/**
 * Represents a flying, burning ball that bounces off the sides
 * of the window. The player dies if it comes in contact with a
 * Ball object. 
 * 
 * @author Divyansh Prakash
 */
public class Ball extends Sprite {

	int xvel=1, yvel=1;
	
	Ball(){
		setRandomVel();
		
		addImage(new ImageIcon("media/balls/sprites/ball/1.png"));
		addImage(new ImageIcon("media/balls/sprites/ball/2.png"));
		addImage(new ImageIcon("media/balls/sprites/ball/3.png"));
		setDelay(5);
	}
	
	@Override
	public void update(){
		moveSprite(xvel, yvel);
		
		if(getX()+getWidth()>=getGameWindow().getWidth() || getX()<=0)
			xvel *= -1;
		if(getY()+getHeight()>=getGameWindow().getHeight()-20 || getY()<=0)
			yvel *= -1;
	}
	
	@Override
	public void collided(Sprite s){
		if(Math.abs((s.getX()+s.getWidth()/2)-(getX()+getWidth()/2))<=20 && getY()+getHeight()>=s.getY()+10){
			
			Game.curr = new Date();
			Game.time = (int) (Math.abs(Game.init.getTime() - Game.curr.getTime())/1000);
			
			getGameWindow().pause(true);
			
			((Actor)s).setCurrentAnimation(3);
			s.pause(false);
			
			Game.ended = true;
			s.setGravity(true);
			s.getGravity().setDelay(50);
			
			getGameWindow().setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(getGameWindow(), "You survived for "+(Game.time-Game.ptime)+" seconds.");
			
			Highscores h = new Highscores();
			h.addScore(Game.time-Game.ptime);
			h.display();
		}
	}
	
	public void setVel(int x, int y){
		xvel = x;
		yvel = y;
	}
	
	public double getVel(){
		return Math.sqrt(xvel*xvel + yvel*yvel);
	}
	
	public void setRandomVel(){
		Random r = new Random();
		xvel = r.nextInt(2)+1;
		yvel = r.nextInt(2)+1;
		
		if(r.nextBoolean())
			xvel *= -1;
		if(r.nextBoolean())
			yvel *= -1;
	}
	
}
