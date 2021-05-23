package bin;

import java.awt.Color;


public class DegradaColor {
	private Color color1,color2;
	private String posision;


	public DegradaColor(Color nc1, Color nc2, String pos) {

	    this.color1 = nc1;
	    this.color2 = nc2;
        this.posision=pos;
    }

	public Color getcolor1() {
	    return color1;
	}

	public void setcolor1(Color c1) {
	    this.color1 = c1;
	}

	public Color getcolor2() {
	    return color2;
	}

	public void setcolor2o(Color c2) {
	    this.color2 = c2;
	}
	public String getposision() {
		return posision;
	}
	public void setposision(String pos) {
		this.posision=pos;
	}
}







