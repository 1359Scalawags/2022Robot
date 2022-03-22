package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import edu.wpi.first.wpilibj2.command.Subsystem;
// import frc.robot.Constants;
// import frc.robot.Robot;
// import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;

/**
 *
 */
public class moveFoward extends CommandBase {

    private DriveSystem m_driveSystem;
    private double speed;
    private double start;
    private double current;
    private double target;
    private double distance;

    
    public moveFoward(DriveSystem subsystem, double distance, double speed) {
        m_driveSystem = subsystem;
        addRequirements(m_driveSystem);
        this.distance = distance; 
        this.speed = speed;
    
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        start = m_driveSystem.getAverageDistance();
        target = m_driveSystem.getNormalizedAngle();
        //System.out.println("REQUESTED DISTANCE: " + distance);

    }



    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //TODO: Fix gyro based code (let's not worry about this now)
        //m_driveSystem.driveForward(speed, target);
        current = m_driveSystem.getAverageDistance();
        m_driveSystem.tankDrive(speed, speed);
        //System.out.println("CURRENT DISTANCE: " + (current-start));
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(Math.abs(current - start) >= distance) {
            //System.out.println("MOVED FORWARD: " + (current - start));
            //System.out.println("AVERAGE DISTANCE: " + m_driveSystem.getAverageDistance());
            //System.out.println("LEFT DISTANCE: " + m_driveSystem.getDistanceLeft());
            //System.out.println("RIGHT DISTANCE: " + m_driveSystem.getDistanceRight()); 

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
