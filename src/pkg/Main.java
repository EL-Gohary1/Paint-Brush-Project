package pkg;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame4 = new JFrame();
        frame4.setTitle("Paint");
        frame4.setSize(500,500);
        frame4.setContentPane(new PaintWindow());
        frame4.setVisible(true);
    }
}