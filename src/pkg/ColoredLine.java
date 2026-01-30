package pkg;

import java.awt.*;
import java.awt.geom.Line2D;


public class ColoredLine extends Line2D.Double {
    private Color c;

    public ColoredLine(double x1,double y1,double x2,double y2,Color color){
        super(x1,y1,x2,y2);
        c=color;
    }
    public void setColor(int r,int g,int b){
        c=new Color(r,g,b);

    }
    public Color getColor() {
        return c;
    }
}
