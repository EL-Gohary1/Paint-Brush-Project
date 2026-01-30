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

                        break;
                    case RECTANGLE:
                        ColoredRectangle r = new ColoredRectangle(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor);
                        rectangles.add(r);
                        break;
                    case LINE:
                        ColoredLine l = new ColoredLine(startPoint.x, startPoint.y,endPoint.x,endPoint.y,currentColor);
                        lines.add(l);
                        break;
                    case PENCIL:
                        FreeHand f = new FreeHand(startPoint.x, startPoint.y, 50, 50, currentColor);
                        freeHands.add(f);
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
                repaint();
            }
        });

    }

    public void draw(Graphics2D g2d){
        switch(current_Tool){
            case OVAL:

            break;
            case RECTANGLE:
                ColoredRectangle r = new ColoredRectangle(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor);
                g2d.setColor(r.getColor());
                g2d.drawRect(r.x, r.y, r.width, r.height);
            break;
            case LINE:
                ColoredLine l = new ColoredLine(startPoint.x, startPoint.y,endPoint.x,endPoint.y,currentColor);
                g2d.setColor(l.getColor());
                g2d.drawLine((int) l.x1, (int) l.y1, (int) l.x2, (int) l.y2);
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
        for (ColoredRectangle r : rectangles) {
            g2d.setColor(r.getColor());
            g2d.drawRect(r.x, r.y, r.width, r.height);
        }
        for (ColoredOval o : ovals) {
            g2d.setColor(o.getColor());
            g2d.drawOval((int) o.x, (int) o.y, (int) o.width, (int) o.height);
        }
        for (ColoredLine l : lines) {
            g2d.setColor(l.getColor());
            g2d.drawLine((int) l.x1, (int) l.y1, (int) l.x2, (int) l.y2);
        }
        for (FreeHand f : freeHands) {
            g2d.setColor(f.getColor());
            g2d.fillOval((int) f.x, (int) f.y, (int) f.width, (int) f.height);
        }


    }

}
