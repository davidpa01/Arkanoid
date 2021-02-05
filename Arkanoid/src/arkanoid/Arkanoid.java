package arkanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 * 
 * @author david
 *
 */
public class Arkanoid {
	
	private static int FPS = 60;
	private  List<Actor> actores = new ArrayList<Actor>();
	private  MiCanvas canvas = null;
	private JFrame ventana = null;
	Nave nave = new Nave();
	private List<Actor> actoresParaIncorporar = new ArrayList<Actor>();
	private List<Actor> actoresParaEliminar = new ArrayList<Actor>();
	
	
	private static Arkanoid instance = null;
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public static Arkanoid getInstance() {
		if (instance == null) {
			instance = new Arkanoid();
		}
		return instance;
	}
		

	
	
	/**
	 * 
	 */
	public Arkanoid() {
		ventana = new JFrame("Arkanoid");
		ventana.setBounds(0, 0, 800, 600);
		
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		
		actores = crearActores();
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new MiCanvas(actores);
		
		canvas.setBackground(Color.BLACK);
		
		canvas.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				nave.mover(e.getX());
			}
			
		});
		
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				nave.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				nave.keyReleased(e);
			}
			
			
			
		});
		
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		
		ventana.setIgnoreRepaint(true);
		
		// Hago que la ventana sea visible
		ventana.setVisible(true);
		
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
			
		});	 
		
	}

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ResourcesCache.getInstance().cargarRecursosEnMemoria();

		
		Arkanoid.getInstance().juego();
	}
	
	
	
	/**
	 * 
	 */
	public void juego() {
		int millisPorCadaFrame = 1000 / FPS;
		do {
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			if (ventana.getFocusOwner() != null && !ventana.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
			
			canvas.pintaEscena();
			
			for (Actor a : actores) {
				a.actua();
			}
			
			
			detectoColisiones();
			
			actualizaActores();
			
			
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa;
			
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while (true);
	}
	



	/**
	 * 
	 * @param actores
	 */
	private List<Actor> crearActores() {
		
		List<Actor> actores = new ArrayList<Actor>();
		
		Pelota pelota = new Pelota(300, 300);
		Color[] color = new Color[] {Color.BLUE, Color.YELLOW, Color.red, Color.pink, Color.GREEN};
		int x ;
		int y = 0;
		actores.add(pelota);
		for (int i = 0; i < 5; i++) {
			for (x = 0; x < 800; x+=40) {
				Ladrillo ladrillo = new Ladrillo(x, y, color[i]);
				actores.add(ladrillo);
			}
			y += 40;
		}
		
		nave = new Nave(300, 500);
		actores.add(nave);
		return actores;
	}
	
	
	/**
	 * 
	 */
	private void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"¿Desea cerrar la aplicación?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	
	/**
	 * 
	 * @param a
	 */
	public void incorporaNuevoActor (Actor a) {
		this.actoresParaIncorporar.add(a);
	}
	
	
	/**
	 * 
	 * @param a
	 */
	public void eliminaActor (Actor a) {
		this.actoresParaEliminar.add(a);
	}
	
	
	/**
	 * 
	 */
	private void actualizaActores () {
		// Incorporo los nuevos actores
		for (Actor a : this.actoresParaIncorporar) {
			this.actores.add(a);
		}
		this.actoresParaIncorporar.clear(); // Limpio la lista de actores a incorporar, ya están incorporados
		
		// Elimino los actores que se deben eliminar
		for (Actor a : this.actoresParaEliminar) {
			this.actores.remove(a);
		}
		this.actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
	}
	
	/**
	 * 
	 */
	private void detectoColisiones() {
		for (Actor actor1: this.actores) {
			
			Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
			
			for (Actor actor2 : this.actores) {
				
				if (!actor1.equals(actor2)) {

					Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getAlto());

					if (rect1.intersects(rect2)) {
						actor1.colisionaCon(actor2); 
						actor2.colisionaCon(actor1); 
					}
				}
			}
		}
	}

	
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public MiCanvas getCanvas() {
		return canvas;
		
	}

}
