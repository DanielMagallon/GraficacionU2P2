package lib.modals;

import javax.swing.*;
import java.awt.*;

public class Transparencia extends JDialog {
	private int alpha;
	private JLabel et2;
	private JButton bac,bca,colorLbl;
    private JSlider slider;

	public Transparencia(JFrame v, boolean modal) {
		super(v,modal);
		setTitle("Transparecnia");
		setSize(800,150);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout() );
		et2=new JLabel("Deslize para asignar un valor de trasnparencia: ");
		colorLbl = new JButton("255");
		colorLbl.setForeground(Color.white);
        slider = new JSlider(0, 255, 255);
        slider.addChangeListener(c->{
            colorLbl.setBackground(new Color(colorAl.getRed(),colorAl.getGreen(),colorAl.getBlue(),slider.getValue()));
            colorLbl.setText(slider.getValue()+"");
        });
        // create a slider

        // paint the ticks and tracks
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // set spacing
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(5);
		bac =new JButton("Aceptar");
		bca =new JButton("Cancelar");

		colorLbl.setOpaque(true);
		bac.addActionListener(arg0 -> {

		    alpha=slider.getValue();
         setVisible(false);
         dispose();
        });
		
		bca.addActionListener(e -> {
            alpha =-1;
            setVisible(false);
             dispose();
        });

		JPanel sliderPan = new JPanel(new FlowLayout()){{add(et2);add(slider);add(colorLbl);}};
		JPanel btnsPan = new JPanel(new FlowLayout()){{add(bac);add(bca);}};
		add(sliderPan);
		add(btnsPan,"South");

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}

	private Color colorAl;
	
	public int getAlpha(Color color) {
		colorAl = color;
		colorLbl.setBackground(colorAl);
		setVisible(true);
		return alpha;
	}
}
