
package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.Ball.*;
import frc.robot.commands.Climb.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.AutoShoot;
import frc.robot.commands.autonomous.Auto.Automodes;
import frc.robot.commands.vision.SetCameraSource;
import frc.robot.helper.DPadButton;
import frc.robot.commands.autonomous.Auto;
import frc.robot.subsystems.*;
import frc.robot.subsystems.VisionSystem.USBCameras;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

    private static RobotContainer m_robotContainer = new RobotContainer();

    // The robot's subsystems
    public final VisionSystem m_visionSystem = new VisionSystem();
    public final ClimbSystem m_climbSystem = new ClimbSystem();
    public final BallHandlingSystem m_ballHandlingSystem = new BallHandlingSystem();
    public final DriveSystem m_driveSystem = new DriveSystem();
    public final DisplaySystem m_displaySystem = new DisplaySystem(m_climbSystem);

    // Joysticks
    private final XboxController assistController = new XboxController(1);
    private final XboxController driverController = new XboxController(0);

    // A chooser for autonomous commands
    SendableChooser<Automodes> m_autoChooser = new SendableChooser<Automodes>();
    SendableChooser<Integer> m_autoAngleChooser = new SendableChooser<Integer>();
 
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        
        // Smartdashboard Subsystems

        // SmartDashboard Buttons
        /*SmartDashboard.putData("LoadBall", new LoadBall(m_ballHandlingSystem));
        SmartDashboard.putData("ShootBall", new ShootBall(m_ballHandlingSystem));
        SmartDashboard.putData("ReverseDrive", new ReverseDrive(m_driveSystem));
        SmartDashboard.putData("ProcessCameraImage", new ProcessCameraImage(m_visionSystem));
        SmartDashboard.putData("AutoShoot", new AutoShoot(m_ballHandlingSystem));
        SmartDashboard.putData("AutoDrive", new AutoDrive(m_driveSystem));

        SmartDashboard.putData("LockClimber", new LockClimber(m_climbSystem));
        // SmartDashboard.putData("UnlockClimber", new UnlockTraverse(m_climbSystem));
        SmartDashboard.putData("RaiseClimber", new ManuelClimber(m_climbSystem));
        // SmartDashboard.putData("LowerClimber", new LowerClimber(m_climbSystem));
        SmartDashboard.putData("ManualDrive", new ManualDrive(m_driveSystem));
        // SmartDashboard.putData("RaiseTraverse", new RaiseTraverse(m_climbSystem));
        // SmartDashboard.putData("LowerTraverse", new LowerTraverse(m_climbSystem));
        */

        // Configure the button bindings
        configureButtonBindings();

        // Configure default commands
        m_driveSystem.setDefaultCommand(new ManualDrive(m_driveSystem));
        m_climbSystem.setDefaultCommand(new ManualClimber(m_climbSystem));
        // Configure autonomous sendable chooser

        m_autoAngleChooser.addOption("0", 0);
        m_autoAngleChooser.addOption("90", 90);
        m_autoAngleChooser.addOption("180", 180);
        m_autoAngleChooser.addOption("-90", -90);
        m_autoAngleChooser.setDefaultOption("0", 0);
        // m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ',
        // '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ',
        // '')} ));
        // m_autoChooser.addOption("AutoShoot", new AutoShoot(m_ballHandlingSystem));
        // m_autoChooser.addOption("AutoDrive", new AutoDrive(m_driveSystem));
        // m_autoChooser.addOption("AutoShootAndDrive", new Auto(m_driveSystem, m_ballHandlingSystem, m_visionSystem, getAutonomousAngle()));
        m_autoChooser.addOption("AutoShoot", Automodes.Shoot);
        m_autoChooser.addOption("AutoDrive", Automodes.MoveForward);
        m_autoChooser.addOption("AutoShootAndDrive", Automodes.AimAndShoot);
        m_autoChooser.addOption("AutoStandStill", Automodes.StandStill);
        m_autoChooser.setDefaultOption("AutoDrive", Automodes.MoveForward);

        SmartDashboard.putData("Auto Mode", m_autoChooser);
        SmartDashboard.putData("Auto Angle", m_autoAngleChooser);
        // SmartDashboard.putData("AutoShootAndDrive", new Auto(m_driveSystem, m_ballHandlingSystem, m_visionSystem, getAutonomousAngle()));
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Create some buttons
        // final JoystickButton leftStick = new JoystickButton(driverController, XboxController.Button.kLeftStick.value);
        // leftStick.whenPressed(new ManualDrive(m_driveSystem), true);
        // SmartDashboard.putData("leftStick", new ManualDrive(m_driveSystem));

        // final JoystickButton rightStick = new JoystickButton(driverController, XboxController.Button.kRightStick.value);
        // rightStick.whenPressed(new ManualDrive(m_driveSystem), true);
        // SmartDashboard.putData("rightStick", new ManualDrive(m_driveSystem));

        final JoystickButton reverseDriveBtn = new JoystickButton(driverController, XboxController.Button.kBack.value);
        reverseDriveBtn.whenPressed(new ReverseDrive(m_driveSystem), true);
        //SmartDashboard.putData("reverseDriveBtn", new ReverseDrive(m_driveSystem));

        final JoystickButton lockClimberBtn = new JoystickButton(driverController, XboxController.Button.kX.value);
        lockClimberBtn.whenPressed(new LockClimber(m_climbSystem), true);
        //SmartDashboard.putData("lockClimberBtn", new LockClimber(m_climbSystem));

        final JoystickButton unlockClimberBtn = new JoystickButton(driverController, XboxController.Button.kY.value);
        unlockClimberBtn.whenPressed(new ReleaseTensionAndUnlock(m_climbSystem), true);
        //SmartDashboard.putData("unlockClimberBtn", new ReleaseTensionAndUnlock(m_climbSystem));



        // final JoystickButton raiseClimberBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
        // raiseClimberBtn.whenPressed(new ManuelClimber(m_climbSystem), true);
        // SmartDashboard.putData("raiseClimberBtn", new ManuelClimber(m_climbSystem));

        // final JoystickButton lowerClimberBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
        // lowerClimberBtn.whenPressed(new LowerClimber(m_climbSystem), true);
        // SmartDashboard.putData("lowerClimberBtn", new LowerClimber(m_climbSystem));

        // final JoystickButton raiseTraverseClimberBtn = new JoystickButton(assistController,
        //         XboxController.Button.kA.value);
        // raiseTraverseClimberBtn.whenPressed(new RaiseTraverse(m_climbSystem), true);
        // SmartDashboard.putData("RaiseTraverseClimberBtn", new RaiseTraverse(m_climbSystem));

        // final JoystickButton lowerTraverseClimberBtn = new JoystickButton(assistController,
        //         XboxController.Button.kA.value);
        // lowerTraverseClimberBtn.whenPressed(new LowerTraverse(m_climbSystem), true);
        // SmartDashboard.putData("LowerTraverseClimberBtn", new LowerTraverse(m_climbSystem));

        // final JoystickButton shootBallBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
        final JoystickButton loadBallBtn = new JoystickButton(assistController, XboxController.Button.kX.value);
        final JoystickButton sequenceShotBtn = new JoystickButton(assistController, XboxController.Button.kA.value);    
        final JoystickButton reverseBallBtn = new JoystickButton(assistController, XboxController.Button.kY.value);
        final JoystickButton stopLoadBallMotorsBtn = new JoystickButton(assistController, XboxController.Button.kB.value);
        
       

        DPadButton loadBallDpadBtn;
        DPadButton sequenceShotDPad;
        DPadButton reverseBallDpad;
        DPadButton StopBallDpadBtn;

        // loadBallDpadBtn = new DPadButton(assistController, DPadButton.Direction.DOWN);
        // loadBallDpadBtn.whenPressed(new ManualStageBall(m_ballHandlingSystem), true);
        loadBallDpadBtn = new DPadButton(assistController, DPadButton.Direction.DOWN);
        loadBallDpadBtn.whenPressed(new StartLoadCommands(m_ballHandlingSystem), true);

        sequenceShotDPad = new DPadButton(assistController, DPadButton.Direction.RIGHT);
        sequenceShotDPad.whenPressed(new SequenceShot(m_ballHandlingSystem), true);

        reverseBallDpad = new DPadButton(assistController, DPadButton.Direction.UP);
        reverseBallDpad.whenPressed(new ReverseBall(m_ballHandlingSystem), true);

        StopBallDpadBtn = new DPadButton(assistController, DPadButton.Direction.LEFT);
        StopBallDpadBtn.whenPressed(new StopLoadCommands(m_ballHandlingSystem), true);

        loadBallBtn.whenPressed(new StartLoadCommands(m_ballHandlingSystem), true);
        //SmartDashboard.putData("loadBallBtn", new LoadBall(m_ballHandlingSystem));
        // loadBallBtn.whenPressed(new ManualStageBall(m_ballHandlingSystem), true);
        // SmartDashboard.putData("loadBallBtn", new ManualStageBall(m_ballHandlingSystem));

        sequenceShotBtn.whenPressed(new SequenceShot(m_ballHandlingSystem), true);
        //SmartDashboard.putData("sequenceShotBtn", new SequenceShot(m_ballHandlingSystem));   
        
        // shootBallBtn.whenPressed(new ShootBall(m_ballHandlingSystem), true);               
        // SmartDashboard.putData("lowerClimberBtn", new ShootBall(m_ballHandlingSystem));

        reverseBallBtn.whenPressed(new ReverseBall(m_ballHandlingSystem), true);
        //SmartDashboard.putData("reverseBallBtn", new ReverseBall(m_ballHandlingSystem));

        stopLoadBallMotorsBtn.whenPressed(new StopLoadCommands(m_ballHandlingSystem), true);
        //SmartDashboard.putData("stopBallMotorsBtn", new StopBallMotors(m_ballHandlingSystem));
  
        final JoystickButton topCameraBtn = new JoystickButton(assistController, XboxController.Button.kLeftBumper.value);
        final JoystickButton botCameraBtn = new JoystickButton(assistController, XboxController.Button.kRightBumper.value);
        final JoystickButton top2CameraBtn = new JoystickButton(driverController, XboxController.Button.kLeftBumper.value);
        final JoystickButton bot2CameraBtn = new JoystickButton(driverController, XboxController.Button.kRightBumper.value);

        topCameraBtn.whenPressed(new SetCameraSource(m_visionSystem, USBCameras.TopCamera));
        botCameraBtn.whenPressed(new SetCameraSource(m_visionSystem, USBCameras.BottomCamera));
        top2CameraBtn.whenPressed(new SetCameraSource(m_visionSystem, USBCameras.TopCamera));
        bot2CameraBtn.whenPressed(new SetCameraSource(m_visionSystem, USBCameras.BottomCamera));


    }

    public XboxController getdriverController() {
        return driverController;
    }

    public XboxController getassistController() {
        return assistController;
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return new Auto(m_driveSystem, m_ballHandlingSystem, m_autoChooser.getSelected(), m_autoAngleChooser.getSelected()); 
    }

    // public Integer getAutonomousAngle() {
    //     return m_autoAngleChooser.getSelected();
    // }

    public Command getClimbIndexer() {
        return new IndexClimberSequence(m_climbSystem);
    }

    public Command getArmIndexer() {
        return new RaiseArm(m_ballHandlingSystem);
    }

}
