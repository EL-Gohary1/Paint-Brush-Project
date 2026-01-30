package pkg;


import java.awt.*;
import java.awt.geom.Ellipse2D;


public class ColoredOval extends Ellipse2D.Double {
private Color c;

public ColoredOval(double  x,double  y,double  width,double height,Color color){
    super(x,y,width,height);
    c=color;
}
public void setColor(int r,int g,int b){
    c=new Color(r,g,b);

}
public Color getColor() {
    return c;
}


}
