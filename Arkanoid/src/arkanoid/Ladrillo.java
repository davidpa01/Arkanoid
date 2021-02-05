package arkanoid;

import java.awt.Color;
import java.awt.Graphics;



public class Ladrillo extends Actor {
	
	private Color color;

	

	public Ladrillo(int x, int y, Color color) {
		super(x, y);
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
	
	
	
	
	
	@Override
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		
		if (a instanceof Pelota) {
			Arkanoid.getInstance().eliminaActor(this);
			ResourcesCache.getInstance().playSonido("explosion.wav");
			Arkanoid.getInstance().incorporaNuevoActor(new Explosion(this.x, this.y));
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
