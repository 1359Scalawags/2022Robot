package frc.robot.commands.Ball;

import frc.robot.subsystems.BallHandlingSystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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
            new ShootBall(subsystem)
           // new LoadBall(subsystem)
        );
    }
}
