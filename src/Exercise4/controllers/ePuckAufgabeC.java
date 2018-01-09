package Exercise4.controllers;

import Exercise4.controllers.superController.BangBangSuperController;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;



public class ePuckAufgabeC extends BangBangSuperController {

    private static int TIME_STEP = 15;
    private static int LIGHT_SENSOR_VALUE = 400;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 500; // max. motor speed

    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuckAufgabeC() {
        super();

        // get distance sensors and save them in array
        sensors = new DistanceSensor[] { getDistanceSensor("ps7"),
                getDistanceSensor("ps0") };
        for (int i=0; i<2; i++) {
            sensors[i].enable(15);
        }
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
                System.out.println("Left: " + sensors[S_FRONT_LEFT].getValue());
                System.out.println("Right: " + sensors[S_FRONT_RIGHT].getValue());
                if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() <= 2000)) {
                    driveForward();
                } else if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() >= 2000)) {
                    driveRight();
                } else if ((sensors[S_FRONT_RIGHT].getValue() <= 2000) && (sensors[S_FRONT_LEFT].getValue() >= 2000)) {
                    driveLeft();
                } else {
                    stop();
                }
        }
    }

    public static void main(String[] args) {
        ePuckAufgabeC controller = new ePuckAufgabeC();
        controller.run();
    }
}
