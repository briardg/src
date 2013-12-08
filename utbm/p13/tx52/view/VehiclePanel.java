package utbm.p13.tx52.view;

import com.sun.xml.internal.bind.v2.TODO;
import java.awt.GridLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;

/**
 *
 * @author Gaut
 */
public class VehiclePanel extends JPanel implements Iview{

    private AbstractHybridVehicle v;
    
    
    //Representation of vehicle data by Graph

    private JFreeChart chartBattery;
    private XYSeriesCollection datasetBattery = new XYSeriesCollection();
    private XYSeries seriesBattery = new XYSeries("Battery");


    private JFreeChart chartPowerEM;
    private XYSeriesCollection datasetPowerEM = new XYSeriesCollection();
    private XYSeries seriesPowerEM = new XYSeries("PowerEM");
    private XYSeries seriesAveragePowerEM = new XYSeries("Average PowerEM");
    private double averageOfPower=0;

    private JFreeChart chartTank;
    private XYSeriesCollection datasetTank = new XYSeriesCollection();
    private XYSeries seriesTank = new XYSeries("Tank");
    
    
    
    public VehiclePanel(){
        super(new GridLayout(0,1));
        this.build();
    }
    
    public VehiclePanel(AbstractHybridVehicle v){
        super(new GridLayout(0,1));
        this.v=v;
        this.build();
    }
    
    @Override
    public void build(){
        

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

        this.add(new ChartPanel(chartBattery));


        // Add the series to your data set
        datasetPowerEM.addSeries(seriesPowerEM);
        datasetPowerEM.addSeries(seriesAveragePowerEM);
        // Generate the graph
        chartPowerEM = ChartFactory.createXYLineChart(
        "Power EM", // Title
        "Time in second", // x-axis Label
        "Power in kW", // y-axis Label
        datasetPowerEM, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );

        this.add(new ChartPanel(chartPowerEM));

        // Add the series to your data set
        datasetTank.addSeries(seriesTank);
        // Generate the graph
        chartTank = ChartFactory.createXYLineChart(
        "Tank Quantity", // Title
        "Time in second", // x-axis Label
        "Tank Quantity in Liter", // y-axis Label
        datasetTank, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );

        this.add(new ChartPanel(chartTank));
        
    }
    
    @Override
    public void update(){
        this.seriesBattery.add(this.seriesBattery.getItemCount(), this.v.getBattery().getPourcentageOfCharge());
        this.seriesTank.add(this.seriesTank.getItemCount(), this.v.getEngine().getTank());
        this.seriesPowerEM.add(this.seriesPowerEM.getItemCount(), this.v.getElectricMotor().getCurrentPower());
        //TODO : checked for average usefull or not
        //this.averageOfPower=((this.averageOfPower*nbBoucle)+this.electricMotor.getCurrentPower())/(nbBoucle+1);
        //this.seriesAveragePowerEM.addOrUpdate(nbBoucle,averageOfPower );
    }
}
