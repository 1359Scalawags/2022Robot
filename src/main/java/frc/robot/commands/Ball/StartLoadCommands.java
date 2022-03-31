package frc.robot.commands.Ball;

import frc.robot.subsystems.BallHandlingSystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 *
 */

public class StartLoadCommands extends ParallelCommandGroup {

    //private BallHandlingSystem m_ballHandlingSystem;
        // private boolean isBallAlreadyLoaded;
        // private boolean isBallAlreadyStaged;

    public StartLoadCommands(BallHandlingSystem subsystem) {
        // m_ballHandlingSystem = subsystem;
        
        addCommands(
            //new LowerArm(subsystem),
            // new StartSpinMotor(subsystem),
            new LoadBall(subsystem)
           // new LoadBall(subsystem)
        );
    }

    @Override
    public void end(boolean interrupted) {

    }
}
