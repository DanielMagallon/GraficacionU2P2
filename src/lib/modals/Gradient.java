package lib.modals;

import bin.DegradaColor;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gradient extends JDialog{
	JButton color1, color2,btnc;
	JLabel label,label2,c1,c2;
    Color colorone=Color.white,colortwo=Color.white;
    JRadioButton opcion1 = new JRadioButton("Horizontal");
    JRadioButton opcion2 = new JRadioButton("Vertical");
    ButtonGroup buttons = new ButtonGroup();
   
    DegradaColor datos = new DegradaColor(colorone,colortwo,"horizontal");
	public Gradient(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Escalar una figura");
		setSize(600,500);
		setLocationRelativeTo(this);
		setResizable(false);
		setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.RED);
		label = new JLabel("Eliga el color uno y dos  del gradiante deseado");
		label2 = new JLabel("Eliga la orientacion deseada del gradiente");
	
		label.setBounds (140,20,400,20); // Bot�n en posicion 10,10 con ancho 40 pixels y alto 20
		buttons.add(opcion1);
	    buttons.add(opcion2);
		color1 =new JButton("Primer color ");
		color1.setBounds (100,50,150,40); // Bot�n en posicion 10,10 con ancho 40 pixels y alto 20
		JLabel c1 = new JLabel("  ");
		c1.setBorder(blackline);
		c1.setBackground(colorone);
		c1.setBounds(270, 50, 40, 40);
		c1.setOpaque(true);
		color1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				colorone = JColorChooser.showDialog(Gradient.this, "Pick Color", colorone);
				datos.setcolor1(colorone);
				c1.setBackground(colorone);
			}
		});
		color2 =new JButton("Segundo color ");
		color2.setBounds (330,50,150,40);
		JLabel c2 = new JLabel("  ");
		c2.setBackground(colortwo);
		c2.setBounds(490, 50, 40, 40);
		c2.setBorder(blackline);
		c2.setOpaque(true);
		color2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				colortwo = JColorChooser.showDialog(Gradient.this, "Pick Color", colortwo);
				datos.setcolor2o(colortwo);
				c2.setBackground(colortwo);
			}
		});
		
		//agregar checbox
		label2.setBounds(150, 100, 250, 40);
		opcion1.setBounds(180,150, 100, 40);
		opcion1.setSelected(true);
		opcion2.setBounds(300,150, 100, 40);
		add(label2);
		add(opcion1);
		add(opcion2);
		
		btnc=new JButton("Aceptar");
		btnc.setBounds (230,250,100,40);
		btnc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(opcion1.isSelected()) {
					datos.setposision("Horizontal");
				}else {
					datos.setposision( "Vertical");
				}
				
				setVisible(false);
			}
		});
		add(label);
		add(color1);
		add(c1);
		add(color2);
		add(c2);
		add(btnc);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
public DegradaColor mostrar() {
		
		setVisible(true);
		return datos;
		
	}
}
