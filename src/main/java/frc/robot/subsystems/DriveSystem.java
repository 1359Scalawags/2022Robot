
package frc.robot.subsystems;

import frc.robot.commands.*;
import frc.robot.helper.PID_Values;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Utilities;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADIS16470_IMU;



public class DriveSystem extends SubsystemBase {



    
    // CED Gyro stuff
    private PID_Values gyroPids;
    private ADIS16470_IMU driveGyro;
    private PIDController gyroControl;

    private CANSparkMax leftFrontMotor;
    private CANSparkMax leftRearMotor;
    private MotorControllerGroup leftSpeedController;
    private RelativeEncoder leftEncoder; 

    private CANSparkMax rightFrontMotor;
    private CANSparkMax rightRearMotor;
    private MotorControllerGroup rightSpeedController;
    private RelativeEncoder rightEncoder;


    public enum Directions{
    Backwards,
    Forwards
    }
   Directions direction = Directions.Forwards;
    // boolean reverse = false;
    private DifferentialDrive differentialDrive;
    
    public DriveSystem() {
        gyroPids = new PID_Values(Constants.Drive.gyrokP, Constants.Drive.gyrokI, Constants.Drive.gyrokD, Constants.Drive.gyrokIz, Constants.Drive.gyrokFf);
        driveGyro = new ADIS16470_IMU();
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

        differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);
        addChild("DifferentialDrive", differentialDrive);
        
    
        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);
        leftEncoder = leftRearMotor.getEncoder();
        leftEncoder.setPositionConversionFactor(Constants.Drive.kConversionFactor);
        rightEncoder = rightRearMotor.getEncoder();
        rightEncoder.setPositionConversionFactor(Constants.Drive.kConversionFactor);
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

    // public void move(double leftSpeed, double rightSpeed) {
    //     differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
    // }

 

   

    public void reverseDirection() {
        if (direction == Directions.Forwards) {
            direction = Directions.Backwards;
        } else {
            direction = Directions.Forwards;
        }
    }

    public void setDirection(Directions direction){
    this.direction = direction;
    }

    public double getDistanceLeft() {
        return leftEncoder.getPosition(); 
    }
    public double getDistanceRight() {
        return rightEncoder.getPosition();

    }

    public double getAverageDistance() {
        return (Math.abs(getDistanceRight()) + Math.abs(getDistanceLeft())) / 2;
    }

    public void driveBackward(double speed, double targetHeading) {
        final double scale = .01;
        double leftSpeed;
        double rightSpeed;
        double headingError = getAngle() - targetHeading;

        leftSpeed =Utilities.Clamp(-(speed) - headingError * scale,
        -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        rightSpeed = Utilities.Clamp(-(speed) + headingError * scale,
        -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        tankDrive(leftSpeed, rightSpeed);
    }
    public void driveForward(double speed, double targetHeading) {
        final double scale = .01;
        double leftSpeed;
        double rightSpeed;
        double headingError = getAngle() - targetHeading;

        leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale,
        -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale,
        -Constants.Drive.kMaxDriveSpeed, Constants.Drive.kMaxDriveSpeed);
        tankDrive(leftSpeed, rightSpeed);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        if (direction == Directions.Backwards) {
            differentialDrive.tankDrive(rightSpeed, leftSpeed);
        } else {
            differentialDrive.tankDrive(-leftSpeed, -rightSpeed);
        }
    }

    public void stop() {
        tankDrive(0, 0);
    }
}