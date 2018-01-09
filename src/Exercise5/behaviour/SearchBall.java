package Exercise5.behaviour;

import Exercise5.behaviour.methods.CameraMethods;
import Exercise5.behaviour.interfaces.BehaviourFace;
import com.cyberbotics.webots.controller.Camera;

/**
 * Created by Christoph on 12.12.2017.
 */
public class SearchBall implements BehaviourFace {

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    private final Camera camera;
    private final CameraMethods cameraMethods;

    public SearchBall(Camera camera) {
        this.camera = camera;
        cameraMethods = new CameraMethods(this.camera);
    }

    public boolean isActivateable() {
        return true;
    }

    public float[] calculateSpeed() {
        float[] speed = {0,0};
        if (cameraMethods.foundBall()) {
            speed[0] = MIN_SPEED;
            speed[1] = MIN_SPEED;
        } else {
            speed[0] = MIN_SPEED;
            speed[1] = MAX_SPEED/3;
        }
        System.out.println("Pushball: " + speed[0] + " " + speed[1]);
        return speed;
    }
}
