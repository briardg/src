package utbm.p13.tx52.motor;

/**
 *
 * @author Gaut
 */
public class Engine extends AbstractMotor {

    private double tank; //gramme
    private double weightOfLiter;
    private double consumption; // g/kW/h
    private double OptimalPower; //KW
    private boolean turnON=false;

    private int efficiency; //efficacité du moteur en %

    //tank given in liter
    public Engine(int tank, double weightOfLitter, double consumption, double OptimalPower) {
        this.tank = weightOfLitter*tank;
        this.consumption = consumption;
        this.OptimalPower = OptimalPower;
    }

    public boolean isTurnON() {
        return turnON;
    }

    public double getTank(){
        return tank;
    }

    public double getOptimalPower(){
        return OptimalPower;
    }

    public void setTurnON(boolean turnON) {
        this.turnON = turnON;
    }


    public void updateTank(int numberOfSecond){
        tank-=(consumption*(numberOfSecond/3600))*OptimalPower;
    }


    public double consommation(double power){
        return 1*power/10000;
    }

}
