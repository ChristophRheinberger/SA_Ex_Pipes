package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;


public abstract class ProportionalSuperController extends DifferentialWheels {

    protected void speed(double speedLeft, double speedRight){
        setSpeed(speedLeft, speedRight);
    }

    public abstract void run();
}
