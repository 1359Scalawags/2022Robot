
package frc.robot.commands.Climb;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSystem;

/**
 *
 */
public class RaiseTraverse extends CommandBase {
    private ClimbSystem m_climbSystem;

    public RaiseTraverse(ClimbSystem subsystem) {
        m_climbSystem = subsystem;
        addRequirements(m_climbSystem);    

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
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
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
