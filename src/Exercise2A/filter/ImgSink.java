package Exercise2A.filter;

import Exercise1B.Line;
import pmp.filter.Coordinate;
import pmp.filter.Sink;

import javax.media.jai.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;
import java.util.ArrayList;

public class ImgSink extends Sink<ArrayList<Coordinate>> {

    @Override
    public void write(ArrayList<Coordinate> value) throws IOException {
        if (value != null) {
            for (Coordinate c : value) {
                System.out.println(c.toString());
            }
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
