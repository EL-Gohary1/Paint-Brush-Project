package pkg;


import java.awt.*;
import java.awt.geom.Ellipse2D;


public class ColoredOval extends Ellipse2D.Double implements MyShape {
private Color c;
boolean isFilled;
boolean isDotted;

public ColoredOval(double  x,double  y,double  width,double height,Color color,boolean isFilled,boolean isDotted){
    super(x,y,width,height);
    c=color;
    this.isDotted = isDotted;
    this.isFilled = isFilled;
}
public void setColor(int r,int g,int b){
    c=new Color(r,g,b);

}
public Color getColor() {
    return c;
}
    public void draw(Graphics2D g2d){
        float[] f= {4,5};
        if(isFilled && isDotted){
            g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10f, f, 0.0f));
            g2d.setColor(c);
            g2d.fillOval((int) x, (int) y, (int) width, (int) height);
        }
        else if (isDotted) {
            g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10f,f, 0.0f));
            g2d.setColor(c);
            g2d.drawOval((int) x, (int) y, (int) width, (int) height);
        } else if (isFilled) {
            g2d.setStroke(new BasicStroke());
            g2d.setColor(c);
            g2d.fillOval((int) x, (int) y, (int) width, (int) height);
        }
        else{
            g2d.setStroke(new BasicStroke());
            g2d.setColor(c);
            g2d.drawOval((int) x, (int) y, (int) width, (int) height);
        }
    }


}
