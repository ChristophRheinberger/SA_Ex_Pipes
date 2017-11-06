package Exercise2A.filter;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgDilateFilter extends DataTransformationFilter1<PlanarImage> {

    int amountDilate;

    public ImgDilateFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, int amount) throws InvalidParameterException {
        super(input, output);
        this.amountDilate = amount;
    }

    public ImgDilateFilter(Readable<PlanarImage> input, int amount) throws InvalidParameterException {
        super(input);
        this.amountDilate = amount;
    }

    public ImgDilateFilter(Writeable<PlanarImage> output, int amount) throws InvalidParameterException {
        super(output);
        this.amountDilate = amount;
    }

    @Override
    protected void process(PlanarImage entity) {
        for (int i = 0; i < amountDilate; i++) {
            entity = DilateDescriptor.create(entity, KernelJAI.ERROR_FILTER_FLOYD_STEINBERG, null);
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
