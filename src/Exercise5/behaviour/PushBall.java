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
    private static int S_FRONT_RIGHT = 1; // Sensor front right

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

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
        float[] speed = {0, 0};
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
        }
        return speed;
    }
}
