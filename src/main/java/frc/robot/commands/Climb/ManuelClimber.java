package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimbSystem;

/**
 *
 */
public class ManuelClimber extends CommandBase {

    private ClimbSystem m_climbSystem;

    public ManuelClimber(ClimbSystem subsystem) {
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
        if(m_climbSystem.getClimbPosition() < Constants.Climb.kClimbReboundHeight) {
            m_climbSystem.move(Constants.Climb.kClimbMotorSpeed);
        } else {
           double climbSpeed= -RobotContainer.getInstance().getassistController().getLeftY();
           m_climbSystem.move(climbSpeed);          
        }


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
