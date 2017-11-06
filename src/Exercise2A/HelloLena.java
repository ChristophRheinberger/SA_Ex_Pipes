package Exercise2A;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.Coordinate;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.ThresholdDescriptor;
import javax.swing.*;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloLena
{
 public static void main(String[] args)
 {
// Load the image which file name was passed as the first argument to
// the application.
   float[] kernelMatrix;
   KernelJAI kernel = null;
   kernelMatrix = new float[]    {0, -1, 0, 
                                 -1, 8, -1,
                                  0, -1, 0 };

   ArrayList<Coordinate> expected_coordinates= new ArrayList<Coordinate> ();
   // [(73,77), (110,80), (202,80), (265,79), (330,81), (396,81)]

   expected_coordinates.add(new Coordinate(75, 77));
   expected_coordinates.add(new Coordinate(110, 80));
   expected_coordinates.add(new Coordinate(202, 80));
   expected_coordinates.add(new Coordinate(265, 79));
   expected_coordinates.add(new Coordinate(330, 81));
   expected_coordinates.add(new Coordinate(396, 81));

   kernel = new KernelJAI(3, 3, kernelMatrix);

   PlanarImage image = JAI.create("fileload", "loetstellen.jpg");

   int xOffset = 0;
   int yOffset = 50;

   Rectangle rectangleCut = new Rectangle(xOffset, yOffset, image.getWidth(), image.getHeight()/5);

   PlanarImage imageCut = PlanarImage.wrapRenderedImage(image.getAsBufferedImage(rectangleCut, image.getColorModel()));

// Get some information about the image
   String imageInfo =
   "Dimensions: "+image.getWidth()+"x"+image.getHeight()+ " Bands:"+image.getNumBands();

// Create a frame for display.
   JFrame frame = new JFrame();
   frame.setTitle("DisplayJAI: loetstellen.jpg");

// Get the JFrame’ ContentPane.
   Container contentPane = frame.getContentPane();
   contentPane.setLayout(new BorderLayout());

// prepare the parameters for a filter operation with the mask "kernelmatrix"
   ParameterBlock pb = new ParameterBlock();
   pb.addSource(imageCut);
   pb.add(kernel);
 
// apply a filter operation with the mask "kernelmatrix"
   imageCut = ThresholdDescriptor.create(imageCut, new double[]{0}, new double []{40}, new double[]{255}, null);

   imageCut = MedianFilterDescriptor.create(imageCut, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5, null);

   int amountErode = 7;

   for (int i = 0; i < amountErode; i++) {
     imageCut = ErodeDescriptor.create(imageCut, KernelJAI.ERROR_FILTER_FLOYD_STEINBERG, null);
   }

   int amountDilate = 6;

   for (int i = 0; i < amountDilate; i++) {
     imageCut = DilateDescriptor.create(imageCut, KernelJAI.ERROR_FILTER_FLOYD_STEINBERG, null);
   }

// Save Image to FIle
   File myNewPNGFile = new File("loetstellen_punkte.png");

   try {
     ImageIO.write(imageCut, "PNG", myNewPNGFile);
   } catch (IOException e) {
     e.printStackTrace();
   }

// Get center of Points


// Create an instance of DisplayJAI.
   DisplayJAI dj = new DisplayJAI(imageCut);
 

// Add to the JFrame’ ContentPane an instance of JScrollPane
// containing the DisplayJAI instance.
   contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
 
// Add a text label with the image information.
   contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);

// Set the closing operation so the application is finished.   
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(imageCut.getWidth()+50,imageCut.getHeight()+60); // adjust the frame size.
   frame.setVisible(true); // show the frame.
 }
 }