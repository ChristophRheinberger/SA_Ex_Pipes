package Exercise5;

import Exercise5.SuperController.BangBangSuperController;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;



public class ePuck_BallPusher extends BangBangSuperController {

    private static int TIME_STEP = 15;
    private static int LIGHT_SENSOR_VALUE = 400;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 500; // max. motor speed

    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuck_BallPusher() {
        super(10);

        // get distance sensors and save them in array
        sensors = new DistanceSensor[] { getDistanceSensor("ps7"),
                getDistanceSensor("ps0") };
        for (int i=0; i<2; i++) {
            sensors[i].enable(15);
        }
    }

    public void run() {

        int[] image;
        int width;
        int height;
        boolean pushingBall = false;

        while (step(TIME_STEP) != -1) {

            int red = 0;

            width = getCameraWidth();
            height = getCameraHeight();

            if ( pushingBall == false ) {
                int[] imagePixels = getCameraValues();
                /*for (int i=0; i < imagePixels.length; i++) {
                    int pixel = imagePixels[i];
                    int r = Camera.pixelGetRed(pixel);
                    int g = Camera.pixelGetGreen(pixel);
                    int b = Camera.pixelGetBlue(pixel);
                    if (isRed(r,g,b)) {
                        red++;
                    }
                }
                */
                if (isRed(Camera.pixelGetRed(getCenterPixel(imagePixels)), Camera.pixelGetGreen(getCenterPixel(imagePixels)), Camera.pixelGetBlue(getCenterPixel(imagePixels)))) {
                    System.out.println("Center Pixel Red");
                    driveForward();
                } else {
                    driveRight();
                }
                if ((sensors[S_FRONT_LEFT].getValue() >= 150) && (sensors[S_FRONT_RIGHT].getValue() >= 150)) {
                    pushingBall = true;
                }
            } else {
                System.out.println("Left: " + sensors[S_FRONT_LEFT].getValue());
                System.out.println("Right: " + sensors[S_FRONT_RIGHT].getValue());
                if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() <= 2000)) {
                    driveForward();
                } else if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() >= 2000)) {
                    driveRight();
                } else if ((sensors[S_FRONT_RIGHT].getValue() <= 2000) && (sensors[S_FRONT_LEFT].getValue() >= 2000)) {
                    driveLeft();
                } else {
                    pushingBall = false;
                    stop();
                }
            }
        }
    }

    private int getCenterPixel(int[] pixels) {
        return pixels[pixels.length/2];
    }

    private boolean isRed(int r, int g, int b) {
        if ( g < 10 && b < 10 ) {
            if ( r > 50 ) {
                System.out.println("hallo");
                return true;
            }
            else
                return false;
        } else {
            return false;
        }
    }

    private boolean lightSensorValuesInTolerance() {
        if (Math.abs(sensors[S_FRONT_LEFT].getValue() - Math.abs(sensors[S_FRONT_RIGHT].getValue())) < LIGHT_SENSOR_VALUE ) {
            System.out.println(Math.abs(sensors[S_FRONT_LEFT].getValue() - Math.abs(sensors[S_FRONT_RIGHT].getValue())));
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ePuck_BallPusher controller = new ePuck_BallPusher();
        controller.run();
    }
}
