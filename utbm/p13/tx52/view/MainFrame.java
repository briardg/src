package utbm.p13.tx52.view;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;

/**
 *
 * @author Gaut
 */
public class MainFrame extends JFrame implements Iview{
    
    private JTabbedPane vehicleTabsPanel = new JTabbedPane();
    private MyJDialog torqueJD= new MyJDialog("Torque");
    
    private List<AbstractHybridVehicle> vehicles;

    public MainFrame(String title, List<AbstractHybridVehicle> vehicles) throws HeadlessException {
        super(title);
        this.vehicles=vehicles;
        this.build();
    }
    
    @Override
    public void build(){
        
        for(int i=0; i<vehicles.size(); i++){
            this.vehicleTabsPanel.addTab("Vehicle n°"+(i+1),null,new VehiclePanel(vehicles.get(i)),null);
        }
        this.vehicleTabsPanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        
        this.add(vehicleTabsPanel);
        torqueJD.setVisible(true);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null); 
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    @Override
    public void update() {
        for(int i=0; i<this.vehicleTabsPanel.getTabCount();i++){
            ((VehiclePanel)this.vehicleTabsPanel.getComponentAt(i)).update();
        }
       
    }
    
}
