
package frc.robot.subsystems;

import frc.robot.commands.*;
import frc.robot.extensions.SendableCANSparkMax;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.*;

import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class BallHandlingSystem extends SubsystemBase {

    private SendableCANSparkMax loadMotor;
 //   private CANSparkMax loadMotor2;
 //   private MotorControllerGroup loadMotor;
    private SendableCANSparkMax stagingMotor;


    private SendableCANSparkMax shootMotor;
    private RelativeEncoder shootEncoder;
    private SparkMaxPIDController shootController;


    // private DigitalInput loadSensor;
    // private AnalogInput loadSensor;
    private Ultrasonic loadSensor;
    private LinearFilter loadSensorFilter;
    // private DigitalInput stagingSensor;
    // private AnalogInput stagingSensor;
    private LinearFilter stageSensorFilter;
    private Ultrasonic stageSensor;
    public BallHandlingSystem() {

        // loadMotor1 = new CANSparkMax(Constants.BallHandling.kLoadMotor1, MotorType.kBrushless);

        // loadMotor1.restoreFactoryDefaults();
        // loadMotor1.setInverted(false);
        // loadMotor1.setIdleMode(IdleMode.kCoast);

        // loadMotor2 = new CANSparkMax(Constants.BallHandling.kLoadMotor2, MotorType.kBrushless);

        // loadMotor2.restoreFactoryDefaults();
        // loadMotor2.setInverted(false);
        // loadMotor2.setIdleMode(IdleMode.kCoast);

        // loadMotor = new MotorControllerGroup(loadMotor1);
        loadMotor = new SendableCANSparkMax(Constants.BallHandling.kLoadMotor1, MotorType.kBrushless, this);

        loadMotor.restoreFactoryDefaults();
        loadMotor.setInverted(false);
        loadMotor.setIdleMode(IdleMode.kCoast);
        //addChild("LoadMotors", loadMotor);

        stagingMotor = new SendableCANSparkMax(Constants.BallHandling.kStagingMotor, MotorType.kBrushless, this);

        stagingMotor.restoreFactoryDefaults();
        stagingMotor.setInverted(false);
        stagingMotor.setIdleMode(IdleMode.kCoast);

        shootMotor = new SendableCANSparkMax(Constants.BallHandling.kShootMotor, MotorType.kBrushless, this);
        shootMotor.restoreFactoryDefaults();
        shootMotor.setInverted(false);
        shootMotor.setIdleMode(IdleMode.kCoast);

        shootEncoder = shootMotor.getEncoder();
        shootController = shootMotor.getPIDController();
        shootController.setP(Constants.BallHandling.kP);
        shootController.setI(Constants.BallHandling.kI);
        shootController.setD(Constants.BallHandling.kD);
        shootController.setIZone(Constants.BallHandling.kIz);
        shootController.setFF(Constants.BallHandling.kFF);
        shootController.setOutputRange(Constants.BallHandling.kMinOutput, Constants.BallHandling.kMaxOutput);

        // loadSensor = new DigitalInput(Constants.BallHandling.kloadinput);
        //loadSensor = new AnalogInput(Constants.BallHandling.kloadinput);
        ///loadSensor.setAverageBits(5);
        loadSensor = new Ultrasonic(Constants.BallHandling.kloadPingChannel, Constants.BallHandling.kloadEchoChannel);
        loadSensorFilter = LinearFilter.movingAverage(Constants.BallHandling.kSensorAverageSamples);
        addChild("LoadSensor", loadSensor);

        // stagingSensor = new DigitalInput(Constants.BallHandling.kstaginginput);
        // stagingSensor = new AnalogInput(Constants.BallHandling.kstaginginput);
        // stagingSensor.setAverageBits(5);
        // addChild("StagingSensor", stagingSensor);
        stageSensor = new Ultrasonic(Constants.BallHandling.kstagePingChannel, Constants.BallHandling.kstageEchoChannel);
        stageSensorFilter = LinearFilter.movingAverage(Constants.BallHandling.kSensorAverageSamples);
        addChild("StageSensor", stageSensor);

    }

    private int pingCounter = 0;
    @Override
    public void periodic() {
        if(pingCounter > 2) {
            stageSensor.ping();
            loadSensor.ping();
            pingCounter = 0;
        }
        else {
            //TODO: Take this out when we're done
            System.out.println("stage: " + stageSensor.getRangeMM());
            System.out.println("load:  " + loadSensor.getRangeMM());
        }
        pingCounter++;

    }

    @Override
    public void simulationPeriodic() {

    }

    public void setLoadMotor(double speed) {
        loadMotor.set(speed);
    }

    public void setStagingMotor(double speed) {
        stagingMotor.set(speed);
    }

    public void setShootMotorRPM(double speed) {
        speed = Math.min(speed, Constants.BallHandling.kShootMotorMaxRPM);
        shootController.setReference(speed, CANSparkMax.ControlType.kVelocity);   
    }

    public double getShooterMotorRPM() {
        return shootEncoder.getVelocity();
    }

    public void stopAllBallMotors() {
        loadMotor.stopMotor();
        stagingMotor.stopMotor();
        shootMotor.stopMotor();
    }

    public boolean getBallLoadedSensor() {
        if (loadSensorFilter.calculate(loadSensor.getRangeMM()) < Constants.BallHandling.kloadSensorTripValue) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getBallStagedSensor() {
        if (stageSensorFilter.calculate(stageSensor.getRangeMM()) < Constants.BallHandling.kstageSensorTripValue) {
            return true;
        } else {
            return false;
        }
    }

    public double getBallShootVelocity() {
        return shootMotor.getEncoder().getVelocity();
    }

}
