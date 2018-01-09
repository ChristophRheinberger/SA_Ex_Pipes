package Exercise5.behaviour;

import Exercise5.behaviour.interfaces.BehaviourFace;
import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.DistanceSensor;

/**
 * Created by Christoph on 12.12.2017.
 */
public class NextBall implements BehaviourFace{

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 5; // Sensor front right
    private static int S_LEFT = 1;
    private static int S_RIGHT = 4;

    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 999; // max. motor speed

    private double lastLeftValue = 0;
    private double lastRightValue = 0;

    private final Accelerometer accelerometer;
    private final DistanceSensor[] sensors;
    private int amount;
    private int isAtWall;

    public NextBall(DistanceSensor[] sensors, Accelerometer acc) {
        this.accelerometer = acc;
        this.sensors = sensors;
        this.amount = 0;
        this.isAtWall = 0;
    }

    public boolean isActivateable() {
        return shouldReset();
    }

    private boolean shouldReset() {
        System.out.println("Accelerometer: X " + accelerometer.getValues()[0] + " Y: " + accelerometer.getValues()[1]);

        if (((sensors[S_RIGHT].getValue() > 1800) || (sensors[S_LEFT].getValue() > 1800)) && isAtWall < 5) {
            System.out.println("Right: " + sensors[S_RIGHT].getValue());
            System.out.println("Left: " + sensors[S_LEFT].getValue());
            isAtWall++;
        }

        if (amount > 0) {
            System.out.println("4");
            isAtWall = 0;
            return true;
        } else if (isAtWall >= 5) {
            isAtWall = 0;
            return true;
        } else if ((sensors[S_FRONT_LEFT].getValue() > 1400) && (sensors[S_FRONT_RIGHT].getValue() > 1400)) {
            //if (lastLeftValue > 1400 && lastRightValue > 1400) {
            if ((accelerometer.getValues()[1] < (-0.7)) && Math.abs(accelerometer.getValues()[0]) > 0.2) {
                amount = 100;
                lastLeftValue = 0;
                lastRightValue = 0;
                System.out.println("1");
                isAtWall = 0;
                return true;
            } else if (accelerometer.getValues()[1] < -2) {
                amount = 100;
                lastLeftValue = 0;
                lastRightValue = 0;
                return true;
            } else {
                lastLeftValue = sensors[S_FRONT_LEFT].getValue();
                lastRightValue = sensors[S_FRONT_RIGHT].getValue();
                return false;
            }
            /*} else {
                lastLeftValue = sensors[S_FRONT_LEFT].getValue();
                lastRightValue = sensors[S_FRONT_RIGHT].getValue();
                return false;
            } */
        } else {
            isAtWall = 0;
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
        } else {
            speed[0] = MIN_SPEED;
            speed[1] = MIN_SPEED;
        }
        /*
        speed[0] = MAX_SPEED;
        speed[1] = MAX_SPEED;
        */
        System.out.println("Nextball: " + speed[0] + " " + speed[1]);

        return speed;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
