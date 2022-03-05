//testing if it works to push
package frc.robot.subsystems;

// import frc.robot.commands.*; 
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Utilities;
import frc.robot.extensions.SendableCANSparkMax;

//import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.hal.EncoderJNI;
import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Servo;
// import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbSystem extends SubsystemBase {
    private DigitalInput LowerClimbLimitSwitch;
    // private DigitalInput LowerTraverseLimitSwitch;

    private SendableCANSparkMax climbMotor;
    private Servo antidropClimbServo;
    // private Solenoid climberSolenoid;
    // private CANSparkMax traverseMotor;
    // private Servo antidropTraverseServo;


    private RelativeEncoder climbEncoder;

    // private RelativeEncoder traverseEncoder;

    // used to turn off motors to prevent unnecessary strain
    private int localClimbMotorMultiplier;
    // private int localTraverseMotorSpeed;
    private boolean climberIsLocked;

    public ClimbSystem() {

        climbMotor = new SendableCANSparkMax(Constants.Climb.kClimbMotor, MotorType.kBrushless, this);

        climbMotor.restoreFactoryDefaults();
        climbMotor.setInverted(false);
        climbMotor.setIdleMode(IdleMode.kCoast);
        // climberSolenoid = new Solenoid(Constants.Climb.kClimberSolenoid, PneumaticsModuleType.CTREPCM, 0);
        // addChild("ClimberSolenoid", climberSolenoid);

        antidropClimbServo = new Servo(Constants.Climb.kAntiDropClimbServo);
        addChild("AntidropClimbServo", antidropClimbServo);

        // antidropTraverseServo = new Servo(Constants.Climb.kAntiDropTraverseServo);
        // addChild("AntidropTraverseServo", antidropTraverseServo);

        // traverseMotor = new CANSparkMax(Constants.Climb.kTraverseMotor, MotorType.kBrushless);

        // traverseMotor.restoreFactoryDefaults();
        // traverseMotor.setInverted(false);
        // traverseMotor.setIdleMode(IdleMode.kCoast);

        LowerClimbLimitSwitch = new DigitalInput(Constants.Climb.kClimbLimitSwitchPort);
        addChild("LowerClimbLimitSwitch", LowerClimbLimitSwitch);
        // LowerTraverseLimitSwitch = new DigitalInput(Constants.Climb.kTraverseLimitSwitchPort);
        // addChild("LowerTraverseLimitSwitch", LowerTraverseLimitSwitch);

        climbEncoder = climbMotor.getEncoder();
        climbEncoder.setPositionConversionFactor(Constants.Climb.kClimbConversionFactor);
        // traverseEncoder = traverseMotor.getEncoder();
        // traverseEncoder.setPositionConversionFactor(Constants.Climb.kTraverseConversionFactor);
        lockClimber(true);
    }

    //private int counter = 0;
    @Override
    public void periodic() {
        double currentJoystick = RobotContainer.getInstance().getassistController().getLeftY();
        int tempMultiplier = 1;
        //counter++;
        //System.out.println("Running: " + counter);

        // Ced Makes sure the motors don't fry or breack anything when they hit the
        // bottom
        if (LowerClimbLimitSwitch.get() == Constants.Climb.kClimbLimitSwitchActivated) {
            // TODO: figure out wich direction down is and adjust accordingly
            if (climbMotor.get() < 0 || RobotContainer.getInstance().getassistController().getLeftY() < 0) {
                climbMotor.stopMotor();
                tempMultiplier = 0;
                System.out.println("LowerClimbSwitchActivated & Motor Turned off");
            }
            System.out.println("LowerclimbSwitchActivated");
        }

        // if (LowerTraverseLimitSwitch.get() == Constants.Climb.kTraverseLimitSwitch) {

        //     if (traverseMotor.get() < 0) {
        //         traverseMotor.set(0);
        //     }
        // }

        //This may be the issue. There are really 3 cases
        // 1) Climber is unlocked and move any direction (OK)
        // 2) Climber is locked and going down (OK)
        // 3) Climber is locked and going up (NOT OK)
        boolean isServoCloseToLockPosition = Utilities.IsCloseTo(antidropClimbServo.get(), Constants.Climb.kClimbServoLockPosition, Constants.Climb.kClimbServoPositionTolerance);
        if (isServoCloseToLockPosition && (climbMotor.get() > 0 || RobotContainer.getInstance().getassistController().getLeftY() > 0)) {
            tempMultiplier  = 0;
            climbMotor.stopMotor();
            System.out.println("Servo locked and trying to go up...Motor disabled");
        } 

        // if (Utilities.IsCloseTo(antidropTraverseServo.get(), Constants.Climb.transferLockedServoPosition)) {
        //     localTraverseMotorSpeed = 0;
        // } else {
        //     localTraverseMotorSpeed = 1;
        // }

        if (climbEncoder.getPosition() > Constants.Climb.kClimbHeightlimit) {
            if (climbMotor.get() > 0 || RobotContainer.getInstance().getassistController().getLeftY() > 0) {
                climbMotor.stopMotor();
                tempMultiplier  = 0;

            }
        }
    //   //  if (traverseEncoder.getPosition() > Constants.Climb.kTraverseHeightlimit) {

    //        // if (traverseMotor.get() > 0) {
    //             traverseMotor.set(0);
    //         }
    //     }
        localClimbMotorMultiplier = tempMultiplier;
        
    }

    // public double getTraversePosition() {
    //     return traverseEncoder.getPosition();
    // }
      
    public double getClimbPosition() {
         return climbEncoder.getPosition();
     } 
    
    
    
    

    @Override
    public void simulationPeriodic() {

    }

    // public void lockTraverse(boolean isLocked) {
    //     // Ced both locks position and prevent motors from turning
    //     if (isLocked) {
    //         antidropTraverseServo.set(Constants.Climb.transferLockedServoPosition);

    //         // climbMotor.set(0);
    //     } else {
    //         antidropTraverseServo.set(Constants.Climb.transferUnlockedServoPosition);

    //     }

    // }

    public void lockClimber(boolean isLocked) {

        if (isLocked == true) {
            antidropClimbServo.set(Constants.Climb.kClimbServoLockPosition);
            climberIsLocked = true;
            // traverseMotor.set(0);
        } else {
            antidropClimbServo.set(Constants.Climb.kClimbServoUnlockPosition);
            climberIsLocked = false;
        }
    }

    public boolean getClimberLocked() {
        return climberIsLocked;
    }

    public void move(double climbSpeed) {
        if(Math.abs(climbSpeed) > 0.1f) {
            climbMotor.set(climbSpeed * localClimbMotorMultiplier);
        }
        else {
            climbMotor.stopMotor();
        }
        // traverseMotor.set(traverseSpeed * localTraverseMotorSpeed);
    }

}
