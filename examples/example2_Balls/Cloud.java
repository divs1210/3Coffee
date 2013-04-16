/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package example2_Balls;

import javax.swing.ImageIcon;

import com.threecoffee.anim.Sprite;

/**
 * Represents a cloud that keeps drifting across the game-window
 * at constant speed.
 * 
 * @author Divyansh Prakash
 */
public class Cloud extends Sprite {

	Cloud(int type){
		addImage(new ImageIcon("media/balls/sprites/clouds/cloud"+type+".png"));
	}
	
	@Override
	public void update(){
		if(getX()+getWidth()<=0)
			setLocation(getGameWindow().getWidth(), getY());

		moveSprite(-1, 0);
	}	
	
}
