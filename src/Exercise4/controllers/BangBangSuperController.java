package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;

/**
 * Created by Clemens on 10.12.2017.
 */
public abstract class BangBangSuperController extends DifferentialWheels {

    private static int MIN_SPEED = 0;
    private static int MAX_SPEED = 1000; // max. motor speed

    protected void speed(double speedLeft, double speedRight){
        setSpeed(speedLeft, speedRight);
    }

    private void stop() {
        setSpeed(MIN_SPEED, MIN_SPEED);
    }

    private void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    private void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    private void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    public abstract void run();
}
