
package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;



// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSystem extends SubsystemBase {

    public enum CameraModes {
        vision,
        driver
    }




    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = getValue("tx");
    NetworkTableEntry ty = getValue("ty");
    NetworkTableEntry ta = getValue("ta");

    public VisionSystem() {
       setCamMode(CameraModes.vision);
    }

    public static void setCamMode(CameraModes mode) {
        getValue("camMode").setNumber(mode.ordinal());
    }

    @Override
    public void periodic() {
        // read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }



    private static NetworkTableEntry getValue(String key) {
		if (table == null) {
			table = NetworkTableInstance.getDefault().getTable("limelight");
		}
        return table.getEntry(key);
    }

    @Override
    public void simulationPeriodic() {
        
    }
}