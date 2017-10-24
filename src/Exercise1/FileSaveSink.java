package Exercise1;

import pmp.filter.Sink;

import java.io.*;
import java.util.ArrayList;

/*
 * a simple sink: takes passively objects or pulls actively objects without doing anything with the stream objects
 * derive your sink from this simple sink
 *
 * hook method:   	write(T): void 		which writes a T object somewhere (file, network, ...);
 *                                      a default version is supplied which does nothing (a dark sink, for testing purposes)
 * contract: a null input signals end-of-stream
 */
public class FileSaveSink extends Sink<ArrayList<String>> {

    @Override
    public ArrayList<String> read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }

    @Override
    public void write(ArrayList<String> value) throws IOException {
        File file = new File("BookIndex.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        String indexString = null;

        for (String s : value) {
            indexString = indexString.concat(s);
            indexString.concat(System.lineSeparator());
        }

        bw.write(indexString);
    }
}