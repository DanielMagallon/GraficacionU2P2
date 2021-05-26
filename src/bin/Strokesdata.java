package bin;

import java.awt.Color;

public class Strokesdata {
	private Color color1;
	private String posision;
	private int grosor;

	public Strokesdata(Color nc1, String pos, int gro) {

	    this.color1 = nc1;
        this.posision=pos;
        this.grosor=gro;
    }

	public Color getcolor1() {
	    return color1;
	}

	public void setcolor1(Color c1) {
	    this.color1 = c1;
	}
	
	public int getgrosor() {
	    return grosor;
	}

	public void setgrosor(int c1) {
	    this.grosor = c1;
	}
	public String getposision() {
		return posision;
	}
	public void setposision(String pos) {
		this.posision=pos;
	}
}
