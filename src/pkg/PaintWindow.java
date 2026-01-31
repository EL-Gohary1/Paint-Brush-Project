package pkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class PaintWindow extends JPanel {

    private JButton blue;
    private JButton black;
    private JButton green;
    private JButton red;
    private JButton rect;
    private JButton oval;
    private JButton line;
    private JButton eraser;
    private JButton pencil;
    private JCheckBox checkFill;
    private JCheckBox checkDotted;
    private JButton save;
    private JButton open;
    private JButton undo;
    private JButton clearAll;
    BufferedImage myImage;

    Vector<MyShape> myShapes;

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
        red = new JButton("red");
        green = new JButton("green");
        rect = new JButton("rect");
        oval = new JButton("oval");
        line = new JButton("line");
        pencil = new JButton("pencil");
        eraser = new JButton("eraser");
        checkFill = new JCheckBox("isFill");
        checkDotted = new JCheckBox("isDotted");
        clearAll=new JButton("clearAll");
        undo=new JButton("Undo");
        save = new JButton("save");
        open = new JButton("open");

        myShapes=new Vector<>();
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

        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.GREEN;
            }

        });
        this.add(green);

        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.RED;
            }

        });
        this.add(red);

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

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myShapes.clear();
                startPoint = null;
                endPoint = null;
                repaint();
            }
        });
        this.add(clearAll);

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myShapes.removeLast();
                startPoint = null;
                endPoint = null;
                repaint();
            }
        });
        this.add(undo);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    BufferedImage bImage = new BufferedImage(1500, 700, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = bImage.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0,1500, 700);
                    for (MyShape shape : myShapes) {
                        shape.draw(g2d);
                    }
                    try {
                        ImageIO.write(bImage, "png", new File("myshape.png"));
                        System.out.println("Saved Successfully!");
                    } catch (IOException b) {
                        b.printStackTrace();
                    }
                }
            });
        this.add(save);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        BufferedImage importedImage = ImageIO.read(selectedFile);
                        myImage = new BufferedImage(
                                importedImage.getWidth(),
                                importedImage.getHeight(),
                                BufferedImage.TYPE_INT_ARGB
                        );
                        Graphics2D g2d = myImage.createGraphics();
                        g2d.drawImage(importedImage, 0, 0, null);
                        g2d.dispose();
                        repaint();
                    } catch (IOException b) {
                        b.printStackTrace();
                    }
                }
            }
        });
        this.add(open);

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
                        myShapes.add(o);
                        break;
                    case RECTANGLE:
                        ColoredRectangle r = new ColoredRectangle(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor,isFilled,isDotted);
                        myShapes.add(r);
                        break;
                    case LINE:
                        ColoredLine l = new ColoredLine(startPoint.x, startPoint.y,endPoint.x,endPoint.y,currentColor,isDotted);
                        myShapes.add(l);
                        break;
                    case PENCIL:
                        FreeHand f = new FreeHand(startPoint.x, startPoint.y, 50, 50, currentColor);
                        myShapes.add(f);
                    break;
                    case ERASER:
                        Eraser er = new Eraser(startPoint.x, startPoint.y, 50, 50);
                        myShapes.add(er);
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
                    myShapes.add(f);
                }
                if(current_Tool==Tool.ERASER){
                    Eraser er = new Eraser(endPoint.x, endPoint.y, 50, 50);
                    myShapes.add(er);
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
        if (myImage != null) {
            g2d.drawImage(myImage, 0, 0, null);
        }
        for(MyShape shape: myShapes){
            shape.draw(g2d);
        }
        if(startPoint != null && endPoint != null) {
            draw(g2d);
        }
    }

}
