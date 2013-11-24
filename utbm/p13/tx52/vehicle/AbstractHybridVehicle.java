package utbm.p13.tx52.vehicle;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utbm.p13.tx52.battery.AbstractBattery;
import utbm.p13.tx52.motor.ElectricMotor;
import utbm.p13.tx52.motor.Engine;

/**
 *
 * @author Gaut
 */
public class AbstractHybridVehicle implements Ivehicle{

    private static Integer index =1;
    protected String id="Vehicule number"+index++;
    protected Engine engine;
    protected AbstractBattery battery;
    protected ElectricMotor electricMotor;

    //rang of power than the batttery can give
    private double[] rangOfPower= new double[]{0,0};

    //power made in extra or missing (need engine or other vehicule)
    private double extraPower=0;
    private double missingPower=0;

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


    public AbstractBattery getBattery() {
        return battery;
    }

    public void setBattery(AbstractBattery battery) {
        this.battery = battery;
    }

    public ElectricMotor getElectricMotor() {
        return electricMotor;
    }

    public void setElectricMotor(ElectricMotor electricMotor) {
        this.electricMotor = electricMotor;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

        public double getExtraPower() {
        return extraPower;
    }

    public void setExtraPower(double extraPower) {
        this.extraPower = extraPower;
    }

    public double getMissingPower() {
        return missingPower;
    }

    public void setMissingPower(double missingPower) {
        this.missingPower = missingPower;
    }

    public void initGraph(int incline){
        JPanel panel= new JPanel(new GridLayout(0,1));

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

        panel.add(new ChartPanel(chartBattery));


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

        panel.add(new ChartPanel(chartPowerEM));

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

        panel.add(new ChartPanel(chartTank));

        JFrame mainFrame= new JFrame(id);
        mainFrame.add(new JScrollPane(panel));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void updateGraph(int nbBoucle,int incline){
        //new incline
        this.electricMotor.setIncline(incline);

        //ElectricMotor
        this.seriesPowerEM.addOrUpdate(nbBoucle, this.electricMotor.getCurrentPower());
        this.averageOfPower=((this.averageOfPower*nbBoucle)+this.electricMotor.getCurrentPower())/(nbBoucle+1);
        this.seriesAveragePowerEM.addOrUpdate(nbBoucle,averageOfPower );
        System.out.println("Power needs for ElecMOtor: "+this.electricMotor.getCurrentPower());
        System.out.println("Average Power:"+this.averageOfPower);

        //Battery
        this.rangOfPower=this.battery.getRangePower();
        System.out.println("\nBorne de puissance pour la prochaine vague  -->"+this.rangOfPower[0]+" - "+this.rangOfPower[1]);

        if(rangOfPower[0]>this.electricMotor.getCurrentPower()){
            this.extraPower=rangOfPower[0]-this.electricMotor.getCurrentPower();
            this.missingPower=0;
            System.out.println("Extra power="+this.extraPower);
            this.battery.setCurrentOutPower(rangOfPower[0]);
        }else if(rangOfPower[1]<this.electricMotor.getCurrentPower()){
            this.missingPower=this.electricMotor.getCurrentPower()-rangOfPower[1];
            this.extraPower=0;
            System.out.println("The power max of the battery will not be enough !");
            System.out.println("Missing power="+this.missingPower);
            this.battery.setCurrentOutPower(rangOfPower[1]);
        }else{
            this.extraPower=0;
            this.missingPower=0;
            System.out.println("Perfect rang!");
            this.battery.setCurrentOutPower(this.electricMotor.getCurrentPower());
        }

        //if we neeed power we are turn on the engine get the missing power
        // and put the reste in the extra power variable
        if(missingPower>0){
            this.engine.setTurnON(true);
            this.engine.updateTank();
            this.extraPower=this.engine.getOptimalPower()-missingPower;
            this.missingPower=0;
            if(this.extraPower<0){
                this.missingPower=this.extraPower;
                this.extraPower=0;
            }
        }


        //remove the classic utilisation
        this.battery.setCurrentCapacity();
        //put the extra power
        if(this.battery.isNeedOfPower()){
            if(this.extraPower>0){
                this.battery.setCurrentCapacity(this.extraPower);
            }
            if(!this.engine.isTurnON()){
                System.out.println("charging engine not turn on");
                this.engine.setTurnON(true);
                this.engine.updateTank();
                this.battery.setCurrentCapacity(this.engine.getOptimalPower());
            }
        }

        

        System.out.println("Battery current capacity: -->"+this.battery.getCurrentCapacity()+" Ah ="+this.battery.getPourcentageOfCharge()+"%");
        this.seriesBattery.addOrUpdate(nbBoucle, this.battery.getPourcentageOfCharge());

        //engine update graph
        this.engine.setTurnON(false);
        this.seriesTank.addOrUpdate(nbBoucle, this.engine.getTank()/this.engine.getWeightOfLiter());




    }

    

}