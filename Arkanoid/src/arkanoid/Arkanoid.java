package arkanoid;

import java.awt.BorderLayout;
import java.awt.Color;
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
		canvas.requestFocus();
		
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
		Arkanoid.getInstance().juego();
	}
	
	
	
	/**
	 * 
	 */
	public void juego() {
		int millisPorCadaFrame = 1000 / FPS;
		do {
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			canvas.repaint();
			
			for (Actor a : actores) {
				a.actua();
			}
			
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
		
		Pelota pelota = new Pelota(300, 300, "");
		Color[] color = new Color[] {Color.BLUE, Color.YELLOW, Color.red, Color.pink, Color.GREEN};
		int x ;
		int y = 0;
		actores.add(pelota);
		for (int i = 0; i < 5; i++) {
			for (x = 0; x < 800; x+=40) {
				Ladrillo ladrillo = new Ladrillo(x, y, "", color[i]);
				actores.add(ladrillo);
			}
			y += 40;
		}
		
		nave = new Nave(300, 500, "");
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
	 * @return
	 */
	public MiCanvas getCanvas() {
		return canvas;
		
	}

}
