package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;



public class ePuckProportionalA extends DifferentialWheels {

    private static int TIME_STEP = 15;

    private static int MAX_SPEED = 1000; // max. motor speed

    private static double[] priorities= {1, 0, 0, 0, 0, 0, 0, 1};
    private static double speedLeft;
    private static double speedRight;


    private LightSensor[] sensors; // Array with all distance sensors

    public ePuckProportionalA() {
        super();

        sensors = new LightSensor[] { getLightSensor("ls7"), getLightSensor("ls6"), getLightSensor("ls5"), getLightSensor("ls4"),
                getLightSensor("ls3"), getLightSensor("ls2"), getLightSensor("ls1"), getLightSensor("ls0") };
        for (int i=0; i<8; i++)
            sensors[i].enable(10);
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
            drive();
        }

    }

    private void drive() {

        System.out.println("RIGHT: " + sensors[7].getValue());
        System.out.println("LEFT: " + sensors[0].getValue());

        speedLeft = 10*((priorities[0] * sensors[0].getValue()) + (priorities[1] * sensors[1].getValue()) + (priorities[2] * sensors[2].getValue()) + (priorities[3] * sensors[3].getValue()));
        speedRight = 10*((priorities[7] * sensors[7].getValue()) + (priorities[6] * sensors[6].getValue()) + (priorities[5] * sensors[5].getValue()) + (priorities[4] * sensors[4].getValue()));

        if(speedLeft > MAX_SPEED){
            speedLeft = MAX_SPEED;
        }

        if(speedRight > MAX_SPEED){
            speedRight = MAX_SPEED;
        }

        setSpeed(speedLeft, speedRight);
    }

    public static void main(String[] args) {
        ePuckProportionalA controller = new ePuckProportionalA();
        controller.run();
    }
}