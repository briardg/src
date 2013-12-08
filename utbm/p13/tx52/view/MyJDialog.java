package utbm.p13.tx52.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Gaut
 */
public class MyJDialog extends JDialog implements Iview{

    private JFreeChart chart;
    private XYSeriesCollection dataset = new XYSeriesCollection();
    private XYSeries series; 
    private String titleOfGraphe="";
    
    public MyJDialog(String titleOfGraphe) {
        this.titleOfGraphe=titleOfGraphe;
        this.series = new XYSeries(titleOfGraphe);
        this.build();
    }

    @Override
    public void build() {
                
        // Add the series to your data set
        dataset.addSeries(series);
        // Generate the graph
        chart = ChartFactory.createXYLineChart(
        this.titleOfGraphe, // Title
        "Time in second", // x-axis Label
        this.titleOfGraphe, // y-axis Label
        dataset, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );

        series.addOrUpdate(0,0);
        this.add(new ChartPanel(chart));
                
        
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
    }
    
    public void addValue(double y){
        this.series.addOrUpdate(this.series.getItemCount(), y);
    }

    @Override
    public void update() {}
    
    
    
    
}
