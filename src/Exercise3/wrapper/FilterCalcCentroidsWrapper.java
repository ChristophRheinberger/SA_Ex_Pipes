package Exercise3.wrapper;

import Exercise3.filter.FilterCalcCentroids;
import Exercise3.wrapper.events.ArrayCooridinatesEvent;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.ArrayListListener;
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
    private ArrayList<Coordinate> coordinates;
    private Vector listeners;

    public FilterCalcCentroidsWrapper() {
        this.filterCalcCentroids = new FilterCalcCentroids(this);
        this.listeners = new Vector();
    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {
        this.coordinates = filterCalcCentroids.process(image.getImage());

        ArrayCooridinatesEvent arrayCooridinatesEvent = new ArrayCooridinatesEvent(this, coordinates);
        for (Object el : listeners) {
            ArrayListListener arrayListListener = (ArrayListListener) el;
            arrayListListener.coordinatesChangedEvent(arrayCooridinatesEvent);
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
