
package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSystem;

/**
 *
 */
public class AutoDrive extends CommandBase {

    private final DriveSystem m_driveSystem;

    public AutoDrive(DriveSystem subsystem) {

        m_driveSystem = subsystem;
        addRequirements(m_driveSystem);

    }

    public enum AutoMoveOptions {
        driveForward,
        driveBackward
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
