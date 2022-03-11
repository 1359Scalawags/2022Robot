package frc.robot;

public class Constants {

    public static final boolean PRESSED = false;
    public static final boolean NOTPRESSED = true;
    public static final double TOLERANCE = .1;

    public static final class Auto {
        //TODO: Calibrate the distance the robot travels
        public static final double MotorSpeed = 1;
        public static final double MotorDistance = 1;
        public static final double AngleTurnBy = 0;
    }

    public static final class Drive {
        //Drive motor ports
        public static final int kLeftFrontPort = 13;
        public static final int kLeftRearPort = 14;
        public static final int kRightFrontPort = 11;
        public static final int kRightRearPort = 12;
        //Drive motor speeds
        public static final float kTeleopBoostSpeed = 0.7f;
        public static final float kTeleopBaseDriveSpeed = 0.3f;        
        public static final float kDriveSpeed = 0.85f;

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

        public static final double kConversionFactor = 259.06735751;
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
        public static final double kClimbReboundHeight = 40;

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
        public static final float kShootMotorMaxRPM = 2500f;

        //Ball Timers
        public static final float shootTimerStartupTime = 2f;
        public static final float shootTimerLength = 3f; //TODO: Change this later if needed
        public static final float reverseballTimer = 5f;

        public static final boolean BALLPRESENT = false;

        public static final double kP = 4.9e-4; 
        public static final double kI = 0;
        public static final double kD = 0; 
        public static final double kIz = 0; 
        public static final double kFF = 0; 
        public static final int kMaxOutput = 1; 
        public static final int kMinOutput = -1;

        //public static final int kloadinput = 7;        
        //public static final int kstaginginput = 8;        
        
        //TODO: Do we need to change the Averaging sample count
        public static final int kSensorAverageSamples = 5; 
        public static final int kloadPingChannel = 1;
        public static final int kloadEchoChannel = 2;
        public static final int kloadSensorTripValue = 80;

        public static final int kstagePingChannel = 6;
        public static final int kstageEchoChannel = 7;
        public static final int kstageSensorTripValue = 100;

    }

    public static final class DisplaySystem {
        public static final int PDHCANID = 1;
    }
    

}
