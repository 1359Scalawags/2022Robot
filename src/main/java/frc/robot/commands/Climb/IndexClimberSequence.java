package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Delay;
import frc.robot.subsystems.ClimbSystem;

public class IndexClimberSequence extends SequentialCommandGroup {
    private ClimbSystem m_ClimbSystem;
    public IndexClimberSequence(ClimbSystem climber) {
        m_ClimbSystem = climber;
        addCommands(
            new UnlockClimberMaster(m_ClimbSystem),
            new UnlockClimber(m_ClimbSystem),
            //new Delay(m_ClimbSystem, 0.5),
            new PullClimberToIndex(m_ClimbSystem),
            //new Delay(m_ClimbSystem, 0.5),
            new PushClimberToSafePosition(m_ClimbSystem),
            //new Delay(m_ClimbSystem, 0.5),
            new LockClimber(m_ClimbSystem),
            new LockClimberMaster(m_ClimbSystem)
        );

    }
}
