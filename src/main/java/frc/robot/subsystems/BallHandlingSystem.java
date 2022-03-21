
package frc.robot.subsystems;

import frc.robot.extensions.SendableCANSparkMax;
import frc.robot.helper.PIDValues;
import frc.robot.helper.SparkMaxVelocityTuner;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.Ultrasonic;

public class BallHandlingSystem extends SubsystemBase {

    private SendableCANSparkMax loadMotor;
    private SendableCANSparkMax stagingMotor;

    private SendableCANSparkMax shootMotor;
    private RelativeEncoder shootEncoder;
    private SparkMaxPIDController shootController;

    // private DigitalInput loadSensor;
    // private AnalogInput loadSensor;
    // private DigitalInput stagingSensor;
    // private AnalogInput stagingSensor;    

    private Ultrasonic loadSensor;
    private LinearFilter loadSensorFilter;

    private LinearFilter stageSensorFilter;
    private Ultrasonic stageSensor;
    private double stageSensorAverage;


    public BallHandlingSystem() {

        // loadMotor = new MotorControllerGroup(loadMotor1);
        loadMotor = new SendableCANSparkMax(Constants.BallHandling.kLoadMotor, MotorType.kBrushless, this);
        addChild("Loading Motor", loadMotor);

        loadMotor.restoreFactoryDefaults();
        loadMotor.setInverted(false);
        loadMotor.setIdleMode(IdleMode.kCoast);
        //addChild("LoadMotors", loadMotor);

        stagingMotor = new SendableCANSparkMax(Constants.BallHandling.kStagingMotor, MotorType.kBrushless, this);
        addChild("Staging Motor", stagingMotor);

        stagingMotor.restoreFactoryDefaults();
        stagingMotor.setInverted(false);
        stagingMotor.setIdleMode(IdleMode.kCoast);

        shootMotor = new SendableCANSparkMax(Constants.BallHandling.kShootMotor, MotorType.kBrushless, this);
        addChild("Shooting Motor", shootMotor);
       
        shootMotor.restoreFactoryDefaults();
        shootMotor.setInverted(false);
        shootMotor.setIdleMode(IdleMode.kCoast);

        shootEncoder = shootMotor.getEncoder();
        shootController = shootMotor.getPIDController();
        shootController.setP(Constants.BallHandling.kShooterP);
        shootController.setI(Constants.BallHandling.kShooterI);
        shootController.setD(Constants.BallHandling.kShooterD);
        shootController.setIZone(Constants.BallHandling.kShooterIz);
        shootController.setFF(Constants.BallHandling.kShooterFf);
        shootController.setOutputRange(Constants.BallHandling.kShooterMinOutput, Constants.BallHandling.kShooterMaxOutput);
        
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
        if(pingCounter == 1) {
            //System.out.println("Ping Stage");
            stageSensor.ping();
        } else if(pingCounter == 1 + Constants.BallHandling.kUltrasonicFrameCount) {
            //System.out.println("Ping Load");
            loadSensor.ping();
        } else if(pingCounter == 1 + 2 * Constants.BallHandling.kUltrasonicFrameCount) {
            pingCounter = 0;
        }
        else {
            //System.out.println("stage: " + stageSensor.getRangeMM());
            //System.out.println("load:  " + loadSensor.getRangeMM());
        }

        // Calculate the average stage sensor value each frame
        double stageValue = stageSensor.getRangeMM();
        if(stageValue > 0 && Double.isFinite(stageValue)) {
            stageSensorAverage = stageSensorFilter.calculate(stageSensor.getRangeMM());
        }

        //TODO: Also calculate the load sensor average periodically
        
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

    // !!The trip values were 80 for the load sensor and 100 for the stage sensor!!
    public boolean getBallLoadedSensor() {
        // TODO: This only evaluates when we're checking and may get checked multiple times per frame...use a variable to store the precalculated value
        if (loadSensorFilter.calculate(loadSensor.getRangeMM()) < Constants.BallHandling.kloadSensorTripValue) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getBallStagedSensor() {
        if (stageSensorAverage < Constants.BallHandling.kstageSensorTripValue) {
            return true;
        } else {
            return false;
        }
    }

    public double getBallShootVelocity() {
        return shootMotor.getEncoder().getVelocity();
    }

    public SparkMaxVelocityTuner initializeTests() {
        PIDValues initialPID = new PIDValues(Constants.BallHandling.kShooterP, 
                                             Constants.BallHandling.kShooterI, 
                                             Constants.BallHandling.kShooterD, 
                                             Constants.BallHandling.kShooterIz, 
                                             Constants.BallHandling.kShooterFf);
        SparkMaxVelocityTuner tuner = new SparkMaxVelocityTuner("Shoot Tune", shootEncoder, shootController, initialPID, (int)Constants.BallHandling.kShootMotorMaxRPM);
        return tuner;
    }

}
