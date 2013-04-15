/*
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/ or send 
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 */

package com.threecoffee.media;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Provides methods to convert a java.awt.Imaage instance into a java.awt.image.BufferedImage
 */
public class BufferedImageBuilder {

    private static final int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;

    /**
     * Returns a BufferedImage with the default settings
     */
    public BufferedImage bufferImage(Image image) {
        return bufferImage(image, DEFAULT_IMAGE_TYPE);
    }

    /**
     * Returns a BufferedImage with the type parameter
     * defined by the user. The default type value is
     * BufferedImage.TYPE_INT_RGB
     */
    public BufferedImage bufferImage(Image image, int type) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, null, null);
        waitForImage(bufferedImage);
        return bufferedImage;
    }

    private void waitForImage(BufferedImage bufferedImage) {
        final ImageLoadStatus imageLoadStatus = new ImageLoadStatus();
        bufferedImage.getHeight(new ImageObserver() {
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                if (infoflags == ALLBITS) {
                    imageLoadStatus.heightDone = true;
                    return true;
                }
                return false;
            }
        });
        bufferedImage.getWidth(new ImageObserver() {
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                if (infoflags == ALLBITS) {
                    imageLoadStatus.widthDone = true;
                    return true;
                }
                return false;
            }
        });
        while (!imageLoadStatus.widthDone && !imageLoadStatus.heightDone) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }
        }
    }

    class ImageLoadStatus {
        public boolean widthDone = false;
        public boolean heightDone = false;
    }

}