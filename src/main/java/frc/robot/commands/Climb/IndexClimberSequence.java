package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimbSystem;

public class IndexClimberSequence extends SequentialCommandGroup {
    private ClimbSystem m_ClimbSystem;
    public IndexClimberSequence(ClimbSystem climber) {
        m_ClimbSystem = climber;
        addCommands(
            new UnlockClimber(m_ClimbSystem),
            new PullClimberToIndex(m_ClimbSystem),
            new PushClimberToSafePosition(m_ClimbSystem),
            new LockClimber(m_ClimbSystem)
        );

    }
}
