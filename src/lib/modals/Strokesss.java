package lib.modals;
import javax.swing.*;
import javax.swing.border.Border;

import bin.Strokesdata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Strokesss extends JDialog{
	JButton btnc, color1;
	  JRadioButton opcion1 = new JRadioButton("Stroke 1");
	    JRadioButton opcion2 = new JRadioButton("Stroke 2");
	    JRadioButton opcion3 = new JRadioButton("Stroke 3");
	    ButtonGroup buttons = new ButtonGroup();
	    JLabel label2,c1,lb;
	    Color colorone=Color.white;
	    String opcion="uno";
	    Strokesdata data = new Strokesdata(colorone,opcion,2);
	    private JSlider slider;
public Strokesss(JFrame v, boolean modal) {
	super(v,modal);
	setTitle("Strokes y uniones");
	setSize(800,400);
	setLocationRelativeTo(null);
	setResizable(false);
	setLayout(null);
	lb =new JLabel("Eliga el gorosor del borde");
	 slider = new JSlider(0, 50, 50);
	label2 = new JLabel("Eliga alguno de los tres posibles combinaciones de terminaciones y uniones");
	label2.setBounds(150, 100, 450, 40);
	label2.setForeground(Color.white);
	opcion1.setBounds(180,150, 100, 40);
	opcion1.setSelected(true);
	opcion2.setBounds(300,150, 100, 40);
	opcion3.setBounds(420,150, 100, 40);
	buttons.add(opcion1);
    buttons.add(opcion2);
    buttons.add(opcion3);
    Border blackline = BorderFactory.createLineBorder(Color.RED);
    color1 =new JButton("color de borde ");
	color1.setBounds (100,50,150,40); // Bot�n en posicion 10,10 con ancho 40 pixels y alto 20
	slider.setBounds(350,10,200,70);
	slider.setPaintTrack(true);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
	lb.setBounds(350,80, 200,30);
	 slider.setMajorTickSpacing(50);
     slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(5);
	JLabel c1 = new JLabel("  ");
	c1.setBorder(blackline);
	c1.setBackground(colorone);
	c1.setBounds(270, 50, 40, 40);
	c1.setOpaque(true);
	color1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			colorone = JColorChooser.showDialog(Strokesss.this, "Pick Color", colorone);
			data.setcolor1(colorone);
			c1.setBackground(colorone);
		}
	});
    
	btnc=new JButton("Aceptar");
	btnc.setBounds (330,250,100,40);
	btnc.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(opcion1.isSelected()) {
				data.setposision("Uno");
			}else if(opcion2.isSelected()){
				data.setposision("Dos");
			}else {
				data.setposision( "Tres");
			}
			data.setgrosor(slider.getValue());
			setVisible(false);
		}
	});
	add(lb);
	add(slider);
	add(c1);
	add(color1);
	add(label2);
	add(opcion1);
	add(opcion2);
	add(opcion3);
	add(btnc);
	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	
}

public Strokesdata Mostrarstroke() {
	setVisible(true);
	return data;
}

}
