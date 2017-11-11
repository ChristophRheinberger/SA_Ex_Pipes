package Exercise2A;

import Exercise2A.filter.*;
import pmp.filter.Coordinate;
import pmp.filter.Sink;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;
import java.util.ArrayList;


/**
 * Created by Christoph on 06.11.2017.
 */
public class filterNPipes {

    public static void main(String args[]) {

        ImgSink sink = new ImgSink();

        SimplePipe sinkPipe = new SimplePipe((Writeable) sink);

        FilterCalcCentroids calcCenterFilter = new FilterCalcCentroids((Writeable<ArrayList<Coordinate>>) sinkPipe);

        SimplePipe calcCentroidPipe = new SimplePipe((Writeable) calcCenterFilter);

        ImgSaveFilter imgSaveFilter = new ImgSaveFilter((Writeable<PlanarImage>) calcCentroidPipe);

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

        loadImgSrc.run();    }


        public static ArrayList<Coordinate> getExpectedCentroids() {
            ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
            coordinates.add(new Coordinate(73,77));
            coordinates.add(new Coordinate(110,80));
            coordinates.add(new Coordinate(202,80));
            coordinates.add(new Coordinate(265,79));
            coordinates.add(new Coordinate(330,81));
            coordinates.add(new Coordinate(396,81));
            return coordinates;
    }
}
