// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class BallHandlingSystem extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private CANSparkMax loadMotor;
private CANSparkMax shootMotor;
private CANSparkMax stagingMotor;
private DigitalInput loadSensor;
private DigitalInput stagingSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public BallHandlingSystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
loadMotor = new CANSparkMax(4, MotorType.kBrushless);
 
 loadMotor.restoreFactoryDefaults();  
loadMotor.setInverted(false);
loadMotor.setIdleMode(IdleMode.kCoast);
  

shootMotor = new CANSparkMax(5, MotorType.kBrushless);
 
 shootMotor.restoreFactoryDefaults();  
shootMotor.setInverted(false);
shootMotor.setIdleMode(IdleMode.kCoast);
  

stagingMotor = new CANSparkMax(6, MotorType.kBrushless);
 
 stagingMotor.restoreFactoryDefaults();  
stagingMotor.setInverted(false);
stagingMotor.setIdleMode(IdleMode.kCoast);
  

loadSensor = new DigitalInput(0);
 addChild("LoadSensor", loadSensor);
 

stagingSensor = new DigitalInput(1);
 addChild("StagingSensor", stagingSensor);
 


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

