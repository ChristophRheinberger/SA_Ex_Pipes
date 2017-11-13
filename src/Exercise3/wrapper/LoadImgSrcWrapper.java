package Exercise3.wrapper;

import Exercise3.filter.LoadImgSrc;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.Vector;

/**
 * Created by Christoph on 13.11.2017.
 */
public class LoadImgSrcWrapper implements Runnable, Writeable<PlanarImage> {

    private LoadImgSrc imgSrc;
    private PlanarImage image;
    private Vector listeners;

    public LoadImgSrcWrapper () {
        imgSrc = new LoadImgSrc(this);
    }

    @Override
    public void write(PlanarImage value) throws IOException {

    }

    @Override
    public void run() {
        imageChangedEvent();
    }

    public void imageChangedEvent() {
        try {
            this.image = imgSrc.read();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PlanarImageEvent imageEvent = new PlanarImageEvent(this, this.image);

        for (Object el : listeners) {
            PlanarImageListener imageListener = (PlanarImageListener) el;
            imageListener.imageChangedEvent(imageEvent);
        }
    }

    public void addImageListener(PlanarImageListener pl) {
        listeners.add(pl);
    }

    public void removeImageListener(PlanarImageListener pl) {
        listeners.remove(pl);
    }

    public LoadImgSrc getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(LoadImgSrc imgSrc) {
        this.imgSrc = imgSrc;
    }

    public PlanarImage getImage() {
        return image;
    }

    public void setImage(PlanarImage image) {
        this.image = image;
    }
}
