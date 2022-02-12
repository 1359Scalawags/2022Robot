package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
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
        double leftJoystick = RobotContainer.getInstance().getdriverController().getLeftY();
        double rightJoystick = RobotContainer.getInstance().getdriverController().getRightY();
        double triggerJoystick = (RobotContainer.getInstance().getdriverController().getLeftTriggerAxis() + RobotContainer.getInstance().getdriverController().getRightTriggerAxis()) / 2;
        
        double leftSpeed = leftJoystick * (Constants.Drive.kBaseDriveSpeed + Constants.Drive.kBoostSpeed * triggerJoystick);
        double rightSpeed = rightJoystick * (Constants.Drive.kBaseDriveSpeed + Constants.Drive.kBoostSpeed * triggerJoystick);
        m_driveSystem.tankDrive(leftSpeed, rightSpeed);
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
