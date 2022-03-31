
package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.subsystems.BallHandlingSystem;


public class RaiseArm extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;

    public RaiseArm(BallHandlingSystem subsystem) {

        m_ballHandlingSystem = subsystem;
        //addRequirements(m_ballHandlingSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() { 
        double ArmMoveSpeed = Utilities.GetSmoothStartStopSpeed(Constants.BallHandling.kArmRetractMotorSpeed,
                m_ballHandlingSystem.getArmMotorPosition(), Constants.BallHandling.kArmMaxExtendAngle, 0);
        //TODO: (IMPORTANT) Does this really need to be hear now?
        ArmMoveSpeed = Constants.BallHandling.kArmRetractMotorSpeed;
        m_ballHandlingSystem.setArmExtendMotor(ArmMoveSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_ballHandlingSystem.park();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_ballHandlingSystem.getArmLimitPressed()) {
            m_ballHandlingSystem.setArmExtendMotor(0.11);
            return true;
        } else {
            return false;
        }
      
      
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
