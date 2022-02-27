
package frc.robot.commands.autonomous;

import org.ejml.equation.Variable;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.BallHandling;
import frc.robot.commands.Ball.ShootBall;
import frc.robot.commands.Drive.TurnByAngle;
import frc.robot.commands.Drive.moveFoward;
import frc.robot.subsystems.BallHandlingSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.VisionSystem;

/**
 *
 */



public class Auto extends SequentialCommandGroup {
    private DriveSystem drive;
    private BallHandlingSystem ballHandling;
    private VisionSystem vision;

        int angleToTurn = RobotContainer.getInstance().getAutonomousAngle();

    public enum Automodes{
        StandStill,
        MoveForward,
        AimAndShoot,
        reverse
    }


    private Automodes choosenMode;

    public Auto(DriveSystem drive, BallHandlingSystem ballHandling, VisionSystem vision) {
        moveFoward move = new moveFoward(drive, Constants.AutoMotorDistance, Constants.AutoMotorSpeed);
        TurnByAngle turn = new TurnByAngle(drive, angleToTurn);
        AutoShoot shoot = new AutoShoot(ballHandling);

        // addCommands(move, turn, shoot);
        if(choosenMode == Automodes.MoveForward){
            addCommands(move);
        } else if(choosenMode == Automodes.AimAndShoot){
            addCommands(turn, shoot);
        } else{
    
        }


    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
