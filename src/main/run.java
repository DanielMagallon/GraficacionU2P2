package main;

import bin.DegradaColor;
import lib.modals.Gradient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

public class run extends JPanel{
	JFrame V;
	GeneralPath FGP;
	Shape S;
	JButton probar,restaurar, Gradientes;
	metodos m ;
	ButtonGroup buttons = new ButtonGroup();
	AffineTransform AF = new AffineTransform();
	GradientPaint gp;
	Color colorone,colortwo;
	DegradaColor datos = new DegradaColor(colorone,colortwo,"horizontal");
	public run() {
		V= new JFrame("Transformaciones en 2D");
		V.setSize(1000,600);
		setSize(1000,600);
		V.setLocationRelativeTo(this);
		V.setResizable(false);
		V.add(this);
		FGP=new GeneralPath();
		FGP.moveTo(150, 180);
		FGP.lineTo(200, 180);
		FGP.lineTo(200, 250);
		FGP.curveTo(200, 250, 270, 300, 200, 350);
		FGP.lineTo(150, 350);
		FGP.curveTo(150, 350, 80, 300, 150, 250);
		FGP.moveTo(150, 180);
		FGP.closePath();
		S=FGP;
		probar=new JButton("Prueba");
		restaurar =new JButton("Restaurar");
		Gradientes = new JButton("Gradientes");
		//setLayout(new BorderLayout());
		
		add(probar);
		add(Gradientes);
		add(restaurar);
		probar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//m.escalar(1.24,1.24);
				//m.rotarsentido(45.0);
				//m.rotarcontra(45.0);
				//m.reflexionx();
				//m.reflexionxy();
				m.deformar(1.0, 0.0);
				repaint();
			}
		});
		Gradientes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Gradient gra =new Gradient(V, true);
				datos= gra.mostrar();
				
				gp=new GradientPaint(150, 180, datos.getcolor1(),150, 350,datos.getcolor2(),true);
				
				repaint();
			}
		});
		restaurar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gp=null;
				AF=new AffineTransform();
				repaint();
			}
		});
		//add(restaurar,BorderLayout.NORTH);
		V.setVisible(true);
		V.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		Graphics2D g2=(Graphics2D)g;
		m=new metodos(g2,S,AF,FGP);
		g2.setPaint(gp);
		g2.setTransform(AF);
		
		g2.fill(S);//la figura rellena
		
	}
	
	
	
	public static void main(String[] args) {
		new run();

	}

}
