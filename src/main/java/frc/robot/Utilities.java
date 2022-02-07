
package frc.robot;

public class Utilities {

    public static double Clamp(double value, double min, double max) { // clamp values of PID
		return Math.max(min, Math.min(max, value));
	}

	public static boolean IsCloseTo(double a, double b) {
		double diff = Math.abs(a-b);
		return diff < Constants.TOLERANCE;
	}
    
}