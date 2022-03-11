
package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//positive x value, right negative, left


// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSystem extends SubsystemBase {

    public enum CameraModes {
        vision,
        driver
    }

    double x, y, area;


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


    public Double getTargetX(){
        return x; 
    }
    public Double getTargetY(){
        return y; 
    }
    public Double getTargetArea(){
        return area; 
    }
}