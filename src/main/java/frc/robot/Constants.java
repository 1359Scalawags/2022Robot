// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Timer; //TODO: Do we need to import a timer or not?
/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be
 * declared globally (i.e. public static). Do not put anything functional in
 * this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {

    public static final boolean PRESSED = false;
    public static final boolean NOTPRESSED = true;
    public static final double TOLERANCE = .1;

   
    public static final double AutoMotorSpeed = 1;
    public static final double AutoMotorDistance = 1;
    public static final double AngleTurnBy = 0;
    public static final class Drive {
        //Drive motor ports
        public static final int kLeftFrontPort = 13;
        public static final int kLeftRearPort = 14;
        public static final int kRightFrontPort = 11;
        public static final int kRightRearPort = 12;
        //Drive motor speeds
        public static final float kBoostSpeed = 0.85f;
        public static final float kMaxDriveSpeed = 0.85f;
        public static final float kBaseDriveSpeed = 0.15f;
        //Motor varibles
        public static final double PID_P = 0.00001;
        public static final double PID_I = 0.000005;
        public static final double PID_D = 0.0001;
        public static final double PID_Iz = 0;
        public static final double PID_Ff = 0;
        //Gyro varibles
        public static final double gyrokP =0.1;
        public static final double gyrokI =0.1;
        public static final double gyrokD =0.1;
        public static final double gyrokIz =0.1;
        public static final double gyrokFf =0.1;
        
        public static final double AutoStraightSpeed = 1;
        public static final double maxTurnSpeed = .5;
    }

    public static final class Climb {
        //Climb Motors and Servos (Solenoids too)
        public static final int kClimbMotor = 30;
        // public static final int kTraverseMotor = 7;
        public static final int kAntiDropClimbServo = 1;
        // public static final int kAntiDropTraverseServo = 1;
        // public static final int kClimberSolenoid = 2;
        //Climb Motor Speeds
        public static final float kClimbMotorSpeed = 0.5f;
        public static final float kTraverseMotorSpeed = 0.5f;
        //gonna need actual values here
        public static final float transferLockedServoPosition = 1f;
        public static final float transferUnlockedServoPosition = 1f;
        public static final float kClimbServoLockPosition = 1.0f;
        public static final float kClimbServoUnlockPosition = 2.0f;


        public static final boolean kClimbLimitSwitch = true;
        public static final int kClimbLimitSwitchPort = 0;
        // public static final boolean kTraverseLimitSwitch = true;
        // public static final int kTraverseLimitSwitchPort = 4;
        public static final double kClimbConversionFactor = 1;
        // public static final double kTraverseConversionFactor = 1;
       
        public static final double kClimbHeightlimit = 20;
        // public static final double kTraverseHeightlimit = 20;
    }

    public static final class BallHandling {
        //Ball Handling Motors
        public static final int kLoadMotor1 = 15;
        // public static final int kLoadMotor2 = 9;
        public static final int kStagingMotor = 25;
        public static final int kShootMotor = 21;
        //Ball Motor Speeds
        public static final float kLoadMotorsSpeed = 0.5f;
        public static final float kStagingMotorSpeed = 0.5f;
        public static final float kShootMotorSpeed = 0.5f;
        public static final float kMinShootMotorSpeed = 0.95f;
        public static final float kShootMotorMaxRPM = 5000.0f;
        //Ball Timers
        public static final float shootTimerLength = 3f; //TODO: Change this later if needed
        public static final float reverseballTimer = 5f;

        public static final boolean BALLPRESENT = false;

        public static final double kP = 6e-5; 
        public static final double kI = 0;
        public static final double kD = 0; 
        public static final double kIz = 0; 
        public static final double kFF = 0.000015; 
        public static final int kMaxOutput = 1; 
        public static final int kMinOutput = -1;

        public static final int kloadinput = 0;        //TODO: Find out and put the actual port numbers for the digital inputs on the robot, here!!!
        public static final int kstaginginput = 1;

    }

    

}
