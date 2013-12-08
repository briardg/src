package utbm.p13.tx52;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;
import utbm.p13.tx52.vehicle.SeriesHybridVehicle;
import utbm.p13.tx52.view.MainFrame;


/**
 *
 * @author Gaut
 */
public class To52 extends TimerTask{

    SeriesHybridVehicle v1 = new SeriesHybridVehicle();
    SeriesHybridVehicle v2 = new SeriesHybridVehicle();
    SeriesHybridVehicle v3 = new SeriesHybridVehicle();
    
    List<AbstractHybridVehicle> vList = new ArrayList<>();
    
    private MainFrame frame ;
    
    private int nbBoucle=0;

    public To52() {
        vList.add(v1);
        vList.add(v2);
        vList.add(v3);
        frame= new MainFrame("TO52", vList);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        
        //Graph vehicules
        this.v1.updateGraph(nbBoucle,30);
        this.v2.updateGraph(nbBoucle,30);
        this.v3.updateGraph(nbBoucle,30);
        
        frame.update();
        
        nbBoucle++;
    }
    
    public static void main(String[] args) {
        Timer t = new Timer("TX52");
        t.schedule(new To52(),0,10);
    }
}
