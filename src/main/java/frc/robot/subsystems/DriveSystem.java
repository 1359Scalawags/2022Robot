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
import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveSystem extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private CANSparkMax leftFrontMotor;
private CANSparkMax leftRearMotor;
private MotorControllerGroup leftSpeedController;
private CANSparkMax rightFrontMotor;
private CANSparkMax rightRearMotor;
private MotorControllerGroup rightSpeedController;
private DifferentialDrive differentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public DriveSystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftFrontMotor = new CANSparkMax(0, MotorType.kBrushless);
 
 leftFrontMotor.restoreFactoryDefaults();  
leftFrontMotor.setInverted(false);
leftFrontMotor.setIdleMode(IdleMode.kCoast);
  

leftRearMotor = new CANSparkMax(1, MotorType.kBrushless);
 
 leftRearMotor.restoreFactoryDefaults();  
leftRearMotor.setInverted(false);
leftRearMotor.setIdleMode(IdleMode.kCoast);
  

leftSpeedController = new MotorControllerGroup(leftFrontMotor, leftRearMotor  );
 addChild("LeftSpeedController",leftSpeedController);
 

rightFrontMotor = new CANSparkMax(2, MotorType.kBrushless);
 
 rightFrontMotor.restoreFactoryDefaults();  
rightFrontMotor.setInverted(true);
rightFrontMotor.setIdleMode(IdleMode.kCoast);
  

rightRearMotor = new CANSparkMax(3, MotorType.kBrushless);
 
 rightRearMotor.restoreFactoryDefaults();  
rightRearMotor.setInverted(true);
rightRearMotor.setIdleMode(IdleMode.kCoast);
  

rightSpeedController = new MotorControllerGroup(rightFrontMotor, rightRearMotor  );
 addChild("RightSpeedController",rightSpeedController);
 

differentialDrive = new DifferentialDrive(leftSpeedController, leftFrontMotor);
 addChild("DifferentialDrive",differentialDrive);
 differentialDrive.setSafetyEnabled(true);
differentialDrive.setExpiration(0.1);
differentialDrive.setMaxOutput(1.0);



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

