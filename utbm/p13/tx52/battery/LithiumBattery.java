package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public class LithiumBattery extends AbstractBattery {
    
    public LithiumBattery(double voltage,double totalCapacity){
        this.voltage=voltage;
        this.totalCapacity=totalCapacity;
        this.coefficientOfCharge=0.5;
        this.coefficientOfDischarge=5;
        this.ratio=(this.coefficientOfCharge+this.coefficientOfDischarge)/2*totalCapacity;
        this.currentCapacity=this.totalCapacity;
        
    }
    
    public LithiumBattery(double voltage,double totalCapacity,int coefficientOfCharge, int coefficientOfDischarge ){
        this(voltage,totalCapacity);
        this.coefficientOfCharge=coefficientOfCharge;
        this.coefficientOfDischarge=coefficientOfDischarge;
        this.ratio=(this.coefficientOfCharge+this.coefficientOfDischarge)/2*totalCapacity;
        
    }
    
}
