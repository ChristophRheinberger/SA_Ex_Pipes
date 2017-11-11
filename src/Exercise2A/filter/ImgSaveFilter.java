package Exercise2A.filter;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 06.11.2017.
 */
public class ImgSaveFilter extends DataTransformationFilter1<PlanarImage> {
    public ImgSaveFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ImgSaveFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ImgSaveFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected void process(PlanarImage entity) {
        // Get some information about the image
        String imageInfo = "Dimensions: "+entity.getWidth()+"x"+entity.getHeight()+ " Bands:"+entity.getNumBands();

        // Create a frame for display.
        JFrame frame = new JFrame();
        frame.setTitle("DisplayJAI: loetstellen.jpg");

        // Get the JFrame� ContentPane.
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Create an instance of DisplayJAI.
        DisplayJAI dj = new DisplayJAI(entity);


        // Add to the JFrame� ContentPane an instance of JScrollPane
        // containing the DisplayJAI instance.
        contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);

        // Add a text label with the image information.
        contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);

        // Set the closing operation so the application is finished.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(entity.getWidth()+50,entity.getHeight()+60); // adjust the frame size.
        frame.setVisible(true); // show the frame.

        // Save Image to FIle
        File myNewPNGFile = new File("loetstellen_punkte.jpg");

        try {
            ImageIO.write(entity, "JPG", myNewPNGFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
