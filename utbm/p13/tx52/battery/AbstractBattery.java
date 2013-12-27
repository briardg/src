package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public abstract class AbstractBattery implements IBattery{

    //basic data
    //constant for all classes
    static final protected double batteryEfficiencyCharging = 0.95;
    static final protected double batteryEfficiencyNotCharging = 0.90;
    //constant
    protected double coefficientOfCharge;
    protected double coefficientOfDischarge;
    
    protected double voltage; //volt
    protected double totalCapacity; //Ah
    protected double ratio; //watt  difference between actual power and next needed power p(t+1)=p(t)+/-ratio
    
    protected boolean isCharging=false;
    
    protected double energyByWeight; // Wh/kg 

    //calculated values from the basic data 
    protected double minCapacity; //Ah
    protected double instantPower; // value * C (0,33C or 1C or 1,5C)

    //calculated values
    protected double currentCapacity; //Ah
    protected double currentOutPower=0;

    // return array with power limit than the battery will be able to produce at the next step 
    public double[] getRangePower(){
        double powerMin=this.currentOutPower-(this.ratio*this.voltage);
        if(powerMin<0)
            powerMin=0;
        
        double powerMax=this.currentOutPower+(this.ratio*this.voltage);
        if(powerMax >= 5 * this.totalCapacity){
            powerMax = 5 * this.totalCapacity;
        }

        return new double[]{powerMin,powerMax};
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

    public double getCoefficientOfCharge() {
        return coefficientOfCharge;
    }

    public double getCoefficientOfDischarge() {
        return coefficientOfDischarge;
    }

    public double getVoltage() {
        return voltage;
    }
    
    
    


    //Power used during one second  = 1/3600 hour
    //because the total intensity, so the total power, is for one hour
    /**
     *
     * @param power power in (positive) or out (negative)
     */
    private void setCurrentCapacity(double power) {
        
        this.currentCapacity +=(power/this.voltage)/3600;

        //test for not going over the max capacity or under 0;
        if (this.currentCapacity>this.totalCapacity)
            this.currentCapacity=this.totalCapacity;
        else if(this.currentCapacity<0)
            this.currentCapacity=0;
    }
    
    public void setCurrentCapacityByCharging(double powerIn){
        if(this.isCharging)
            powerIn = powerIn * batteryEfficiencyCharging;
        else 
            powerIn = powerIn * batteryEfficiencyNotCharging;
        
        this.setCurrentCapacity(powerIn);
    }
    
    public void setCurrentCapacityByUsing(double powerOut){
        if(this.isCharging)
            powerOut = powerOut / batteryEfficiencyCharging;
        else 
            powerOut = powerOut / batteryEfficiencyNotCharging;
        
        this.setCurrentCapacity(0-powerOut);
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

    public double getMaxChargePower() {
        return this.totalCapacity*this.coefficientOfCharge*this.voltage;
    }

    public double getMaxDisChargePower() {
        return this.totalCapacity*this.coefficientOfDischarge*this.voltage;
    }

}
