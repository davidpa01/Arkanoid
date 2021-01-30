package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Nave extends Actor {
	
	private boolean up, down, left, right;
	protected static final int PLAYER_SPEED = 7;

	public Nave(int x, int y, String img) {
		super(x, y, img);
		this.ancho = 100;
		this.alto = 20;
		this.velocidadX = 0;
		this.velocidadY = 0;
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
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
		updateSpeed();		
	}

	

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
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
		if (down)
			this.velocidadY = PLAYER_SPEED;
		if (up)
			this.velocidadY = -PLAYER_SPEED;
		if (left)
			this.velocidadX = -PLAYER_SPEED;
		if (right)
			this.velocidadX = PLAYER_SPEED;			
		}
}
