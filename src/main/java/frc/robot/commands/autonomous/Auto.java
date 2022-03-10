
package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Drive.SetDriveDirection;
import frc.robot.commands.Drive.TurnByAngle;
import frc.robot.commands.Drive.moveFoward;
import frc.robot.subsystems.BallHandlingSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.VisionSystem;
import frc.robot.subsystems.DriveSystem.Directions;

/**
 *
 */
public class Auto extends SequentialCommandGroup {
    private DriveSystem drive;
    private BallHandlingSystem ballHandling;
    private VisionSystem vision;

    public enum Automodes{
        StandStill,
        MoveForward,
        AimAndShoot,
        reverse,
        Shoot
    }

    private Automodes choosenMode;
    private int angleToTurn;

    public Auto(DriveSystem drive, BallHandlingSystem ballHandling, Automodes mode, int angle) {
        // copy the information we received for future use
        this.drive = drive;
        this.ballHandling = ballHandling;
        this.angleToTurn = angle;
        this.choosenMode = mode;

        moveFoward move = new moveFoward(drive, Constants.Auto.MotorDistance, Constants.Auto.MotorSpeed);
        TurnByAngle turn = new TurnByAngle(drive, angleToTurn);
        AutoShoot shoot = new AutoShoot(ballHandling);
        SetDriveDirection Fowards = new SetDriveDirection(drive, Directions.Forwards);
        SetDriveDirection Backwards = new SetDriveDirection(drive, Directions.Backwards);
        // addCommands(move, turn, shoot);
        if(choosenMode == Automodes.MoveForward){
            addCommands(Fowards, move);
        } else if(choosenMode == Automodes.AimAndShoot){
            addCommands(Fowards, turn, shoot);
        } else if(choosenMode == Automodes.reverse){
            addCommands(Backwards, move);
        } else if(choosenMode == Automodes.Shoot){
            addCommands(shoot);
        }

    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
