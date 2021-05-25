package lib.tabbedpane;

import lib.staticlass.AnimationStatus;
import lib.staticlass.OptionsPaint;
import main.Run;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TabbedPane extends JTabbedPane {
    private int index;

    private Tab selectedTab;

    public TabbedPane() {


        this.setUI(new MetalTabbedPaneUI() {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex,
                                            FontMetrics metrics) {
                int width = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
                int extra = tabIndex * 10;
                return width + extra;
            }

            @Override
            protected int calculateMaxTabHeight(int arg0) {
                // TODO Auto-generated method stub
                return 30;
            }

        });

        addChangeListener(c -> getCurrentTab());
    }

    public TabbedPane initTimer() {

        if (isValidPane()) {
            selectedTab.animation = true;
        }
        return this;
    }

    public void resetShape() {
        if (isValidPane()) {

            if (selectedTab.isAnimated("el reset"))
                return;

            selectedTab.gp = null;
            selectedTab.texturePaint = null;
            selectedTab.reset();
            selectedTab.drawer.restaurar();
        }
    }

    public void updatePaint() {
        if (isValidPane())
            selectedTab.updatePaint();
    }
    ///-------------------------------------------------------------------------------------------------------------
    public void updateColortrans() {
        if (isValidPane())
        {
            int alpha = Run.transModal.getAlpha(selectedTab.simpleColor);
            if(alpha!=-1)
                selectedTab.cambiartransparencia(alpha);
        }
    }
    public void translate(int x, int y) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la traslacion"))
                return;

            if (selectedTab.animation) {

                selectedTab.setRunner(() -> {
                    int[] xy = selectedTab.getRandomIncTranslate();
                    selectedTab.drawer.traslacion(xy[0], xy[1]);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);

            }
            else selectedTab.drawer.traslacion(x, y);
        }
    }


    public void scale(double esc) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la escalacion"))
                return;

            if (selectedTab.animation) {

                selectedTab.setRunner(() -> {

                    selectedTab.drawer.escalar(esc,esc);

                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);

            } else selectedTab.drawer.escalar(esc,esc);
        }
    }

    public void stop() {
        if (isValidPane())
            selectedTab.setStatusTimer(AnimationStatus.STOP);
    }

    public void reflexion(boolean x) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la reflexion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {

                        selectedTab.drawer.reflexion(selectedTab.isReflectionX());

                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            }
            else selectedTab.drawer.reflexion(x);
        }
    }

    public void rotateSentido(int ang) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la rotacion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {

                    selectedTab.drawer.rotarsentido(ang);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            }
            else selectedTab.drawer.rotarsentido(ang);
        }
    }

    public void rotateContra(int ang) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la rotacion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {

                    selectedTab.drawer.rotarcontra(ang);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            }
            else selectedTab.drawer.rotarcontra(ang);
        }
    }

    public void deformar(double x, double y) {
        if (isValidPane()) {

            if (selectedTab.isAnimated("la deformacion"))
                return;

            if (selectedTab.animation) {
                selectedTab.setRunner(() -> {
                    selectedTab.drawer.deformar(x, y);
                    selectedTab.repaint();
                });

                selectedTab.setStatusTimer(AnimationStatus.START);
            }
            else selectedTab.drawer.deformar(x, y);
        }
    }

    public void showGrid() {
        if (isValidPane()) {
            selectedTab.setPaintLines(!selectedTab.isPaintLines());
            selectedTab.repaint();
        }
    }

    public Shape getShape(){
        return selectedTab.drawer.S;
    }

    public int getW(){
        return selectedTab.getWidth();
    }

    public Tab getTab(){
        return selectedTab;
    }

    public int getH(){return selectedTab.getHeight();}

    public void close() {
        if (isValidPane()) {
            selectedTab.setStatusTimer(AnimationStatus.STOP);
            this.remove(selectedTab);
            repaint();
            validate();
            index--;
        }
    }

    public boolean isValidPane() {
        if(selectedTab!=null)
            return true;

        Run.optionPaint = OptionsPaint.DEFAULT;
        return false;
    }

    private void getCurrentTab() {
        if (selectedTab != null) {
            if (selectedTab.animationStatus == AnimationStatus.START) {
                selectedTab.setStatusTimer(AnimationStatus.SUSPEND);
            }
        }

        selectedTab = (Tab) this.getSelectedComponent();

        if (selectedTab!=null && selectedTab.animationStatus == AnimationStatus.SUSPEND) {
            selectedTab.setStatusTimer(AnimationStatus.START);
        }
    }


    public void addTab(JFrame f, String title,GraphicsRunnable gr, int maxWidth, int maxHeight, Color bg, Color line,
                        Color sC,int pixelSize)
    {
        this.addTab(title, new Tab(f,gr,maxWidth,maxHeight,bg,line,sC,pixelSize));
        selectTab();
    }

    private void selectTab() {
        index++;
        if (index > 1) {
            this.setSelectedIndex(index - 1);
        }

    }
}
