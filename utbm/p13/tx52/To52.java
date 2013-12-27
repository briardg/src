package utbm.p13.tx52;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import utbm.p13.tx52.connection.Servers;
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;
import utbm.p13.tx52.vehicle.SeriesHybridVehicle;
import utbm.p13.tx52.view.MainFrame;


/**
 *
 * @author Gaut
 */
public class To52 extends TimerTask{

    private SeriesHybridVehicle v1 = new SeriesHybridVehicle();
    private SeriesHybridVehicle v2 = new SeriesHybridVehicle();
    private SeriesHybridVehicle v3 = new SeriesHybridVehicle();
    private List<AbstractHybridVehicle> vList = new ArrayList<>();
    
    private Servers servers;
    
    private MainFrame frame ;
    
    private int nbBoucle=0;

    public To52() {
        this.vList.add(v1);
        this.vList.add(v2);
        this.vList.add(v3);
        
        this.servers = new Servers(vList);
        this.servers.start();
        
        this.frame= new MainFrame("TO52", vList);
        this.frame.setVisible(true);
    }

    @Override
    public void run() {
        
        this.v1.update();
        this.v2.update();
        this.v3.update();
        
        //update all vehicle graph
        frame.update();
        
        nbBoucle++;
    }
    
    public static void main(String[] args) {
        Timer t = new Timer("TX52");
        t.schedule(new To52(),0,1);
    }
}
