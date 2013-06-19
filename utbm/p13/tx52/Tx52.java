package utbm.p13.tx52;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.Day;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utbm.p13.tx52.vehicle.SeriesHybridVehicule;
import utbm.p13.tx52.view.Battery;

/**
 *
 * @author Gaut
 */
public class Tx52 extends TimerTask{

    SeriesHybridVehicule v = new SeriesHybridVehicule();
    LinkedList<Integer> inclines= new LinkedList<Integer>();
    Battery b= new Battery();

    JFrame JFBattery = new JFrame("Batttery");
    JFreeChart chartBattery;
    XYSeriesCollection datasetBattery = new XYSeriesCollection();
    XYSeries seriesBattery = new XYSeries("Battery");

    JFrame JFPowerEM = new JFrame("PowerEM");
    JFreeChart chartPowerEM;
    XYSeriesCollection datasetPowerEM = new XYSeriesCollection();
    XYSeries seriesPowerEM = new XYSeries("PowerEM");

    JFrame JFIncline = new JFrame("Incline");
    JFreeChart chartIncline;
    XYSeriesCollection datasetIncline = new XYSeriesCollection();
    XYSeries seriesIncline = new XYSeries("Incline");

    double[] rang= new double[]{0,0};
    double extraPower=0;
    double missingPower=0;
    int nbBoucle=0;

    public Tx52() {
        this.inclines.push(0);
        v.getElectricMotor().setIncline(0);

        // Add the series to your data set        
        datasetBattery.addSeries(seriesBattery);
        // Generate the graph
        chartBattery = ChartFactory.createXYLineChart(
        "Battery", // Title
        "Time in second", // x-axis Label
        "Power in %", // y-axis Label
        datasetBattery, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );

        JFBattery.setVisible(true);
        JFBattery.setSize(1000,500);
        JFBattery.add(new ChartPanel(chartBattery));

        
        // Add the series to your data set
        datasetPowerEM.addSeries(seriesPowerEM);
        // Generate the graph
        chartPowerEM = ChartFactory.createXYLineChart(
        "Power EM", // Title
        "Time in second", // x-axis Label
        "Power in %", // y-axis Label
        datasetPowerEM, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );

        JFPowerEM.setVisible(true);
        JFPowerEM.setSize(1000,500);
        JFPowerEM.add(new ChartPanel(chartPowerEM));

        // Add the series to your data set
        datasetIncline.addSeries(seriesIncline);
        // Generate the graph
        chartIncline = ChartFactory.createXYLineChart(
        "Incline", // Title
        "Time in second", // x-axis Label
        "Incline", // y-axis Label
        datasetIncline, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );

        seriesIncline.addOrUpdate(0,0);

        JFIncline.setVisible(true);
        JFIncline.setSize(1000,500);
        JFIncline.add(new ChartPanel(chartIncline));
    }

    @Override
    public void run() {
        System.out.print("Incline: "+this.inclines.peek()+"° --> ");
        System.out.println("$--$"+seriesIncline.getY(seriesIncline.getItemCount()-1));
        if(this.inclines.peek()==0)
            seriesIncline.addOrUpdate(nbBoucle,seriesIncline.getY(seriesIncline.getItemCount()-1));
        if(this.inclines.peek()>0)
            seriesIncline.addOrUpdate(nbBoucle,((Double)seriesIncline.getY(seriesIncline.getItemCount()-1))+this.inclines.peek());
        if(this.inclines.peek()<0)
            seriesIncline.addOrUpdate(nbBoucle,((Double)seriesIncline.getY(seriesIncline.getItemCount()-1))+this.inclines.peek());

        System.out.println("Power needs for ElecMOtor: "+v.getElectricMotor().getCurrentPower());
        
        seriesPowerEM.addOrUpdate(nbBoucle, v.getElectricMotor().getCurrentPower());

        this.rang=v.getBattery().getRangePower(v.getElectricMotor().getCurrentPower());
        System.out.println("\nBorne de puissance pour la prochaine vague  -->"+this.rang[0]+" - "+this.rang[1]);

        if(rang[0]>v.getElectricMotor().getCurrentPower()){
            this.extraPower=rang[0]-v.getElectricMotor().getCurrentPower();
            System.out.println("Extra power="+this.extraPower);
            v.getBattery().setCurrentOutPower(rang[0]);
        }else if(rang[1]<v.getElectricMotor().getCurrentPower()){
            this.missingPower=v.getElectricMotor().getCurrentPower()-rang[1];
            System.out.println("The power mac of the battery will not be enough !");
            System.out.println("Missing power="+this.missingPower);
            v.getBattery().setCurrentOutPower(rang[1]);
        }else{
            System.out.println("Perfect rang!");
            v.getBattery().setCurrentOutPower(v.getElectricMotor().getCurrentPower());
        }


        System.out.println("Battery current capacity: -->"+v.getBattery().getCurrentCapacity()+" ="+this.v.getBattery().getPourcentageOfCharge()+"%");

        seriesBattery.addOrUpdate(nbBoucle, this.v.getBattery().getPourcentageOfCharge());

        if(this.v.getBattery().isNeedOfPower()){
            System.out.println("Need EnginePower");
            this.v.getBattery().setCurrentCapacity(v.getElectricMotor().getCurrentPower()+2000, v.getElectricMotor().getCurrentPower());
        }else{
            System.out.println("No Need EnginePower");
            this.v.getBattery().setCurrentCapacity(v.getElectricMotor().getCurrentPower());
        }



        System.out.println("----------------------------------------");
        //modif next step
        this.inclines.push(this.getNewIncline(this.inclines.peek()));
        v.getElectricMotor().setIncline(this.inclines.peek());


        nbBoucle++;
    }

    //generation d'un nouvel angle du chemin
    public int getNewIncline(int previousIncline){
        int[] i = new int[3];
        i[0]=-30;
        i[1]=0;
        i[2]=30;
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
        Date now= new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("ss");
        while(Integer.parseInt(sdf.format(new Date()))!=Integer.parseInt(sdf.format(now))-1 ){}
        t.cancel();
    }
}
