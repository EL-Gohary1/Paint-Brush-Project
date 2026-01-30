package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintWindow extends JPanel {

    private JButton blue;
    private JButton black;
    private JButton oval;
    private JButton line;
    private JButton eraser;
    private Color currentColor;
    private Shape currentShape;

    private enum Tool {
        RECTANGLE,
        OVAL,
        LINE,
        PENCIL,
        ERASER
    }
    Tool current_Tool;

    public PaintWindow(){

        blue = new JButton("blue");
        black = new JButton("black");
        oval = new JButton("oval");
        line = new JButton("line");
        eraser = new JButton("eraser");

        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.BLUE;
            }
        });

        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.BLUE;
            }
        });

        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.OVAL;
            }
        });

        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.LINE;
            }
        });

        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.ERASER;
            }
        });


    }

}
