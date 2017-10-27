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
    protected BufferedWriter bw = null;

    @Override
    public ArrayList<String> read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }

    private BufferedWriter getBw() throws IOException {
        if(bw == null){
            bw = new BufferedWriter(new FileWriter("D:\\Schule\\FHV\\Semester5\\SA\\SA_Ex_1\\BookIndex.txt"));
        }
        return bw;
    }


    @Override
    public void write(ArrayList<String> value) throws IOException {

        BufferedWriter bw = getBw();

        String indexString = new String();

        if (value != null) {
            for (int i = 0; i < value.size(); i++) {
                if (value.get(i) != null) {
                    indexString = indexString.concat(value.get(i));
                    indexString.concat(System.lineSeparator());
                    System.out.println(indexString);
                    bw.write(value.get(i));
                    bw.newLine();
                }
                bw.flush();
            }
        }
    }
}