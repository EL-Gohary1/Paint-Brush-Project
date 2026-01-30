package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class PaintWindow extends JPanel {

    private JButton blue;
    private JButton black;
    private JButton rect;
    private JButton oval;
    private JButton line;
    private JButton eraser;
    private JButton pencil;
    private JCheckBox checkFill;
    private JCheckBox checkDotted;

    Vector<ColoredRectangle> rectangles;
    Vector<ColoredOval> ovals;
    Vector<ColoredLine> lines;
    Vector<Eraser> erases;
    Vector<FreeHand> freeHands;

    private Color currentColor;
    //private Object currentShape;
    private enum Tool {
        RECTANGLE,
        OVAL,
        LINE,
        PENCIL,
        ERASER
    }
    Tool current_Tool;
    boolean isDotted ;
    boolean isFilled;
    Point startPoint;
    Point endPoint;


    public PaintWindow(){

        blue = new JButton("blue");
        black = new JButton("black");
        rect = new JButton("rect");
        oval = new JButton("oval");
        line = new JButton("line");
        pencil = new JButton("pencil");
        eraser = new JButton("eraser");
        checkFill = new JCheckBox("isFill");
        checkDotted = new JCheckBox("isDotted");
        rectangles = new Vector<>();
        ovals = new Vector<>();
        lines = new Vector<>();
        erases = new Vector<>();
        freeHands = new Vector<>();
        startPoint = new Point(0,0);
        endPoint = new Point(0,0);
        current_Tool = Tool.RECTANGLE;
        currentColor=Color.black;
        isDotted = false;
        isFilled = false;
        this.setBackground(Color.white);

        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.BLUE;
            }
        });
        this.add(blue);

        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.BLACK;
            }
        });
        this.add(black);

        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.OVAL;
            }
        });
        this.add(oval);

        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.LINE;
            }
        });
        this.add(line);

        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.ERASER;
            }
        });
        this.add(eraser);

        rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.RECTANGLE;
            }
        });
        this.add(rect);

        pencil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_Tool=Tool.PENCIL;
            }
        });
        this.add(pencil);

        checkFill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFilled=!isFilled;
            }
        });
        this.add(checkFill);

        checkDotted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDotted=!isDotted;
            }
        });
        this.add(checkDotted);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                switch(current_Tool){
                    case OVAL:
                        ColoredOval o = new ColoredOval(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor,isFilled,isDotted);
                        ovals.add(o);
                        break;
                    case RECTANGLE:
                        ColoredRectangle r = new ColoredRectangle(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor,isFilled,isDotted);
                        rectangles.add(r);
                        break;
                    case LINE:
                        ColoredLine l = new ColoredLine(startPoint.x, startPoint.y,endPoint.x,endPoint.y,currentColor,isDotted);
                        lines.add(l);
                        break;
                    case PENCIL:
                        FreeHand f = new FreeHand(startPoint.x, startPoint.y, 50, 50, currentColor);
                        freeHands.add(f);
                    break;
                    case ERASER:
                        Eraser er = new Eraser(startPoint.x, startPoint.y, 50, 50);
                        erases.add(er);
                        break;
                }
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                if(current_Tool==Tool.PENCIL){
                    FreeHand f = new FreeHand(endPoint.x, endPoint.y, 50, 50, currentColor);
                    freeHands.add(f);
                }
                if(current_Tool==Tool.ERASER){
                    Eraser er = new Eraser(endPoint.x, endPoint.y, 50, 50);
                    erases.add(er);
                }
                repaint();
            }
        });

    }

    public void draw(Graphics2D g2d){
        switch(current_Tool){
            case OVAL:
                ColoredOval o = new ColoredOval(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor, isFilled,isDotted);
                o.draw(g2d);
            break;
            case RECTANGLE:
                ColoredRectangle r = new ColoredRectangle(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor, isFilled,isDotted);
                r.draw(g2d);
            break;
            case LINE:
                ColoredLine l = new ColoredLine(startPoint.x, startPoint.y,endPoint.x,endPoint.y,currentColor,isDotted);
                l.draw(g2d);
            break;
            case PENCIL:
               FreeHand f = new FreeHand(startPoint.x, startPoint.y, 50, 50, currentColor);
               g2d.setColor(f.getColor());
               g2d.fillOval((int) f.x, (int) f.y, (int) f.width, (int) f.height);
            break;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
        for (Eraser er : erases) {
            er.draw(g2d);
        }
        for (ColoredRectangle r : rectangles) {
            r.draw(g2d);
        }
        for (ColoredOval o : ovals) {
            o.draw(g2d);
        }
        for (ColoredLine l : lines) {
            l.draw(g2d);
        }
        for (FreeHand f : freeHands) {
            f.draw(g2d);
        }



    }

}
