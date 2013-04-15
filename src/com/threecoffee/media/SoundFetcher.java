/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.media;

import java.applet.Applet;

import java.applet.AudioClip;
import java.io.File;

import com.threecoffee.util.Logger;

/**
 * Provides a static method to get an AudioClip instance
 * from a given .wav/.au file.
 * 
 * @author Divyansh Prakash
 */
public class SoundFetcher {

	/**
	 * Returns an AudioClip object instance
	 * from the given .wav/.au file.
	 */
	public static AudioClip getSound(final String url) {
		AudioClip c = null;
    	
    	try {
			c = Applet.newAudioClip(new File(url).toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log(e);
		}
    	
    	return c;
      }
	
}
