
//Designed by Soumyadeep Ghosh

package dragon_egg;

import java.util.Random;

import javax.swing.ImageIcon;

import com.threecoffee.anim.Actor;
import com.threecoffee.anim.Sprite;

class Dragon extends Actor {
	
	boolean escaped=false, dropped=false;
	int vel=15;
	int dropPoint;
	Random r;
	
	
	public Dragon(){
		r = new Random();
		dropPoint = 100+r.nextInt(400-getWidth());
		
		this.addImage(new ImageIcon("media/dragon egg/dragon/1.jpg"));
		this.addImage(new ImageIcon("media/dragon egg/dragon/2.jpg"));
		this.addImage(new ImageIcon("media/dragon egg/dragon/3.jpg"));
		this.addImage(new ImageIcon("media/dragon egg/dragon/4.jpg"));
		
		this.setDelay(100);
		this.play();
	}
	
	@Override
	public void update(){
		if(!escaped){
			if(!dropped && getX()>dropPoint){
				Egg e = new Egg();
				e.setLocation(getX()+getWidth()-20, 70);
				e.addTo(this.getGameWindow());
				e.addCollider(((Game)this.getGameWindow()).w);
				e.addCollider(((Game)this.getGameWindow()).track);
				e.play();
				
				dropped = true;
			}
			
			this.moveSprite(vel, 0);
		}
		
		if(getX() > getGameWindow().getWidth()){
			escaped = true;
			setLocation(-getWidth(), 0);
			
			if(r.nextBoolean())
				vel += r.nextInt(2);
			
			dropPoint = 100+r.nextInt(getGameWindow().getWidth()-getWidth()-150);
			dropped = false;
			this.setDelay(1000/vel);
			
			new Return(r.nextInt(1000));
		}
	}
	
	
	class Return extends Thread{
		
		long delay;
		
		Return(long l){
			delay = l;
			start();
		}
		
		public void run(){
			try {
				sleep(delay);
			} catch (Exception e) {}
			
			escaped = false;
		}
	}
}
