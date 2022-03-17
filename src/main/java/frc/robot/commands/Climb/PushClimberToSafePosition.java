package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSystem;

public class PushClimberToSafePosition extends CommandBase {
    private ClimbSystem m_climber;

    public PushClimberToSafePosition(ClimbSystem climber) {
        m_climber = climber;
        addRequirements(climber);

    }

    @Override
    public void initialize() {
        //System.out.println("PUSHING");               
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_climber.move(Constants.Climb.kClimbMotorSpeed);
    }
    

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_climber.move(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_climber.getClimbPosition() >= Constants.Climb.kClimbReboundHeight) {
            return true;
        }
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
