
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//positive x value, right negative, left

// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSystem extends SubsystemBase {

    public enum LimelightModes {
        vision,
        driver
    }

    public enum USBCameras {
        TopCamera,
        BottomCamera
    }

    // variables for USB Cams
    UsbCamera camera1;
    UsbCamera camera2;
    VideoSink server;

    // variables for Limelight
    double x, y, area;

    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = getValue("tx");
    NetworkTableEntry ty = getValue("ty");
    NetworkTableEntry ta = getValue("ta");

    public VisionSystem() {
        // limelight initialization
        setCamMode(LimelightModes.vision);

        try {
            // USB Camera initialization
            camera1 = CameraServer.startAutomaticCapture(0);
            camera1.setResolution(640, 480);
            camera1.setFPS(15);
            camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        } catch (Exception e) {
            camera1 = null;
        }

        try {
            camera2 = CameraServer.startAutomaticCapture(1);
            camera2.setResolution(640, 480);
            camera2.setFPS(15);
            camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        } catch (Exception e) {
            camera2 = null;
        }

        server = CameraServer.getServer();
        if (camera1 != null) {
            server.setSource(camera1);
        } else if (camera2 != null) {
            server.setSource(camera2);
        }

    }

    public static void setCamMode(LimelightModes mode) {
        getValue("camMode").setNumber(mode.ordinal());
    }

    public void setUSBCamera(USBCameras camera) {
        if (camera == USBCameras.BottomCamera) {
            server.setSource(camera2);
        } else if (camera == USBCameras.TopCamera) {
            server.setSource(camera1);
        } else {
            server.setSource(null);
        }
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

    public Double getTargetX() {
        return x;
    }

    public Double getTargetY() {
        return y;
    }

    public Double getTargetArea() {
        return area;
    }
}