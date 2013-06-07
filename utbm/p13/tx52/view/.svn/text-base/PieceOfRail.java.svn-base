package utbm.p13.tx52.view;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gaut
 */
public class PieceOfRail implements IDrawable{

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int incline;
    private Color c;

    public PieceOfRail(int width,int distanceFromTop){
        this.startX=0;
        this.startY=distanceFromTop;
        this.endX=width;
        this.endY=distanceFromTop;
        c=Color.RED;
    }

    public PieceOfRail(int sX,int sY,int width,int incline){
        this.startX=sX;
        this.startY=sY;
        this.incline=incline;
        this.endX=(int)(this.startX+(Math.cos(Math.toRadians(incline))*width));
        int endy=(int)Math.sqrt(Math.pow(width,2)-Math.pow(this.endX-this.startX, 2));
        if(incline>0){
                this.endY=this.startY-endy;
        }else{
            this.endY=this.startY+endy;
        }
    }

    public void draw(Graphics g) {
        System.out.println("draw piece of rail:"+startX+"-"+startY+"-"+endX+"-"+endY+"-"+incline);
        g.setColor(this.c);
        g.drawLine(this.startX,this.startY, this.endX,this.endY);
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getIncline() {
        return incline;
    }

    
}
