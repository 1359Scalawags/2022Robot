
// package frc.robot.commands.Ball;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// //import edu.wpi.first.wpilibj2.command.Subsystem;
// import frc.robot.Constants;
// import frc.robot.subsystems.BallHandlingSystem;

// /**
//  *
//  */
// @Deprecated
// public class StartSpinMotor extends CommandBase {

//     private BallHandlingSystem m_ballHandlingSystem;

//     public StartSpinMotor(BallHandlingSystem subsystem) {

//         m_ballHandlingSystem = subsystem;
//         //addRequirements(m_ballHandlingSystem);

//     }

//     // Called when the command is initially scheduled.
//     @Override
//     public void initialize() {
//           m_ballHandlingSystem.setSpinMotor(Constants.BallHandling.kArmSpinMotorSpeed);
//     }

//     // Called every time the scheduler runs while the command is scheduled.
//     @Override
//     public void execute() {


//     }

//     // Called once the command ends or is interrupted.
//     @Override
//     public void end(boolean interrupted) {
    
//     }

//     // Returns true when the command should end.
//     @Override
//     public boolean isFinished() { 
//         return true;
//     }

//     @Override
//     public boolean runsWhenDisabled() {
//         return false;
//     }
// }
