/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.util;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides an error-logging facility.
 * 
 * @author Divyansh Prakash
 */
public class Logger {
	
	/**
	 * Writes the error details into the util/log.txt file
	 */
	public static void log(Exception e){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("util/log.txt", true));
			
			bw.write(e+"\n");
			bw.write("\n");
			bw.close();
		} catch (IOException e1) {}
	}

}
