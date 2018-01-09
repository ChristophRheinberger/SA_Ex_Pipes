package Exercise4.controllers;

import Exercise4.controllers.superController.BangBangSuperController;
import com.cyberbotics.webots.controller.LightSensor;


public class ePuckAufgabeB extends BangBangSuperController {

    private static int TIME_STEP = 15;
    private static int LIGHT_SENSOR_VALUE = 400;

    private static int S_FRONT_LEFT = 0; // Sensor front left
    private static int S_FRONT_RIGHT = 1; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    private LightSensor[] sensors; // Array with all distance sensors

    public ePuckAufgabeB() {
        super();

        // get distance sensors and save them in array
        sensors = new LightSensor[] { getLightSensor("ls7"),
                getLightSensor("ls0") };
        for (int i=0; i<2; i++)
            sensors[i].enable(10);
    }

    public void run() {

        while (step(TIME_STEP) != -1) {
            if (sensors[S_FRONT_LEFT].getValue() < LIGHT_SENSOR_VALUE
                    && sensors[S_FRONT_RIGHT].getValue() < LIGHT_SENSOR_VALUE) {
                if( lightSensorValuesInTolerance() ) {
                    driveForward();
                }else{
                  stop();
                }
            System.out.println("ls7: " + sensors[S_FRONT_LEFT].getValue());
            System.out.println("ls0: " + sensors[S_FRONT_RIGHT].getValue());
          }else if(sensors[S_FRONT_LEFT].getValue() < 150){
            driveLeft();
          }else if(sensors[S_FRONT_RIGHT].getValue() < 150){
            driveRight();
          }else{
            stop();
          }
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
    
    public static void main(String[] args) {
        ePuckAufgabeB controller = new ePuckAufgabeB();
        controller.run();
    }
}