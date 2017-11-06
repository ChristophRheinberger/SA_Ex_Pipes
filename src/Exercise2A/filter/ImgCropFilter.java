package Exercise2A.filter;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgCropFilter extends DataTransformationFilter1<PlanarImage> {

    public ImgCropFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ImgCropFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ImgCropFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }

    @Override
    protected void process(PlanarImage entity) {

        int xOffset = 0;
        int yOffset = 50;

        Rectangle rectangleCut = new Rectangle(xOffset, yOffset, entity.getWidth(), entity.getHeight()/5);

        entity = PlanarImage.wrapRenderedImage(entity.getAsBufferedImage(rectangleCut, entity.getColorModel()));
    }
}
