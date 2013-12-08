package utbm.p13.tx52.motor;



/**
 *
 * @author Gaut
 */

public abstract class AbstractMotor implements IMotor {


    //constant
    private static final double G =9.8; // gravitational force 
    private double airDensity=1.2; // air density in  kg/m^3 constante may be calculate


    //donnée de base
    protected double wheelRadius; // wheel or motor radius in meter 
    protected double surface; //air contact surface of the vehicle in m^2
    protected double aerodynamicCoefficient; // aerodynamics  coefficient without unity
    protected double weight; //kilo
    protected double frictionCoefficient; // resistence coefficient without unity

    //may be modify if necessary 
    protected int velocity; //speed in m/s
    protected double incline; //floor incline

    //calculate
    private double airResistance;
    private double floorResistance;
    private double angularVelocity;
    private double force; // resistence force in Newton N
    private double wheelTorque; //wheel or motor torque  N/m
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

    //*** setter modification coming from another class ***//

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


    //*** methods doing calculation ***//

    public void calculateAirResistance(){
        this.airResistance= 1/2*this.aerodynamicCoefficient*this.airDensity*this.surface*(this.velocity);
    }

    //force for Angular Velocity
    public void calculateAngularVelocity(){
        this.angularVelocity=this.velocity/this.wheelRadius;
    }

    // force of floorResistence plus incline
    public void calculateFloorResistance(){

        //up
        if(this.incline>=0)
           this.floorResistance = (this.weight*G) - (this.weight*G)*(Math.cos(Math.toRadians(this.incline)))+(this.weight*G)*(Math.cos(Math.toRadians(this.incline)))*(this.frictionCoefficient);
        //down
        else
           this.floorResistance = -((this.weight*G) - (this.weight*G)*(Math.cos(Math.toRadians(this.incline))))+(this.weight*G)*(Math.cos(Math.toRadians(this.incline)))*(this.frictionCoefficient);

    }

    //return the total of the resistence forces 
    public void calculateForce(){
       this.force=this.airResistance+this.floorResistance;
    }

    public void calculateWheelTorque(){
        this.wheelTorque=this.wheelRadius*this.force;
    }

    //Power in Watt
    public void calculatedPower(){
        this.currentPower=this.angularVelocity*this.wheelTorque;
    }

    //calculate all the differents part which compose the power
    // the entire update will be by the update of the speed ou the incline or the torque 
    public void initCalculatedValue(){
        this.calculateAirResistance();
        this.calculateAngularVelocity();
        this.calculateFloorResistance();
        this.calculateForce();
        this.calculateWheelTorque();
        this.calculatedPower();
    }
    
    //TODO:calculate from torque and that work with the speed and the whellradisu

}
