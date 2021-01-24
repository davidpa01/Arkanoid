package arkanoid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Arkanoid {
	
	private static int FPS = 60;
	private static List<Actor> actores = new ArrayList<Actor>();
	private static MiCanvas canvas = null;
	private static JFrame ventana = null;

	public static void main(String[] args) {
		ventana = new JFrame("poligonos");
		ventana.setBounds(0, 0, 800, 600);
		
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		ventana.getContentPane().setLayout(new BorderLayout());
		
		crearActores(actores);
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new MiCanvas(actores);
		canvas.setBackground(Color.BLACK);
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		// Hago que la ventana sea visible
		ventana.setVisible(true);
		
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
		});
		
		juego();
	}
	
	
	public static void juego() {
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
	
	private static void crearActores(List<Actor> actores) {
		Pelota pelota = new Pelota(300, 300, "");
		actores.add(pelota);
		Ladrillo ladrillo = new Ladrillo(0, 0, "");
		actores.add(ladrillo);
		Nave nave = new Nave(300, 500, "");
		actores.add(nave);
	}
	
	private static void cerrarAplicacion() {
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(ventana,"¿Desea cerrar la aplicación?","Salir de la aplicación",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}
