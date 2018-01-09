package Exercise5.behaviour;

import Exercise5.behaviour.interfaces.BehaviourFace;
import Exercise5.behaviour.methods.CameraMethods;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

/**
 * Created by Christoph on 12.12.2017.
 */
public class PushBall implements BehaviourFace {

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 5; // Sensor front right

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    private static double[] priorities= {1, 0.3, 0.1, 0.1, 0.3, 1};

    private final Camera camera;
    private final DistanceSensor[] sensors;
    private final CameraMethods cameraMethods;

    public PushBall(Camera camera, DistanceSensor[] sensors) {
        this.camera = camera;
        this.sensors = sensors;
        cameraMethods = new CameraMethods(this.camera);
    }

    public boolean isActivateable() {
        return cameraMethods.foundBall();
    }

    public float[] calculateSpeed() {
        float[] speed = {0, 0};/*
        if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() <= 2000)) {
            speed[0] = MAX_SPEED;
            speed[1] = MAX_SPEED;
        } else if ((sensors[S_FRONT_LEFT].getValue() <= 2000) && (sensors[S_FRONT_RIGHT].getValue() >= 2000)) {
            speed[0] = MAX_SPEED;
            speed[1] = MIN_SPEED;
        } else if ((sensors[S_FRONT_RIGHT].getValue() <= 2000) && (sensors[S_FRONT_LEFT].getValue() >= 2000)) {
            speed[0] = MIN_SPEED;
            speed[1] = MAX_SPEED;
        } else {
            speed[0] = MIN_SPEED;
            speed[1] = MIN_SPEED;
        }*/
        speed[0] = (float) (MAX_SPEED - ((priorities[0] * sensors[0].getValue())
                + (priorities[1] * sensors[1].getValue())
                + (priorities[2] * sensors[2].getValue())) / 8);
        speed[1] = (float) (MAX_SPEED - ((priorities[5] * sensors[5].getValue())
                + (priorities[4] * sensors[4].getValue())
                + (priorities[3] * sensors[3].getValue())) / 8);

        System.out.println("Pushball: " + speed[0] + " " + speed[1]);
        return speed;
    }
}
