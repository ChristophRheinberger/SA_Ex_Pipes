package Exercise4.controllers;

import Exercise4.controllers.superController.BangBangSuperController;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;


public class ePuckAufgabeD extends BangBangSuperController {

    private static int TIME_STEP = 10;
    private static int LIGHT_SENSOR_VALUE = 400;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed


    private DistanceSensor[] sensors; // Array with all distance sensors
    private Camera camera;

    public ePuckAufgabeD() {
        super();

        // get distance sensors and save them in array
        sensors = new DistanceSensor[] { getDistanceSensor("ps7"),
                getDistanceSensor("ps0") };
        camera = new Camera("camera");
        for (int i=0; i<2; i++) {
            sensors[i].enable(10);
            camera.enable(10);
        }
    }

    public void run() {

        int[] image;
        int width;
        int height;
        boolean pushingBall = false;

        while (step(TIME_STEP) != -1) {

            int red = 0;

            image = camera.getImage();
            width = camera.getWidth();
            height = camera.getHeight();

            /*if ( pushingBall == false ) {

                int[] imagePixels = camera.getImage();
                for (int i=0; i < image.length; i++) {
                    int pixel = image[i];
                    int r = Camera.pixelGetRed(pixel);
                    int g = Camera.pixelGetGreen(pixel);
                    int b = Camera.pixelGetBlue(pixel);
                    if (isRed(r,g,b)) {
                        red++;
                    }
                }

                if (red > 5) {
                    driveForward();
                } else {
                    stop();
                }

                if ((sensors[S_FRONT_LEFT].getValue() >= 150) && (sensors[S_FRONT_RIGHT].getValue() >= 150)) {
                    pushingBall = true;
                }

            } else { */
            System.out.println("Left: " + sensors[S_FRONT_LEFT].getValue());
            System.out.println("Right: " + sensors[S_FRONT_RIGHT].getValue());
                /*if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() <= 2000)) {
                    driveForward();
                } else if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() >= 2000)) {
                    driveRight();
                } else if ((sensors[S_FRONT_RIGHT].getValue() <= 2000) && (sensors[S_FRONT_LEFT].getValue() >= 2000)) {
                    driveLeft();
                } else {
                    pushingBall = false;
                    stop();
                }*/

            if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() <= 2000)) {
                driveForward();

               /*if (((sensors[S_FRONT_LEFT].getValue() + 150) < sensors[S_FRONT_RIGHT].getValue()) && ((sensors[S_FRONT_LEFT].getValue() - 150) < sensors[S_FRONT_RIGHT].getValue())) {
                   driveForward(); */
            }else if(sensors[S_FRONT_LEFT].getValue() < sensors[S_FRONT_RIGHT].getValue()){
                driveRight();
            }else if(sensors[S_FRONT_LEFT].getValue() > sensors[S_FRONT_RIGHT].getValue()) {
                driveLeft();
            }else{
                stop();
            }

            //}
        }
    }

    private boolean isRed(int r, int g, int b) {
        if ( g < 10 && b < 10 ) {
            if ( r > 50 )
                return true;
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
        ePuckAufgabeD controller = new ePuckAufgabeD();
        controller.run();
    }
}