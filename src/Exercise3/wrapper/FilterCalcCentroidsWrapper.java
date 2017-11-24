package Exercise3.wrapper;

import Exercise3.filter.FilterCalcCentroids;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.filter.Coordinate;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Clemens on 24.11.2017.
 */
public class FilterCalcCentroidsWrapper implements Writeable<ArrayList<Coordinate>>, PlanarImageListener, Serializable {
    private FilterCalcCentroids filterCalcCentroids;
    private PlanarImage image;
    private Vector listeners;

    public FilterCalcCentroidsWrapper() {
        this.filterCalcCentroids = new FilterCalcCentroids(this);
        this.listeners = new Vector();
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        this.image = image.getImage();
        filterCalcCentroids.process(image.getImage());

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
    public void write(ArrayList<Coordinate> value) throws IOException {

    }
}
