package Exercise3.wrapper;

import Exercise3.filter.ImgSaveFilter;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Christoph on 20.11.2017.
 */
public class ImgSaveWrapper implements Writeable<PlanarImage>, PlanarImageListener, Serializable {

    private ImgSaveFilter imgSaveFilter;
    private PlanarImage image;
    private Vector listeners;

    public ImgSaveWrapper () {
        listeners = new Vector();
        this.imgSaveFilter = new ImgSaveFilter(this);
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        imgSaveFilter.process(image.getImage());

        PlanarImageEvent imageEvent = new PlanarImageEvent(this, image.getImage());
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
    public void write(PlanarImage value) throws IOException { }
}
