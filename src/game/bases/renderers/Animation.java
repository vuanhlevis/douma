package game.bases.renderers;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Arrays;

/**
 * Created by VALV on 7/25/2017.
 */
public class Animation implements Renderer {

    private List<BufferedImage> images;
    private int imageIndex;
    private FrameCounter frameCounter;
    public Animation(int delayFrame, BufferedImage... imageArr) {
        this.images = Arrays.asList(imageArr);
        this.frameCounter = new FrameCounter(delayFrame);
    }
    @Override
    public void render(Graphics g2d, Vector2D position) {
        if (frameCounter.run()) {
            changeIndex();
            frameCounter.reset();
        }

        BufferedImage image = images.get(imageIndex);
        g2d.drawImage(image,(int)position.x - image.getWidth() / 2,(int) position.y - image.getHeight() / 2, null);
    }

    public Animation (BufferedImage... imageArr) {
        this(3,imageArr);
    }

    private void changeIndex() {
        imageIndex ++;
        if (imageIndex >= images.size()) {
            imageIndex = 0;
        }
    }
}
