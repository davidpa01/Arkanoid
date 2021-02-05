package arkanoid;

import java.awt.Color;
import java.awt.Graphics;


public class Pelota extends Actor {
	
	

	public Pelota(int x, int y) {
		super(x, y);
		this.velocidadX = -5;
		this.velocidadY = -5;

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
		super.actua();
		this.x += this.velocidadX;
		MiCanvas limites = Arkanoid.getInstance().getCanvas();
		
		if (this.x < 0 || (this.x + this.ancho) > limites.getWidth()) {
			this.velocidadX = -this.velocidadX;
		}
		
		this.y += this.velocidadY;
		
		if (this.y < 0 || (this.y + this.alto) > limites.getHeight()) {
			this.velocidadY = -this.velocidadY;
		}
	}

}
