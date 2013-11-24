package utbm.p13.tx52.view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;

/**
 *
 * @author Gaut
 */
public class MainFrame extends JFrame {
    
    private JTabbedPane vehicleTabsPanel = new JTabbedPane();
    
    private List<AbstractHybridVehicle> vehicles;

    public MainFrame(String title, List<AbstractHybridVehicle> vehicles) throws HeadlessException {
        super(title);
        this.vehicles=vehicles;
        this.build();
    }
    
    private void build(){
        
        for(int i=1; i<=3/*vehicles.size()*/; i++){
            vehicleTabsPanel.addTab("Vehicle n°"+i,null,new VehiclePanel(),null);
        }
        
        this.vehicleTabsPanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        this.add(vehicleTabsPanel);
        
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null); 
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
}
