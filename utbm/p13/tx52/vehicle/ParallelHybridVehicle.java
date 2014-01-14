package utbm.p13.tx52.vehicle;

import utbm.p13.tx52.battery.LithiumBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class ParallelHybridVehicle extends AbstractHybridVehicle {
 
    public ParallelHybridVehicle() {
        this.engine=new Engine(60,0.75,0.195,2);
        this.battery=new LithiumBattery(12,138);
        this.electricMotor=new ElectricMotor(0.5, 2, 0.5, 1000, 0.04, 14,0);
    }

    @Override
    public void update() {
        super.update();
        
        
        if(this.electricMotor.getCurrentPower()<this.battery.getMaxDisChargePower() && this.battery.getPourcentageOfCharge()>15){
            this.battery.setCurrentOutPower(this.electricMotor.getCurrentPower());
            this.battery.setCurrentCapacityByUsing(this.electricMotor.getCurrentPower());
            
            //recharge battery if needed
            if(this.battery.isNeedOfPower()){
                this.engine.setTurnON(true);
                //reduce capacity from tank
                this.engine.updateTank();
                //recharge battery
                this.battery.setCurrentCapacityByCharging(this.engine.getOptimalPower());
            }else{
                this.engine.setTurnON(false);
            }
            
            if(this.battery.getCurrentOutPower()!=0.0)
                this.sender.setTorque(this.battery.getCurrentOutPower()/this.electricMotor.getAngularVelocity());
            else
                this.sender.setTorque(0.0);
        }
        else{
            //direct connection between EM and Engine
            
            //consumption in g/kW.h
            double consumption = this.engine.getConsumptionFromTorqueAndAV(this.electricMotor.getwheelTorque(),this.electricMotor.getAngularVelocity());
            
            this.engine.updateTankWithConsumption(consumption, this.electricMotor.getCurrentPower());
            
            this.sender.setTorque(this.electricMotor.getwheelTorque());
        }
        
    }
    
}
