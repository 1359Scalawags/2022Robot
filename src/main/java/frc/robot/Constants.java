package frc.robot;

public class Constants {

    public static final boolean PRESSED = false;
    public static final boolean NOTPRESSED = !PRESSED;
    public static final double TOLERANCE = .1;
    public static final double SMOOTHBREAKPOINTLOCATIONPERCENT = 0.1;
    public static final double SMOOTHBREAKPOINTSPEEDFACTOR = 0.5;

    public static final class Auto {
        //TODO: (ON HOLD) Calibrate the distance the robot travels more accurately
        public static final double MotorSpeed = .4;
        public static final double MotorDistance = 8;
        public static final double AngleTurnBy = 0;
    }

    public static final class Drive {
        //Drive motor ports
        public static final int kLeftFrontPort = 13;
        public static final int kLeftRearPort = 14;
        public static final int kRightFrontPort = 11;
        public static final int kRightRearPort = 12;
        //Drive motor speeds
        public static final float kTeleopBoostSpeed = 0.55f;
        public static final float kTeleopBaseDriveSpeed = 0.45f;        
        public static final float kDriveSpeed = 0.85f;

        //Motor varibles
        // public static final double kDriveP = 0.00001; //these are not being used anywhere
        // public static final double kDriveI = 0.000005;
        // public static final double kDriveD = 0.0001;
        // public static final double kDriveIz = 0;
        // public static final double kDriveFf = 0; 
        //Gyro varibles
        public static final double kGyroP =0.001; //TODO: (ON HOLD) These PID values are very large and should be changed
        public static final double kGyroI =0.000001;
        public static final double kGyroD =0.00000001;
        public static final double kGyroIz =0.01;
        public static final double kGyroFf =0.00001;
        
        public static final double AutoStraightSpeed = 1;
        public static final double maxTurnSpeed = .5;

        public static final double kConversionFactor = .16;
    }

    public static final class Climb {
        //Climb Motors and Servos (Solenoids too)
        public static final int kClimbMotor = 30;
        public static final int kAntiDropClimbServo = 1;
        //Climb Motor Speeds
        public static final float kClimbMotorSpeed = 0.5f;
        public static final float kClimbServoLockPosition = 0.4f;
        public static final float kClimbServoUnlockPosition = 0.65f;
        public static final float kClimbServoPositionTolerance = 0.15f;

        public static final boolean kClimbLimitSwitchActivated = false;
        public static final int kClimbLimitSwitchPort = 0;
        public static final double kClimbConversionFactor = 1;
       
        public static final double kClimbHeightlimit = 280;
        public static final double kClimbReboundHeight = 25;

        public static final double kReleaseTensionSpeed = -0.5;
        public static final double kReleaseTensionTimer = 0.1;
    }

    public static final class BallHandling {
        //Ball Handling Motors
        public static final int kLoadMotor = 15;
        public static final int kStagingMotor = 25;
        public static final int kShootMotor = 21;

        //Ball Motor Speeds
        public static final float kLoadMotorsSpeed = 0.5f;
        public static final float kStagingMotorSpeed = 0.5f;
        public static final float kShootMotorMaxRPM = 2250f;

        //Ball Timers
        public static final float shootTimerStartupTime = 1f;
        public static final float shootTimerLength = 1.5f; 
        public static final float reverseballTimer = 5f;
        public static final float manualStageBallTime = 1.0f;

        public static final boolean BALLPRESENT = false;

        public static final double kShooterP = .00049;
        public static final double kShooterI = 0;
        public static final double kShooterD = 0; 
        public static final double kShooterIz = 0; 
        public static final double kShooterFf = 0.0; // Tuned for 2268 RPM
        public static final int kShooterMaxOutput = 1; 
        public static final int kShooterMinOutput = -1;

        //public static final int kloadinput = 7;        
        //public static final int kstaginginput = 8;        
        
        //Sensor Stuff
        public static final int kSensorAverageSamples = 3;
        public static final int kloadPingChannel = 3;
        public static final int kloadEchoChannel = 4;
        // public static final int kloadSensorTripValue = 150;
        public static final int kloadSensorTripValue = 80;
        // !!WHY WAS THIS ↑ DELETED INSTEAD OF BEING COMMENTED OUT!!

        public static final int kstagePingChannel = 8;
        public static final int kstageEchoChannel = 9;
        // public static final int kstageSensorTripValue = 150;
        public static final int kstageSensorTripValue = 100;
        // !!WHY WAS THIS ↑ DELETED INSTEAD OF BEING COMMENTED OUT!!

        public static final int kUltrasonicFrameCount = 3;

        //Ball Arm Motors
        public static final int kArmExtendMotor = 40; 
        public static final int kArmSpinMotor = 41;
        
        //Ball Arm Limits
        public static final double kArmMaxExtendAngle = 45;
        public static final int kArmRetractLimitID = 6; 
        public static final double kArmExtendTolerance = 1;

        //Arm Speeds
        public static final double kArmSpinMotorSpeed = 0.15f; //Change Thses Later if needed
        public static final double kArmExtendMotorSpeed = 0.2f;
        public static final double kArmRetractMotorSpeed = 0.25f;
        public static final double kArmParkSpeedMultiplier = 0.03;
        public static final double kMaxArmParkSpeed = 0.05;
    }

    public static final class DisplaySystem {
        public static final int PDHCANID = 1;
        public static final int CAM_WIDTH = 320;
        public static final int CAM_HEIGHT = 240;
        public static final int CAM_FPS = 15;
    }
    

}
