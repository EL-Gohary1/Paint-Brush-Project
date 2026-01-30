package pkg;

import java.awt.*;

public class Eraser extends ColoredRectangle {

    public Eraser(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white);
    }

    public void moveTo(int mouseX, int mouseY) {
        this.x = mouseX - (this.width / 2);
        this.y = mouseY - (this.height / 2);
    }

}
