package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {

	public Ladrillo(int x, int y, String img) {
		super(x, y, img);
		// TODO Auto-generated constructor stub
	}

	public Ladrillo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub

	}

}
