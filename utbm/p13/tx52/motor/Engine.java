package utbm.p13.tx52.motor;

/**
 *
 * @author Gaut
 */
public class Engine extends AbstractMotor {

    private double tank; //kg
    private double weightOfLiter;
    private double consumption; //kg/kW/h
    private double OptimalPower; //KW
    private boolean turnON=false;

    //tank given in liter
    public Engine(int tank, double weightOfLitter, double consumption, double OptimalPower) {
        this.weightOfLiter=weightOfLitter;
        this.tank = tank*weightOfLitter;
        this.consumption = consumption;
        this.OptimalPower = OptimalPower;
    }

    public boolean isTurnON() {
        return this.turnON;
    }

    public double getTank(){
        return this.tank;
    }

   //watt
    public double getOptimalPower(){
        return this.OptimalPower*1000;
    }

    public void setTurnON(boolean turnON) {
        this.turnON = turnON;
    }

    public double getWeightOfLiter() {
        return this.weightOfLiter;
    }


    public void updateTank(){
        this.tank=this.tank-(this.consumption*this.OptimalPower/3600);
    }

}
