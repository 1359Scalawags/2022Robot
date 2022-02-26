package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.subsystems.DriveSystem;

/**
 *
 */
public class TurnByAngle extends CommandBase {

    private final DriveSystem m_driveSystem;
    private double turnAngle;
    private double originalAngle;

    public TurnByAngle(DriveSystem subsystem, double turnAngle) {

        this.m_driveSystem = subsystem;
        addRequirements(m_driveSystem);
        this.turnAngle = turnAngle;


    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        originalAngle = m_driveSystem.getAngle();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveSystem.arcadeDrive(Constants.Drive.kBaseDriveSpeed, Constants.Drive.maxTurnSpeed, turnAngle+originalAngle);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(Utilities.IsCloseTo(m_driveSystem.getAngle(), turnAngle+originalAngle, 3))
            return true;
        else
            return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
