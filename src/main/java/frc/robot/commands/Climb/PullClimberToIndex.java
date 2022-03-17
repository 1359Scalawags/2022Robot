package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSystem;

public class PullClimberToIndex extends CommandBase {
    
    private ClimbSystem m_climber;

    public PullClimberToIndex(ClimbSystem climber) {
        m_climber = climber;
        addRequirements(climber);

    }

    @Override
    public void initialize() {
        //System.out.println("PULLING");         
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_climber.move(-Constants.Climb.kClimbMotorSpeed);
    }
    

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_climber.move(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_climber.getBottomLimit()) {
            System.out.println("HIT BOTTOM");
            return true;
        }
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
