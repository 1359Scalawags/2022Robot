package frc.robot.commands.Ball;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.subsystems.BallHandlingSystem;

public class StartShooter extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;
    private Timer m_timer;

    public StartShooter(BallHandlingSystem subsystem) {
        m_ballHandlingSystem = subsystem;
        addRequirements(m_ballHandlingSystem);
        m_timer = new Timer();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_ballHandlingSystem.setShootMotorRPM(Constants.BallHandling.kShootMotorMaxRPM );

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // if( Utilities.IsCloseTo(Constants.BallHandling.kShootMotorMaxRPM, m_ballHandlingSystem.getShooterMotorRPM(), 500)) {
        //     return true;
        // }
        // System.out.println(m_ballHandlingSystem.getShooterMotorRPM());
        if(m_timer.get() > 3) {
            return true;
        }
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
