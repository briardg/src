package utbm.p13.tx52.view;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Gaut
 */
public class Rail implements IDrawable {

    private LinkedList<PieceOfRail> listOfPieceOfRail = new LinkedList<PieceOfRail>();
    private int width;
    private int numberOfPieceOfRail;
    private int distanceFromTop;

    public Rail(int width,int distanceFromTop, int numberOfPieceOfRail){
        this.width=width;
        this.distanceFromTop=distanceFromTop;
        this.numberOfPieceOfRail=numberOfPieceOfRail;

        PieceOfRail previousPieceOfRail=new PieceOfRail(width/numberOfPieceOfRail,distanceFromTop);
        this.listOfPieceOfRail.add(previousPieceOfRail);
        for (int i = 1; i < numberOfPieceOfRail; i++) {
            previousPieceOfRail=new PieceOfRail(previousPieceOfRail.getEndX(),previousPieceOfRail.getEndY(),width/numberOfPieceOfRail, getNextIncline(previousPieceOfRail.getIncline()));
            this.listOfPieceOfRail.add(previousPieceOfRail);
        }
    }

    public void draw(Graphics g) {
        System.out.println("draw rail");
        for(int i=0; i<this.numberOfPieceOfRail; i++){
            this.listOfPieceOfRail.get(i).draw(g);
        }
    }

    private static int getNextIncline(int previousIncline){
        int[] v = new int[3];
        v[0]=-20;
        v[1]=0;
        v[2]=20;
        if(previousIncline==0)
            return v[new Random().nextInt(3)];
        else if(previousIncline>0){
            return v[new Random().nextInt(2)+1];
        }
        else{
            return v[new Random().nextInt(2)];
        }
    }

}
