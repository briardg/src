package utbm.p13.tx52;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;
import utbm.p13.tx52.view.MainFrame;

/**
 *
 * @author Gaut
 */
public class T052ViewTest {
    public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				MainFrame frame = new MainFrame("TX-TO Project", new ArrayList<AbstractHybridVehicle>());
				frame.setVisible(true);
			}
		});
	}
}
