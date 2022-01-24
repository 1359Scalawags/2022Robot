package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;

/**
 *
 */
public class ManualDrive extends CommandBase {

    private final DriveSystem m_driveSystem;
 

    public ManualDrive(DriveSystem subsystem) {

        m_driveSystem = subsystem;
        addRequirements(m_driveSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double leftSpeed = RobotContainer.getInstance().getdriverController().getLeftY();
        double rightSpeed = 0;

        m_driveSystem.move(leftSpeed, rightSpeed);

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
