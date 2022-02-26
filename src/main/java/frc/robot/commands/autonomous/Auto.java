
package frc.robot.commands.autonomous;

import org.ejml.equation.Variable;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
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

    public enum Automodes{
        StandStill,
        MoveForward,
        AimAndShoot
    }


    private Automodes choosenMode;

    public Auto(DriveSystem drive, BallHandlingSystem ballHandling, VisionSystem vision) {
    addCommands(
        new moveFoward(drive, Constants.AutoMotorDistance, Constants.AutoMotorSpeed),
        new TurnByAngle(drive, Constants.AngleTurnBy),
        new AutoShoot(ballHandling)
        );

    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
