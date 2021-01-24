package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Nave extends Actor {

	public Nave(int x, int y, String img) {
		super(x, y, img);
		// TODO Auto-generated constructor stub
	}

	public Nave() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, this.ancho, this.alto);

	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub

	}

}
