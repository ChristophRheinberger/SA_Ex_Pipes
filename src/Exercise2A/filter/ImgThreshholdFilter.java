package Exercise2A.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.ThresholdDescriptor;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgThreshholdFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {
    public ImgThreshholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ImgThreshholdFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ImgThreshholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        return entity= ThresholdDescriptor.create(entity, new double[]{0}, new double []{40}, new double[]{255}, null);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
