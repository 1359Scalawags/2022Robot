package frc.robot.commands.Ball;

import frc.robot.subsystems.BallHandlingSystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 *
 */
public class StopLoadCommands extends ParallelCommandGroup {

    // private BallHandlingSystem m_ballHandlingSystem;
    // private boolean isBallAlreadyLoaded;
    // private boolean isBallAlreadyStaged;

    public StopLoadCommands(BallHandlingSystem subsystem) {
        //m_ballHandlingSystem = subsystem;
        
        addCommands(
            new StopBallMotors(subsystem)            
            //new RaiseArm(subsystem)
            // new StopSpinMotor(subsystem),
            // new LoadBall(subsystem)
        );
    }
}
