package Exercise3.wrapper;

import Exercise3.filter.ImgCropFilter;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Christoph on 24.11.2017.
 */
public class ImgCropFilterWrapper implements Writeable<PlanarImage>, PlanarImageListener, Serializable {

    private ImgCropFilter imgCropFilter;
    private PlanarImage image;
    private Vector listeners;

    public ImgCropFilterWrapper() {
        this.imgCropFilter = new ImgCropFilter(this);
        this.listeners = new Vector();
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        this.image = image.getImage();
        imgCropFilter.process(image.getImage());

        PlanarImageEvent imageEvent = new PlanarImageEvent(this, this.image);
        for (Object el : listeners) {
            PlanarImageListener imageListener = (PlanarImageListener) el;
            imageListener.imageChangedEvent(imageEvent);
        }
    }

    public void addPlanarImageListener(PlanarImageListener pl) {
        listeners.add(pl);
    }

    public void removePlanarImageListener(PlanarImageListener pl) {
        listeners.remove(pl);
    }

    @Override
    public void write(PlanarImage value) throws IOException {

    }
}
