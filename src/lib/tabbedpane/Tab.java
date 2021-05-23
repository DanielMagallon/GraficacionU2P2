package lib.tabbedpane;

import bin.Drawer;
import lib.staticlass.AnimationStatus;
import lib.staticlass.AppProperties;
import lib.staticlass.ImageLoader;
import lib.staticlass.ShapePoints;
import main.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

public class Tab extends JPanel
{
    private GraphicsRunnable graphicsRunnable;

    private Color defaultBg,defaultLineColor;

    private boolean paintLines,mover;

    private int pixelSize;
    private boolean enable,selectPoint;
    private int points[];
    private JPopupMenu popupMenu;
    private Timer timer;
    public boolean animation;
    private int delay=500;
    private Runnable runner;
    public Drawer drawer;
    private JLabel msg;
    public AnimationStatus animationStatus;

    private JPanel panelDibujo;


    public  GradientPaint gp;
    public TexturePaint texturePaint;
    public Color simpleColor = Color.orange;

    private Tab(JFrame f)
    {
        setLayout(new BorderLayout());

        animationStatus = AnimationStatus.NONE;

        drawer = new Drawer(ShapePoints.gp1);

        popupMenu = new JPopupMenu();

        JMenuItem mtReset = new JMenuItem("Restaurar figura", ImageLoader.resetR);
        mtReset.addActionListener(a-> {
            drawer.restaurar();
            repaint();
        });
        popupMenu.add(mtReset);

        JMenuItem mtSelectPoint = new JMenuItem("Seleccionar punto origen",ImageLoader.origenR);
        mtSelectPoint.addActionListener(a-> {
            cursorSelect();
        });
        popupMenu.add(mtSelectPoint);

        JMenuItem mtClose = new JMenuItem("Cerrar",ImageLoader.cerrarR);
        mtClose.addActionListener(a-> Run.tabbedPane.close());
        popupMenu.add(mtClose);

        addMouseWheelListener(mw->{
                int sentRueda=mw.getWheelRotation();
                if(sentRueda>=0) {
                    drawer.escalar(.995,.995);
                }else {
                    drawer.escalar(1.005,1.005);
                }
                repaint();
        });

        JLabel lblX = new JLabel("X: 0");
        JLabel lblY = new JLabel("Y: 0");
        msg = new JLabel("Mensaje: ");
        JPanel pn = new JPanel(){{add(lblX);add(lblY);add(msg);}};
        this.add(pn,"North");

        panelDibujo = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                paintPanel(graphics);
            }
        };

        this.add(panelDibujo);

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if(selectPoint){
                    int x = e.getX();
                    int y = e.getY();
                    if(ShapePoints.isInRange(points,new int[]{x,y}))
                    {
                        selectPoint=false;
                        setCursor(null);
                        updatePaint();
                    }
                    return;
                }

                int cx=e.getX();
                int cy=e.getY();
                int pfx= (int) drawer.S.getBounds2D().getCenterX();
                int pfy= (int) drawer.S.getBounds2D().getCenterY();
                if(cx<pfx && e.getClickCount()>=2)
                    drawer.rotarcontra(5);
                else
                if(cx>=pfx && e.getClickCount()>=2)
                    drawer.rotarsentido(5);
                else
                    showMenu(e);

                int maxX = (int)drawer.S.getBounds2D().getMaxX();
                int maxY = (int)drawer.S.getBounds2D().getMaxY();
                int minX = (int)drawer.S.getBounds2D().getMinX();
                int minY = (int)drawer.S.getBounds2D().getMinY();

                if((cx>=minX && cx<=maxX) && (cy>=minY && cy<=maxY)) {
                    mover = true;
                    setCursor(AppProperties.handCursor);
                }
                else {
                    mover = false;
                }

                updatePaint();
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
               showMenu(mouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(mover && !selectPoint){
                    setCursor(null);
                }
                showMenu(mouseEvent);
            }
        });

        addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(mover) {

                    int cx=e.getX();
                    int cy=e.getY();
                    int pcx=(int)drawer.S.getBounds2D().getCenterX();
                    int pcy=(int)drawer.S.getBounds2D().getCenterY();
                    int tx=(cx-pcx);
                    int ty=cy-pcy;
                    drawer.traslacion(tx, ty);
                    updatePaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                lblX.setText("X: "+mouseEvent.getX());
                lblY.setText("Y: "+mouseEvent.getY());
            }
        } );

        timer = new Timer(delay,a->runner.run());
        this.animation = false;
    }

    public Tab(JFrame f, GraphicsRunnable gr,int maxWidth,int maxHeight,Color bg,Color line,int pixelSize)
    {
        this(f);
        graphicsRunnable = gr;
        defaultBg = bg;
        defaultLineColor = line;
        this.paintLines = true;
        this.pixelSize = pixelSize;
        setPreferredSize(new Dimension(maxWidth,maxHeight));
    }

    public Tab(JFrame f, GraphicsRunnable gr,int maxWidth,int maxHeight,Color bg,Color line)
    {
        this(f);
        graphicsRunnable = gr;
        defaultBg = bg;
        defaultLineColor = line;
        pixelSize = 10;
        setPreferredSize(new Dimension(maxWidth,maxHeight));
    }

    private void showMenu(MouseEvent mouseEvent){
    
        if(mouseEvent.isPopupTrigger()){
            popupMenu.show(mouseEvent.getComponent(),mouseEvent.getX(),mouseEvent.getY());
        }
    }

    public void setRunner(Runnable runner) {
        this.runner = runner;
    }

    public boolean isPaintLines() {
        return paintLines;
    }

    public void setPaintLines(boolean paintLines) {
        this.paintLines = paintLines;
    }


    private boolean isReflectX=true;

    public boolean isReflectionX(){
           return isReflectX = !isReflectX;
    }

    public void paintPanel(Graphics graphics)
    {
        graphics.setColor(defaultBg);
        graphics.fillRect(0,0, getPreferredSize().width, getPreferredSize().height);

        if (paintLines)
        {
            graphics.setColor(defaultLineColor);

            //dibuja lineas verticales
            for(int x=pixelSize; x<=getWidth(); x+=pixelSize)
            {
                graphics.drawLine(x,0,x,getHeight());
            }

            //dibuja lineas horizontales
            for(int y=pixelSize; y<=getHeight(); y+=pixelSize)
            {
                graphics.drawLine(0,y,getWidth(),y);
            }
        }
        graphicsRunnable.paintCanvas((Graphics2D) graphics);

    }

    public void updatePaint(){
        panelDibujo.repaint();
    }

    public boolean isAnimated(String animacion){
        if(timer.isRunning()){
            msg.setText(String.format("Mensaje: No es posible aplicar %s durante una animacion.",animacion));
            return true;
        }
        return false;
    }

    public void setStatusTimer(AnimationStatus animationStatus){
        this.animationStatus = animationStatus;

        if(animationStatus==AnimationStatus.START) {
            timer.start();
            msg.setText("Mensaje: Animacion en curso.");
        }

        else{
            animation = false;
            timer.stop();
            msg.setText(String.format("Mensaje: Animacion %s.",animationStatus==AnimationStatus.STOP ?
                    "finalizada" : "suspendida"));
        }
    }

    private static Random lb = new Random();

    public int[] getRandomIncTranslate(){
        int x = lb.nextInt(101);
        int y = lb.nextInt(101);
        int flagX = lb.nextInt(2);
        int flagY = lb.nextInt(2);

        if(flagX==0)
            x=-x;
        if(flagY==0)
            y=-y;

        return new int[]{x,y};
    }

    public void cursorSelect(){
        setCursor(AppProperties.selectCursor);
        selectPoint = true;
        repaint();
    }
}