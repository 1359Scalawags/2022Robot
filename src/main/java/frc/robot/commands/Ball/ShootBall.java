
package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BallHandlingSystem;

/**
 *
 */
public class ShootBall extends CommandBase {

    private final BallHandlingSystem m_ballHandlingSystem;

    private boolean isBallAlreadyLoaded;
    private boolean isBallAlreadyStaged;

    public ShootBall(BallHandlingSystem subsystem) {
        m_ballHandlingSystem = subsystem;
        addRequirements(m_ballHandlingSystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        isBallAlreadyLoaded = m_ballHandlingSystem.getBallLoadedSensor();
        isBallAlreadyStaged = m_ballHandlingSystem.getBallStagedSensor();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_ballHandlingSystem.setShootMotorRPM(Constants.BallHandling.kShootMotorSpeed);
        m_ballHandlingSystem.setStagingMotor(Constants.BallHandling.kStagingMotorSpeed);
        if (isBallAlreadyLoaded) {
            m_ballHandlingSystem.setLoadMotor(Constants.BallHandling.kLoadMotorsSpeed);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_ballHandlingSystem.setLoadMotor(0);
        m_ballHandlingSystem.setStagingMotor(0);
        m_ballHandlingSystem.setShootMotorRPM(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
