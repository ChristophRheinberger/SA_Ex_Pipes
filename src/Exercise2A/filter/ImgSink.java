package Exercise2A.filter;

import Exercise1B.Line;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;
import pmp.filter.Coordinate;
import pmp.filter.Sink;

import pmp.interfaces.Readable;
import javax.media.jai.*;
import java.io.*;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ImgSink extends Sink<ArrayList<Coordinate>> {
    private ArrayList<Coordinate> expectedValues;
    private int tolerance;
    private int expectedRadius;

    public ImgSink (Readable<ArrayList<Coordinate>> input, ArrayList<Coordinate> expectedValues,  int expectedRadius, int tolerance ) throws InvalidParameterException {
        super(input);
        this.expectedValues = expectedValues;
        this.expectedRadius = expectedRadius;
        this.tolerance = tolerance;
    }

    public ImgSink (ArrayList<Coordinate> expectedValues, int expectedRadius, int tolerance) {
        this.expectedValues = expectedValues;
        this.expectedRadius = expectedRadius;
        this.tolerance = tolerance;
    }

    public void write(ArrayList<Coordinate> value) throws IOException {
        ArrayList<Pair<Integer, Integer>> actualCentroids = new ArrayList<>();
        if (value != null) {
            for (int i = 0; i < value.size(); i++) {
                if ((value.get(i)._x >= expectedValues.get(i)._x - tolerance && value.get(i)._x <= expectedValues.get(i)._x + tolerance)
                        && ( value.get(i)._y >= expectedValues.get(i)._y - tolerance && value.get(i)._y <= expectedValues.get(i)._y + tolerance)) {
                    actualCentroids.add(new Pair(0,0));
                } else if (value.get(i)._x < expectedValues.get(i)._x - tolerance && value.get(i)._x > expectedValues.get(i)._x + tolerance) {
                    if (value.get(i)._y >= expectedValues.get(i)._y - tolerance && value.get(i)._y <= expectedValues.get(i)._y + tolerance) {
                        actualCentroids.add(new Pair((expectedValues.get(i)._x - value.get(i)._x), 0));
                    } else {
                        actualCentroids.add(new Pair((expectedValues.get(i)._x - value.get(i)._x), (expectedValues.get(i)._y - value.get(i)._y)));
                    }
                } else {
                    if (value.get(i)._x >= expectedValues.get(i)._x - tolerance && value.get(i)._x <= expectedValues.get(i)._x + tolerance) {
                        actualCentroids.add(new Pair(0, (expectedValues.get(i)._y - value.get(i)._y)));
                    } else {
                        actualCentroids.add(new Pair((expectedValues.get(i)._x - value.get(i)._x), (expectedValues.get(i)._y - value.get(i)._y)));
                    }
                }
            }

            PrintWriter writer = new PrintWriter("Centriods.txt", "UTF-8");
            for(int i = 0; i < expectedValues.size(); i++){
                if (actualCentroids.get(i).getKey().equals(0) && actualCentroids.get(i).getValue().equals(0)) {
                    writer.write("Lötstelle " + (i+1) + ": Erwartete Koordinate = " + expectedValues.get(i) +
                            "  Tatsächliche Koordinate: " + value.get(i) + "  Erwarteter Radius: " + expectedRadius + "  Tatsächlicher Radius: " + value.get(i)._radius
                            + "  | Im Toleranzbereich"  +  System.lineSeparator());
                } else {
                    if(!(actualCentroids.get(i).getKey().equals(0) && actualCentroids.get(i).equals(0))) {
                        writer.write("Lötstelle " + (i+1) + ": Erwartete Koordinate = " + expectedValues.get(i) +
                                "  Tatsächliche Koordinate: " + value.get(i) + " y:" + actualCentroids.get(i).getValue() + "  Erwarteter Radius: " + expectedRadius + "  Tatsächlicher Radius: " + value.get(i)._radius
                                + "  | Abweichung des Toleranzbereiches: " + "x:" + actualCentroids.get(i).getKey() + System.lineSeparator());
                    } else if(!actualCentroids.get(i).getValue().equals(0)) {
                        writer.write("Lötstelle " + (i+1) + ": Erwartete Koordinate = " + expectedValues.get(i) +
                                "  Tatsächliche Koordinate: " + value.get(i) + " y:" + actualCentroids.get(i).getValue() + "  Erwarteter Radius: " + expectedRadius + "  Tatsächlicher Radius: " + value.get(i)._radius
                                + "  | Abweichung des Toleranzbereiches: " + "x:" + actualCentroids.get(i).getKey() + System.lineSeparator());

                    } else if(!actualCentroids.get(i).getValue().equals(0)) {
                        writer.write("Lötstelle " + (i+1) + ": Erwartete Koordinate = " + expectedValues.get(i) +
                                "  Tatsächliche Koordinate: " + value.get(i) + " y:" + actualCentroids.get(i).getValue() + "  Erwarteter Radius: " + expectedRadius + "  Tatsächlicher Radius: " + value.get(i)._radius
                                + "  | Abweichung des Toleranzbereiches: " + "x:" + actualCentroids.get(i).getKey() + System.lineSeparator());
                    }
                }
            }
            writer.close();
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }

    @Override
    public ArrayList<Coordinate> read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }
}
