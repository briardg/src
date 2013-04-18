package utbm.p13.tx52.vehicle;

import utbm.p13.tx52.battery.AbstractBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class AbstractHybridVehicle implements Ivehicle{

    protected Engine engine;
    protected AbstractBattery battery;
    protected ElectricMotor electricMotor;

}