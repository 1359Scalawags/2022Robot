package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.BallHandling;
import frc.robot.subsystems.BallHandlingSystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//TODO: Do we need to import these ↓ or not?
// import frc.robot.commands.LoadBall;
// import frc.robot.commands.ShootBall;
// import frc.robot.subsystems.BallHandlingSystem;

/**
 *
 */
public class SequenceShot extends SequentialCommandGroup {

        private final BallHandlingSystem m_ballHandlingSystem;
        // private boolean isBallAlreadyLoaded;
        // private boolean isBallAlreadyStaged;

    public SequenceShot(BallHandlingSystem subsystem) {
        m_ballHandlingSystem = subsystem;
        addRequirements(m_ballHandlingSystem);

        //TODO: Do we use line 28 or line 30-33? Or do we use both?
        //new SequentialCommandGroup(new LoadBall(subsystem), new ShootBall(subsystem));
        
        addCommands(
            new LoadBall(subsystem),
            new ShootBall(subsystem)
        );
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
    }

    // Called ever y time the scheduler runs while the command is scheduled.
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
