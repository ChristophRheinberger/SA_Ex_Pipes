package Exercise2A.filter;

/* this filter expects the bonding discs to be completely white: pixel value of 255 on a scale of 0..255
 * all other pixels in the image are expected to have a pixel value < 255
 * use this filter adapting eventually the package name 
 */

import pmp.filter.Coordinate;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;
import pmp.filter.DataTransformationFilter2;

import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class FilterCalcCentroids extends DataTransformationFilter2<PlanarImage, ArrayList<Coordinate>> {

    private HashMap<Coordinate, Boolean> _general = new HashMap<Coordinate, Boolean>();
    private LinkedList<ArrayList<Coordinate>> _figures = new LinkedList<ArrayList<Coordinate>>();
    private PlanarImage _image;

    
    public FilterCalcCentroids(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public FilterCalcCentroids(Writeable<ArrayList<Coordinate>> output) throws InvalidParameterException {
        super(output);
    }


    protected ArrayList<Coordinate> process(PlanarImage entity) {
        BufferedImage bi = entity.getAsBufferedImage();
        _image = entity;

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int p = bi.getRaster().getSample(x, y, 0);
                if (p >= 248 && _general.containsKey(new Coordinate(x, y)) == false) {
                    getNextFigure(bi, x, y);        //if there is a not visited white pixel, save all pixels belonging to the same figure
                }
            }
        }

        return calculateCentroids();    //calculate the centroids of all figures
    }

    private void getNextFigure(BufferedImage img, int x, int y) {
    	ArrayList<Coordinate> figure = new ArrayList<Coordinate>();
        _general.put(new Coordinate(x, y), true);
        figure.add(new Coordinate(x, y));

        addConnectedComponents(img, figure, x, y);

        _figures.add(figure);
    }

    private void addConnectedComponents(BufferedImage img, ArrayList<Coordinate> figure, int x, int y) {
        if (x - 1 >= 0 && _general.containsKey((new Coordinate(x - 1, y))) == false && img.getRaster().getSample(x - 1, y, 0) >= 248) {
            _general.put(new Coordinate(x - 1, y), true);
            figure.add(new Coordinate(x - 1, y));
            addConnectedComponents(img, figure, x - 1, y);
        }
        if (x + 1 < img.getWidth() && _general.containsKey((new Coordinate(x + 1, y))) == false && img.getRaster().getSample(x + 1, y, 0) >= 248) {
            _general.put(new Coordinate(x + 1, y), true);
            figure.add(new Coordinate(x + 1, y));
            addConnectedComponents(img, figure, x + 1, y);
        }
        if (y - 1 >= 0 && _general.containsKey((new Coordinate(x, y - 1))) == false && img.getRaster().getSample(x, y - 1, 0) >= 248) {
            _general.put(new Coordinate(x, y - 1), true);
            figure.add(new Coordinate(x, y - 1));
            addConnectedComponents(img, figure, x, y - 1);
        }
        if (y + 1 < img.getHeight() && _general.containsKey((new Coordinate(x, y + 1))) == false && img.getRaster().getSample(x, y + 1, 0) >= 248) {
            _general.put(new Coordinate(x, y + 1), true);
            figure.add(new Coordinate(x, y + 1));
            addConnectedComponents(img, figure, x, y + 1);
        }
    }

    private ArrayList<Coordinate> calculateCentroids() {
    	ArrayList<Coordinate> centroids = new ArrayList<Coordinate>();
        int i = 0;
        for (ArrayList<Coordinate> figure : _figures) {
            LinkedList<Integer> xValues = new LinkedList<Integer>();
            LinkedList<Integer> yValues = new LinkedList<Integer>();

            for (Coordinate c : figure) {
                xValues.add(c._x);
                yValues.add(c._y);
            }

            Collections.sort(xValues);
            Collections.sort(yValues);

            int xMedian = xValues.get(xValues.size() / 2);
            int yMedian = yValues.get(yValues.size() / 2);

            int radius = (xValues.getLast() - xValues.getFirst())/2;

            centroids.add(new Coordinate(xMedian + (Integer) _image.getProperty("offsetX"), yMedian + (Integer) _image.getProperty("offsetY"), radius));

            i++;
        }
        return centroids;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}