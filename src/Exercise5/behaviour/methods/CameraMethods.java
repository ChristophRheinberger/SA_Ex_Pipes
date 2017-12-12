package Exercise5.behaviour.methods;

import com.cyberbotics.webots.controller.Camera;

/**
 * Created by Christoph on 12.12.2017.
 */
public class CameraMethods {

    private final Camera camera;

    public CameraMethods(Camera camera) {
        this.camera = camera;
    }

    public boolean foundBall() {
        int[] imagePixels = camera.getImage();

        return isRed(Camera.pixelGetRed(getCenterPixel(imagePixels))
                , Camera.pixelGetGreen(getCenterPixel(imagePixels))
                , Camera.pixelGetBlue(getCenterPixel(imagePixels)));
    }

    private int getCenterPixel(int[] pixels) {
        return pixels[pixels.length/2];
    }

    private boolean isRed(int r, int g, int b) {
        if ( g < 10 && b < 10 ) {
            if ( r > 50 ) {
                return true;
            }
            else
                return false;
        } else {
            return false;
        }
    }
}
