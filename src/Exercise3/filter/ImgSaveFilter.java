package Exercise3.filter;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgSaveFilter extends DataTransformationFilter1<PlanarImage> {
    public ImgSaveFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ImgSaveFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ImgSaveFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected void process(PlanarImage entity) {
        // Save Image to FIle
        File myNewPNGFile = new File("loetstellen_punkte.jpg");

        try {
            ImageIO.write(entity, "JPG", myNewPNGFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
