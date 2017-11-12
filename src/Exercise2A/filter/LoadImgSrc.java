package Exercise2A.filter;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;
import pmp.interfaces.Readable;

/**
 * Created by Christoph on 06.11.2017.
 */
public class LoadImgSrc extends Source<PlanarImage> {

    boolean sent = false;

    public LoadImgSrc(Readable<PlanarImage> input){
    }

    public LoadImgSrc(){}

    public LoadImgSrc (Writeable<PlanarImage> output) {
        super(output);
    }

    @Override
    public PlanarImage read() throws StreamCorruptedException, FileNotFoundException {
        if(!sent) {
            PlanarImage image = JAI.create("fileload", "loetstellen.jpg");
            sent = true;
            return image;
        } else {
            return null;
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
