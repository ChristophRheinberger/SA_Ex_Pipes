package Exercise5.SuperController;

public abstract class BangBangSuperController extends RobotWithCameraAccelerometerWheelEncoder {

    private static int MIN_SPEED = 0;
    private static int MAX_SPEED = 1000; // max. motor speed

    public BangBangSuperController(int sensorResponse) {
        super(sensorResponse);
    }

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
