package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;



public class ePuckProportionalC extends DifferentialWheels {

    private static int TIME_STEP = 15;

    private static int MAX_SPEED = 1000; // max. motor speed

    private static double[] priorities= {1, 0.4, 0.1, 0.1, 0.4, 1};
    private static double speedLeft;
    private static double speedRight;


    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuckProportionalC() {
        super();

        sensors = new DistanceSensor[] { getDistanceSensor("ps7"), getDistanceSensor("ps6"), getDistanceSensor("ps5"),
                getDistanceSensor("ps2"), getDistanceSensor("ps1"), getDistanceSensor("ps0") };
        for (int i=0; i<6; i++)
            sensors[i].enable(10);
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
            drive();
        }

    }

    private void drive() {
        System.out.println("RIGHT: " + sensors[5].getValue());
        System.out.println("LEFT: " + sensors[0].getValue());

        speedLeft = MAX_SPEED - ((priorities[0] * sensors[0].getValue())
                + (priorities[1] * sensors[1].getValue())
                + (priorities[2] * sensors[2].getValue())) / 3;
        speedRight = MAX_SPEED - ((priorities[5] * sensors[5].getValue())
                + (priorities[4] * sensors[4].getValue())
                + (priorities[3] * sensors[3].getValue())) / 3;

        System.out.println("RIGHT Speed: " + speedRight);
        System.out.println("LEFT Speed: " + speedLeft);

        setSpeed(speedLeft, speedRight);
    }

    public static void main(String[] args) {
        ePuckProportionalC controller = new ePuckProportionalC();
        controller.run();
    }
}