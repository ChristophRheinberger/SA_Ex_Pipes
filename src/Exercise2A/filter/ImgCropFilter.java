package Exercise2A.filter;

import pmp.filter.DataTransformationFilter2;
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
public class ImgCropFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

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
    protected PlanarImage process(PlanarImage entity) {
        int xOffset = 0;
        int yOffset = 50;

        Rectangle rectangleCut = new Rectangle(xOffset, yOffset, entity.getWidth(), entity.getHeight()/5);


        return entity = PlanarImage.wrapRenderedImage(entity.getAsBufferedImage(rectangleCut, entity.getColorModel()));
    }
}
