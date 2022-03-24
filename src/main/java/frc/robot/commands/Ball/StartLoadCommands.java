package frc.robot.commands.Ball;

import frc.robot.subsystems.BallHandlingSystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 *
 */
@Deprecated
public class StartLoadCommands extends ParallelCommandGroup {

    private BallHandlingSystem m_ballHandlingSystem;
        // private boolean isBallAlreadyLoaded;
        // private boolean isBallAlreadyStaged;

    public StartLoadCommands(BallHandlingSystem subsystem) {
        m_ballHandlingSystem = subsystem;
        
        addCommands(
            new LowerArm(subsystem), //TODO: Fold this into LoadBall
            new StartSpinMotor(subsystem), //TODO: Fold this into LoadBall
            new LoadBall(subsystem)
           // new LoadBall(subsystem)
        );
    }

    @Override
    public void end(boolean interrupted) {

    }
}
