package Exercise3.wrapper;

import Exercise3.filter.ImgDilateFilter;
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
public class ImgDilateFilterWrapper implements Writeable<PlanarImage>, PlanarImageListener, Serializable {

    private ImgDilateFilter imgDilateFilter;
    private PlanarImage image;
    private Vector listeners;
    private Integer amount = 5;

    public ImgDilateFilterWrapper() {
        this.imgDilateFilter = new ImgDilateFilter(this, amount);
        this.listeners = new Vector();
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        this.image = imgDilateFilter.process(image.getImage());

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

    public void setAmount(int amount) {
        this.amount = amount;
        imageChangedEvent(new PlanarImageEvent(this, this.image));
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void write(PlanarImage value) throws IOException {

    }
}
