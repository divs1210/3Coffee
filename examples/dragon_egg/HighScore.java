//Designed by Soumyadeep Ghosh

package dragon_egg;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;

import com.threecoffee.control.GameWindow;
import com.threecoffee.util.Logger;

public class HighScore implements Serializable {
	
	JLabel score[], name[]; 
	
	int num=-1;
	String name_arr[];
	int score_arr[];
	
	HighScore h;
	JButton b;
	
	HighScore() {
		score_arr = new int[10];
		name_arr = new String[10];
		
		for(int i=0;i<10;i++) {
			score_arr[i]=0;
			name_arr[i]=" ";
		}
		
		File f = new File("media/dragon egg/scores.obj");
		
		//extract highscore vales from highscore object file
		if(f.exists()){
			try {
				FileInputStream file = new FileInputStream("media/dragon egg/scores.obj");
				ObjectInputStream ois = new ObjectInputStream(file);
				
				Object o = ois.readObject();
				h = (HighScore)o;
				
				for(int i=0; i<10; i++){
					score_arr[i] = h.score_arr[i];
					name_arr[i] = h.name_arr[i];
				}
				
				ois.close();
			} catch (Exception e) {
				Logger.log(e);
			}
		}
	}
	
	//check for highscore and update the table
	public void addScore(int sc){
		int i=0;
		String player;
		
		for(i=0; i<10; i++)
			if(score_arr[i] < sc)
			{
				num = i;
				player = ""+JOptionPane.showInputDialog("You made a HIGHSCORE! Enter your name: ");
				
				for(int j=9; j>i; j--){
					score_arr[j] = score_arr[j-1];
					name_arr[j] = name_arr[j-1];
				}
				
				score_arr[i] = sc;
				name_arr[i] = player;
				
				save();
				
				break;
			}
		
	}
	
	// save the table to the file again after updating
	public void save(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("media/dragon egg/scores.obj"));
			
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			Logger.log(e);
		}
	}
	
	//display highscore table
	public void display(){
		final JFrame f = new JFrame("HighScores");
		f.setResizable(false);
		f.setAlwaysOnTop(true);
		f.setSize(350, 350);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		Container cp = f.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.WHITE);
		
		int widthL=100,heightL=50;
		
		score=new JLabel[11];
		name=new JLabel[11];
		
		for(int i=1;i<11;i++) {
			score[i]=new JLabel("0");
			name[i]=new JLabel(" ");
		}
		
		score[0]=new JLabel("SCORE");
		score[0].setBounds(250, 10, widthL, heightL);
		score[0].setHorizontalAlignment(SwingConstants.CENTER);
		score[0].setFont(new Font("Times New Roman", Font.BOLD, 18));
		score[0].setForeground(new Color(0, 0, 0));
		cp.add(score[0]);
		
		name[0]=new JLabel("NAME");
		name[0].setBounds(10, 10, widthL, heightL);
		name[0].setHorizontalAlignment(SwingConstants.LEFT);
		name[0].setFont(new Font("Times New Roman", Font.BOLD, 18));
		name[0].setForeground(new Color(0, 0, 0));
		cp.add(name[0]);
		
		for(int i=1;i<10;i++) {
			
			if(name_arr[i]==null) {
				name_arr[i]=" ";
				score_arr[i]=0;
			}
			String s_label= (score_arr[i-1]==0) ? " " : ""+score_arr[i-1];
			score[i].setText(s_label);
			score[i].setBounds(250, 50+(i*20), widthL, heightL);
			score[i].setHorizontalAlignment(SwingConstants.CENTER);
			score[i].setFont(new Font("Times New Roman", Font.PLAIN, 16));
			score[i].setForeground(new Color(240, 70, 80));
			
			name[i].setText(i+" . "+name_arr[i-1]);
			name[i].setBounds(10, 50+(i*20), widthL, heightL);
			name[i].setHorizontalAlignment(SwingConstants.LEFT);
			name[i].setFont(new Font("Times New Roman", Font.PLAIN, 16));
			name[i].setForeground(new Color(240, 70, 80));
			
			cp.add(score[i]);
			cp.add(name[i]);
		}
		
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