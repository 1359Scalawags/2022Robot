
package frc.robot.subsystems;

import frc.robot.commands.*;
import frc.robot.helper.PID_Values;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Utilities;


import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class DriveSystem extends SubsystemBase {
    // CED Gyro stuff
    private PID_Values gyroPids;
    private ADXRS450_Gyro driveGyro;
    private PIDController gyroControl;
    
    private CANSparkMax leftFrontMotor;
    private CANSparkMax leftRearMotor;
    private MotorControllerGroup leftSpeedController;
    private CANSparkMax rightFrontMotor;
    private CANSparkMax rightRearMotor;
    private MotorControllerGroup rightSpeedController;
    boolean reverse = false;
    private DifferentialDrive differentialDrive;

    public DriveSystem() {
        gyroPids = new PID_Values(Constants.Drive.gyrokP, Constants.Drive.gyrokI, Constants.Drive.gyrokD, Constants.Drive.gyrokIz, Constants.Drive.gyrokFf);
        driveGyro = new ADXRS450_Gyro();
        addChild("Gyro", driveGyro);
        gyroControl = new PIDController(gyroPids.kP, gyroPids.kI, gyroPids.kD);



        leftFrontMotor = new CANSparkMax(Constants.Drive.kLeftFrontPort, MotorType.kBrushless);

        leftFrontMotor.restoreFactoryDefaults();
        leftFrontMotor.setInverted(false);
        leftFrontMotor.setIdleMode(IdleMode.kCoast);

        leftRearMotor = new CANSparkMax(Constants.Drive.kLeftRearPort, MotorType.kBrushless);

        leftRearMotor.restoreFactoryDefaults();
        leftRearMotor.setInverted(false);
        leftRearMotor.setIdleMode(IdleMode.kCoast);

        leftSpeedController = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
        addChild("LeftSpeedController", leftSpeedController);

        rightFrontMotor = new CANSparkMax(Constants.Drive.kRightFrontPort, MotorType.kBrushless);

        rightFrontMotor.restoreFactoryDefaults();
        rightFrontMotor.setInverted(true);
        rightFrontMotor.setIdleMode(IdleMode.kCoast);

        rightRearMotor = new CANSparkMax(Constants.Drive.kRightRearPort, MotorType.kBrushless);

        rightRearMotor.restoreFactoryDefaults();
        rightRearMotor.setInverted(true);
        rightRearMotor.setIdleMode(IdleMode.kCoast);

        rightSpeedController = new MotorControllerGroup(rightFrontMotor, rightRearMotor);
        addChild("RightSpeedController", rightSpeedController);

        differentialDrive = new DifferentialDrive(leftSpeedController, leftFrontMotor);
        addChild("DifferentialDrive", differentialDrive);
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

    }
    //CED even more gyro stuff
    public void ResetGyro(){
        driveGyro.reset();
      }
      public double getAngle() {
        return driveGyro.getAngle();
      }
      public double getGyroRate() {
        return driveGyro.getRate();
      }
    
      public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
        double angleInput = driveGyro.getAngle();
        gyroControl.setSetpoint(targetAngle);
        double angleOutput = Utilities.Clamp(gyroControl.calculate(angleInput), -maxTurnSpeed, maxTurnSpeed);
        differentialDrive.arcadeDrive(moveSpeed, angleOutput);
       
      }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void move(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
    }

    // TODO: what are we using for gyro? we do need one for automode right?
    // public double getAngle() {
    // return driveGyro.getAngle();
    // }

    public void driveForward(double speed, double targetHeading) {
        final double scale = .01;
        double leftSpeed;
        double rightSpeed;
        // double headingError = getAngle() - targetHeading;

        // leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale,
        // -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        // rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale,
        // -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        // tankDrive(leftSpeed, rightSpeed);
    }

    public void reverseDirection() {
        if (reverse) {
            reverse = false;
        } else {
            reverse = true;
        }
    }

    public void driveBackward(double speed, double targetHeading) {
        final double scale = .01;
        double leftSpeed;
        double rightSpeed;
        // double headingError = getAngle() - targetHeading;

        // leftSpeed =Utilities.Clamp(-(speed) - headingError * scale,
        // -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        // rightSpeed = Utilities.Clamp(-(speed) + headingError * scale,
        // -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        // tankDrive(leftSpeed, rightSpeed);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        if (reverse) {
            differentialDrive.tankDrive(rightSpeed, leftSpeed);
        } else {
            differentialDrive.tankDrive(-leftSpeed, -rightSpeed);
        }
    }

    public void stop() {
        tankDrive(0, 0);
    }
}
