package Exercise3.wrapper;

import Exercise3.filter.ImgSink;
import Exercise3.wrapper.events.ArrayCooridinatesEvent;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.ArrayListListener;
import Exercise3.wrapper.interfaces.PlanarImageListener;
import pmp.filter.Coordinate;
import pmp.interfaces.Writeable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class ImgSinkWrapper implements ArrayListListener, Serializable {

    private int tolerance;
    private int expectedRadius;
    private ImgSink imgSink;
    private ArrayList<Coordinate> coordinates;
    private Vector listeners;

    public ImgSinkWrapper() {
        this.imgSink = new ImgSink(getExpectedCentroids(), expectedRadius, tolerance);
        this.coordinates = getExpectedCentroids();
        this.listeners = new Vector();
    }

    @Override
    public void coordinatesChangedEvent(ArrayCooridinatesEvent image) {
        this.coordinates = coordinates;
        try {
            imgSink.write(coordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayCooridinatesEvent arrayCooridinatesEvent = new ArrayCooridinatesEvent(this, this.coordinates);
        for (Object el : listeners) {
            ArrayListListener imageListener = (ArrayListListener) el;
            imageListener.coordinatesChangedEvent(arrayCooridinatesEvent);
        }
    }

    public void addArrayListListener(ArrayListListener pl) {
        listeners.add(pl);
    }

    public void removeArrayListListener(ArrayListListener pl) {
        listeners.remove(pl);
    }

    public static ArrayList<Coordinate> getExpectedCentroids() {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(73, 77));
        coordinates.add(new Coordinate(110, 80));
        coordinates.add(new Coordinate(202, 80));
        coordinates.add(new Coordinate(265, 79));
        coordinates.add(new Coordinate(330, 81));
        coordinates.add(new Coordinate(396, 81));
        return coordinates;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public int getTolerance() {
        return this.tolerance;
    }

    public void setExpectedRadius(int expectedRadius) {
        this.expectedRadius = expectedRadius;
    }

    public int getExpectedRadius() {
        return this.expectedRadius;
    }
}
