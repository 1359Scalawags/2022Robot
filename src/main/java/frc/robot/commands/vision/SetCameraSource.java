package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.VisionSystem;
import frc.robot.subsystems.VisionSystem.USBCameras;

public class SetCameraSource extends CommandBase {
    
  
    private USBCameras chosenCamera;
    private VisionSystem m_visionSystem;

    public SetCameraSource(VisionSystem vision, USBCameras camera) {
        this.chosenCamera = camera;
        m_visionSystem = vision;
    }

       // Called when the command is initially scheduled.
       @Override
       public void initialize() {
           m_visionSystem.setUSBCamera(chosenCamera);
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
