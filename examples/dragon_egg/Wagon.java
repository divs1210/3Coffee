
//Designed by Soumyadeep Ghosh 

package dragon_egg;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.threecoffee.anim.Actor;
import com.threecoffee.anim.Animation;
import com.threecoffee.control.GameWindow;

import example2_Balls.Game;

public class Wagon extends Actor {
	
	
	public Wagon() {
		
		setName("wagon");
		Animation[] anims = new Animation[3];
		
		anims[0] = new Animation();
		anims[0].setName("stand");
		anims[0].add(new ImageIcon("media/dragon egg/wagon/1.PNG"));
		
		anims[1] = new Animation();
		anims[1].setName("move");
		anims[1].setDelay(45);
		anims[1].add(new ImageIcon("media/dragon egg/wagon/1.PNG"));
		anims[1].add(new ImageIcon("media/dragon egg/wagon/2.PNG"));
		anims[1].add(new ImageIcon("media/dragon egg/wagon/3.PNG"));
		anims[1].add(new ImageIcon("media/dragon egg/wagon/4.PNG"));
		
		anims[2] = new Animation();
		anims[2].setName("die");
		anims[2].add(new ImageIcon("media/dragon egg/wagon/broken_egg.jpg"));
		
		this.addAnimation(anims[0]);
		this.addAnimation(anims[1]);
		this.addAnimation(anims[2]);
		
		this.setCurrentAnimation(0); 
		
		this.play();
		
	}

	//left-right movement of wagon
	@Override
	public void update(){
		if(GameWindow.isKeyDown(KeyEvent.VK_RIGHT)) {
			moveSprite(15, 0);
			setCurrentAnimation(1);
		}
		else if(GameWindow.isKeyDown(KeyEvent.VK_LEFT)) {
			moveSprite(-15, 0);
			setCurrentAnimation(1);
		}
		else 
			setCurrentAnimation(0);
		
		if(getX()>getGameWindow().getWidth()-getWidth())
			setLocation(getGameWindow().getWidth()-getWidth(), getY());
		else if(getX()<0)
			setLocation(0, getY());
	}

	
}
