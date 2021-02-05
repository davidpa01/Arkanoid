package arkanoid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Actor {

	protected int x, y; // Coordenadas x e y del actor
	protected int ancho = 30, alto = 30; // ancho y alto que ocupa el actor en pantalla
	protected int velocidadX = 0; // Velocidades en cada eje
	protected int velocidadY = 0;
	protected BufferedImage spriteActual;
	protected boolean marcadoParaEliminacion = false;
	// Posibilidad de que el actor sea animado, a trav�s del siguiente array de sprites y las variables
	// velocidadDeCambioDeSprite y unidadDeTiempo
	protected List<BufferedImage> spritesDeAnimacion = new ArrayList<BufferedImage>();
	protected int velocidadDeCambioDeSprite = 0;
	private int unidadDeTiempo = 0;
	
	
	
	
	
	/**
	 * Constructor sin parámetros de entrada
	 */
	public Actor() {
	}

	/**
	 * Constructor con parámetros de entrada
	 * @param x
	 * @param y
	 * @param img
	 */
	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(this.spriteActual, this.x, this.y, null);
	}	
	
	/**
	 * 
	 */
	public void actua() {
		// En el caso de que exista un array de sprites el actor actual se tratar� de una animaci�n, para eso llevaremos a cabo los siguientes pasos
		if (this.spritesDeAnimacion != null && this.spritesDeAnimacion.size() > 0) {
			unidadDeTiempo++;
			if (unidadDeTiempo % velocidadDeCambioDeSprite == 0){
				unidadDeTiempo = 0;
				int indiceSpriteActual = spritesDeAnimacion.indexOf(this.spriteActual);
				int indiceSiguienteSprite = (indiceSpriteActual + 1) % spritesDeAnimacion.size();
				this.spriteActual = spritesDeAnimacion.get(indiceSiguienteSprite);
			}
		}
	}
	
	
	/**
	 * 
	 * @param a
	 */
	public void colisionaCon(Actor a) {
	}

	
	
	
	
	
	//getters y setters
	
	
	
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

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public BufferedImage getSpriteActual() {
		return spriteActual;
	}

	public void setSpriteActual(BufferedImage spriteActual) {
		this.spriteActual = spriteActual;
		this.ancho = this.spriteActual.getWidth();
		this.alto = this.spriteActual.getHeight();
	}

	public boolean isMarcadoParaEliminacion() {
		return marcadoParaEliminacion;
	}

	public void setMarcadoParaEliminacion(boolean marcadoParaEliminacion) {
		this.marcadoParaEliminacion = marcadoParaEliminacion;
	}

	public List<BufferedImage> getSpritesDeAnimacion() {
		return spritesDeAnimacion;
	}

	public void setSpritesDeAnimacion(List<BufferedImage> spritesDeAnimacion) {
		this.spritesDeAnimacion = spritesDeAnimacion;
	}

	public int getVelocidadDeCambioDeSprite() {
		return velocidadDeCambioDeSprite;
	}

	public void setVelocidadDeCambioDeSprite(int velocidadDeCambioDeSprite) {
		this.velocidadDeCambioDeSprite = velocidadDeCambioDeSprite;
	}

	public int getUnidadDeTiempo() {
		return unidadDeTiempo;
	}

	public void setUnidadDeTiempo(int unidadDeTiempo) {
		this.unidadDeTiempo = unidadDeTiempo;
	}



	
	
	
	
	
	
}
