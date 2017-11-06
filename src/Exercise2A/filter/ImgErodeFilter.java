package Exercise2A.filter;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.ErodeDescriptor;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgErodeFilter extends DataTransformationFilter1<PlanarImage> {

    int amountErode;

    public ImgErodeFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, int amount) throws InvalidParameterException {
        super(input, output);
        this.amountErode = amount;
    }

    public ImgErodeFilter(Readable<PlanarImage> input, int amount) throws InvalidParameterException {
        super(input);
        this.amountErode = amount;
    }

    public ImgErodeFilter(Writeable<PlanarImage> output, int amount) throws InvalidParameterException {
        super(output);
        this.amountErode = amount;
    }

    @Override
    protected void process(PlanarImage entity) {
        for (int i = 0; i < amountErode; i++) {
            entity = ErodeDescriptor.create(entity, KernelJAI.ERROR_FILTER_FLOYD_STEINBERG, null);
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
