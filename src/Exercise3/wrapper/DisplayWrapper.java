package Exercise3.wrapper;

import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Clemens on 26.11.2017.
 */
public class DisplayWrapper extends Canvas implements Writeable<PlanarImage>, PlanarImageListener {

    private BufferedImage img;

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
            g.drawImage(img, x, y, this);
        }
    }

}
