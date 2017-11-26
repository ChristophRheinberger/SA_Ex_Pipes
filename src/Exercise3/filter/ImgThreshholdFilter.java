package Exercise3.filter;

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
    private int low;
    private int high;
    private int target;

    public ImgThreshholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
        low = 0;
        high = 40;
        target = 255;
    }

    public ImgThreshholdFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
        low = 0;
        high = 40;
        target = 255;
    }

    public ImgThreshholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
        low = 0;
        high = 40;
        target = 255;
    }

    @Override
    public PlanarImage process(PlanarImage entity) {
        entity.setProperty("offsetX", 50);
        entity.setProperty("offsetY", 50);

        return entity = ThresholdDescriptor.create(entity, new double[]{low}, new double []{high}, new double[]{target}, null);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getLow() {
        return low;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getHigh() {
        return high;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}
