package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {
	
	private Color color;

	

	public Ladrillo(int x, int y, String img, Color color) {
		super(x, y, img);
		this.color = color;
		// TODO Auto-generated constructor stub
	}

	public Ladrillo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub

	}
	
	
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
