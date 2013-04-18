package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public class LithiumBattery extends AbstractBattery {

    public double calculatedNeededPower(double outPower){
        return outPower/0.9;
    }
}
