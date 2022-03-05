
package frc.robot.commands.Drive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallHandlingSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.DriveSystem.Directions;


/**
 *
 */
public class SetDriveDirection extends CommandBase {

    private DriveSystem m_driveSystem;
    private Directions m_direction;
    public SetDriveDirection(DriveSystem subsystem, Directions direction) {
        m_direction = direction;
        m_driveSystem = subsystem;
        addRequirements(m_driveSystem);

    }



    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_driveSystem.setDirection(m_direction);
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
        return true;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
