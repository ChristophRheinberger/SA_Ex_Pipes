package Exercise4.controllers;

import Exercise4.controllers.superController.ProportionalSuperController;
import com.cyberbotics.webots.controller.DistanceSensor;

public class ePuckProportionalE extends ProportionalSuperController {

    private static int TIME_STEP = 15;
    private static int MAX_SPEED = 1000; // max. motor speed

    private static double[] priorities= {1 , 1, 0.5, 1};
    private static double speedLeft;
    private static double speedRight;

    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuckProportionalE() {
        super();

        sensors = new DistanceSensor[] { getDistanceSensor("ps7"), getDistanceSensor("ps6"), getDistanceSensor("ps5"),
                getDistanceSensor("ps0") };
        for (int i=0; i<4; i++)
            sensors[i].enable(10);
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
            drive();
        }

    }

    private void drive() {
        System.out.println("RIGHT: " + sensors[3].getValue());
        System.out.println("LEFT: " + sensors[0].getValue());
        System.out.println("LEFT MID: " + sensors[2].getValue());

        speedRight = MAX_SPEED - ((priorities[0] * sensors[0].getValue()) + (priorities[3] * sensors[3].getValue()) + (priorities[1] * sensors[1].getValue()));
        speedLeft = (priorities[2] * sensors[2].getValue());

        System.out.println("RIGHT Speed: " + speedRight);
        System.out.println("LEFT Speed: " + speedLeft);

        speed(speedLeft, speedRight);
    }

    public static void main(String[] args) {
        ePuckProportionalE controller = new ePuckProportionalE();
        controller.run();
    }
}