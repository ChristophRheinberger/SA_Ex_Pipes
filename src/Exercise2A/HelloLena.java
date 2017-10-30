package Exercise2A;

import com.sun.media.jai.widget.DisplayJAI;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.ThresholdDescriptor;
import javax.swing.*;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;

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

   kernel = new KernelJAI(3, 3, kernelMatrix);

   int xOffset = 0;
   int yOffset = 50;
	  
   PlanarImage image = JAI.create("fileload", "loetstellen.jpg");

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

   imageCut = MedianFilterDescriptor.create(imageCut, MedianFilterDescriptor.MEDIAN_MASK_PLUS, 15, null);

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