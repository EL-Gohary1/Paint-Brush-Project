package pkg;

import java.awt.*;

public class ColoredRectangle extends Rectangle {

    private Color color;
    private boolean isFilled;
    private boolean isDotted;

    public ColoredRectangle(int x, int y, int width, int height, Color color, boolean isFilled, boolean isDotted){
        super(x, y, width, height);
        this.color = color;
        this.isDotted = isDotted;
        this.isFilled = isFilled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2d){
        float[] f= {4,5};
        if(isFilled && isDotted){
            g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10f, f, 0.0f));
            g2d.setColor(color);
            g2d.fillRect(x,y,width,height);
        }
        else if (isDotted) {
            g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10f,f, 0.0f));
            g2d.setColor(color);
            g2d.drawRect(x,y,width,height);
        } else if (isFilled) {
            g2d.setStroke(new BasicStroke());
            g2d.setColor(color);
            g2d.fillRect(x,y,width,height);
        }
        else{
            g2d.setStroke(new BasicStroke());
            g2d.setColor(color);
            g2d.drawRect(x,y,width,height);
        }
    }
}
