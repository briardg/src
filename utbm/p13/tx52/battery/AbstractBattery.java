package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public abstract class AbstractBattery implements IBattery{

    //constante
    private final static double cosPhi=0.6; // constante value for alternative current

    //données de base
    private double voltage; //tension nominale volt
    private double totalCapacity; //Ah
    private double energyByWeight; //Wh/kg
    private double lifetime; //nb of Year
    private double cycleNumber; //nombre de cycle 100% decharge - 100% charge
    private double selfDischarge; //% par mois
    private double efficiency; // rendement de la batterie out=%in
    
    private boolean continuousCurrent; // is continous or alternative
    
    //valeurs calculées via données de base
    private double minCapacity; //Ah
    private double instantPower; // value * C (0,33C ou 1C ou 1,5C)

    //valeurs calculées
    private double currentCapacity; //Ah

}
