package utbm.p13.tx52.battery;

/**
 *
 * @author Gaut
 */
public abstract class AbstractBattery implements IBattery{

    //données de base
    protected boolean isCharging=false;
    protected double voltage; //tension nominale volt
    protected double totalCapacity; //Ah
    protected double ratio; //difference entre puissance et la puissance pour le cycle suivant p(t+1)=p(t)+ratio

    protected double energyByWeight; //Wh/kg

    protected double efficiency; // rendement de la batterie out=%in

    //valeurs calculées via données de base
    protected double minCapacity; //Ah
    protected double instantPower; // value * C (0,33C ou 1C ou 1,5C)

    //valeurs calculées
    protected double currentCapacity; //Ah

    //retourne un tableau avec les borne de puissance que la batterie pourra produire par la suite
    public double[] getRangePower(double power){
        return new double[]{power-this.ratio,power+this.ratio};
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    //Puissance utilisé pendant une seconde = 1/3600 heure
    //car l'intensité total donc la puissance total est pour une heure
    public void setCurrentCapacity(double powerIn, double powerOut) {
        this.currentCapacity += ((powerIn-powerOut)/this.voltage)/3600;
    }

    //utilisation de la batteri sans recharge
    public void setCurrentCapacity(double powerOut) {
        this.currentCapacity -=(powerOut/this.voltage)/3600;
    }

    //la batterie à besoin d'etre recharger si le seul de 30% est passé
    // si la batterie est après 90% c'est bon pas besoin de la recharger
    //si on la rechargai et qu'elle est toujours entre 30% et 90% alors on continu
    // si c'est l'inverse on continu de l'utiliser sans la recharger
    public boolean isNeedOfEnginePower(){
        if(this.currentCapacity>=this.totalCapacity*90/100){
            return isCharging=false;
        }else if(this.currentCapacity<=this.totalCapacity*30/100){
            return isCharging=true;
        }else{
            return isCharging;
        }
    }



}