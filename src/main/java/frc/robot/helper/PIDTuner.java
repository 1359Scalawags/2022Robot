// package frc.robot.helper;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

// public class PIDTuner {
//     private String name;
//     private PIDController controller;

//     private ShuffleboardTab tunerTab;
//     private NetworkTableEntry pEntry;
//     private NetworkTableEntry iEntry;
//     private NetworkTableEntry dEntry;
//     private NetworkTableEntry izEntry;
//     private NetworkTableEntry ffEntry;
//     private NetworkTableEntry actualEntry;
//     private NetworkTableEntry targetEntry;
//     private NetworkTableEntry errorEntry;

//     private PIDValues pidValues;
//     private double targetValue;
//     private double errorValue;
//     private double actualValue;

//     public PIDTuner(String name, PIDController controller, PIDValues initialValues, int initialTarget) {
//         this.name = name;
//         this.controller = controller;
//         this.pidValues = new PIDValues(initialValues);
//         this.targetValue = initialTarget;

//         controller.setP(this.pidValues.kP);
//         controller.setI(this.pidValues.kI);
//         controller.setD(this.pidValues.kD);

//         initialize();

//     }

//      // ShuffleBoard version
//      private void initialize() {
//         tunerTab = Shuffleboard.getTab(name);
//         layoutShuffleboard(tunerTab);
//         pEntry.setDouble(this.pidValues.kP);
//         iEntry.setDouble(this.pidValues.kI);
//         dEntry.setDouble(this.pidValues.kD);
//         targetEntry.setDouble(this.targetValue);
//     }

//      // ShuffleBoard version
//      public void periodic() {
//         double p = pEntry.getDouble(0);
//         double i = iEntry.getDouble(0);
//         double d = dEntry.getDouble(0);
//         double target = targetEntry.getDouble(0);

//         if((p != pidValues.kP)) { controller.setP(p); pidValues.kP = p; }
//         if((i != pidValues.kI)) { controller.setI(i); pidValues.kI = i; }
//         if((d != pidValues.kD)) { controller.setD(d); pidValues.kD = d; }
//         if(target != targetValue) {controller.setSetpoint(target); targetValue = target;}

//         //actualValue = encoder.getVelocity();
//         //errorValue = targetValue - actualValue;

//         actualEntry.setDouble(actualValue);
//         //errorEntry.setDouble(errorValue);
//         //System.out.println("Actual: " + actualValue + " Error: " + errorValue);
//     }

//     private void layoutShuffleboard(ShuffleboardTab shuffleTab) {
//         pEntry = shuffleTab
//                     .add("P Gain", 0)
//                     .withWidget(BuiltInWidgets.kTextView)
//                     .withSize(1,1)
//                     .withPosition(0, 0)
//                     .getEntry();

//         iEntry = shuffleTab
//                     .add("I Gain", 0)
//                     .withWidget(BuiltInWidgets.kTextView)
//                     .withSize(1,1)
//                     .withPosition(1, 0)
//                     .getEntry();

//         dEntry = shuffleTab
//                     .add("D Gain", 0)
//                     .withWidget(BuiltInWidgets.kTextView)
//                     .withSize(1,1)
//                     .withPosition(2, 0)
//                     .getEntry();

//         targetEntry = shuffleTab
//                     .add("Target", 0)
//                     .withWidget(BuiltInWidgets.kTextView)
//                     .withSize(1,1)
//                     .withPosition(1, 1)
//                     .getEntry();

//         // actualEntry = shuffleTab
//         //             .add("Actual", 0)
//         //             .withWidget(BuiltInWidgets.kTextView)
//         //             .withSize(1,1)
//         //             .withPosition(2, 1)
//         //             .getEntry();

//         // errorEntry = shuffleTab
//         //             .add("Error", 0)
//         //             .withWidget(BuiltInWidgets.kTextView)
//         //             .withSize(1,1)
//         //             .withPosition(3, 1)
//         //             .getEntry();                    
//     }


    
// }
