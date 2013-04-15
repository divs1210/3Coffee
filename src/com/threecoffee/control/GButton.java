/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Can be used to create a button using two images.
 * 
 * @author Divyansh Prakash
 */
public class GButton extends JLabel {
	
	/**
	 * Creates a button using two images.
	 */
	public GButton(final ImageIcon i1, final ImageIcon i2){
		super(i1);
		
		setSize(i1.getIconWidth(), i1.getIconHeight());
		
		addMouseListener(new MouseAdapter(){

			public void mouseEntered(MouseEvent arg0) {
				setIcon(i2);
			}

			public void mouseExited(MouseEvent arg0) {
				setIcon(i1);
			}
			
		});
	}
	
}
