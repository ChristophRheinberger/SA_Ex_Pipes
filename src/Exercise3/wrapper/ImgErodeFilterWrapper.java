package Exercise3.wrapper;

import Exercise3.filter.ImgErodeFilter;
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
public class ImgErodeFilterWrapper implements Writeable<PlanarImage>, PlanarImageListener, Serializable {

    private ImgErodeFilter imgErodeFilter;
    private PlanarImage saveImage;
    private PlanarImage image;
    private Vector listeners;
    private Integer amount;

    public ImgErodeFilterWrapper() {
        this.amount = 5;
        this.imgErodeFilter = new ImgErodeFilter(this, amount);
        this.listeners = new Vector();
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        this.saveImage = image.getImage();
        this.image = imgErodeFilter.process(image.getImage());

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
        if (this.saveImage != null) {
            imageChangedEvent(new PlanarImageEvent(this, saveImage));
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void write(PlanarImage value) throws IOException {

    }
}
