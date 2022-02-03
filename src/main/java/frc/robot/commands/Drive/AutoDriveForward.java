package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;  //uncomment when adding something that requires the constants file.
//import frc.robot.Robot;
import frc.robot.Robot;
import frc.robot.Constants.Drive;
import frc.robot.subsystems.DriveSystem;



/**
 *
 */
public class AutoDriveForward extends CommandBase {


    private double start;
    private double current;
    private double target;
    private double distance;
    private DriveSystem m_DriveSystem;

    public AutoDriveForward(DriveSystem subsystem, double distance) {
        m_DriveSystem = subsystem;
        addRequirements(subsystem);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        start = m_DriveSystem.getAverageDistance();
        target = m_DriveSystem.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        current = m_DriveSystem.getAverageDistance();
        m_DriveSystem.driveForward(Drive.AutoStraightSpeed, target);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        if(Math.abs(current - start) >= distance) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_DriveSystem.arcadeDrive(0, 0, 0);
    }

}
