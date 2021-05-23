package main;

import java.awt.*;
import java.awt.geom.*;
public class metodos {
Shape S;
Graphics2D g2;
GeneralPath FGP;
AffineTransform AF;
	public metodos(Graphics2D g, Shape s, AffineTransform af, GeneralPath fgp) {
		S=s;
		g2=(Graphics2D)g;
		AF=af;
		FGP=fgp;
		
	}
	public void regresarorigen() {
		AF.translate(S.getBounds2D().getCenterX(),S.getBounds2D().getCenterY());
	}
	public void regresarposision() {
		AF.translate(-S.getBounds2D().getCenterX(),-S.getBounds2D().getCenterY());
	}
	public void escalar(Double A, Double B) {
		regresarorigen();
		AF.scale(A, B);
		regresarposision();
		 //g2.fill(S);
		 System.out.println("jola"); 
	}
	
	public void rotarsentido(Double grados) {
		double gr=Math.toRadians(grados);
		AF.rotate(gr,S.getBounds2D().getCenterX(),S.getBounds2D().getCenterY());
	}
	public void rotarcontra(Double grados) {
		double gr=Math.toRadians(-grados);
		AF.rotate(gr,S.getBounds2D().getCenterX(),S.getBounds2D().getCenterY());
	}
	
	public void reflexionx() {
		regresarorigen();
		AF.scale(1, -1);
		regresarposision();
	}
	
	public void reflexiony() {
		regresarorigen();
		AF.scale(-1, 1);
		regresarposision();
	}
	
	public void reflexionxy() {
		regresarorigen();
		AF.scale(-1, -1);
		regresarposision();
	}
	public void traslacion(Double x, Double y) {
		AF.translate(x,y);
	}
	public void deformar(Double x, Double y) {
		regresarorigen();
		AF.shear(x, y);
		regresarposision();
	}
	public void restaurar() {
		S=FGP;
		
	}
	
}
