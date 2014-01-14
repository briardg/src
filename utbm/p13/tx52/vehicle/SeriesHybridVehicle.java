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
        this.engine=new Engine(60,0.75,0.195,2);
        this.battery=new LithiumBattery(12,138);
        this.electricMotor=new ElectricMotor(0.5, 2, 0.5, 1000, 0.04, 14,0);
    }

    @Override
    public void update() {
        super.update();
        
        //TODO : ask again for that
        /************** Range calculation ****************/
        /*
         
            this.rangOfPower=this.battery.getRangePower();
            System.out.println("\nBorne de puissance pour la prochaine vague  -->"+this.rangOfPower[0]+" - "+this.rangOfPower[1]);

            if(rangOfPower[0]>this.electricMotor.getCurrentPower()){
                this.extraPower=rangOfPower[0]-this.electricMotor.getCurrentPower();
                this.missingPower=0;
                System.out.println("Extra power="+this.extraPower);
                this.battery.setCurrentOutPower(rangOfPower[0]);
            }else if(rangOfPower[1]<this.electricMotor.getCurrentPower()){
                this.missingPower=this.electricMotor.getCurrentPower()-rangOfPower[1];
                this.extraPower=0;
                System.out.println("The power max of the battery will not be enough !");
                System.out.println("Missing power="+this.missingPower);
                this.battery.setCurrentOutPower(rangOfPower[1]);
            }else{
                this.extraPower=0;
                this.missingPower=0;
                System.out.println("Perfect rang!");
                this.battery.setCurrentOutPower(this.electricMotor.getCurrentPower());
            }
        
        /******************* end ****************************/
        this.battery.setCurrentOutPower(this.electricMotor.getCurrentPower());
        if(this.electricMotor.getCurrentPower()>this.battery.getMaxDisChargePower())
            this.battery.setCurrentOutPower(this.battery.getMaxDisChargePower());
        System.out.println("Power:"+this.electricMotor.getCurrentPower());
        this.battery.setCurrentCapacityByUsing(this.electricMotor.getCurrentPower());
         

        
        /**********Parallel calculation**********/
            //if we neeed power we are turn on the engine get the missing power 
            // and put the reste in the extra power variable
            /*
            if(missingPower>0){
                this.engine.setTurnON(true);
                this.engine.updateTank();
                this.extraPower=this.engine.getOptimalPower()-missingPower;
                this.missingPower=0;
                if(this.extraPower<0){
                    this.missingPower=this.extraPower;
                    this.extraPower=0;
                }
            }
        
        /************ end *******************/
        
        
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
    
}
