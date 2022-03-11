package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimbSystem;

public class ReleaseTensionAndUnlock extends SequentialCommandGroup {

    public ReleaseTensionAndUnlock(ClimbSystem climber) {
        addCommands(
          new UnlockClimber(climber),
          new ReleaseTension(climber)
        );
    }
    
}

