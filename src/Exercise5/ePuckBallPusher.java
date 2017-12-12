package Exercise5;

import Exercise5.SuperController.BangBangSuperController;
import Exercise5.behaviour.NextBall;
import Exercise5.behaviour.PushBall;
import Exercise5.behaviour.SearchBall;
import com.cyberbotics.webots.controller.DistanceSensor;



public class ePuckBallPusher extends BangBangSuperController {

    private static int TIME_STEP = 15;
    private static int LIGHT_SENSOR_VALUE = 400;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 500; // max. motor speed

    private DistanceSensor[] sensors; // Array with all distance sensors

    public ePuckBallPusher() {
        super(10);

        // get distance sensors and save them in array
        sensors = new DistanceSensor[] { getDistanceSensor("ps7"),
                getDistanceSensor("ps0") };
        for (int i=0; i<2; i++) {
            sensors[i].enable(15);
        }
    }

    public void run() {

        SearchBall searchBall = new SearchBall(this.camera);
        PushBall pushBall = new PushBall(this.camera,this.sensors);
        NextBall nextBall = new NextBall(this.sensors, this.accelerometer);

        float[] speed = {0, 0};

        while (step(TIME_STEP) != -1) {
            speed[0] = 0;
            speed[1] = 0;
            if (nextBall.isActivateable()) {
                speed = nextBall.calculateSpeed();
            } else if (pushBall.isActivateable()) {
                speed = pushBall.calculateSpeed();
            } else if (searchBall.isActivateable()) {
                speed = searchBall.calculateSpeed();
            } else {
                System.out.println("Should not get here");
            }
            setSpeed(speed[0], speed[1]);
        }
    }

    public static void main(String[] args) {
        ePuckBallPusher controller = new ePuckBallPusher();
        controller.run();
    }
}
