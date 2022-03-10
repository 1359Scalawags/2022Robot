package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSystem;

public class ReleaseTension extends CommandBase {
    private ClimbSystem m_climbSystem;
    private Timer m_timer;

    public ReleaseTension(ClimbSystem subsystem) {
        m_climbSystem = subsystem;
        addRequirements(m_climbSystem);
        m_timer = new Timer();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_climbSystem.move(Constants.Climb.kReleaseTensionSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (m_timer.get() > Constants.Climb.kReleaseTensionTimer){
            m_climbSystem.move(0);
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
