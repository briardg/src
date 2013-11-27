package utbm.p13.tx52.battery;

import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;



public class ProposedBatteryModel {

    private boolean isCharging = false;
    
    // State of Capacity (input and output)
    private double SoC;
    
    // those variables are constants durning calculations
    // but have to be arranged separetly for each battery
    private double voltage;
    private double capacity;
    
    // 90% when discharging, 95% when charging
    private double batteryEfficiency;
    
    
    public ProposedBatteryModel(double voltage,double capacity) {
        this.voltage=voltage;
        this.capacity=capacity;
        this.SoC=this.capacity; }
        
    
    public double CalculateSoC(double SoC, double current) {
        
        if(isCharging) batteryEfficiency = 0.95;
        else batteryEfficiency = 0.90;
        
        double RC = SoC / capacity;
        //double As = new TrapezoidIntegrator(current * batteryEfficiency) / 3600;
        double As = new TrapezoidIntegrator(current * batteryEfficiency) / 3600;
        SoC = As / RC;
        
        return SoC;
    }
}
