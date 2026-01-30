package pkg;

import java.awt.*;

public class FreeHand extends ColoredOval {

    public FreeHand(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        moveTo(x,y);
    }
    public void moveTo(int mouseX, int mouseY) {
        this.x = mouseX - (this.width / 2);
        this.y = mouseY - (this.height / 2);
    }
}
