
package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Delay;
import frc.robot.commands.Ball.SequenceShot;
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
        // AutoShoot shoot = new AutoShoot(ballHandling);
        SequenceShot shoot = new SequenceShot(ballHandling);
        SetDriveDirection forwardMode = new SetDriveDirection(drive, Directions.Forwards);
        SetDriveDirection reverseMode = new SetDriveDirection(drive, Directions.Backwards);
        Delay delay = new Delay(ballHandling, 4);
        // addCommands(move, turn, shoot);

        //TODO: Remove this if we need to choose a mode
        //choosenMode = Automodes.AimAndShoot;
        
        //AUTONOMOUS MOVES OPPOSITE DIRECTION OF JOYSTICK CONTROL
        if(choosenMode == Automodes.MoveForward){
            addCommands(reverseMode, move, forwardMode);
        } else if(choosenMode == Automodes.AimAndShoot){
            addCommands(delay, shoot, reverseMode, move, forwardMode);
        } else if(choosenMode == Automodes.reverse){
            addCommands(forwardMode, move);
        } else if(choosenMode == Automodes.Shoot){
            addCommands(shoot);
        } else {
            addCommands(shoot, reverseMode, move, forwardMode);
        }

    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
