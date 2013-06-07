package utbm.p13.tx52.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author Gaut
 */
public class TrainOnTheRails extends Canvas {

    private LinkedList<Integer> lignes= new LinkedList<Integer>();
    private int startX;
    private int startY;
    private int widthCar=50;
    private int heightCar=25;
    private Rail rail= new Rail(1000, 200, 25);

    public TrainOnTheRails(LinkedList<Integer> ll) {
        this.lignes=ll;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.rail.draw(g);
//        Graphics2D g2d= (Graphics2D)g;
//        int height=this.getParent().getHeight();
//        int width=this.getParent().getWidth();
//
//      //  g2d.setColor(Color.WHITE);
//      //  g2d.fillRect(0, 0, this.width,this.height);
//        g2d.setColor(Color.red);
//
//        this.startX=0;
//        this.startY=height/2;
//
//        for (int i = 0; i < lignes.size(); i++) {
//
//            int finalY=(int)Math.sqrt(Math.pow(100/Math.cos(Math.toRadians(this.lignes.get(i))),2)-Math.pow(100,2));
//            if(this.lignes.get(i)>0){
//                finalY=this.startY-finalY;
//            }else{
//                finalY=this.startY+finalY;
//            }
//            System.out.println(this.startX+","+this.startY+","+(width/5)*(i+1)+","+finalY);
//            g2d.drawLine(this.startX,this.startY, (width/5)*(i+1), finalY);
//
//            this.startX=(width/5)*(i+1);
//            this.startY=finalY;
//        }
//        g2d.setColor(Color.BLUE);
//        g2d.fillRect((width/5-this.widthCar)/2,height/2-this.heightCar,this.widthCar,this.heightCar);

    }

    public LinkedList<Integer> getLignes() {
        return lignes;
    }

    public void setLignes(LinkedList<Integer> lignes) {
        this.lignes = lignes;
    }



}
