package com.proyectoFinal.grupo2.Juegos;

<<<<<<< Updated upstream
import javax.swing.ImageIcon;
=======
import java.awt.Dimension;
import java.awt.GridLayout;
>>>>>>> Stashed changes
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.proyectoFinal.grupo2.Clases.Casilla;
import com.proyectoFinal.grupo2.Clases.TableroBuscaminas;
import java.awt.BorderLayout;
<<<<<<< Updated upstream
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
=======
import java.awt.event.ActionListener;
>>>>>>> Stashed changes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class BuscaMinas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean salir = false;
	private String[] niveles = { "Fácil", "Medio", "Difícil" };
	private String[] opcionesTrasAcabar = { "Volver a Jugar", "Salir" };
	private JPanel contentPane;
	private int tamanoTauler;
	private int cantidadMinas;
<<<<<<< Updated upstream
	private JButton[][] botonesTablero;
	private JPanel panelTablero = new JPanel();
	private JLabel labelCasillasDescubiertas;
	private JLabel labelCasillasCubiertas;
	private JLabel labelBanderasPuestas;
	boolean partidaVigente;
	int segundos;
	Timer timer;
	TableroBuscaminas tableroBuscaminas;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaMinas frame = new BuscaMinas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

=======
	
>>>>>>> Stashed changes
	/**
	 * Create the frame.
	 */
	public BuscaMinas() {
		super("Busca Minas - ");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton botonReset = new JButton("Nueva Partida");
		botonReset.setFocusable(false);
		botonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BuscaMinas buscaMinas = new BuscaMinas();
				buscaMinas.setVisible(true);
			}
		});
		botonReset.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(botonReset);

		JButton botonGuardar = new JButton("Guardar Partida");
		botonGuardar.setFocusable(false);
		botonGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel.add(botonGuardar);

		JButton botonSalir = new JButton("Salir");
		botonSalir.setFocusable(false);
		botonSalir.setHorizontalAlignment(SwingConstants.CENTER);
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(botonSalir);

		String estiloCSS = "<html><body><p style='font-size: 16px;'>Selecciona un nivel</p></body></html>";
		JLabel label = new JLabel(estiloCSS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		int seleccion = JOptionPane.showOptionDialog(null, label, "Buscaminas", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, niveles, niveles[0]);

		setSize(new Dimension(900, 900));
		setResizable(false);
		setLocationRelativeTo(null);

		switch (seleccion) {
		case 0:
			tamanoTauler = 10;
			cantidadMinas = 10;
			this.setTitle(getTitle() + "Facil");
			break;
		case 1:
			tamanoTauler = 15;
			cantidadMinas = 15;
			this.setTitle(getTitle() + "Medio");
			break;
		case 2:
			tamanoTauler = 20;
			cantidadMinas = 20;
			this.setTitle(getTitle() + "Dificil");
			break;
		default:
			tamanoTauler = 5;
			cantidadMinas = 5;
			this.setTitle(getTitle() + "Facil");
			break;
		}
		crearBotones(tamanoTauler);
		crearTablero();

		JPanel panelInformacion = new JPanel();
		contentPane.add(panelInformacion, BorderLayout.NORTH);
		panelInformacion.setLayout(new GridLayout(2, 1));

		JPanel panelLabels1 = new JPanel();
		panelLabels1.setLayout(new GridLayout(1, 3));

		String estiloCSSMinas = "<html><body><p style='font-size: 18px;'>Minas: " + tableroBuscaminas.getNumMinas()
				+ "</p></body></html>";
		JLabel labelMinas = new JLabel(estiloCSSMinas);
		labelMinas.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabels1.add(labelMinas);

		String rutaImagen2 = "/com/proyectoFinal/grupo2/Juegos/Imagenes/minaBuscaMinas.png";
		ImageIcon imagen = new ImageIcon(getClass().getResource(rutaImagen2));
		Image imagenOriginal2 = imagen.getImage();
		Image imagenRedimensionada2 = imagenOriginal2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado2 = new ImageIcon(imagenRedimensionada2);
		
		JLabel labelImagen = new JLabel(iconRedimensionado2);
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabels1.add(labelImagen);

		JLabel labelTimer = new JLabel(
				"<html><body><p style='font-size: 18px;'>Tiempo: " + segundos + " segundos</p></body></html>");
		labelTimer.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabels1.add(labelTimer);

		panelInformacion.add(panelLabels1);

		JPanel panelLabels2 = new JPanel();
		panelLabels2.setLayout(new GridLayout(1, 3));

		String estiloCSSCasillasDescubiertas = "<html><body><p style='font-size: 18px;'>Casillas descubiertas: "
				+ tableroBuscaminas.getNumCasillasAbiertas() + "</p></body></html>";
		labelCasillasDescubiertas = new JLabel(estiloCSSCasillasDescubiertas);
		labelCasillasDescubiertas.setHorizontalAlignment(SwingConstants.CENTER);

		panelLabels2.add(labelCasillasDescubiertas);

		String estiloCSSCasillasCubiertas = "<html><body><p style='font-size: 18px;'>Casillas cubiertas: "
				+ (tableroBuscaminas.getNumFilas() * tableroBuscaminas.getNumFilas()) + "</p></body></html>";
		labelCasillasCubiertas = new JLabel(estiloCSSCasillasCubiertas);
		labelCasillasCubiertas.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabels2.add(labelCasillasCubiertas);

		String estiloCSSCasillasBanderas = "<html><body><p style='font-size: 18px;'>Banderas: "
				+ tableroBuscaminas.getNumBanderas() + "</p></body></html>";
		labelBanderasPuestas = new JLabel(estiloCSSCasillasBanderas);
		labelBanderasPuestas.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabels2.add(labelBanderasPuestas);
		panelInformacion.add(panelLabels2);

		timer = new Timer(1000, new ActionListener() {
			int segundos = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				segundos++;
				String estiloCSSTimer = "<html><body><p style='font-size: 18px;'>Tiempo: " + segundos
						+ " segundos</p></body></html>";
				labelTimer.setText(estiloCSSTimer);
			}
		});
		timer.start();

	}

	private void crearTablero() {
		tableroBuscaminas = new TableroBuscaminas(tamanoTauler, tamanoTauler, cantidadMinas);
		tableroBuscaminas.imprimirTablero();
	}

	public void crearBotones(int tamanoTauler) {
		botonesTablero = new JButton[tamanoTauler][tamanoTauler];
		contentPane.add(panelTablero, BorderLayout.CENTER);
		panelTablero.setLayout(new GridLayout(tamanoTauler, tamanoTauler));

		for (int i = 0; i < botonesTablero.length; i++) {
			for (int j = 0; j < botonesTablero[i].length; j++) {
				botonesTablero[i][j] = new JButton();
				botonesTablero[i][j].setFocusable(false);
				botonesTablero[i][j].setName(i + ", " + j);
				panelTablero.add(botonesTablero[i][j]);
				botonesTablero[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							clickIzquierdoBoton(e);
						} else if (e.getButton() == MouseEvent.BUTTON3) {
							clickDerechoBoton(e);
						}
					}
				});
			}
		}
	}

	private void actualizarBotones() {
		for (int i = 0; i < botonesTablero.length; i++) {
			for (int j = 0; j < botonesTablero[i].length; j++) {
				JButton boton = botonesTablero[i][j];
				Casilla casilla = tableroBuscaminas.getCasilla(i, j);
				if (casilla.isAbierta()) {
					boton.setEnabled(false);
					int numMinasAlrededor = casilla.getNumMinasAlrededor();
					if (numMinasAlrededor > 0) {
						boton.setText(Integer.toString(numMinasAlrededor));
					}
				}
			}
		}
	}

	private void clickDerechoBoton(MouseEvent e) {
		JButton btnButton = (JButton) e.getSource();
		String coordenada = btnButton.getName();
		int fila = Integer.parseInt(coordenada.split(", ")[0]);
		int columna = Integer.parseInt(coordenada.split(", ")[1]);

		if (btnButton.isEnabled()) {
			if (tableroBuscaminas.tieneBanderaCasilla(fila, columna)) {
				btnButton.setIcon(null);
				tableroBuscaminas.quitarBanderaCasilla(fila, columna);
				actualizarBanderasPuestas();
			} else if (tableroBuscaminas.getNumMinas() > tableroBuscaminas.getNumBanderas()) {
				tableroBuscaminas.ponerBanderaCasilla(fila, columna);
				String rutaImagen2 = "/com/proyectoFinal/grupo2/Juegos/Imagenes/banderaBuscaMinas.png";
				ImageIcon imagen = new ImageIcon(getClass().getResource(rutaImagen2));
				Image imagenOriginal2 = imagen.getImage();
				Image imagenRedimensionada2 = imagenOriginal2.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconRedimensionado2 = new ImageIcon(imagenRedimensionada2);
				btnButton.setIcon(iconRedimensionado2);
				actualizarBanderasPuestas();
			}
		}

	}

	private void clickIzquierdoBoton(MouseEvent e) {
		JButton btnButton = (JButton) e.getSource();
		String coordenada = btnButton.getName();
		int fila = Integer.parseInt(coordenada.split(", ")[0]);
		int columna = Integer.parseInt(coordenada.split(", ")[1]);
		if (!tableroBuscaminas.getCasilla(fila, columna).isBandera()) {
			if (tableroBuscaminas.tieneBanderaCasilla(fila, columna)) {
				btnButton.setIcon(null);
				tableroBuscaminas.quitarBanderaCasilla(fila, columna);
			}
			if (tableroBuscaminas.casillaContieneMina(fila, columna)) {
				btnButton.setBackground(Color.RED);
				mostrarMinas();
				mostrarDialogoPartidaPerdida();
			} else {
				tableroBuscaminas.marcarCasillaAbierta(fila, columna);
				actualizarBotones();
				actualizarCasillasAbiertas();
				actualizarCasillasCerradas();
				if (tableroBuscaminas.partidaGanada()) {
					mostrarMinas();
					desactivarBotones();
					mostrarDialogoPartidaGanada();
				}
			}
		}
	}

	private void actualizarBanderasPuestas() {
		String estiloCSSBanderas = "<html><body><p style='font-size: 18px;'>Banderas: "
				+ tableroBuscaminas.getNumBanderas() + "</p></body></html>";
		labelBanderasPuestas.setText(estiloCSSBanderas);
	}

	private void actualizarCasillasAbiertas() {
		String estiloCSSCasillasDescubiertas = "<html><body><p style='font-size: 18px;'>Casillas descubiertas: "
				+ tableroBuscaminas.getNumCasillasAbiertas() + "</p></body></html>";
		labelCasillasDescubiertas.setText(estiloCSSCasillasDescubiertas);
	}

	private void actualizarCasillasCerradas() {
		String estiloCSSCasillasCubiertas = "<html><body><p style='font-size: 18px;'>Casillas cubiertas: "
				+ ((tableroBuscaminas.getNumFilas() * tableroBuscaminas.getNumFilas())
						- tableroBuscaminas.getNumCasillasAbiertas())
				+ "</p></body></html>";
		labelCasillasCubiertas.setText(estiloCSSCasillasCubiertas);
	}

	private void desactivarBotones() {
		for (int i = 0; i < botonesTablero.length; i++) {
			for (int j = 0; j < botonesTablero[i].length; j++) {
				JButton boton = botonesTablero[i][j];
				boton.setEnabled(false);
			}
		}
	}

	private void mostrarDialogoPartidaGanada() {
		timer.stop();
		String rutaImagen2 = "/com/proyectoFinal/grupo2/Juegos/Imagenes/winBuscaMinas.png";
		ImageIcon imagen = new ImageIcon(getClass().getResource(rutaImagen2));
		Image imagenOriginal2 = imagen.getImage();
		Image imagenRedimensionada2 = imagenOriginal2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado2 = new ImageIcon(imagenRedimensionada2);
		String estiloCSS = "<html><body><p style='font-size: 16px;'>¡Has ganado, Enhorabuena!</p></body></html>";
		int seleccion = JOptionPane.showOptionDialog(null, estiloCSS, "Buscaminas", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, iconRedimensionado2, opcionesTrasAcabar, opcionesTrasAcabar[0]);
		if (seleccion == 0) {
			dispose();
			BuscaMinas buscaMinas = new BuscaMinas();
			buscaMinas.setVisible(true);
		} else {
			dispose();
		}
	}

	private void mostrarDialogoPartidaPerdida() {
		timer.stop();
		String rutaImagen2 = "/com/proyectoFinal/grupo2/Juegos/Imagenes/imagenBuscaMinasDanger.png";
		ImageIcon imagen = new ImageIcon(getClass().getResource(rutaImagen2));
		Image imagenOriginal2 = imagen.getImage();
		Image imagenRedimensionada2 = imagenOriginal2.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado2 = new ImageIcon(imagenRedimensionada2);
		String estiloCSS = "<html><body><p style='font-size: 16px;'>¡Has perdido el juego, tropezaste con la mina!</p></body></html>";
		int seleccion = JOptionPane.showOptionDialog(null, estiloCSS, "Buscaminas", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, iconRedimensionado2, opcionesTrasAcabar, opcionesTrasAcabar[0]);
		if (seleccion == 0) {
			dispose();
			BuscaMinas buscaMinas = new BuscaMinas();
			buscaMinas.setVisible(true);
		} else {
			dispose();
		}
	}

	private void mostrarMinas() {
		String rutaImagen = "/com/proyectoFinal/grupo2/Juegos/Imagenes/minaBuscaMinas.png";
		ImageIcon nuevoIcono = new ImageIcon(getClass().getResource(rutaImagen));
		Image imagenOriginal = nuevoIcono.getImage();
		Image imagenRedimensionada = imagenOriginal.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenRedimensionada);
		for (int i = 0; i < botonesTablero.length; i++) {
			for (int j = 0; j < botonesTablero[i].length; j++) {
				if (tableroBuscaminas.casillaContieneMina(i, j)) {
					botonesTablero[i][j].setIcon(iconRedimensionado);
				}
			}
		}

	}
}
