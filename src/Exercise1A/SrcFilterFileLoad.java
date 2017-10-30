package Exercise1A;

import Exercise1B.Line;
import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Created by Christoph on 23.10.2017.
 */
public class SrcFilterFileLoad extends Source<Line>{
    public BufferedReader br = null;
    private String file;

    public SrcFilterFileLoad(Writeable<Line> output, String file) {
        super(output);
        this.file = file;
    }

    private BufferedReader getBr() throws FileNotFoundException {
        if(br == null){
            br = new BufferedReader(new FileReader("aliceInWonderland.txt"));
        }
        return br;
    }

    @Override
    public Line read() throws FileNotFoundException {
        String string = null;
        Line line = new Line();
        BufferedReader buffReader = getBr();
        try {
            while((string = buffReader.readLine()) != null) {
                line.setLine(string);
                return line;
            }
            return null;
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
