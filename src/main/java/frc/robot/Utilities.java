
package frc.robot;

import java.security.CodeSigner;

public class Utilities {

    public static double Clamp(double value, double min, double max) { // clamp values of PID
		return Math.max(min, Math.min(max, value));
	}

	public static boolean IsCloseTo(double a, double b) {
		double diff = Math.abs(a-b);
		return diff < Constants.TOLERANCE;
	}
	public static boolean IsCloseTo(double a, double b, double tolerance) {
		double diff = Math.abs(a-b);
		return diff < tolerance;
	}

	/**
	 * Constrains an angle to the range -180 and 180
	 * @param angle the original angle
	 * @return an equivalent angle between -180 and 180
	 */
	public static double NormalizeAngle(double angle) {
		double temp = angle % 360; // remove multiples of 360
		temp = (temp + 360) % 360; // force it to be positive
        if(temp > 180) {
			temp -= 360;
		}
		return temp;
	}

	public static double GetSmoothStartStopSpeed(double fullSpeed, double slowFactor, double currentPosition, double maxPosition, double minPosition) {
		if(maxPosition < minPosition) {
			return fullSpeed;
		}
		double range = maxPosition - minPosition;
		if(currentPosition < minPosition + range * Constants.SMOOTHBREAKPOINTLOCATIONPERCENT) {
			return fullSpeed * Constants.SMOOTHBREAKPOINTSPEEDFACTOR;
		} else if(currentPosition > maxPosition - range * Constants.SMOOTHBREAKPOINTLOCATIONPERCENT ) {
			return fullSpeed * Constants.SMOOTHBREAKPOINTSPEEDFACTOR;
		} else {
			return fullSpeed;
		}
	}
    
}