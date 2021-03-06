package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallHandlingSystem;

public class StopBallMotors extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;

    public StopBallMotors(BallHandlingSystem system) {
        m_ballHandlingSystem = system;
        addRequirements(system);
    }
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_ballHandlingSystem.stopAllBallMotors();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //m_ballHandlingSystem.stopAllBallMotors();

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
