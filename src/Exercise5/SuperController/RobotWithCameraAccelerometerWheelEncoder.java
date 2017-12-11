package Exercise5.SuperController;

import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DifferentialWheels;


public abstract class RobotWithCameraAccelerometerWheelEncoder extends DifferentialWheels {

	public Camera camera;
	public Accelerometer accelerometer;

	public RobotWithCameraAccelerometerWheelEncoder(int sensorResponse) {

		camera = new Camera("camera");
		accelerometer = new Accelerometer("accelerometer");
		this.getCamera(getCameraName()).enable(sensorResponse);
		this.getAccelerometer(getAccelerometerName()).enable(sensorResponse);
		this.enableEncoders(sensorResponse);
	}

	protected String getCameraName() {
		return camera.getName();
	}

	protected String getAccelerometerName() {
		return accelerometer.getName();
	}

	public int getCameraWidth() {
		return this.getCamera(getCameraName()).getWidth();
	}

	public int getCameraHeight() {
		return this.getCamera(getCameraName()).getHeight();
	}

	public int[] getCameraValues() {
		return this.getCamera(getCameraName()).getImage();
	}

	public double getXAccel() {
		return this.getAccelerometer(getAccelerometerName()).getValues()[0];
	}

	public double getYAccel() {
		return this.getAccelerometer(getAccelerometerName()).getValues()[1];
	}

	public double getZAccel() {
		return this.getAccelerometer(getAccelerometerName()).getValues()[2];
	}

	public double[] getEncoderValues() {
		return new double[]{this.getLeftEncoder(), this.getRightEncoder()};
	}
}
