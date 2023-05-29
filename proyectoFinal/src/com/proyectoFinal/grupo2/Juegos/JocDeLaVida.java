package com.proyectoFinal.grupo2.Juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;

public class JocDeLaVida extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingUtilities.invokeLater(() -> {
						JocDeLaVida juego = new JocDeLaVida();
						juego.mostrarMenutamaño();
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private static final int tamaño_TABLERO_PEQUENO = 40;
	private static final int tamaño_TABLERO_MEDIANO = 60;
	private static final int tamaño_TABLERO_GRANDE = 80;
	private static final int RETARDO = 200;
	private int celulasNuevas;
	private int generaciones;

	private boolean tableroAnterior[][];
	private boolean tableroAnterior2[][];
	private JButton botonIniciar;
	private JButton botonDetener;
	private JButton botonMasRapido;
	private JButton botonMasLento;
	private JButton botonAleatorio;
	private JButton botonSiguiente;
	private int contSiguiente;

	private JPanel panelTablero;

	private boolean[][] tablero;
	private Timer temporizador;

	public JocDeLaVida() {
		setTitle("Juego de la Vida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		botonIniciar = new JButton("Iniciar");
		botonDetener = new JButton("Detener");
		botonMasRapido = new JButton("Más rápido");
		botonMasLento = new JButton("Más lento");
		botonAleatorio = new JButton("Aleatorio");
		botonSiguiente = new JButton("Siguiente");

		botonSiguiente.addActionListener(this);

		botonIniciar.addActionListener(this);
		botonDetener.addActionListener(this);
		botonMasRapido.addActionListener(this);
		botonMasLento.addActionListener(this);
		botonAleatorio.addActionListener(this);

		JPanel panelControl = new JPanel();
		panelControl.add(botonSiguiente);
		panelControl.add(botonIniciar);
		panelControl.add(botonDetener);
		panelControl.add(botonMasRapido);
		panelControl.add(botonMasLento);
		panelControl.add(botonAleatorio);

		panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(tamaño_TABLERO_PEQUENO, tamaño_TABLERO_PEQUENO));

		tablero = new boolean[tamaño_TABLERO_PEQUENO][tamaño_TABLERO_PEQUENO];

		// Creamos el primer panel default, que será el tamaño de tablero pequeño
		for (int i = 0; i < tamaño_TABLERO_PEQUENO; i++) {
			for (int j = 0; j < tamaño_TABLERO_PEQUENO; j++) {
				JPanel panelCelda = new JPanel();
				panelCelda.setBackground(Color.WHITE);
				panelCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelCelda.addMouseListener(new MouseAdapterCelda(i, j));
				panelTablero.add(panelCelda);
				tablero[i][j] = false;
				// la establecemos a false porque está muerta y como hemos definido luego, si
				// está false el color es white
			}
		}

		add(panelControl, BorderLayout.SOUTH);
		add(panelTablero, BorderLayout.CENTER);
		pack();
		// Anotación: !!! importante !!!
		// Anotación: !!! importante !!!
		// Anotación: !!! importante !!!
		// Cuando se llama al método pack() en una ventana, se calcula el tamaño óptimo
		// de la ventana en función del tamaño
		// preferido de sus componentes y se ajusta para que todos los componentes sean
		// visibles.
		// Esto garantiza que no haya componentes cortados o fuera de la vista debido a
		// restricciones de tamaño.

		// Anotación: !!! importante !!!
		// Anotación: !!! importante !!!
		// Anotación: !!! importante !!!
		// Para que al ejecutarse salga al medio de la pantalla
		setLocationRelativeTo(null);
		celulasNuevas = 0;
		generaciones = 0;
		setVisible(true);
	}

	public void mostrarMenutamaño() {
	    JFrame frame = this; // Obtener referencia al frame principal
	    
		JDialog dialogotamaño = new JDialog(this, "Seleccionar tamaño de tablero", true);
		
		dialogotamaño.setLayout(new FlowLayout());

		JButton botonPequeno = new JButton("Pequeño");
		JButton botonMediano = new JButton("Mediano");
		JButton botonGrande = new JButton("Grande");
		JButton botonCreativo = new JButton("Circulo");
		JButton botonPiramide = new JButton("Piramide");
		JButton botonX = new JButton("X");
		JButton botonMosaico = new JButton("Mosaico");
		JButton botonModos = new JButton("Modos personalizados!");

		botonPequeno.addActionListener(e -> {
			establecertamaño(tamaño_TABLERO_PEQUENO);
			dialogotamaño.dispose();
		});

		botonMediano.addActionListener(e -> {
			establecertamaño(tamaño_TABLERO_MEDIANO);
			dialogotamaño.dispose();
		});

		botonGrande.addActionListener(e -> {
			establecertamaño(tamaño_TABLERO_GRANDE);
			dialogotamaño.dispose();
		});

		botonModos.addActionListener(e -> {

			// Crear el nuevo diálogo
			JDialog Dialog2 = new JDialog(this, "Escoge el modo de juego!", true);

			Dialog2.setLayout(new FlowLayout());
			Dialog2.setTitle("Escoge la figura personalizada");
			Dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			// Mostrar el nuevo diálogo
			dialogotamaño.dispose();

			botonCreativo.addActionListener(e2 -> {
				pintarCirculo();
				Dialog2.dispose();
			});
			botonPiramide.addActionListener(e2 -> {
				pintarPiramide();
				Dialog2.dispose();
			});
			botonX.addActionListener(e2 -> {
				pintarX();
				Dialog2.dispose();
			});

			botonMosaico.addActionListener(e2 -> {
				pintarMosaico();
				Dialog2.dispose();
			});

			Dialog2.add(botonCreativo);
			Dialog2.add(botonPiramide);
			Dialog2.add(botonX);
			Dialog2.add(botonMosaico);

			Dialog2.pack();

			Dialog2.setLocationRelativeTo(this);

			Dialog2.setVisible(true);

		});
		
		dialogotamaño.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose(); // Cerrar el frame principal
				frame.setVisible(false);
			}
		});
	    

		dialogotamaño.add(botonPequeno);
		dialogotamaño.add(botonMediano);
		dialogotamaño.add(botonGrande);
		dialogotamaño.add(botonModos);
		dialogotamaño.pack();
		dialogotamaño.setLocationRelativeTo(this);
		dialogotamaño.setVisible(true);

	}

	private void pintarCirculo() {
		panelTablero.removeAll();
		panelTablero.setLayout(new GridLayout(40, 40));
		tablero = new boolean[40][40];

		int radio = 15; // Radio del círculo
		int centroX = 20; // Coordenada X del centro del círculo
		int centroY = 20; // Coordenada Y del centro del círculo

		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 40; j++) {
				JPanel panelCelda = new JPanel();
				panelCelda.setBackground(Color.WHITE);
				panelCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelCelda.addMouseListener(new MouseAdapterCelda(i, j));
				panelTablero.add(panelCelda);
			}
		}

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {

				// Calcular la distancia desde el centro del círculo
				int distanciaX = Math.abs(i - centroX);
				int distanciaY = Math.abs(j - centroY);
				double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

				// Si la distancia es aproximadamente igual al radio, pintar el borde del
				// círculo
				if (Math.abs(distancia - radio) < 0.5) {
					tablero[i][j] = true;
				} else {
					tablero[i][j] = false;
				}
			}
		}
		pack();
		repaint();
		setLocationRelativeTo(null);
	}

	private void pintarX() {

		panelTablero.removeAll();
		panelTablero.setLayout(new GridLayout(40, 40));
		tablero = new boolean[40][40];

		int altura = 21; // Altura del rombo

		int filaInicio = 19 - altura / 2; // Fila de inicio del rombo
		int columnaInicio = 19; // Columna de inicio del rombo

		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 40; j++) {
				JPanel panelCelda = new JPanel();
				panelCelda.setBackground(Color.WHITE);
				panelCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelCelda.addMouseListener(new MouseAdapterCelda(i, j));
				panelTablero.add(panelCelda);
				tablero[i][j] = false;
			}
		}

		int espacio = altura / 2;
		for (int i = filaInicio; i <= filaInicio + altura / 2; i++) {
			int inicio = columnaInicio - espacio;
			int fin = columnaInicio + espacio;

			if (inicio >= 0 && fin < 40) {
				tablero[i][inicio] = true; // Pintar borde izquierdo del rombo
				tablero[i][fin] = true; // Pintar borde derecho del rombo
			}

			espacio--;
		}

		espacio = 1;
		for (int i = filaInicio + altura / 2 + 1; i <= filaInicio + altura; i++) {
			int inicio = columnaInicio - espacio;
			int fin = columnaInicio + espacio;

			if (inicio >= 0 && fin < 40) {
				tablero[i][inicio] = true; // Pintar borde izquierdo del rombo
				tablero[i][fin] = true; // Pintar borde derecho del rombo
			}
			espacio++;
		}
		pack();
		repaint();
		setLocationRelativeTo(null);
	}

	private void pintarMosaico() {

		panelTablero.removeAll();
		panelTablero.setLayout(new GridLayout(40, 40));
		tablero = new boolean[40][40];

		int tamañoPatron = 3; // Tamaño del patrón
		int separacionPatrones = 9; // Separación entre patrones

		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 40; j++) {
				JPanel panelCelda = new JPanel();
				panelCelda.setBackground(Color.WHITE);
				panelCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelCelda.addMouseListener(new MouseAdapterCelda(i, j));
				panelTablero.add(panelCelda);
			}
		}
		for (int i = 0; i < 40; i += (tamañoPatron + separacionPatrones)) {
			for (int j = 0; j < 40; j += (tamañoPatron + separacionPatrones)) {
				// Generar un patrón aleatorio para cada repetición
				Random random = new Random();
				boolean[][] patron = new boolean[tamañoPatron][tamañoPatron];

				for (int x = 0; x < tamañoPatron; x++) {
					for (int y = 0; y < tamañoPatron; y++) {
						if (random.nextDouble() < 0.5) { // 50% de probabilidad de pintar la celda
							patron[x][y] = true;
						}
					}
				}

				// Pintar el patrón en el tablero
				for (int x = 0; x < tamañoPatron; x++) {
					for (int y = 0; y < tamañoPatron; y++) {
						if (i + x < 40 && j + y < 40) {
							tablero[i + x][j + y] = patron[x][y];
						}
					}
				}
			}
		}
		pack();
		repaint();
		setLocationRelativeTo(null);
	}

	private void pintarPiramide() {
		panelTablero.removeAll();
		panelTablero.setLayout(new GridLayout(40, 40));
		tablero = new boolean[40][40];

		int altura = 20; // Altura del triángulo

		int filaInicio = 39 - altura; // Fila de inicio del triángulo

		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 40; j++) {
				JPanel panelCelda = new JPanel();
				panelCelda.setBackground(Color.WHITE);
				panelCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelCelda.addMouseListener(new MouseAdapterCelda(i, j));
				panelTablero.add(panelCelda);
				tablero[i][j] = false;
			}
		}

		int espacio = 0;
		for (int i = filaInicio; i < filaInicio + altura; i++) {
			int inicio = 20 - espacio;
			int fin = 20 + espacio;

			if (inicio >= 0 && fin < 40) {
				tablero[i][inicio] = true; // Pintar borde izquierdo del triángulo
				tablero[i][fin] = true; // Pintar borde derecho del triángulo
			}

			espacio++;
		}

		for (int j = 0; j < 40; j++) {
			tablero[filaInicio + altura][j] = true; // Pintar base del triángulo
		}

		pack();
		repaint();
		setLocationRelativeTo(null);
	}

	private void establecertamaño(int tamaño) {
		panelTablero.removeAll();
		panelTablero.setLayout(new GridLayout(tamaño, tamaño));
		tablero = new boolean[tamaño][tamaño];

		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				JPanel panelCelda = new JPanel();
				panelCelda.setBackground(Color.WHITE);
				panelCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelCelda.addMouseListener(new MouseAdapterCelda(i, j));
				panelTablero.add(panelCelda);
				tablero[i][j] = false;
			}
		}

		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonIniciar) {
			iniciar();
		} else if (e.getSource() == botonDetener) {
			detener();
		} else if (e.getSource() == botonMasRapido) {
			establecerRetardo(RETARDO / 2);
		} else if (e.getSource() == botonMasLento) {
			establecerRetardo(RETARDO * 2);
		} else if (e.getSource() == botonAleatorio) {
			aleatorizarCeldas();
		} else if (e.getSource() == botonSiguiente) {
			contSiguiente = 0;
			iniciar2();
		}
	}

	private void iniciar() {
		botonIniciar.setEnabled(false);
		botonDetener.setEnabled(true);
		botonMasRapido.setEnabled(true);
		botonMasLento.setEnabled(true);
		botonAleatorio.setEnabled(false); // Desactivar el botón "Aleatorio" durante la ejecución

		// Creamos un temporizador que se ejecuta infinitamente de forma recurrente, le
		// pasamos dos argumentos a la función
		// El tiempo en milisegundos y lo que queremos hacer cada vez
		temporizador = new Timer(RETARDO, e -> {
			evolucionar();
			repaint();

		});
		temporizador.start();
	}

	private void iniciar2() {
		botonIniciar.setEnabled(true);
		botonDetener.setEnabled(true);
		botonMasRapido.setEnabled(true);
		botonMasLento.setEnabled(true);
		botonAleatorio.setEnabled(false); // Desactivar el botón "Aleatorio" durante la ejecución

		// Creamos un temporizador que se ejecuta infinitamente de forma recurrente, le
		// pasamos dos argumentos a la función
		// El tiempo en milisegundos y lo que queremos hacer cada vez
		temporizador = new Timer(RETARDO, e -> {
			evolucionar2();
			repaint();

		});
		temporizador.start();
	}

	private void detener() {

		// resetear
		botonIniciar.setEnabled(true);
		botonDetener.setEnabled(false);
		botonMasRapido.setEnabled(false);
		botonMasLento.setEnabled(false);
		botonAleatorio.setEnabled(true); // Reactivar el botón "Aleatorio" al detener la ejecución

		if (temporizador != null) {
			temporizador.stop();
			temporizador = null;
			mostrarEstadisticas();
		}
		// resetear
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = false; // Reiniciar todas las celdas a muertas
			}
		}
		repaint();
	}

	// Con los botones usamos esta función para la velocidad
	private void establecerRetardo(int retardo) {
		if (temporizador != null) {
			temporizador.setDelay(retardo);
		}
	}

	private void evolucionar() {
		tableroAnterior2 = tableroAnterior;
		tableroAnterior = tablero.clone();

		boolean[][] nuevoTablero = new boolean[tablero.length][tablero[0].length];
		int nuevasCeldas = 0;
		boolean hayCambio = false;
		boolean hayCambio2 = false;

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				int vecinosVivos = contarVecinosVivos(i, j);

				if (tablero[i][j]) {
					if (vecinosVivos < 2 || vecinosVivos > 3) {
						nuevoTablero[i][j] = false; // Muere por soledad o sobrepoblación
						hayCambio = true;
					} else {
						nuevoTablero[i][j] = true; // Sigue viva
						if (!hayCambio && tableroAnterior[i][j] != nuevoTablero[i][j]) {
							hayCambio = true;
						}
					}
				} else {
					if (vecinosVivos == 3) {
						nuevoTablero[i][j] = true; // Nace
						nuevasCeldas++;
						if (!hayCambio && tableroAnterior[i][j] != nuevoTablero[i][j]) {
							hayCambio = true;
						}
					} else {
						nuevoTablero[i][j] = false; // Sigue muerta
					}
				}

				if (!hayCambio2 && tableroAnterior2 != null && tableroAnterior2[i][j] != nuevoTablero[i][j]) {
					hayCambio2 = true;
				}
			}
		}
		System.out.println(hayCambio);
		System.out.println(hayCambio2);

		if ((!hayCambio || !hayCambio2) && generaciones > 0) {
			detener();
			return;
		}

		tablero = nuevoTablero;
		celulasNuevas += nuevasCeldas;
		generaciones++;
	}

	//

	private void evolucionar2() {
		tableroAnterior2 = tableroAnterior;
		tableroAnterior = tablero.clone();

		boolean[][] nuevoTablero = new boolean[tablero.length][tablero[0].length];
		int nuevasCeldas = 0;
		boolean hayCambio = false;
		boolean hayCambio2 = false;

		// para cuando count = 1 tengo que regresar al tablero anterior

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				int vecinosVivos = contarVecinosVivos(i, j);

				if (tablero[i][j]) {
					if (vecinosVivos < 2 || vecinosVivos > 3) {
						nuevoTablero[i][j] = false; // Muere por soledad o sobrepoblación
						hayCambio = true;
					} else {
						nuevoTablero[i][j] = true; // Sigue viva
						if (!hayCambio && tableroAnterior[i][j] != nuevoTablero[i][j]) {
							hayCambio = true;
						}
					}
				} else {
					if (vecinosVivos == 3) {
						nuevoTablero[i][j] = true; // Nace
						nuevasCeldas++;
						if (!hayCambio && tableroAnterior[i][j] != nuevoTablero[i][j]) {
							hayCambio = true;
						}
					} else {
						nuevoTablero[i][j] = false; // Sigue muerta
					}
				}

				if (!hayCambio2 && tableroAnterior2 != null && tableroAnterior2[i][j] != nuevoTablero[i][j]) {
					hayCambio2 = true;
				}
			}

		}
		System.out.println(hayCambio);
		System.out.println(hayCambio2);

		if ((!hayCambio || !hayCambio2) && generaciones > 0) {
			detener();
			return;
		}
		if (contSiguiente == 1) {
			temporizador.stop();
			nuevoTablero = tableroAnterior.clone();
			// si arriba aci es que está tot bé

		}
		contSiguiente++;

		tablero = nuevoTablero;
		celulasNuevas += nuevasCeldas;
		generaciones++;
	}
	//

	private int contarVecinosVivos(int x, int y) {
		int contador = 0;

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < tablero.length && j >= 0 && j < tablero[0].length && !(i == x && j == y)) {
					if (tablero[i][j]) {
						contador++;
					}
				}
			}
		}

		return contador;
	}

	private void aleatorizarCeldas() {
		Random random = new Random();

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j] = false; // Reiniciar todas las celdas a muertas
			}
		}

		int contadorCeldas = 0;
		int maximoContadorCeldas = (tablero.length * tablero[0].length) / 12; // Establecer un máximo del 5% de celdas
																				// vivas

		while (contadorCeldas < maximoContadorCeldas) {
			int x = random.nextInt(tablero.length);
			int y = random.nextInt(tablero[0].length);

			if (!tablero[x][y]) {
				tablero[x][y] = true; // Establecer una celda como viva
				contadorCeldas++;
			}
		}

		celulasNuevas = 0;
		generaciones = 0;
		repaint();
	}

	private void mostrarEstadisticas() {
		String mensaje = "Estadísticas:\n";
		mensaje += "Células nuevas creadas: " + celulasNuevas + "\n";
		mensaje += "Generaciones: " + generaciones;

		JOptionPane.showMessageDialog(this, mensaje, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);

		celulasNuevas = 0;
		generaciones = 0;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				JPanel panelCelda = (JPanel) panelTablero.getComponent(i * tablero[0].length + j);

				if (tablero[i][j]) {
					panelCelda.setBackground(new Color(102, 102, 255)); // Célula viva
				} else {
					panelCelda.setBackground(Color.WHITE); // Célula muerta
				}
			}
		}
	}

	private class MouseAdapterCelda extends MouseAdapter {
		private int x;
		private int y;

		public MouseAdapterCelda(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (tablero[x][y]) {
				tablero[x][y] = false; // Cambiar el estado de una celda al hacer clic (viva -> muerta)
			} else {
				tablero[x][y] = true; // Cambiar el estado de una celda al hacer clic (muerta -> viva)
			}

			repaint();
		}
	}
}