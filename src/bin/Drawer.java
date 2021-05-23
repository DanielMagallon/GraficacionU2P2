package bin;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Drawer
{
    public Shape S;
    private final GeneralPath FGP;

    public Drawer(GeneralPath fgp) {
        FGP=fgp;
        S=fgp;
    }

    public boolean isOnShape(int x, int y){
        return S.contains(x,y);
    }


    public void backToOrigin(AffineTransform AF) {
        AF.translate(S.getBounds2D().getCenterX(),S.getBounds2D().getCenterY());
    }

    public void backToPos(AffineTransform AF) {
        AF.translate(-S.getBounds2D().getCenterX(),-S.getBounds2D().getCenterY());
    }

    public void escalar(Double A, Double B) {
        AffineTransform af = new AffineTransform();
        backToOrigin(af);
        af.scale(A,B);
        backToPos(af);
        S = af.createTransformedShape(S);
    }

    public void rotarsentido(int grados) {
        double gr=Math.toRadians(grados);
        AffineTransform af = new AffineTransform();
        af.setToRotation(gr,S.getBounds2D().getCenterX(),S.getBounds2D().getCenterY());
        S = af.createTransformedShape(S);

    }
    public void rotarcontra(int grados) {
        rotarsentido(-grados);
    }

    public void reflexion(boolean x) {
        AffineTransform af = new AffineTransform();
        backToOrigin(af);
        af.scale(x ? -1 : 1,x ? 1 : -1);
        backToPos(af);
        S = af.createTransformedShape(S);
    }

    public void traslacion(int x, int y) {
        AffineTransform af = new AffineTransform();
        backToOrigin(af);
        af.translate(x,y);
        backToPos(af);
        S = af.createTransformedShape(S);
    }

    public void deformar(double x, double y) {
        AffineTransform af = new AffineTransform();
        backToOrigin(af);
        af.shear(x,y);
        backToPos(af);
        S = af.createTransformedShape(S);
    }

    public void restaurar() {
        S=FGP;
    }
}