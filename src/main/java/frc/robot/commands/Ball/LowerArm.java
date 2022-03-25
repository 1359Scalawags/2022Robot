
package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.subsystems.BallHandlingSystem;

/**
 * // TODO: Fix the speed of Lower Arm
 */
public class LowerArm extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;

    public LowerArm(BallHandlingSystem subsystem) {

        m_ballHandlingSystem = subsystem;
        // addRequirements(m_ballHandlingSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double ArmMoveSpeed = Utilities.GetSmoothStartStopSpeed(-Constants.BallHandling.kArmExtendMotorSpeed,
                m_ballHandlingSystem.getArmMotorPosition(), Constants.BallHandling.kArmMaxExtendAngle, 0);
        m_ballHandlingSystem.setArmExtendMotor(ArmMoveSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (m_ballHandlingSystem.getArmIsExtended()) {
            m_ballHandlingSystem.setArmExtendMotor(0);
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
