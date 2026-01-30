package pkg;

import java.awt.*;
import java.awt.geom.Line2D;


public class ColoredLine extends Line2D.Double implements MyShape{
    private Color c;
    private boolean isDotted;

    public ColoredLine(double x1,double y1,double x2,double y2,Color color,boolean isDotted){
        super(x1,y1,x2,y2);
        c=color;
        this.isDotted = isDotted;
    }
    public void setColor(int r,int g,int b){
        c=new Color(r,g,b);

    }
    public Color getColor() {
        return c;
    }
    public void draw(Graphics2D g2d){
        float[] f= {4,5};
         if (isDotted) {
            g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10f,f, 0.0f));
            g2d.setColor(c);
            g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
        else{
            g2d.setStroke(new BasicStroke());
            g2d.setColor(c);
             g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }
}
