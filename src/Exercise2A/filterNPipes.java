package Exercise2A;

import Exercise2A.filter.*;
import pmp.filter.Coordinate;
import pmp.filter.Sink;
import pmp.interfaces.Readable;
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

        if(args[0].equals("push")){


            ImgSink sink = new ImgSink(getExpectedCentroids(), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

            SimplePipe sinkPipe = new SimplePipe((Writeable) sink);

            FilterCalcCentroids calcCenterFilter = new FilterCalcCentroids((Writeable<ArrayList<Coordinate>>) sinkPipe);

            SimplePipe calcCentroidPipe = new SimplePipe((Writeable) calcCenterFilter);

            ImgSaveFilter imgSaveFilter = new ImgSaveFilter((Writeable<PlanarImage>) calcCentroidPipe);

            SimplePipe savePipe = new SimplePipe((Writeable) imgSaveFilter);

            ImgDilateFilter imgDilateFilter = new ImgDilateFilter((Writeable<PlanarImage>) savePipe, 5);

            SimplePipe dilPipe = new SimplePipe((Writeable) imgDilateFilter);

            ImgErodeFilter imgErodeFilter = new ImgErodeFilter((Writeable<PlanarImage>) dilPipe, 5);

            SimplePipe eroPipe = new SimplePipe((Writeable) imgErodeFilter);

            ImgMedianFilter imgMedianFilter = new ImgMedianFilter((Writeable<PlanarImage>) eroPipe);

            SimplePipe medPipe = new SimplePipe((Writeable) imgMedianFilter);

            ImgThreshholdFilter imgThreshholdFilter = new ImgThreshholdFilter((Writeable<PlanarImage>) medPipe);

            SimplePipe threshPipe = new SimplePipe((Writeable) imgThreshholdFilter);

            ImgCropFilter imgCropFilter = new ImgCropFilter((Writeable<PlanarImage>) threshPipe);

            SimplePipe cropPipe = new SimplePipe((Writeable) imgCropFilter);

            LoadImgSrc loadImgSrc = new LoadImgSrc((Writeable<PlanarImage>) cropPipe);

            loadImgSrc.run();

        }else if(args[0].equals("pull")){

            LoadImgSrc loadImgSrc = new LoadImgSrc();

            SimplePipe cropPipe = new SimplePipe((Readable) loadImgSrc);

            ImgCropFilter imgCropFilter = new ImgCropFilter((Readable<PlanarImage>) cropPipe);

            SimplePipe threshPipe = new SimplePipe((Readable) imgCropFilter);

            ImgThreshholdFilter imgThreshholdFilter = new ImgThreshholdFilter((Readable<PlanarImage>) threshPipe);

            SimplePipe mediaPipe = new SimplePipe((Readable) imgThreshholdFilter);

            ImgMedianFilter imgMedianFilter = new ImgMedianFilter((Readable<PlanarImage>) mediaPipe);

            SimplePipe eroPipe = new SimplePipe((Readable) imgMedianFilter);

            ImgErodeFilter imgErodeFilter = new ImgErodeFilter((Readable<PlanarImage>) eroPipe, 5);

            SimplePipe dilPipe = new SimplePipe((Readable) imgErodeFilter);

            ImgDilateFilter imgDilateFilter = new ImgDilateFilter((Readable<PlanarImage>) dilPipe, 5);

            SimplePipe savePipe = new SimplePipe((Readable) imgDilateFilter);

            ImgSaveFilter imgSaveFilter = new ImgSaveFilter((Readable<PlanarImage>) savePipe);

            SimplePipe calcCentroidPipe = new SimplePipe((Readable) imgSaveFilter);

            FilterCalcCentroids filterCalcCentroids = new FilterCalcCentroids((Readable<PlanarImage>) calcCentroidPipe);

            SimplePipe sinkPipe = new SimplePipe((Readable) filterCalcCentroids);

            ImgSink imgSink = new ImgSink(sinkPipe, Integer.parseInt(args[1]), Integer.parseInt(args[2]));

            imgSink.run();

        }else{
            System.out.println("Please enter what pipesandfilter strategie you want to use. The options are push and pull");
        }
    }
    public static ArrayList<Coordinate> getExpectedCentroids() {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(73, 77));
        coordinates.add(new Coordinate(110, 80));
        coordinates.add(new Coordinate(202, 80));
        coordinates.add(new Coordinate(265, 79));
        coordinates.add(new Coordinate(330, 81));
        coordinates.add(new Coordinate(396, 81));
        return coordinates;
    }
}
