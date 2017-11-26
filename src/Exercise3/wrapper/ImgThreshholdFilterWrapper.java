package Exercise3.wrapper;

import Exercise3.filter.ImgThreshholdFilter;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Clemens on 24.11.2017.
 */
public class ImgThreshholdFilterWrapper implements Writeable<PlanarImage>, PlanarImageListener, Serializable {

    private ImgThreshholdFilter imgThresholdFilter;
    private PlanarImage saveImage;
    private PlanarImage image;
    private Vector listeners;

    public ImgThreshholdFilterWrapper() {
        this.imgThresholdFilter = new ImgThreshholdFilter(this);
        this.listeners = new Vector();
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        this.saveImage = image.getImage();
        this.image = imgThresholdFilter.process(image.getImage());

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

    public void setLow(int low) {
        this.imgThresholdFilter.setLow(low);
        if(this.image != null) {
            imageChangedEvent(new PlanarImageEvent(this, saveImage));
        }
    }

    public int getLow() {
        return imgThresholdFilter.getLow();
    }

    public void setHigh(int high) {
        this.imgThresholdFilter.setHigh(high);
        if (this.saveImage != null) {
            imageChangedEvent(new PlanarImageEvent(this, saveImage));
        }
    }

    public int getHigh() {
        return this.imgThresholdFilter.getHigh();
    }

    public void setTarget(int target) {
        this.imgThresholdFilter.setTarget(target);
        if (saveImage != null) {
            imageChangedEvent(new PlanarImageEvent(this, saveImage));
        }
    }

    public int getTarget() {
        return this.imgThresholdFilter.getTarget();
    }

    @Override
    public void write(PlanarImage value) throws IOException {

    }
}
