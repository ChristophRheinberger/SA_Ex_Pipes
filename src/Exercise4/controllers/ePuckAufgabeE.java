package Exercise4.controllers;

import com.cyberbotics.webots.controller.DistanceSensor;


public class ePuckAufgabeE  extends BangBangSuperController{
    private static int TIME_STEP = 15;
    private static int MAX_SENSOR_VALUE = 150;
    private static int MIN_SENSOR_VALUE = 120;

    private static int S_LEFT = 0; // Sensor left
    private static int S_MIDDLE_LEFT = 1;  // Sensor middle left
    private static int S_FRONT_LEFT = 2; // Sensor front left
    private static int S_FRONT_RIGHT = 3; // Sensor front right
    private static int S_MIDDLE_RIGHT = 4;  // Sensor middle right
    private static int S_RIGHT = 5; // Sensor left
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed


    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuckAufgabeE() {
        super();
        // get distance sensors and save them in array
        sensors = new DistanceSensor[] { getDistanceSensor("ps5"), getDistanceSensor("ps6"),
                getDistanceSensor("ps7"), getDistanceSensor("ps0"), getDistanceSensor("ps1"),
                getDistanceSensor("ps2") };
        for (int i=0; i<6; i++)
            sensors[i].enable(10);
    }

    public void run() {
        while (step(TIME_STEP) != -1) {
            System.out.println("Left: " + sensors[S_FRONT_LEFT].getValue());
            System.out.println("Right: " + sensors[S_FRONT_RIGHT].getValue());
            if (sensors[S_FRONT_LEFT].getValue() > MAX_SENSOR_VALUE
                    || sensors[S_MIDDLE_LEFT].getValue() > MAX_SENSOR_VALUE
                    || sensors[S_FRONT_RIGHT].getValue() > MAX_SENSOR_VALUE) {
                // drive right - reached a wall
                driveRight();
                System.out.println("Drive Right");
            } else if (sensors[S_LEFT].getValue() < MAX_SENSOR_VALUE
                    && sensors[S_MIDDLE_LEFT].getValue() < MIN_SENSOR_VALUE) {
                // drive forward if nothing is in front of the robot
                driveLeft();
                System.out.println("Drive Left");
            } else {
                driveForward();
                System.out.println("Drive");
            }
            //System.out.println("ps7: " + sensors[S_FRONT_RIGHT].getValue());
        }

    }

    public static void main(String[] args) {
        ePuckAufgabeE controller = new ePuckAufgabeE();
        controller.run();
    }
}
