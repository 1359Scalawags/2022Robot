
package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BallHandlingSystem;

/**
 *
 */
public class RaiseArm extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;

    public RaiseArm(BallHandlingSystem subsystem) {

        m_ballHandlingSystem = subsystem;
        // addRequirements(m_ballHandlingSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_ballHandlingSystem.setArmExtendMotor(-Constants.BallHandling.kArmExtendMotorSpeed);
    }
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() { 

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_ballHandlingSystem.getArmLimitPressed()) {
            m_ballHandlingSystem.setArmExtendMotor(0);
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
