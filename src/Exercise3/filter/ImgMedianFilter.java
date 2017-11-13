package Exercise3.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgMedianFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {
    public ImgMedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ImgMedianFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ImgMedianFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        return entity = MedianFilterDescriptor.create(entity, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 6, null);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
