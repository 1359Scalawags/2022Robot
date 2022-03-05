package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallHandlingSystem;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.Constants.BallHandling;

public class StopBallMotors extends CommandBase {

    private final BallHandlingSystem m_ballHandlingSystem;

    public StopBallMotors(BallHandlingSystem system) {
        m_ballHandlingSystem = system;
    }
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_ballHandlingSystem.stopAllBallMotors();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

}
