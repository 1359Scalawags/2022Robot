package frc.robot.commands.Ball;
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

        private BallHandlingSystem m_ballHandlingSystem;
        // private boolean isBallAlreadyLoaded;
        // private boolean isBallAlreadyStaged;

    public SequenceShot(BallHandlingSystem subsystem) {
        m_ballHandlingSystem = subsystem;
        
        addCommands(
            new StartShooter(subsystem),
            new ShootBall(subsystem),
            new LoadBall(subsystem)
        );
    }
}
