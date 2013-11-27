package utbm.p13.tx52.vehicle;

import utbm.p13.tx52.battery.LithiumBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class SeriesHybridVehicle extends AbstractHybridVehicle {

    public SeriesHybridVehicle() {
        this.engine=new Engine(60,0.75,0.195,200);
        this.battery=new LithiumBattery(12,1000);
        this.electricMotor=new ElectricMotor(0.5, 2, 0.5, 1000, 0.04, 14,0);
    }

}