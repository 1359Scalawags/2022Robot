package frc.robot.helper;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Map;

public class PIDPositionTuner {
    private SparkMaxPIDController sparkController;
    //private PIDController stdController;
    private RelativeEncoder sparkEncoder;
    //private Encoder stdEncoder;

    private ShuffleboardTab tunerTab;
    private NetworkTableEntry pEntry;
    private NetworkTableEntry iEntry;
    private NetworkTableEntry dEntry;
    private NetworkTableEntry izEntry;
    private NetworkTableEntry ffEntry;
    private NetworkTableEntry actualEntry;
    private NetworkTableEntry targetEntry;
    private NetworkTableEntry errorEntry;

    private PIDValues pidValues;
    private double targetValue;
    private double errorValue;
    private double actualValue;

    public PIDPositionTuner(String name, RelativeEncoder encoder, SparkMaxPIDController controller, PIDValues initialValues, int initialTarget) {
        this.sparkController = controller;
        this.sparkEncoder = encoder;
        this.pidValues = new PIDValues(initialValues);
        this.targetValue = initialTarget;
        tunerTab = Shuffleboard.getTab(name);
        layoutTabs(tunerTab);

        controller.setP(this.pidValues.kP);
        controller.setI(this.pidValues.kI);
        controller.setD(this.pidValues.kD);
        controller.setIZone(this.pidValues.kIz);
        controller.setFF(this.pidValues.kFf);

        pEntry.setDouble(this.pidValues.kP);
        iEntry.setDouble(this.pidValues.kI);
        dEntry.setDouble(this.pidValues.kD);
        izEntry.setDouble(this.pidValues.kIz);
        ffEntry.setDouble(this.pidValues.kFf);
        targetEntry.setDouble(this.targetValue);


        // SmartDashboard.putNumber("P Gain", pidValues.kP);
        // SmartDashboard.putNumber("I Gain", pidValues.kI);
        // SmartDashboard.putNumber("D Gain", pidValues.kD);
        // SmartDashboard.putNumber("I Zone", pidValues.kIz);
        // SmartDashboard.putNumber("Feed Forward", pidValues.kFf);
        // SmartDashboard.putNumber("Target LOC", targetValue);
        // actualValue = (int)encoder.getVelocity();
        // errorValue = targetValue - actualValue;
        // SmartDashboard.putNumber("Actual LOC", actualValue);
        // SmartDashboard.putNumber("Error LOC", errorValue);
    }

    public void periodic() {
        syncEntries();
        // double p = SmartDashboard.getNumber("P Gain", 0);
        // double i = SmartDashboard.getNumber("I Gain", 0);
        // double d = SmartDashboard.getNumber("D Gain", 0);
        // double iz = SmartDashboard.getNumber("I Zone", 0);
        // double ff = SmartDashboard.getNumber("Feed Forward", 0);
        // double target = SmartDashboard.getNumber("Target LOC", 0);

        // if((p != pidValues.kP)) { controller.setP(p); pidValues.kP = p; }
        // if((i != pidValues.kI)) { controller.setI(i); pidValues.kI = i; }
        // if((d != pidValues.kD)) { controller.setD(d); pidValues.kD = d; }
        // if((iz != pidValues.kIz)) { controller.setIZone(iz); pidValues.kIz = iz; }
        // if((ff != pidValues.kFf)) { controller.setFF(ff); pidValues.kFf = ff; }
        // if(target != targetValue) {controller.setReference(target, ControlType.kVelocity); targetValue = target;}

        // actualValue = encoder.getPosition();
        // errorValue = targetValue - actualValue;
        // SmartDashboard.putNumber("Actual LOC", actualValue);
        // SmartDashboard.putNumber("Error LOC", errorValue);
        // System.out.println("Target: " + targetValue + "  Actual: " + actualValue + "  Error: " + errorValue);

    }

    private void syncEntries() {
        double p = pEntry.getDouble(0);
        double i = iEntry.getDouble(0);
        double d = dEntry.getDouble(0);
        double iz = izEntry.getDouble(0);
        double ff = ffEntry.getDouble(0);
        double target = targetEntry.getDouble(0);

        if((p != pidValues.kP)) { sparkController.setP(p); pidValues.kP = p; }
        if((i != pidValues.kI)) { sparkController.setI(i); pidValues.kI = i; }
        if((d != pidValues.kD)) { sparkController.setD(d); pidValues.kD = d; }
        if((iz != pidValues.kIz)) { sparkController.setIZone(iz); pidValues.kIz = iz; }
        if((ff != pidValues.kFf)) { sparkController.setFF(ff); pidValues.kFf = ff; }
        if(target != targetValue) {sparkController.setReference(target, ControlType.kPosition); targetValue = target;}

        actualValue = sparkEncoder.getPosition();
        errorValue = targetValue - actualValue;

        actualEntry.setDouble(actualValue);
        errorEntry.setDouble(errorValue);
        System.out.println("Actual: " + actualValue + " Error: " + errorValue);
    }

    private void layoutTabs(ShuffleboardTab shuffleTab) {
        pEntry = shuffleTab
                    .add("P Gain", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(0, 0)
                    .getEntry();

        iEntry = shuffleTab
                    .add("I Gain", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(1, 0)
                    .getEntry();

        dEntry = shuffleTab
                    .add("D Gain", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(2, 0)
                    .getEntry();

        izEntry = shuffleTab
                    .add("I Zone", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(3, 0)
                    .getEntry();

        ffEntry = shuffleTab
                    .add("Feed Forward", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(4, 0)
                    .getEntry();

        targetEntry = shuffleTab
                    .add("Target LOC", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(1, 1)
                    .getEntry();

        actualEntry = shuffleTab
                    .add("Actual LOC", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(2, 1)
                    .getEntry();

        errorEntry = shuffleTab
                    .add("Error LOC", 0)
                    .withWidget(BuiltInWidgets.kTextView)
                    .withSize(1,1)
                    .withPosition(3, 1)
                    .getEntry();                    
    }

}
