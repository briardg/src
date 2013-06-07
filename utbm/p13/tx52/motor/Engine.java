package utbm.p13.tx52.motor;

/**
 *
 * @author Gaut
 */
public class Engine extends AbstractMotor {

    private int tank; //liter or gramme
    private double weightOfLiter;
    private double consumption; // g/kW/h
    private double OptimalPower; //W

    private int efficiency; //efficacité du moteur en %

    
    public double consommation(double power){
        return 1*power/10000;
    }

}
