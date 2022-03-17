package frc.robot.helper;
//import frc.robot.Constants.Drive;
//CED
public class PIDValues {
    public final double kP;
    public final double kI;
    public final double kD;
    public final double kIz;
    public final double kFf;

    /**
     * Instantiate a PID object using all zeros.
     */
    public PIDValues(){
        this(0,0,0,0,0);
    }

    /**
     * Instantiate a PID object using values from an ordered array.
     * @param values Values in the order of P, I, D, Iz and Ff
     */
    public PIDValues(double[] values){
        this(values[0], values[1], values[2], values[3], values[4]);
    }
    
    public PIDValues(double P, double I, double D, double Iz, double Ff){
        this.kP = P;
        this.kI = I;
        this.kD = D;
        this.kIz = Iz;
        this.kFf = Ff;
    }

    public boolean equals(PIDValues pid){
        return(this.kP == pid.kP && this.kI == pid.kI && this.kD == pid.kD && this.kIz == pid.kIz && this.kIz == pid.kFf);
    }

    public boolean equals(double[] pidArray){
        double[] pid = toArray();
        for (int i=0; i<pid.length; i++) {
            if(pid[i] != pidArray[i])
                return false;
        }
        return true;
    }

    public double[] toArray(){
        return new double[] {kP, kI, kD, kIz, kFf};
    }
}