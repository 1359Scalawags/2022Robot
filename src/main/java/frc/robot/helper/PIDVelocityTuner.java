package frc.robot.helper;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDVelocityTuner {
    private SparkMaxPIDController controller;
    private RelativeEncoder encoder;

    private PIDValues pidValues;
    private double targetValue;
    private double errorValue;
    private double actualValue;

    public PIDVelocityTuner(RelativeEncoder encoder, SparkMaxPIDController controller, PIDValues initialValues, int initialTarget) {
        this.controller = controller;
        this.encoder = encoder;
        this.pidValues = new PIDValues(initialValues);
        this.targetValue = initialTarget;

        controller.setP(this.pidValues.kP);
        controller.setI(this.pidValues.kI);
        controller.setD(this.pidValues.kD);
        controller.setIZone(this.pidValues.kIz);
        controller.setFF(this.pidValues.kFf);

        SmartDashboard.putNumber("P Gain", pidValues.kP);
        SmartDashboard.putNumber("I Gain", pidValues.kI);
        SmartDashboard.putNumber("D Gain", pidValues.kD);
        SmartDashboard.putNumber("I Zone", pidValues.kIz);
        SmartDashboard.putNumber("Feed Forward", pidValues.kFf);
        SmartDashboard.putNumber("Target RPM", targetValue);
        actualValue = (int)encoder.getVelocity();
        errorValue = targetValue - actualValue;
        SmartDashboard.putNumber("Actual RPM", actualValue);
        SmartDashboard.putNumber("Error RPM", errorValue);
    }

    public void periodic() {
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double target = SmartDashboard.getNumber("Target RPM", 0);

        if((p != pidValues.kP)) { controller.setP(p); pidValues.kP = p; }
        if((i != pidValues.kI)) { controller.setI(i); pidValues.kI = i; }
        if((d != pidValues.kD)) { controller.setD(d); pidValues.kD = d; }
        if((iz != pidValues.kIz)) { controller.setIZone(iz); pidValues.kIz = iz; }
        if((ff != pidValues.kFf)) { controller.setFF(ff); pidValues.kFf = ff; }
        if(target != targetValue) {controller.setReference(target, ControlType.kVelocity); targetValue = target;}

        actualValue = encoder.getVelocity();
        errorValue = targetValue - actualValue;
        SmartDashboard.putNumber("Actual RPM", actualValue);
        SmartDashboard.putNumber("Error RPM", errorValue);
        System.out.println("Target: " + targetValue + "  Actual: " + actualValue + "  Error: " + errorValue);

    }

}
