# Paint-Brush-Project
A Java Paint application built with OOP principles includes a modular design for shapes, real-time mouse interaction, and advanced features.
Overview

Paint-Brush-Project is a small Java Swing paint application (package pkg) that demonstrates object-oriented design for drawable shapes, real-time mouse interaction (press/drag/release), and a simple tool palette (rectangle, oval, line, pencil/freehand, eraser, colors, dotted/fill, save/open/undo/clear).
The app stores drawn objects in a collection (Vector<MyShape>) and repaints them each frame; preview drawing is shown while the user is dragging.
Main source files (what they do)

src/pkg/Main.java — entry point (launches the Swing window).
src/pkg/PaintWindow.java — the main JPanel with UI buttons, tool state, event listeners (mouse and action), the shape list, saving/loading and paint logic.
src/pkg/MyShape.java — shape API used by the app (shapes implement a draw(Graphics2D) method).
src/pkg/ColoredRectangle.java — rectangle shape implementation (fill / dotted / stroke).
src/pkg/ColoredOval.java — oval shape implementation (fill / dotted).
src/pkg/ColoredLine.java — line shape implementation (dotted).
src/pkg/FreeHand.java — pencil/brush: small ovals added continuously while dragging (used for freehand drawing).
src/pkg/Eraser.java — an “eraser” shape that draws white-filled ovals to cover content.
Key implementation notes (based on the repository code)

Tool & color state

PaintWindow keeps an enum Tool { RECTANGLE, OVAL, LINE, PENCIL, ERASER } and fields current_Tool, currentColor, isDotted, isFilled.
Buttons set tool and color by simple ActionListeners.
Mouse interaction lifecycle (press / drag / release)

mousePressed stores startPoint = e.getPoint();
mouseReleased stores endPoint = e.getPoint(), then creates a shape instance according to current_Tool and appends it to myShapes; finally repaint() is called.
mouseDragged is used for continuous tools (PENCIL and ERASER) — it creates and appends many small FreeHand or Eraser shapes while dragging, so the path is approximated with a sequence of small ovals.
Example: the switch that creates shapes on mouse release (excerpt)

EL-Gohary1 / Paint-Brush-Project / src / pkg / PaintWindow.java
v1
                endPoint = e.getPoint();
                switch(current_Tool){
                    case OVAL:
                        ColoredOval o = new ColoredOval(startPoint.x, startPoint.y, Math.abs(endPoint.x-startPoint.x), Math.abs(endPoint.y-startPoint.y), currentColor,isFilled,isDotted);
                        myShapes.add(o);
                        break;
Preview while dragging and repaint behavior

paintComponent draws the background image (if one was opened), then iterates myShapes calling shape.draw(g2d), and if startPoint and endPoint are set it draws a preview of the currently selected shape via the PaintWindow.draw(g2d) helper. This is how a live preview of the rectangle/oval/line appears while dragging.
paintComponent excerpt:

EL-Gohary1 / Paint-Brush-Project / src / pkg / PaintWindow.java
v2
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
Shape implementations and drawing rules

Each shape class implements a draw(Graphics2D) method that applies color, stroke and either draw/fill behavior. For dotted lines/outlined shapes, BasicStroke with dash patterns is used.
Example: ColoredRectangle handles all four combinations (filled/dotted/not filled/not dotted) and sets stroke and color appropriately.
ColoredRectangle.draw excerpt:

EL-Gohary1 / Paint-Brush-Project / src / pkg / ColoredRectangle.java
    public void draw(Graphics2D g2d){
        float[] f= {4,5};
        if(isFilled && isDotted){
            g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10f, f, 0.0f));
            g2d.setColor(color);
            g2d.fillRect(x,y,width,height);
Saving / opening images
The Save action creates a BufferedImage, renders all MyShape objects into it (white background then shapes), and writes a PNG file (myshape.png).
The Open action lets the user choose an image, reads it into a BufferedImage and stores it in myImage; paintComponent draws myImage before shapes so it appears as background.
How the project was created (high-level steps someone likely followed)

Define a shape interface (MyShape) with a draw(Graphics2D) contract.
Implement concrete shape classes by extending Java 2D primitives:
Rectangle -> ColoredRectangle
Ellipse2D.Double -> ColoredOval
Line2D.Double -> ColoredLine
Small ovals for FreeHand / Eraser Each shape stores color and attributes (isFilled, isDotted) and implements draw to render itself.
Build a Swing JPanel subclass (PaintWindow) that:
Creates UI controls (JButton, JCheckBox).
Maintains current tool, color, flags and start/end points.
Registers MouseListener and MouseMotionListener to capture interaction and build shape instances.
Keeps a list (Vector<MyShape>) of shapes and repaints them in paintComponent.
Adds actions for saving/opening/undo/clearAll.
Provide a small Main.java to create a JFrame and put an instance of PaintWindow inside.
How to build and run locally

In the project root (source files are in src/pkg), compile and run with javac/java:
Compile:
javac -d out src/pkg/*.java
Run:
java -cp out pkg.Main
Or use your IDE (IntelliJ/Eclipse/NetBeans): import project as a Java project, set Main.java as the run configuration and run.
Practical suggestions and common improvements

Replace Vector with ArrayList<MyShape> (Vector is synchronized and legacy).
Add bounds normalization when creating shapes: currently width/height use Math.abs(...) but x/y should be adjusted so shapes appear as expected when dragging left/up.
Improve undo: guard against empty list in undo action to avoid exceptions; consider command stack for multi-level undo/redo.
Persist shapes (serialize model) instead of just rendering to PNG; that would allow reopening & editing.
Separate model/view/controller (move shape storage and logic out of the JPanel) to make unit testing and extension easier.
Add a color chooser (JColorChooser) instead of fixed color buttons.
Optimize freehand drawing by storing a path (GeneralPath) instead of many small ovals.
Use SwingUtilities.invokeLater for UI startup if not already used in Main.
How to add a new tool (example plan)

Create a new class implementing MyShape (e.g., RoundedRectangle).
Add enum value to Tool.
Create button and ActionListener in PaintWindow to set current_Tool.
Handle creation in mouseReleased / preview in draw() similar to existing shapes.
Add any specific UI options (corner radius slider, etc).
<img width="1048" height="691" alt="image" src="https://github.com/user-attachments/assets/8d9698bc-1423-4ba0-a04b-d98d36cfebe2" />
