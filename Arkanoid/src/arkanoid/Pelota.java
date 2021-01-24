package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	private int velocidadX = -5;
	private int velocidadY = -5;

	public Pelota(int x, int y, String img) {
		super(x, y, img);
		// TODO Auto-generated constructor stub
	}

	public Pelota() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(this.x, this.y, this.ancho, this.alto);

	}

	@Override
	public void actua() {
		this.x += this.velocidadX;
		
		if (this.x < 0 || this.x > 770) {
			this.velocidadX = -this.velocidadX;
		}
		
		this.y += this.velocidadY;
		
		if (this.y < 0 || this.y > 570) {
			this.velocidadY = -this.velocidadY;
		}
	}

}
