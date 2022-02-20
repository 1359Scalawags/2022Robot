
package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.Ball.*;
import frc.robot.commands.Climb.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.AutoShoot;
import frc.robot.commands.autonomous.AutoShootAndDrive;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

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

    // Joysticks
    private final XboxController assistController = new XboxController(1);
    private final XboxController driverController = new XboxController(0);

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        // Smartdashboard Subsystems

        // SmartDashboard Buttons
        SmartDashboard.putData("LoadBall", new LoadBall(m_ballHandlingSystem));
        SmartDashboard.putData("ShootBall", new ShootBall(m_ballHandlingSystem));
        SmartDashboard.putData("ReverseDrive", new ReverseDrive(m_driveSystem));
        SmartDashboard.putData("ProcessCameraImage", new ProcessCameraImage(m_visionSystem));
        SmartDashboard.putData("AutoShoot", new AutoShoot(m_ballHandlingSystem));
        SmartDashboard.putData("AutoDrive", new AutoDrive(m_driveSystem));
        SmartDashboard.putData("AutoShootAndDrive", new AutoShootAndDrive());
        SmartDashboard.putData("LockClimber", new LockClimber(m_climbSystem));
        // SmartDashboard.putData("UnlockClimber", new UnlockTraverse(m_climbSystem));
        SmartDashboard.putData("RaiseClimber", new ManuelClimber(m_climbSystem));
        // SmartDashboard.putData("LowerClimber", new LowerClimber(m_climbSystem));
        SmartDashboard.putData("ManualDrive", new ManualDrive(m_driveSystem));
        // SmartDashboard.putData("RaiseTraverse", new RaiseTraverse(m_climbSystem));
        // SmartDashboard.putData("LowerTraverse", new LowerTraverse(m_climbSystem));

        // Configure the button bindings
        configureButtonBindings();

        // Configure default commands
        m_driveSystem.setDefaultCommand(new ManualDrive(m_driveSystem));
        // Configure autonomous sendable chooser

        m_chooser.addOption("AutoShoot", new AutoShoot(m_ballHandlingSystem));
        m_chooser.addOption("AutoDrive", new AutoDrive(m_driveSystem));
        m_chooser.addOption("AutoShootAndDrive", new AutoShootAndDrive());
        // m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ',
        // '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ',
        // '')} ));

        SmartDashboard.putData("Auto Mode", m_chooser);
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
        SmartDashboard.putData("reverseDriveBtn", new ReverseDrive(m_driveSystem));

        final JoystickButton lockClimberBtn = new JoystickButton(driverController, XboxController.Button.kX.value);
        lockClimberBtn.whenPressed(new LockClimber(m_climbSystem), true);
        SmartDashboard.putData("lockClimberBtn", new LockClimber(m_climbSystem));

        final JoystickButton unlockClimberBtn = new JoystickButton(driverController, XboxController.Button.kA.value);
        unlockClimberBtn.whenPressed(new UnlockClimber(m_climbSystem), true);
        SmartDashboard.putData("unlockClimberBtn", new UnlockClimber(m_climbSystem));

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

        final JoystickButton loadBallBtn = new JoystickButton(assistController, XboxController.Button.kB.value);
        loadBallBtn.whenPressed(new LoadBall(m_ballHandlingSystem), true);
        SmartDashboard.putData("loadBallBtn", new LoadBall(m_ballHandlingSystem));

    }

    public XboxController getdriverController() {
        return driverController;
    }

    public XboxController getassistController() {
        return assistController;
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return m_chooser.getSelected();
    }

}
