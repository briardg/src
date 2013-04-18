package utbm.p13.tx52.vehicle;

import java.util.ArrayList;
import utbm.p13.tx52.battery.LithiumBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class SeriesHybridVehicule extends AbstractHybridVehicle {

    public SeriesHybridVehicule() {
        this.engine=new Engine();
        this.battery=new LithiumBattery();
        this.electricMotor=new ElectricMotor(0.5, 2, 0.5, 1000, 0.4, 14,0);
    }


    public static void main(String[] args) {
        SeriesHybridVehicule v = new SeriesHybridVehicule();
        ArrayList<Integer> list = new ArrayList<Integer>(10);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(10);
        list.add(10);
        list.add(0);
        list.add(-20);
        for(int i : list) {
            v.electricMotor.setIncline(i);
            System.out.println(v.engine.consommation(((LithiumBattery)v.battery).calculatedNeededPower(v.electricMotor.calculatedPower())));
        }
    }
}
