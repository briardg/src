package utbm.p13.tx52;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import utbm.p13.tx52.vehicle.SeriesHybridVehicule;

/**
 *
 * @author Gaut
 */
public class Tx52 extends TimerTask{

    SeriesHybridVehicule v = new SeriesHybridVehicule();
    LinkedList<Integer> inclines= new LinkedList<Integer>();

    public Tx52() {
        this.inclines.push(0);
        v.getElectricMotor().setIncline(0);
    }

    @Override
    public void run() {
        System.out.print("Incline: "+this.inclines.peek()+"° --> ");
        System.out.println("Power need by EM: "+v.getElectricMotor().calculatedPower());
        System.out.println("--Batterie: capacité courante -->"+v.getBattery().getCurrentCapacity());
        double[] rang=v.getBattery().getRangePower(v.getElectricMotor().getActualPower());
        System.out.println("----Borne de puissance  -->"+rang[0]+" - "+rang[1]);
        if(this.v.getBattery().isNeedOfEnginePower()){
            this.v.getBattery().setCurrentCapacity(v.getElectricMotor().getActualPower(), v.getElectricMotor().getActualPower());
        }else{
            this.v.getBattery().setCurrentCapacity(v.getElectricMotor().getActualPower());
        }


        //modif next step
        this.inclines.push(this.getNewIncline(this.inclines.peek()));
        v.getElectricMotor().setIncline(this.inclines.peek());
        System.out.println("----------------------------------------");
    }

    //generation d'un nouvel angle du chemin
    public int getNewIncline(int previousIncline){
        int[] i = new int[3];
        i[0]=-20;
        i[1]=0;
        i[2]=20;
        if(previousIncline==0)
            return i[new Random().nextInt(3)];
        else if(previousIncline>0){
            return i[new Random().nextInt(2)+1];
        }
        else{
            return i[new Random().nextInt(2)];
        }
    }
    
    public static void main(String[] args) {
        Timer t = new Timer("TX52");
        t.schedule(new Tx52(),0,1000);
    }
}
