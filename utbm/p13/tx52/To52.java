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

    private List<AbstractHybridVehicle> vList = new ArrayList<>();
    
    private Servers servers;
    
    private MainFrame frame ;
    

    public To52() {
        
        for(int i=0;i<4;i++){
            this.vList.add(new SeriesHybridVehicle());
        }
        
        this.servers = new Servers(vList);
        this.servers.start();
        
        this.frame= new MainFrame("TO52", vList);
        this.frame.setVisible(true);
    }

    @Override
    public void run() {
        
        for(AbstractHybridVehicle v: this.vList)
            v.update();
        
        //update all vehicle graph
        frame.update();

    }
    
    public static void main(String[] args) {
        Timer t = new Timer("T052");
        t.schedule(new To52(),0,1);
    }
}
