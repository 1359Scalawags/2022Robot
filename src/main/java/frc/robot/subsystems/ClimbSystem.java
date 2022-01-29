//testing if it works to push
package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class ClimbSystem extends SubsystemBase {

    private CANSparkMax climbMotor;
    private Servo antidropClimbServo;
    private Solenoid climberSolenoid;
    private CANSparkMax traverseMotor;
    private Servo antidropTraverseServo;

    public ClimbSystem() {

        climbMotor = new CANSparkMax(Constants.Climb.kClimbMotor, MotorType.kBrushless);

        climbMotor.restoreFactoryDefaults();
        climbMotor.setInverted(false);
        climbMotor.setIdleMode(IdleMode.kCoast);
        climberSolenoid = new Solenoid(Constants.Climb.kClimberSolenoid, PneumaticsModuleType.CTREPCM, 0);
        addChild("ClimberSolenoid", climberSolenoid);

        antidropClimbServo = new Servo(Constants.Climb.kAntiDropClimbServo);
        addChild("AntidropClimbServo", antidropClimbServo);

        antidropTraverseServo = new Servo(Constants.Climb.kAntiDropTraverseServo);
        addChild("AntidropTraverseServo", antidropTraverseServo);

        traverseMotor = new CANSparkMax(Constants.Climb.kTraverseMotor, MotorType.kBrushless);

        traverseMotor.restoreFactoryDefaults();
        traverseMotor.setInverted(false);
        traverseMotor.setIdleMode(IdleMode.kCoast);

    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }



    public void lockTraferseClimber(boolean locked) {
        if (locked){
            antidropTraverseServo.set(Constants.Climb.transferLockedServoPosition);
        } else {
            antidropTraverseServo.set(Constants.Climb.transferUnlockedServoPosition);
        }

    }
    
    public void setClimbLock(boolean isLocked) {
        if (isLocked == true) {
            antidropClimbServo.set(Constants.Climb.kClimbServoLockPosition);
        } else {
            antidropClimbServo.set(Constants.Climb.kClimbServoUnlockPosition);
        }
    }


}
