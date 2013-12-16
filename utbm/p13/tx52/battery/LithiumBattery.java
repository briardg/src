package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public class LithiumBattery extends AbstractBattery {

    
    public LithiumBattery(double voltage,double totalCapacity){
        this.voltage=voltage;
        this.totalCapacity=totalCapacity;
        this.ratio=1.5*totalCapacity;
        this.currentCapacity=this.totalCapacity;
    }
}
