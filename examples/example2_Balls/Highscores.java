/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package example2_Balls;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;

import com.threecoffee.control.GameWindow;
import com.threecoffee.util.Logger;

/**
 * Stores and displays highscores at the end of every game.
 * 
 * @author Divyansh Prakash
 */
public class Highscores implements Serializable {
	
	int[] time;
	int num=-1;
	String[] name;
	
	Highscores h;
	JButton b;
	
	Highscores(){
		time = new int[10];
		name = new String[10];
		
		File f = new File("media/balls/scores.obj");
		
		if(f.exists()){
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("media/balls/scores.obj"));
				
				Object o = ois.readObject();
				h = (Highscores)o;
				
				for(int i=0; i<10; i++){
					time[i] = h.time[i];
					name[i] = h.name[i];
				}
				
				ois.close();
			} catch (Exception e) {
				Logger.log(e);
			}
		}
	}
	
	public void addScore(int score){
		int i=0;
		String player;
		
		for(i=0; i<10; i++)
			if(time[i] < score)
			{
				num = i;
				
				player = ""+JOptionPane.showInputDialog("You made a HIGHSCORE! Enter your name: ");
				
				for(int j=9; j>i; j--){
					time[j] = time[j-1];
					name[j] = name[j-1];
				}
				
				time[i] = score;
				name[i] = player;
				
				save();
				
				break;
			}
	}
	
	public void save(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("media/balls/scores.obj"));
			
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			Logger.log(e);
		}
	}
	
	public void display(){
		final JFrame f = new JFrame("Highscores");
		f.setResizable(false);
		f.setAlwaysOnTop(true);
		f.setSize(350, 350);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		Container cp = f.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.WHITE);
		
		JLabel[] lbl = new JLabel[11];
		
		lbl[0] = new JLabel("Player                      Score");
		lbl[0].setBounds(0, 10, 350, 20);
		lbl[0].setHorizontalAlignment(SwingConstants.CENTER);
		lbl[0].setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl[0].setForeground(new Color(240, 70, 80));
		cp.add(lbl[0]);
		
		for(int i=1; i<11; i++){
			lbl[i] = new JLabel("       "+i+".         "+name[i-1]+"                                     "+time[i-1]);
			lbl[i].setBounds(0, 30+20*i, 350, 20);
			lbl[i].setFont(new Font("Times New Roman", Font.PLAIN, 16));
			lbl[i].setHorizontalAlignment(SwingConstants.LEFT);
			cp.add(lbl[i]);
		}
		
		lbl[10].setText("       "+"10"+".       "+name[9]+"                                     "+time[9]);
		
		if(num!=-1)
			lbl[num+1].setForeground(Color.BLUE);
		
		b = new JButton("OK");
		b.setBounds(125, 270, 100, 20);
		b.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		cp.add(b);
		
		f.setVisible(true);
	}
}
