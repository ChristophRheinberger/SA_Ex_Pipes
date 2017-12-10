package Exercise4.controllers;
;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

public class ePuckProportionalB extends ProportionalSuperController {

    private static int TIME_STEP = 15;

    private static int MAX_SPEED = 1000; // max. motor speed

    private static double[] prioritiesLight = {1, 0.1, 0.2, 0.5, 0.5, 0.2, 0.1, 1};
    private static double[] prioritiesDistance = {1, 1};
    private static double speedLeft;
    private static double speedRight;


    private LightSensor[] sensorsLight; // Array with all light sensors
    private DistanceSensor[] sensorsDistance; // Array with all distance sensors

    public ePuckProportionalB() {
        super();

        sensorsLight = new LightSensor[] { getLightSensor("ls7"), getLightSensor("ls6"), getLightSensor("ls5"), getLightSensor("ls4"),
                getLightSensor("ls3"), getLightSensor("ls2"), getLightSensor("ls1"), getLightSensor("ls0") };
        for (int i=0; i<sensorsLight.length; i++)
            sensorsLight[i].enable(10);

        sensorsDistance = new DistanceSensor[] { getDistanceSensor("ps7"), getDistanceSensor("ps0") };

        for (int i=0; i<sensorsDistance.length; i++)
            sensorsDistance[i].enable(10);
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
            drive();
        }

    }

    private void drive() {
        System.out.println("RIGHT: " + sensorsLight[7].getValue());
        System.out.println("LEFT: " + sensorsLight[0].getValue());

        speedLeft = ((prioritiesLight[0] * sensorsLight[0].getValue())
                    + (prioritiesLight[1] * sensorsLight[1].getValue())
                    + (prioritiesLight[2] * sensorsLight[2].getValue())
                    + (prioritiesLight[3] * sensorsLight[3].getValue())) / 4
                    - (prioritiesDistance[0] * sensorsDistance[0].getValue());
        speedRight = ((prioritiesLight[7] * sensorsLight[7].getValue())
                    + (prioritiesLight[6] * sensorsLight[6].getValue())
                    + (prioritiesLight[5] * sensorsLight[5].getValue())
                    + (prioritiesLight[4] * sensorsLight[4].getValue())) / 4
                    - (prioritiesDistance[1] * sensorsDistance[1].getValue());

        if(speedLeft >= 1000 && speedRight >= 1000){
            speedLeft = 0;
            speedRight = 0;
        }

        System.out.println("RIGHT Speed: " + speedRight);
        System.out.println("LEFT Speed: " + speedLeft);

        speed(speedLeft, speedRight);
    }

    public static void main(String[] args) {
        ePuckProportionalB controller = new ePuckProportionalB();
        controller.run();
    }
}