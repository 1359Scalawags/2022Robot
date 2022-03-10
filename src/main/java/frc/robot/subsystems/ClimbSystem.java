package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Utilities;
import frc.robot.extensions.SendableCANSparkMax;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
// import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbSystem extends SubsystemBase {
    private DigitalInput LowerClimbLimitSwitch;
    // private DigitalInput LowerTraverseLimitSwitch;

    private SendableCANSparkMax climbMotor;
    private Servo antidropClimbServo;


    private RelativeEncoder climbEncoder;

    // used to turn off motors to prevent unnecessary strain
    private int localClimbMotorMultiplier;
    private boolean climberIsMasterLocked;

    public ClimbSystem() {
        climberIsMasterLocked = true;
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

        int tempMultiplier = 1;

        // Ced Makes sure the motors don't fry or breack anything when they hit the
        // bottom
        if (LowerClimbLimitSwitch.get() == Constants.Climb.kClimbLimitSwitchActivated) {
            climbEncoder.setPosition(0);
            if (climbMotor.get() < 0 || RobotContainer.getInstance().getassistController().getLeftY() < 0) {
                climbMotor.stopMotor();
                tempMultiplier = 0;
                System.out.println("LowerClimbSwitchActivated & Motor Turned off");
            }
            System.out.println("LowerclimbSwitchActivated");
        }

        boolean isServoCloseToLockPosition = Utilities.IsCloseTo(antidropClimbServo.get(), Constants.Climb.kClimbServoLockPosition, Constants.Climb.kClimbServoPositionTolerance);
        if (isServoCloseToLockPosition && (climbMotor.get() > 0 || RobotContainer.getInstance().getassistController().getLeftY() > 0)) {
            tempMultiplier  = 0;
            climbMotor.stopMotor();
            System.out.println("Servo locked and trying to go up...Motor disabled");
        } 

        if (climbEncoder.getPosition() > Constants.Climb.kClimbHeightlimit) {
            if (climbMotor.get() > 0 || RobotContainer.getInstance().getassistController().getLeftY() > 0) {
                climbMotor.stopMotor();
                tempMultiplier  = 0;

            }
        }

        if(climberIsMasterLocked) {
            climbMotor.stopMotor();
            tempMultiplier = 0;
        }
        localClimbMotorMultiplier = tempMultiplier;
        
    }

    public double getClimbPosition() {
         return climbEncoder.getPosition();
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
            climbMotor.set(climbSpeed * localClimbMotorMultiplier);
        }
        else {
            climbMotor.stopMotor();
        }
    }
}
