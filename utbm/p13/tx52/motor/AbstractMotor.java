package utbm.p13.tx52.motor;



/**
 *
 * @author Gaut
 */

public abstract class AbstractMotor implements IMotor {


    //constant
    private static final double G =9.8; // force accélération gravitationnelle
    private double airDensity=1.2; // densité de l'air en kg/m^3 constant mais calcul possible


    //donnée de base
    protected double wheelRadius; // rayon de la roue ou du moteur en mètre
    protected double surface; //surface en contact avec l'air du vehicule en m^2
    protected double aerodynamicCoefficient; // coef d'aerodynamisme pas d'unité
    protected double weight; //poid en kilo
    protected double frictionCoefficient; // coefficient de frottement sans unité

    //modifier si necessaire
    protected int velocity; //vitesse en m/s
    protected double incline; //angle de pente en degré

    //valeur calculer
    private double force; // force de frottement en Newton N
    private double wheelTorque; //couple roue ou moteur N/m


    //*** getter ***//

    public double getForce(){
        return force;
    }

    public double getwheelTorque(){
        return wheelTorque;
    }

    //*** setter modification venant d'une autre class ***//

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setIncline(double incline) {
        this.incline = incline;
    }

    //*** methodes effectuant des calcules ***//

    public double calculatedAirResistance(){
        return 1/2*aerodynamicCoefficient*airDensity*surface*(velocity*velocity);
    }

    //force de frottement plus force de la pense si il y a.
    public double calculatedFloorResistance(){
        //en monté
        if(incline>=0)
            return ((weight*G)*(1-Math.cos(incline*Math.PI/180)+frictionCoefficient));
        //descente
        else
            return ((weight*G)*(-1+Math.cos(incline*Math.PI/180)+frictionCoefficient));

    }

    //retourne le total des force de frottement
    public double calculatedForce(){
        return force=calculatedAirResistance()+calculatedFloorResistance();
    }

    public double calculatedWheelTorque(){
        return wheelTorque=wheelRadius*calculatedForce();
    }

    public double calculatedAngularVelocity(){
        return velocity/wheelRadius;
    }

    //Power en Watt
    public double calculatedPower(){
        return calculatedAngularVelocity()*calculatedWheelTorque();
    }

}
