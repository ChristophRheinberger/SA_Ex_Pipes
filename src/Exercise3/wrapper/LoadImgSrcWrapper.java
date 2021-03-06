package Exercise3.wrapper;

import Exercise3.filter.LoadImgSrc;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Vector;

/**
 * Created by Christoph on 13.11.2017.
 */
public class LoadImgSrcWrapper implements Serializable, Writeable<PlanarImage> {

    private LoadImgSrc imgSrc;
    private PlanarImage image;
    private String imgUrl = "loetstellen.jpg";
    private Vector listeners;

    public LoadImgSrcWrapper () {
        listeners = new Vector();
        imgSrc = new LoadImgSrc(this);
    }

    @Override
    public void write(PlanarImage value) throws IOException {

    }

    public synchronized void imageChangedEvent() {

        try {
            this.image = imgSrc.read(imgUrl);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PlanarImageEvent imageEvent = new PlanarImageEvent(this, this.image);
        for ( Object pl : listeners ) {
            PlanarImageListener imageListener = (PlanarImageListener) pl;
            imageListener.imageChangedEvent(imageEvent);
        }
    }

    public void addPlanarImageListener(PlanarImageListener pl) {
        listeners.add(pl);
    }

    public void removePlanarImageListener(PlanarImageListener pl) {
        listeners.remove(pl);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        imageChangedEvent();
    }
}
