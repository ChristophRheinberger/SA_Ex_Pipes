package Exercise5.behaviour;

import Exercise5.behaviour.interfaces.BehaviourFace;
import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.DistanceSensor;

/**
 * Created by Christoph on 12.12.2017.
 */
public class NextBall implements BehaviourFace{

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    private final Accelerometer accelerometer;
    private final DistanceSensor[] sensors;
    private int amount;

    public NextBall(DistanceSensor[] sensors, Accelerometer acc) {
        this.accelerometer = acc;
        this.sensors = sensors;
        this.amount = 0;
    }

    public boolean isActivateable() {
        return shouldReset();
    }

    private boolean shouldReset() {
        if ((sensors[S_FRONT_LEFT].getValue() > 1000) && (sensors[S_FRONT_RIGHT].getValue() > 1000) && (accelerometer.getValues()[0] < 0)) {
            amount = 100;
            return true;
        } else if (amount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public float[] calculateSpeed() {
        float[] speed = {0, 0};
        if (amount > 10) {
            speed[0] = -MAX_SPEED;
            speed[1] = -MAX_SPEED;
            amount--;
        } else if (amount > 0) {
            speed[0] = -MAX_SPEED;
            speed[1] = MAX_SPEED;
            amount--;
        }
        else {
            speed[0] = MIN_SPEED;
            speed[1] = MIN_SPEED;
        }
        return speed;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
