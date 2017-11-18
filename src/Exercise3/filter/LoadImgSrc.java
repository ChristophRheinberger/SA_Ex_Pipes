package Exercise3.filter;

import pmp.filter.Source;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;

/**
 * Created by Christoph on 06.11.2017.
 */
public class LoadImgSrc extends Source<PlanarImage> {

    boolean sent = false;
    String imgUrl;

    public LoadImgSrc(Readable<PlanarImage> input){
        this.imgUrl = imgUrl;
    }

    public LoadImgSrc(){}

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

    public LoadImgSrc (Writeable<PlanarImage> output) {
        super(output);
    }

    public PlanarImage read(String imgUrl) throws StreamCorruptedException, FileNotFoundException {
        if(!sent) {
            PlanarImage image = JAI.create("fileload", imgUrl);
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
