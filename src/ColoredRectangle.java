import java.awt.*;

public class ColoredRectangle extends Rectangle {

    private Color color;

    public ColoredRectangle(int x, int y, int width, int height, Color color){
        super(x, y, width, height);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
