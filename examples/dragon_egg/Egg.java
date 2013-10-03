
//Designed by Soumyadeep Ghosh

package dragon_egg;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.threecoffee.anim.Sprite;

public class Egg extends Sprite {

	static final ImageIcon egg = new ImageIcon("media/dragon egg/egg.jpg");
	static final ImageIcon broken_egg = new ImageIcon("media/dragon egg/broken_egg.jpg");
	boolean broken;
	
	//adding properties to egg
	public Egg(){
		
		broken=false;
		this.addImage(egg);
		this.setGravity(true);
		this.getGravity().setDelay(50);
		this.getGravity().setBounce(0);
		
	}
		
	//egg collision with wagon increase score by 1
	@Override
	public void collided(Sprite s) {
		if(s.getName().equals("wagon")) {
			this.stop();
			this.setVisible(false);
			dragon_egg.Game.score++;
			
			((dragon_egg.Game)this.getGameWindow()).score_display.setText("Score: "+dragon_egg.Game.score);
			
		}
		
		//if wagon misses, egg breaks
		else {
			this.setGravity(false);
			this.getImages().remove(this.getCurrentImage());
			this.addImage(broken_egg);
			
			broken=true;
			dragon_egg.Game.no_breaks++;
			
			((dragon_egg.Game)this.getGameWindow()).life_left.setText("Life left : "+(3-dragon_egg.Game.no_breaks));
			
			//after 3 times breaking of egg, game ends
			if(dragon_egg.Game.no_breaks>=3){
				try{
					getGameWindow().pause(true);
				}catch(Exception e){}
				
				getGameWindow().setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(getGameWindow(), "Score : "+dragon_egg.Game.score);
			   
				
				HighScore h=new HighScore();
				h.addScore(dragon_egg.Game.score);
				h.display();				
			}
		}
		
		getColliders().remove(s);
	}
	
}