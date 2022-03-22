
// package frc.robot.commands.Ball;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import frc.robot.subsystems.BallHandlingSystem;

// /**
//  *
//  */
// public class ManualStageBall extends CommandBase {

//     private BallHandlingSystem m_ballHandlingSystem;
//     private Timer timer;

//     public ManualStageBall(BallHandlingSystem subsystem) {

//         m_ballHandlingSystem = subsystem;
//         addRequirements(m_ballHandlingSystem);
//         timer = new Timer();
//     }   

//     // Called when the command is initially scheduled.
//     @Override
//     public void initialize() {
//         timer.reset();
//         timer.start();
//     }

//     // Called every time the scheduler runs while the command is scheduled.
//     @Override
//     public void execute() {
//         m_ballHandlingSystem.setLoadMotor(Constants.BallHandling.kLoadMotorsSpeed);
//         m_ballHandlingSystem.setStagingMotor(Constants.BallHandling.kStagingMotorSpeed);
  

//     }

//     // Called once the command ends or is interrupted.
//     @Override
//     public void end(boolean interrupted) {
//         m_ballHandlingSystem.setLoadMotor(0);
//         m_ballHandlingSystem.setStagingMotor(0);
//     }

//     // Returns true when the command should end.
//     @Override
//     public boolean isFinished() {
//         if(timer.get() > Constants.BallHandling.manualStageBallTime) {
//             return true;
//         }
//         return false;
//     }

//     @Override
//     public boolean runsWhenDisabled() {
//         return false;
//     }
// }
