package utbm.p13.tx52.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Gaut
 */
public class Battery extends JComponent{

    //pourcentage de batterie
    private int charge=100;

    private int x=5;
    private int y=5;
    private int height=50;
    private int width= 25;

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(this.x, this.y, this.width, this.height);

        if(this.charge>50)
            g.setColor(Color.GREEN);
        else if(this.charge > 30)
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.RED);

        g.fillRect(this.x+1, this.y+1+this.height-(this.height*charge/100), this.width-1,(this.height*charge/100)-1);
    }

    public Battery() {

    }

    public Battery(int x, int y, int height, int width) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=width;

    }

    public void setCharge(int charge){
        this.charge=charge;
    }

}
