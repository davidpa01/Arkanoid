package arkanoid;

import java.awt.Graphics;

public abstract class Actor {

	protected int x, y, ancho = 30, alto = 30; 
	protected String img;
	protected int velocidadX = 0;
	protected int velocidadY = 0;
	
	
	
	public Actor(int x, int y, String img) {
		super();
		this.x = x;
		this.y = y;
		this.img = img;
	}



	public Actor() {
		super();
	}
	
	
	
	public abstract void paint(Graphics g);
	
	public abstract void actua();



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getAncho() {
		return ancho;
	}



	public void setAncho(int ancho) {
		this.ancho = ancho;
	}



	public int getAlto() {
		return alto;
	}



	public void setAlto(int alto) {
		this.alto = alto;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}
