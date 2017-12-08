package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;



public class ePuckProportionalC extends DifferentialWheels {

    private static int TIME_STEP = 15;

    private static int MAX_SPEED = 1000; // max. motor speed

    private static double[] priorities= {1, 1};
    private static double speedLeft;
    private static double speedRight;


    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuckProportionalC() {
        super();

        sensors = new DistanceSensor[] { getDistanceSensor("ps7"), getDistanceSensor("ps0") };
        for (int i=0; i<2; i++)
            sensors[i].enable(10);
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
            drive();
        }

    }

    private void drive() {
        System.out.println("RIGHT: " + sensors[1].getValue());
        System.out.println("LEFT: " + sensors[0].getValue());

        speedLeft = 10000*(priorities[0] / sensors[0].getValue());
        speedRight = 10000*(priorities[1] / sensors[1].getValue());

        System.out.println("RIGHT Speed: " + speedRight);
        System.out.println("LEFT Speed: " + speedLeft);

        setSpeed(speedLeft, speedRight);
    }

    public static void main(String[] args) {
        ePuckProportionalC controller = new ePuckProportionalC();
        controller.run();
    }
}