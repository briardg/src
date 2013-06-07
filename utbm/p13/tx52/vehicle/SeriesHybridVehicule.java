package utbm.p13.tx52.vehicle;

import java.util.ArrayList;
import utbm.p13.tx52.battery.LithiumBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class SeriesHybridVehicule extends AbstractHybridVehicle {

    public SeriesHybridVehicule() {
        this.engine=new Engine();
        this.battery=new LithiumBattery(12,1000);
        this.electricMotor=new ElectricMotor(0.5, 2, 0.5, 1000, 0.4, 14,0);
    }

}