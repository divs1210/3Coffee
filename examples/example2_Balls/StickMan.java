/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package example2_Balls;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.threecoffee.anim.Actor;
import com.threecoffee.anim.Animation;
import com.threecoffee.control.GameWindow;

/**
 * Represents a stickman character controlled by
 * the player.
 * 
 * @author Divyansh Prakash
 */
public class StickMan extends Actor {

	StickMan(){
		setName("stickman");

		Animation[] anims = new Animation[4];
		
		anims[0] = new Animation();
		anims[0].setName("stand");
		anims[0].setDelay(45);
		anims[0].add(new ImageIcon("media/balls/sprites/player/stand/stand_1.png"));
		anims[0].add(new ImageIcon("media/balls/sprites/player/stand/stand_2.png"));
		
		anims[1] = new Animation();
		anims[1].setName("run_right");
		anims[1].setDelay(45);
		anims[1].add(new ImageIcon("media/balls/sprites/player/run/run_right_1.png"));
		anims[1].add(new ImageIcon("media/balls/sprites/player/run/run_right_2.png"));
		anims[1].add(new ImageIcon("media/balls/sprites/player/run/run_right_3.png"));
		
		anims[2] = new Animation();
		anims[2].setName("run_left");
		anims[2].setDelay(35);
		anims[2].add(new ImageIcon("media/balls/sprites/player/run/run_left_1.png"));
		anims[2].add(new ImageIcon("media/balls/sprites/player/run/run_left_2.png"));
		anims[2].add(new ImageIcon("media/balls/sprites/player/run/run_left_3.png"));
	
		anims[3] = new Animation();
		anims[3].setName("die");
		anims[3].add(new ImageIcon("media/balls/sprites/player/die/1.png"));
		
		addAnimation(anims[0]);
		addAnimation(anims[1]);
		addAnimation(anims[2]);
		addAnimation(anims[3]);
		
		this.setCurrentAnimation(0);
	}
	
	@Override
	public void update(){
		if(GameWindow.isKeyDown(KeyEvent.VK_RIGHT) && !Game.ended){
			moveSprite(15, 0);
			setCurrentAnimation(1);
		}else if(GameWindow.isKeyDown(KeyEvent.VK_LEFT) && !Game.ended){
			moveSprite(-15, 0);
			setCurrentAnimation(2);
		}else if(!Game.ended)
			setCurrentAnimation(0);
		
		if(getX()>getGameWindow().getWidth()-getWidth())
			setLocation(getGameWindow().getWidth()-getWidth(), getY());
		else if(getX()<0)
			setLocation(0, getY());
	}
	
}
