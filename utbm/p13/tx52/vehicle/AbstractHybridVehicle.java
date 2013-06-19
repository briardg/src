package utbm.p13.tx52.vehicle;

import java.text.SimpleDateFormat;
import java.util.Date;
import utbm.p13.tx52.battery.AbstractBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class AbstractHybridVehicle implements Ivehicle{

    protected String id="Vehicule number"+new SimpleDateFormat("ss").format(new Date());
    protected Engine engine;
    protected AbstractBattery battery;
    protected ElectricMotor electricMotor;

    public AbstractBattery getBattery() {
        return battery;
    }

    public void setBattery(AbstractBattery battery) {
        this.battery = battery;
    }

    public ElectricMotor getElectricMotor() {
        return electricMotor;
    }

    public void setElectricMotor(ElectricMotor electricMotor) {
        this.electricMotor = electricMotor;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    

}