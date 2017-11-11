package Exercise2A.filter;

import Exercise1B.Line;
import pmp.filter.Sink;

import javax.media.jai.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;

public class ImgSink extends Sink<PlanarImage> {

    @Override
    public void write(PlanarImage value) throws IOException {
        if ( value != null) {
            if (value.toString() != null) {

            }
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }

    @Override
    public PlanarImage read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }
}
