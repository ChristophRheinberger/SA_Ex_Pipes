package Exercise3.wrapper;

import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Clemens on 26.11.2017.
 */
public class DisplayWrapper extends Canvas implements Serializable, Writeable<PlanarImage>, PlanarImageListener {

    private BufferedImage img;

    public DisplayWrapper(){
        setSize(438, 316);
    }

    @Override
    public void write(PlanarImage value) throws IOException {
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        img = image.getImage().getAsBufferedImage();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (img != null) {
            int x = (getWidth() - img.getWidth()) / 2;
            int y = (getHeight() - img.getHeight()) / 2;
            setSize(img.getWidth(), img.getHeight());
            g.drawImage(img, x, y, this);
        }
    }

}
