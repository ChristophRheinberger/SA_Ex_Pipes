package Exercise4.controllers;

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;


/**
 * Created by Christoph on 27.11.2017.
 */
public class ePuckAufgabeA extends DifferentialWheels {

    private static int TIME_STEP = 15;

    private static int MAX_SENSOR_VALUE = 500;
    private static int LIGHT_SENSOR_VALUE = 400;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed


    private LightSensor[] sensors; // Array with all distance sensors

    /**
     * Constructor
     */
    public ePuckAufgabeA() {
        super();

        // get distance sensors and save them in array
        sensors = new LightSensor[] { getLightSensor("ls7"),
                getLightSensor("ls0") };
        for (int i=0; i<2; i++)
            sensors[i].enable(10);
    }

    /**
     * User defined function for initializing and running the
     * BangBangFollowTheWall class
     */
    public void run() {

        while (step(TIME_STEP) != -1) {
            if (sensors[S_FRONT_LEFT].getValue() < LIGHT_SENSOR_VALUE
                    && sensors[S_FRONT_RIGHT].getValue() < LIGHT_SENSOR_VALUE) {
                if( lightSensorValuesInTolerance() ) {
                    driveForward();
                } else {
                    stop();
                }
            } else {
                stop();
            }
            System.out.println("ls7: " + sensors[S_FRONT_LEFT].getValue());
            System.out.println("ls0: " + sensors[S_FRONT_RIGHT].getValue());
        }

    }

    private boolean lightSensorValuesInTolerance() {
        if (Math.abs(sensors[S_FRONT_LEFT].getValue() - Math.abs(sensors[S_FRONT_RIGHT].getValue())) < LIGHT_SENSOR_VALUE ) {
            System.out.println(Math.abs(sensors[S_FRONT_LEFT].getValue() - Math.abs(sensors[S_FRONT_RIGHT].getValue())));
            return true;
        } else {
            return false;
        }
    }

    private void stop() {
        setSpeed(MIN_SPEED, MIN_SPEED);
    }

    /**
     * Robot drives to the right
     */
    private void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    /**
     * Robot drives to the left
     */
    private void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    /**
     * Robot drives forward
     */
    private void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }


    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        ePuckAufgabeA controller = new ePuckAufgabeA();
        controller.run();
    }
}


/*

#include <webots/robot.h>
        #include <webots/differential_wheels.h>
        #include <webots/light_sensor.h>
        #include <stdio.h>

        #define MAX_SPEED 100
        #define SPEED 60
        #define TIME_STEP 64

        int main() {
        WbDeviceTag ls0, ls1;
        double ls0_value;
        double ls1_value;
        double left_speed, right_speed;

        wb_robot_init();

        get a handler to the distance sensors.
        ls0 = wb_robot_get_device("ls0");
        ls1 = wb_robot_get_device("ls1");

        wb_light_sensor_enable(ls0, TIME_STEP);
        wb_light_sensor_enable(ls1, TIME_STEP);

        printf("You can move the light using your mouse, "
        "the robot will follow it\n");

        while(wb_robot_step(TIME_STEP)!=1) {
        read sensor values
        ls0_value = wb_light_sensor_get_value(ls0);
        ls1_value = wb_light_sensor_get_value(ls1);

        left_speed  = (1024 - ls0_value) / 10.0;
        left_speed = (left_speed < MAX_SPEED) ? left_speed : MAX_SPEED;
        right_speed = (1024 - ls1_value) / 10.0;
        right_speed = (right_speed < MAX_SPEED) ? right_speed : MAX_SPEED;

        wb_differential_wheels_set_speed(left_speed, right_speed);
        }

        wb_robot_cleanup();

        return 0;
        }


 */