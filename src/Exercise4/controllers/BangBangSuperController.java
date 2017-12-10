package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;


public abstract class BangBangSuperController extends DifferentialWheels {

    private static int MIN_SPEED = 0;
    private static int MAX_SPEED = 1000; // max. motor speed

    protected void speed(double speedLeft, double speedRight){
        setSpeed(speedLeft, speedRight);
    }

    public void stop() {
        setSpeed(MIN_SPEED, MIN_SPEED);
    }

    public void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    public void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    public void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    public abstract void run();
}
