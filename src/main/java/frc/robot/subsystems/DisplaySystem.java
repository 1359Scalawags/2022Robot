package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;


import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class DisplaySystem extends SubsystemBase {
    PowerDistribution m_pdh = new PowerDistribution(Constants.DisplaySystem.PDHCANID,ModuleType.kRev);

    private ShuffleboardTab mainTab = Shuffleboard.getTab("Match Tab");
    private NetworkTableEntry timeEntry;
    private NetworkTableEntry climbLockEntry;
    private NetworkTableEntry batteryVolege;
    private ClimbSystem climbSystem;

    public DisplaySystem(ClimbSystem climber) {
        climbSystem = climber;
        Shuffleboard.selectTab("Match Tab");
            timeEntry = mainTab
                        .add("Match Time", 0)
                        .withWidget(BuiltInWidgets.kDial)
                        .withProperties(Map.of("min", 0, "max", 135))
                        .withSize(3,3)
                        .withPosition(7, 0)
                        .getEntry();

            climbLockEntry = mainTab
                        .add("Climber Status", true)
                        .withWidget(BuiltInWidgets.kBooleanBox)
                        .withSize(3,1)
                        .withProperties(Map.of("Color when true", "green", "Color when false", "maroon"))
                        .withPosition(0, 0)
                        .getEntry();

            batteryVolege = mainTab
                        .add("Battery Voltege", 0)
                        .withWidget(BuiltInWidgets.kNumberBar)
                        .withProperties(Map.of("min", 0, "max", 135))
                        .withSize(3,1)
                        .withPosition(0, 1)
                        .getEntry();
    }


    private int counter = 0;
    @Override
    public void periodic() {
        if(counter > 5) {
            if(timeEntry != null) {
                timeEntry.setDouble(DriverStation.getMatchTime());
            }
            if(climbLockEntry != null) {
                climbLockEntry.setBoolean(!climbSystem.getMasterLocked());
            }
            if(batteryVolege != null) {
                batteryVolege.setDouble(m_pdh.getVoltage()); 
            }
            counter = 0;
        }
        
        counter++;
    }

}
