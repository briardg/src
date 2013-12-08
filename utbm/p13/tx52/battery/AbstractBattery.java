package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public abstract class AbstractBattery implements IBattery{

    //basic data
    protected boolean isCharging=false;
    protected double voltage; //volt
    protected double totalCapacity; //Ah
    protected double ratio; // difference between actual power and next needed power p(t+1)=p(t)+ratio

    protected double energyByWeight; // Wh/kg

    protected double efficiency; // efficiency de la batterie out=%in

    //calculated values from the basic data 
    protected double minCapacity; //Ah
    protected double instantPower; // value * C (0,33C or 1C or 1,5C)

    //calculated values
    protected double currentCapacity; //Ah
    protected double currentOutPower=0;

    // return array with power limit than the battery will be able to produce at the next step 
    public double[] getRangePower(){
        double powerMin=this.currentOutPower-this.ratio;
        if(powerMin<0)
            powerMin=0;

        return new double[]{powerMin,this.currentOutPower+this.ratio};
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public double getTotalCapacity(){
        return this.totalCapacity;
    }

    public int getPourcentageOfCharge(){
        return (int)(this.currentCapacity*100/this.totalCapacity);
    }

    public double getCurrentOutPower(){
        return currentOutPower;
    }


    //Power used during one second  = 1/3600 hour
    //because the total intensity, so the total power, is for one hour
    public void setCurrentCapacity(double powerIn) {

        this.currentCapacity +=(powerIn/this.voltage)/3600;

        //test pour ne pas depasser la capacité physique max et min de la batterie
        if (this.currentCapacity>this.totalCapacity)
            this.currentCapacity=this.totalCapacity;
        else if(this.currentCapacity<0)
            this.currentCapacity=0;
    }

    //Utilization of the battery without recharge
    public void setCurrentCapacity() {
         this.currentCapacity -=(this.currentOutPower/this.voltage)/3600;

        //test pour ne pas depasser la capacité physique max et min de la batterie
        if (this.currentCapacity>this.totalCapacity)
            this.currentCapacity=this.totalCapacity;
        else if(this.currentCapacity<0)
            this.currentCapacity=0;
    }

    public void setCurrentOutPower(double currentOutPower){
        this.currentOutPower=currentOutPower;
    }

    //The battery need to be recharge under 15%
    //If the battery level is after 98% no need to recharge anymore
    //If we was recharging and the level is still betwwen 98% and 15% so we still continu recharging
    //if it's th opposite still using the battery but without recharging.
    public boolean isNeedOfPower(){
        if(this.currentCapacity>=this.totalCapacity*98/100){
            return isCharging=false;
        }else if(this.currentCapacity<=this.totalCapacity*15/100){
            return isCharging=true;
        }else{
            return isCharging;
        }
    }



}
