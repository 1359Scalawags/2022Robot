package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimbSystem;

public class IndexClimberSequence extends SequentialCommandGroup {
    private ClimbSystem m_ClimbSystem;
    public IndexClimberSequence(ClimbSystem climber) {
        addCommands(
            new UnlockClimber(climber),
            new PullClimberToIndex(climber),
            new PushClimberToSafePosition(climber),
            new LockClimber(climber)
        );

    }
}
