package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BallHandlingSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ReverseBall extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;

    // private boolean isBallAlreadyLoaded;
    // private boolean isBallAlreadyStaged;
    private Timer reverseballTimer;

    public ReverseBall(BallHandlingSystem subsystem) {

        m_ballHandlingSystem = subsystem;
        addRequirements(m_ballHandlingSystem);

        reverseballTimer = new Timer();
        reverseballTimer.reset();

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // isBallAlreadyLoaded = m_ballHandlingSystem.getBallLoadedSensor();
        // isBallAlreadyStaged = m_ballHandlingSystem.getBallStagedSensor();
        reverseballTimer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_ballHandlingSystem.setLoadMotor(-Constants.BallHandling.kLoadMotorsSpeed);
        m_ballHandlingSystem.setStagingMotor(-Constants.BallHandling.kStagingMotorSpeed);
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_ballHandlingSystem.setLoadMotor(0);
        reverseballTimer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (reverseballTimer.get() >= Constants.BallHandling.reverseballTimer) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

}
