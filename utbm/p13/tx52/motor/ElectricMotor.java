package utbm.p13.tx52.motor;


/**
 *
 * @author Gaut
 */
public class ElectricMotor extends AbstractMotor {

    //default constructor
    public ElectricMotor(){
        super();
    }
    
    //contructor with basic data
    public ElectricMotor(double wheelRadius, double surface, double aerodynamicCoefficient, double weight, double frictionCoefficient) {
        super();
        this.wheelRadius = wheelRadius;
        this.surface = surface;
        this.aerodynamicCoefficient = aerodynamicCoefficient;
        this.weight = weight;
        this.frictionCoefficient = frictionCoefficient;
    }

    //basic constructor + specification
    public ElectricMotor(double wheelRadius, double surface, double aerodynamicCoefficient, double weight, double frictionCoefficient, int velocity, double incline) {
        this(wheelRadius, surface, aerodynamicCoefficient, weight, frictionCoefficient);
        this.velocity = velocity;
        this.incline = incline;
        this.initCalculatedValue();
    }
}


