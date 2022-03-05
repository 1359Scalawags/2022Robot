package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DisplaySystem extends SubsystemBase {

    private ShuffleboardTab mainTab = Shuffleboard.getTab("Match Tab");
    private NetworkTableEntry timeEntry;
    
    public DisplaySystem() {
        Shuffleboard.selectTab("Match Tab");
        timeEntry = mainTab
                    .add("Match Time", 0)
                    .withWidget(BuiltInWidgets.kDial)
                    .withProperties(Map.of("min", 0, "max", 135))
                    .withSize(3,3)
                    .withPosition(10, 0)
                    .getEntry();

    }


    @Override
    public void periodic() {
        if(timeEntry != null) {
            timeEntry.setDouble(DriverStation.getMatchTime());
        }
    }

}
