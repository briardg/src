package utbm.p13.tx52;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utbm.p13.tx52.vehicle.SeriesHybridVehicule;
import utbm.p13.tx52.view.Battery;

/**
 *
 * @author Gaut
 */
public class Tx52 extends TimerTask{

    SeriesHybridVehicule v1 = new SeriesHybridVehicule();
    SeriesHybridVehicule v2 = new SeriesHybridVehicule();
    SeriesHybridVehicule v3 = new SeriesHybridVehicule();
    LinkedList<Integer> inclines= new LinkedList<Integer>();
    Battery b= new Battery();


    JFrame JFIncline = new JFrame("Incline");
    JFreeChart chartIncline;
    XYSeriesCollection datasetIncline = new XYSeriesCollection();
    XYSeries seriesIncline = new XYSeries("Incline");

    int nbBoucle=0;

    double extraPower=0;

    public Tx52() {
        this.v1.initGraph(0);
        this.v2.initGraph(0);
        this.v3.initGraph(0);

        this.inclines.push(0);
        this.inclines.push(0);
        this.inclines.push(0);


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
        JFIncline.add(new ChartPanel(chartIncline));
        JFIncline.pack();
    }

    @Override
    public void run() {

        //incline
        System.out.print("Incline: "+this.inclines.peek()+"° --> ");

        if(this.inclines.peek()==0)
            seriesIncline.addOrUpdate(nbBoucle,seriesIncline.getY(seriesIncline.getItemCount()-1));
        if(this.inclines.peek()>0)
            seriesIncline.addOrUpdate(nbBoucle,((Double)seriesIncline.getY(seriesIncline.getItemCount()-1))+this.inclines.peek());
        if(this.inclines.peek()<0)
            seriesIncline.addOrUpdate(nbBoucle,((Double)seriesIncline.getY(seriesIncline.getItemCount()-1))+this.inclines.peek());



        //Graph vehicules
        this.v1.updateGraph(nbBoucle,this.inclines.get(0));
        this.v2.updateGraph(nbBoucle,this.inclines.get(1));
        this.v3.updateGraph(nbBoucle,this.inclines.get(2));


        //pseudo communication inter-vehicule
        this.extraPower=this.v1.getExtraPower()+this.v2.getExtraPower()+this.v3.getExtraPower();
        System.out.println("***********"+extraPower);
        if(this.v1.getMissingPower()>0){
            if(this.v1.getMissingPower()<=this.extraPower){
                this.extraPower-=this.v1.getMissingPower();
                this.v1.setMissingPower(0);
            }else{
                this.v1.setMissingPower(this.v1.getMissingPower()-this.extraPower);
                this.extraPower=0;
            }
        }
        if(this.v2.getMissingPower()>0 && this.extraPower>0){
            if(this.v2.getMissingPower()<=this.extraPower){
                this.extraPower-=this.v2.getMissingPower();
                this.v2.setMissingPower(0);
            }else{
                this.v2.setMissingPower(this.v2.getMissingPower()-this.extraPower);
                this.extraPower=0;
            }
        }
        if(this.v3.getMissingPower()>0 && this.extraPower>0){
            if(this.v3.getMissingPower()<=this.extraPower){
                this.extraPower-=this.v3.getMissingPower();
                this.v3.setMissingPower(0);
            }else{
                this.v2.setMissingPower(this.v3.getMissingPower()-this.extraPower);
                this.extraPower=0;
            }
        }

        System.out.println("***********"+extraPower);

        System.out.println("----------------------------------------");
        //modif next step
        this.inclines.push(this.getNewIncline(this.inclines.peek()));

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
    }
}
