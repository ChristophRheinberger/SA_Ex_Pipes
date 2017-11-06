package Exercise2A.filter;

import pmp.filter.DataTransformationFilter1;
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
public class ImgMedianFilter extends DataTransformationFilter1<PlanarImage> {
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
    protected void process(PlanarImage entity) {

        entity = MedianFilterDescriptor.create(entity, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5, null);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
