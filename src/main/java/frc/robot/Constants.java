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

    public static final class Drive {
        //Drive motor ports
        public static final int kLeftFrontPort = 0;
        public static final int kLeftRearPort = 1;
        public static final int kRightFrontPort = 2;
        public static final int kRightRearPort = 3;
        //Drive motor speeds
        public static final float kMaxDriveSpeed = 0.85f;
        public static final float kSLowDriveSpeed = 0.15f;
    }

    public static final class Climb {
        //Climb Motors and Servos (Solenoids too)
        public static final int kClimbMotor = 8;
        public static final int kTraverseMotor = 7;
        public static final int kAntiDropClimbServo = 0;
        public static final int kAntiDropTraverseServo = 1;
        public static final int kClimberSolenoid = 2;
        //Climb Motor Speeds
        public static final float kClimbMotorSpeed = 0.5f;
        public static final float kTraverseMotorSpeed = 0.5f;
        //gonna need actual values here
        public static final float transferLockedServoPosition = 1f;
        public static final float transferUnlockedServoPosition = 1f;
        public static final float kClimbServoLockPosition = 1.0f;
        public static final float kClimbServoUnlockPosition = 2.0f;


        public static final boolean kClimbLimitSwitch = true;
        public static final int kClimbLimitSwitchPort = 3;
        public static final boolean kTraverseLimitSwitch = true;
        public static final int kTraverseLimitSwitchPort = 4;
    }

    public static final class BallHandling {
        //Ball Handling Motors
        public static final int kLoadMotor1 = 4;
        public static final int kLoadMotor2 = 9;
        public static final int kStagingMotor = 6;
        public static final int kShootMotor = 5;
        //Ball Motor Speeds
        public static final float kLoadMotorsSpeed = 0.5f;
        public static final float kStagingMotorSpeed = 0.5f;
        public static final float kShootMotorSpeed = 0.5f;
        public static final float kShootMotorMaxRPM = 5000.0f;

        public static final boolean BALLPRESENT = false;

        public static final double kP = 6e-5; 
        public static final double kI = 0;
        public static final double kD = 0; 
        public static final double kIz = 0; 
        public static final double kFF = 0.000015; 
        public static final int kMaxOutput = 1; 
        public static final int kMinOutput = -1;

    }

    

}
