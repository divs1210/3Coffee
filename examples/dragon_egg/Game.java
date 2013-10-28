
//Designed by Soumyadeep Ghosh

package dragon_egg;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.threecoffee.anim.Sprite;
import com.threecoffee.control.GameWindow;
public class Game extends GameWindow {

	Wagon w;
	Dragon d;
	Sprite track;
	public JLabel score_display, life_left;
	
	public static int score=0;
	public static int no_breaks;
	
	ImageIcon bar=new ImageIcon("media/dragon egg/bar.jpg");
	
	public Game(){
		super(600, 300);
		
		Color col=new Color(241, 181, 110);
		this.getContentPane().setBackground(col);
		
		no_breaks=0;
		
		life_left=new JLabel("Life Left: 3");
		life_left.setLocation(500,5);
		life_left.setSize(100, 20);
		this.add(life_left);
		
		score_display=new JLabel("Score: 0");
		score_display.setLocation(5,5);
		score_display.setSize(100, 20);
		this.add(score_display);
		
		//add dragon to the frame
		d = new Dragon();
		d.setLocation(-d.getWidth(), 0);
		d.addTo(this);
		
		//add wagon to the frame
		w=new Wagon();
		w.setLocation(0, getHeight()-w.getHeight()-30);
		w.addTo(this);
		
		//add track to the frame, used to get the base of frame
		track = new Sprite();
		track.addImage(bar);
		track.addTo(this);
		track.setLocation(0, getHeight()-20);
		track.play();
		
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new Game();
	}
}
