package Exercise2A;

import Exercise2A.filter.*;
import pmp.filter.Sink;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;

/**
 * Created by Christoph on 06.11.2017.
 */
public class filterNPipes {

    Sink sink = new Sink() {
        @Override
        public Object read() throws StreamCorruptedException, FileNotFoundException {
            return null;
        }

        @Override
        public int read(CharBuffer cb) throws IOException {
            return 0;
        }
    };

    SimplePipe sinkPipe = new SimplePipe((Writeable) sink);

    ImgSaveFilter imgSaveFilter = new ImgSaveFilter((Writeable<PlanarImage>) sinkPipe);

    SimplePipe savePipe = new SimplePipe((Writeable) imgSaveFilter);

    ImgDilateFilter imgDilateFilter = new ImgDilateFilter((Writeable<PlanarImage>) savePipe, 6);

    SimplePipe dilPipe = new SimplePipe((Writeable) imgDilateFilter);

    ImgErodeFilter imgErodeFilter = new ImgErodeFilter((Writeable<PlanarImage>) dilPipe, 7);

    SimplePipe eroPipe = new SimplePipe((Writeable) imgErodeFilter);

    ImgMedianFilter imgMedianFilter = new ImgMedianFilter((Writeable<PlanarImage>) eroPipe);

    SimplePipe medPipe = new SimplePipe((Writeable) imgMedianFilter);

    ImgThreshholdFilter imgThreshholdFilter = new ImgThreshholdFilter((Writeable<PlanarImage>) medPipe);

    SimplePipe threshPipe = new SimplePipe((Writeable) imgThreshholdFilter);

    ImgCropFilter imgCropFilter = new ImgCropFilter((Writeable<PlanarImage>) threshPipe);

    SimplePipe cropPipe = new SimplePipe((Writeable) imgCropFilter);

    LoadImgSrc loadImgSrc = new LoadImgSrc(cropPipe);

    



}
