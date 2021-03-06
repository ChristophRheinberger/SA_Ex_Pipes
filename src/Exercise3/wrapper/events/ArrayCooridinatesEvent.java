package Exercise3.wrapper.events;

import pmp.filter.Coordinate;

import java.util.ArrayList;
import java.util.EventObject;

public class ArrayCooridinatesEvent extends EventObject {
    private ArrayList<Coordinate> _coordinates;

    public ArrayCooridinatesEvent(Object source, ArrayList<Coordinate> coordinateArrayList) {
        super(source);
        this._coordinates = coordinateArrayList;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return _coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> _coordinates) {
        this._coordinates = _coordinates;
    }
}
