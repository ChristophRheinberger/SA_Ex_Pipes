package Exercise1B;

import pmp.filter.Sink;

import java.io.*;
import java.nio.CharBuffer;

/*
 * a simple sink: takes passively objects or pulls actively objects without doing anything with the stream objects
 * derive your sink from this simple sink
 *
 * hook method:   	write(T): void 		which writes a T object somewhere (file, network, ...);
 *                                      a default version is supplied which does nothing (a dark sink, for testing purposes)
 * contract: a null input signals end-of-stream
 */
public class AlignedFileSaveSink extends Sink<Line> {
    protected BufferedWriter bwAligned = null;

    @Override
    public Line read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }

    private BufferedWriter getBw() throws IOException {
        if(bwAligned == null){
            bwAligned = new BufferedWriter(new FileWriter("Aligned.txt"));
        }
        return bwAligned;
    }


    @Override
    public void write(Line value) throws IOException {
        BufferedWriter bwAligned = getBw();
        if ( value != null) {
            if (value.toString() != null) {
                bwAligned.write(value.toString());
                bwAligned.newLine();
            }
        }
        bwAligned.flush();
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}