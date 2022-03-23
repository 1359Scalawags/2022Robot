
package frc.robot.subsystems;

import frc.robot.helper.PIDValues;
//import frc.robot.helper.SparkMaxVelocityTuner;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.Utilities;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;



public class DriveSystem extends SubsystemBase {



    
    // CED Gyro stuff
    private PIDValues gyroPids;
    
    //private ADIS16470_IMU driveGyro;
    private ADXRS450_Gyro driveGyro;
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
        gyroPids = new PIDValues(Constants.Drive.kGyroP, Constants.Drive.kGyroI, Constants.Drive.kGyroD, Constants.Drive.kGyroIz, Constants.Drive.kGyroFf);
        //driveGyro = new ADIS16470_IMU();
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

        leftFrontMotor.follow(ExternalFollower.kFollowerDisabled,0);
        leftRearMotor.follow(leftFrontMotor);


        
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

        rightFrontMotor.follow(ExternalFollower.kFollowerDisabled,0);
        rightRearMotor.follow(rightFrontMotor);

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
      public double getNormalizedAngle() {
        //Using 0 to 360 means you'll get odd behavior when rotating left past 0 (goes from 0 to 359)
        return Utilities.NormalizeAngle(driveGyro.getAngle());
      }
      public double getGyroRate() {
        return driveGyro.getRate();
      }
    
      public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {
        targetAngle = Utilities.NormalizeAngle(targetAngle);
        double angleInput = this.getNormalizedAngle();
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
        double headingError = this.getNormalizedAngle() - Utilities.NormalizeAngle(targetHeading);

        leftSpeed =Utilities.Clamp(-(speed) - headingError * scale,
        -Constants.Drive.kDriveSpeed, Constants.Drive.kDriveSpeed);
        rightSpeed = Utilities.Clamp(-(speed) + headingError * scale,
        -Constants.Drive.kDriveSpeed, Constants.Drive.kDriveSpeed);
        tankDrive(leftSpeed, rightSpeed);
    }

    //TODO: (ON HOLD) This function causes the robot to spin when drive is reversed
    public void driveForward(double speed, double targetHeading) {
        final double scale = .01;
        double leftSpeed;
        double rightSpeed;
        double headingError = getNormalizedAngle() - Utilities.NormalizeAngle(targetHeading);

        leftSpeed = Utilities.Clamp(Math.abs(speed) - headingError * scale,
        -Constants.Drive.kDriveSpeed, Constants.Drive.kDriveSpeed);
        rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale,
        -Constants.Drive.kDriveSpeed, Constants.Drive.kDriveSpeed);
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

    // Gyro doesn't use a Rev-based controller, so this won't work
    // public PIDVelocityTuner initializeTests() {
    //     PIDValues initialPID = new PIDValues(Constants.Drive.kGyroP, 
    //                                          Constants.Drive.kGyroI, 
    //                                          Constants.Drive.kGyroD, 
    //                                          Constants.Drive.kGyroIz, 
    //                                          Constants.Drive.kGyroFf);
    //     PIDVelocityTuner tuner = new PIDVelocityTuner("Gyro Tune", shootEncoder, shootController, initialPID, (int)Constants.BallHandling.kShootMotorMaxRPM);
    //     return tuner;
    // }
}