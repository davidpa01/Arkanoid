package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Nave extends Actor {
	
	
	private boolean left, right;
	protected static final int PLAYER_SPEED = 7;

	public Nave(int x, int y) {
		super(x, y);
		this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_NAVE));
		
	}

	public Nave() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actua() {
		super.actua();
		MiCanvas canvas = Arkanoid.getInstance().getCanvas();
		
		// Movimiento en el eje horizontal
		this.x += this.velocidadX; // En cada iteración del bucle principal, el monstruo cambiará su posición en el eje X, sumándole a esta el valor de vx
		// si la nave intenta salir por la derecha no se lo permitimos
		if (this.x <  0) {
			this.x = 0;
		}
		// si la nave intenta salir por la izquierda no se lo permitimos
		if (this.x >  (canvas.getWidth() - this.ancho)) {
			this.x = (canvas.getWidth() - this.ancho);
		}
	}
	
	public void mover(int x) {
		this.x = x;
		
		MiCanvas canvas = Arkanoid.getInstance().getCanvas();
		
		if (this.x > (canvas.getWidth() - this.ancho)) {
			this.x = canvas.getWidth() - this.ancho;
		}
		
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		}
		updateSpeed();		
	}

	

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		}
		updateSpeed();		
	}
	
	
	private void updateSpeed() {
		this.velocidadX = 0;
		this.velocidadY = 0;

		if (left)
			this.velocidadX = -PLAYER_SPEED;
		if (right)
			this.velocidadX = PLAYER_SPEED;			
		}
}
