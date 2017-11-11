package Exercise1B;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;

/**
 * Created by ClemensB on 28.10.17.
 */
public class SrcCharFilter extends Source<Character> {

    private FileReader fr;
    private String file;

    private StringBuilder sb = new StringBuilder();

    public SrcCharFilter(Writeable<Character> output, String file) {
        super(output);
        this.file = file;
    }

    private FileReader getFr() throws FileNotFoundException {
        if(fr == null){
            fr = new FileReader(file);
        }
        return fr;
    }

    @Override
    public Character read() throws StreamCorruptedException, FileNotFoundException {
        int r;
        fr = getFr();
        try {
            while((r = fr.read()) != -1){
                return((char) r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
