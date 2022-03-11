package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.extensions.SendableCANSparkMax;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

public class ClimbSystem extends SubsystemBase {
    private DigitalInput LowerClimbLimitSwitch;

    private SendableCANSparkMax climbMotor;
    private RelativeEncoder climbEncoder;  
    private Servo antidropClimbServo;

    // used to turn off motors to prevent unnecessary strain
    //private int localClimbMotorMultiplier;
    private boolean climberIsMasterLocked;
    private double requestedMotorSpeed;

    public ClimbSystem() {
        climberIsMasterLocked = true;
        requestedMotorSpeed = 0;
        climbMotor = new SendableCANSparkMax(Constants.Climb.kClimbMotor, MotorType.kBrushless, this);

        climbMotor.restoreFactoryDefaults();
        climbMotor.setInverted(false);
        climbMotor.setIdleMode(IdleMode.kBrake);

        antidropClimbServo = new Servo(Constants.Climb.kAntiDropClimbServo);
        addChild("AntidropClimbServo", antidropClimbServo);

        LowerClimbLimitSwitch = new DigitalInput(Constants.Climb.kClimbLimitSwitchPort);
        addChild("LowerClimbLimitSwitch", LowerClimbLimitSwitch);

        climbEncoder = climbMotor.getEncoder();
        //climbEncoder.setPosition(0);
        climbEncoder.setPositionConversionFactor(Constants.Climb.kClimbConversionFactor);
        setServoLock(true);
    }

    @Override
    public void periodic() {

        int safetyMultiplier = 0;
        boolean isServoCloseToLockPosition = Utilities.IsCloseTo(antidropClimbServo.get(), Constants.Climb.kClimbServoLockPosition, Constants.Climb.kClimbServoPositionTolerance);
        if(LowerClimbLimitSwitch.get() == Constants.Climb.kClimbLimitSwitchActivated) {
            climbEncoder.setPosition(0);
        }        
            
        if(!climberIsMasterLocked) {
            if(requestedMotorSpeed > 0 || climbMotor.get() > 0) {
                if(!isServoCloseToLockPosition && climbEncoder.getPosition() < Constants.Climb.kClimbHeightlimit) {
                    safetyMultiplier = 1;
                }
            } else if(requestedMotorSpeed < 0 || climbMotor.get() < 0) {
                if(LowerClimbLimitSwitch.get() != Constants.Climb.kClimbLimitSwitchActivated) {
                    safetyMultiplier = 1;
                }
            }
        }

        climbMotor.set(requestedMotorSpeed * safetyMultiplier);

        // // Ced Makes sure the motors don't fry or breack anything when they hit the
        // // bottom
        // if (LowerClimbLimitSwitch.get() == Constants.Climb.kClimbLimitSwitchActivated) {
        //     climbEncoder.setPosition(0);
        //     //} else if (climbMotor.get() < 0 || RobotContainer.getInstance().getassistController().getLeftY() < 0) {
        //     if (requestedMotorSpeed < 0) {
        //         climbMotor.stopMotor();
        //         tempMultiplier = 0;
        //         //System.out.println("LowerClimbSwitchActivated & Motor Turned off");
        //     } 
        // }

        
        // //if (isServoCloseToLockPosition && (climbMotor.get() > 0 || RobotContainer.getInstance().getassistController().getLeftY() > 0)) {
        // if (isServoCloseToLockPosition && requestedMotorSpeed > 0) {
        //     tempMultiplier  = 0;
        //     climbMotor.stopMotor();
        //     //System.out.println("Servo locked and trying to go up...Motor disabled");
        // } 

        // if (climbEncoder.getPosition() > Constants.Climb.kClimbHeightlimit) {
        //     //if (climbMotor.get() > 0 || RobotContainer.getInstance().getassistController().getLeftY() > 0) {
        //     if (requestedMotorSpeed > 0) {
        //         climbMotor.stopMotor();
        //         tempMultiplier  = 0;

        //     }
        // }

        // if(climberIsMasterLocked) {
        //     climbMotor.stopMotor();
        //     tempMultiplier = 0;
        // }
        //localClimbMotorMultiplier = tempMultiplier;
        //climbMotor.set(requestedMotorSpeed * tempMultiplier);
    }

    public double getClimbPosition() {
         return climbEncoder.getPosition();
     } 

    public boolean getBottomLimit() {
        return (LowerClimbLimitSwitch.get() == Constants.Climb.kClimbLimitSwitchActivated);
    }
    
    @Override
    public void simulationPeriodic() {

    }

    public void setServoLock(boolean isLocked) {

        if (isLocked == true) {
            antidropClimbServo.set(Constants.Climb.kClimbServoLockPosition);
        } else {
            antidropClimbServo.set(Constants.Climb.kClimbServoUnlockPosition);
            climberIsMasterLocked = false;
        }
    }

    public void setMasterLock(boolean isLocked) {
        if (isLocked == true) {
            climberIsMasterLocked = true;
        } else {
            climberIsMasterLocked = false;
        }       
    }

    public boolean getServoLocked() {
        if(Utilities.IsCloseTo(antidropClimbServo.get(), Constants.Climb.kClimbServoLockPosition, Constants.Climb.kClimbServoPositionTolerance) ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getMasterLocked() {
        return climberIsMasterLocked;
    }

    public void move(double climbSpeed) {
        if(Math.abs(climbSpeed) > 0.1f) {
            requestedMotorSpeed = climbSpeed;
        }
        else {
            requestedMotorSpeed = 0;
        }
    }
}
