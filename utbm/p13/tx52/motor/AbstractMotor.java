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
    private double airResistance;
    private double floorResistance;
    private double angularVelocity;
    private double force; // force de frottement en Newton N
    private double wheelTorque; //couple roue ou moteur N/m
    private double currentPower; //Watt
    
    

    //*** getter ***//

    public double getAirResistance(){
        return this.airResistance;
    }

    public double getFloorResistance(){
        return this.floorResistance;
    }

    public double getAngularVelocity(){
        return this.angularVelocity;
    }

    public double getForce(){
        return force;
    }

    public double getwheelTorque(){
        return wheelTorque;
    }

    public double getCurrentPower(){
        return this.currentPower;
    }

    //*** setter modification venant d'une autre class ***//

    public void setVelocity(int velocity) {
        this.velocity = velocity;
        this.calculateAirResistance();
        this.calculateAngularVelocity();
        this.calculateForce();
        this.calculateWheelTorque();
        this.calculatedPower();
    }

    public void setIncline(double incline) {
        this.incline = incline;
        this.calculateFloorResistance();
        this.calculateForce();
        this.calculateWheelTorque();
        this.calculatedPower();
    }


    //*** methodes effectuant des calcules ***//

    public void calculateAirResistance(){
        this.airResistance= 1/2*this.aerodynamicCoefficient*this.airDensity*this.surface*(this.velocity);
    }

    //force angulaire
    public void calculateAngularVelocity(){
        this.angularVelocity=this.velocity/this.wheelRadius;
    }

    //force de frottement plus force de la pente si il y a.
    public void calculateFloorResistance(){

        //en monté
        if(this.incline>=0)
           this.floorResistance = (this.weight*G) - (this.weight*G)*(Math.cos(Math.toRadians(this.incline)))+(this.weight*G)*(Math.cos(Math.toRadians(this.incline)))*(this.frictionCoefficient);
        //descente
        else
           this.floorResistance = -((this.weight*G) - (this.weight*G)*(Math.cos(Math.toRadians(this.incline))))+(this.weight*G)*(Math.cos(Math.toRadians(this.incline)))*(this.frictionCoefficient);

    }

    //retourne le total des force de frottement
    public void calculateForce(){
       this.force=this.airResistance+this.floorResistance;
    }

    public void calculateWheelTorque(){
        this.wheelTorque=this.wheelRadius*this.force;
    }

    //Power en Watt
    public void calculatedPower(){
        this.currentPower=this.angularVelocity*this.wheelTorque;
    }

    //calcule des différentes composante de la puissance
    //la mise à jour se fera pas la MaJ de la vitesse ou de l'inclinaison
    public void initCalculatedValue(){
        this.calculateAirResistance();
        this.calculateAngularVelocity();
        this.calculateFloorResistance();
        this.calculateForce();
        this.calculateWheelTorque();
        this.calculatedPower();
    }

}
