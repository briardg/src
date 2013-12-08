package utbm.p13.tx52.vehicle;

import utbm.p13.tx52.battery.AbstractBattery;
import utbm.p13.tx52.connection.ClientSocketReceiver;
import utbm.p13.tx52.connection.ClientSocketSender;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public abstract class AbstractHybridVehicle implements Ivehicle{

    protected Engine engine;
    protected AbstractBattery battery;
    protected ElectricMotor electricMotor;
    protected double torque;
   
    protected ClientSocketReceiver receiver;
    protected ClientSocketSender sender;

    //rang of power than the batttery can give
    private double[] rangOfPower= new double[]{0,0};

    //power made in extra or missing (need engine or other vehicule)
    private double extraPower=0;
    private double missingPower=0;

  
    @Override
    public void update(double torque){
        this.torque=this.receiver.getTorque();
        //calculate new needed power from new torque
        this.electricMotor.setIncline(this.torque);
    }
    
    
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

    public ClientSocketReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(ClientSocketReceiver receiver) {
        this.receiver = receiver;
    }

    public ClientSocketSender getSender() {
        return sender;
    }

    public void setSender(ClientSocketSender sender) {
        this.sender = sender;
    }
    
    
    
    public double getExtraPower() {
        return extraPower;
    }

    public void setExtraPower(double extraPower) {
        this.extraPower = extraPower;
    }

    public double getMissingPower() {
        return missingPower;
    }

    public void setMissingPower(double missingPower) {
        this.missingPower = missingPower;
    }

}