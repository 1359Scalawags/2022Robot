
package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BallHandlingSystem;

/**
 *
 */
public class LoadBall extends CommandBase {

    private BallHandlingSystem m_ballHandlingSystem;

    private boolean isBallAlreadyLoaded;
    private boolean isBallAlreadyStaged;

    public LoadBall(BallHandlingSystem subsystem) {

        m_ballHandlingSystem = subsystem;
        addRequirements(m_ballHandlingSystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
       
        isBallAlreadyLoaded = m_ballHandlingSystem.getBallLoadedSensor();
        isBallAlreadyStaged = m_ballHandlingSystem.getBallStagedSensor();

       // if(isBallAlreadyLoaded == false){
            m_ballHandlingSystem.setSpinMotor(Constants.BallHandling.kArmSpinMotorSpeed);
       // }
    
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_ballHandlingSystem.setLoadMotor(Constants.BallHandling.kLoadMotorsSpeed);
        if (!isBallAlreadyStaged) {
            if(m_ballHandlingSystem.getBallStagedSensor() == true) {
                m_ballHandlingSystem.setStagingMotor(0); 
            } else {
                m_ballHandlingSystem.setStagingMotor(Constants.BallHandling.kStagingMotorSpeed);                
            }
        }
        // if(m_ballHandlingSystem.getBallLoadedSensor() == true && m_ballHandlingSystem.getBallStagedSensor() == true) {
        //     m_ballHandlingSystem.setLoadMotor(0);
        //     m_ballHandlingSystem.setSpinMotor(0);
        //     System.out.println("Both balls are loaded...stopping motors.");
        // }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("LoadBall has ended.");
        m_ballHandlingSystem.setLoadMotor(0);
        m_ballHandlingSystem.setSpinMotor(0);    
        m_ballHandlingSystem.setStagingMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (isBallAlreadyLoaded && isBallAlreadyStaged) {
            return true;
        } else if (m_ballHandlingSystem.getBallLoadedSensor() == true && m_ballHandlingSystem.getBallStagedSensor() == true) {
            return true;
        } else if (isBallAlreadyStaged && m_ballHandlingSystem.getBallLoadedSensor() == true) {
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
