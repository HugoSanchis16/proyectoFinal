package com.proyectoFinal.grupo2.Juegos;

import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

public class BuscaMinas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean salir = false;
	final private static String[] niveles = { "Fácil", "Medio", "Difícil", "Cargar Partida" };
	private String[] opcionesTrasAcabar = { "Volver a Jugar", "Salir" };
	private JPanel contentPane;
	private int tamanoTauler;
	private int cantidadMinas;
	private int segundosTrascurridos;
	private JButton[][] botonesTablero;
	private JPanel panelTablero = new JPanel();
	private JLabel labelCasillasDescubiertas = new JLabel();
	private JLabel labelCasillasCubiertas = new JLabel();
	private JLabel labelBanderasPuestas = new JLabel();
	private JLabel labelTimer = new JLabel();
	boolean partidaVigente;
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

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuscaMinas() {
		String estiloCSS = "<html><body><p style='font-size: 16px;'>Selecciona un nivel</p></body></html>";
		JLabel label = new JLabel(estiloCSS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		int seleccion = JOptionPane.showOptionDialog(null, label, "Buscaminas", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, niveles, niveles[0]);
		if (seleccion != -1) {
			setVisible(true);
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
					int seleccion = JOptionPane.showOptionDialog(null,
							"<html><body><p style='font-size: 16px;'>Selecciona un nivel</p></body></html>",
							"Buscaminas", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, niveles,
							niveles[0]);
					if (seleccion != -1) {
						BuscaMinas frame = new BuscaMinas();
						frame.setVisible(true);
					}
				}
			});
			botonReset.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(botonReset);

			JButton botonGuardar = new JButton("Guardar Partida");
			botonGuardar.setFocusable(false);
			botonGuardar.setHorizontalAlignment(SwingConstants.CENTER);
			botonGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					timer.stop();
					tableroBuscaminas.setSegundosPartida(segundosTrascurridos);
					String tableroSerializado = TableroBuscaminas.serializeTablero(tableroBuscaminas);
					System.out.println(tableroSerializado);

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

			setSize(new Dimension(850, 850));
			setResizable(false);
			setLocationRelativeTo(null);

			iniciarTiempo(tableroBuscaminas);

			switch (seleccion) {
			case 0:
				tamanoTauler = 10;
				cantidadMinas = 10;
				this.setTitle("BuscaMinas - Facil");
				crearBotones(tamanoTauler);
				crearTablero();
				break;
			case 1:
				tamanoTauler = 15;
				cantidadMinas = 15;
				this.setTitle("BuscaMinas - Medio");
				crearBotones(tamanoTauler);
				crearTablero();
				break;
			case 2:
				tamanoTauler = 20;
				cantidadMinas = 30;
				this.setTitle("BuscaMinas - Dificil");
				crearBotones(tamanoTauler);
				crearTablero();
				break;
			case 3:
				tamanoTauler = 1;
				cantidadMinas = 1;
				crearBotones(tamanoTauler);
				crearTablero();
				cargarPartida();
				break;
			}
			JPanel panelInformacion = new JPanel();
			contentPane.add(panelInformacion, BorderLayout.NORTH);
			panelInformacion.setLayout(new GridLayout(2, 1));

			JPanel panelLabels1 = new JPanel();
			panelLabels1.setLayout(new GridLayout(1, 3));

			String estiloCSSMinas = "<html><body><p style='font-size: 16px;'>Minas: " + tableroBuscaminas.getNumMinas()
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

			String estiloCSSTimer = "<html><body><p style='font-size: 16px;'>Tiempo: " + segundosTrascurridos
					+ " segundos</p></body></html>";
			labelTimer.setText(estiloCSSTimer);
			labelTimer.setHorizontalAlignment(SwingConstants.CENTER);
			panelLabels1.add(labelTimer);

			panelInformacion.add(panelLabels1);

			JPanel panelLabels2 = new JPanel();
			panelLabels2.setLayout(new GridLayout(1, 3));

			actualizarCasillasAbiertas();
			labelCasillasDescubiertas.setHorizontalAlignment(SwingConstants.CENTER);

			panelLabels2.add(labelCasillasDescubiertas);

			actualizarCasillasCerradas();
			labelCasillasCubiertas.setHorizontalAlignment(SwingConstants.CENTER);
			panelLabels2.add(labelCasillasCubiertas);

			actualizarBanderasPuestas();
			labelBanderasPuestas.setHorizontalAlignment(SwingConstants.CENTER);
			panelLabels2.add(labelBanderasPuestas);
			panelInformacion.add(panelLabels2);

		} else {
			System.out.println("cerrar");
			setVisible(false);
			dispose();

		}

	}

	private void crearTablero() {
		tableroBuscaminas = new TableroBuscaminas(tamanoTauler, tamanoTauler, cantidadMinas);
		tableroBuscaminas.imprimirTablero();
	}

	public void eliminarBotones() {
		for (int i = 0; i < botonesTablero.length; i++) {
			for (int j = 0; j < botonesTablero[i].length; j++) {
				panelTablero.remove(botonesTablero[i][j]);
			}
		}
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
		mostrarBanderas();
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
				Image imagenRedimensionada2 = imagenOriginal2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
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

	private void iniciarTiempo(TableroBuscaminas tableroBuscaminas) {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				segundosTrascurridos++;
				String estiloCSSTimer = "<html><body><p style='font-size: 16px;'>Tiempo: " + segundosTrascurridos
						+ " segundos</p></body></html>";
				labelTimer.setText(estiloCSSTimer);
			}
		});
		timer.start();
	}

	private void actualizarCasillasAbiertas() {
		String estiloCSSCasillasDescubiertas = "<html><body><p style='font-size: 18px;'>Casillas descubiertas: "
				+ tableroBuscaminas.getNumCasillasAbiertas() + "</p></body></html>";
		labelCasillasDescubiertas.setText(estiloCSSCasillasDescubiertas);
	}

	private void actualizarCasillasCerradas() {

		ArrayList<Casilla> casillasCerradas = tableroBuscaminas.getCasillasCerradas();
		int casillasCerradasInt = casillasCerradas.size();
		System.out.println(casillasCerradasInt);

		String estiloCSSCasillasCubiertas2 = "<html><body><p style='font-size: 18px;'>Casillas cubiertas: "
				+ casillasCerradasInt + "</p></body></html>";
		labelCasillasCubiertas.setText(estiloCSSCasillasCubiertas2);
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

	private void cargarPartida() {

		try {
			String tableroSerializado = JOptionPane.showInputDialog("Introduce el string de la partida guardada:");
			TableroBuscaminas tablero = TableroBuscaminas.deserializeTablero(tableroSerializado);

			// Actualiza el tablero y otros elementos necesarios con los valores cargados
			tableroBuscaminas = tablero;
			tamanoTauler = tablero.getNumFilas();
			cantidadMinas = tablero.getNumMinas();
			segundosTrascurridos = tablero.getSegundosPartida();

			eliminarBotones();
			crearBotones(tamanoTauler);
			actualizarBotones();
			actualizarCasillasCerradas();
			actualizarBanderasPuestas();
			actualizarCasillasAbiertas();

		} catch (Exception e) {
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

	private void mostrarBanderas() {
		String rutaImagen = "/com/proyectoFinal/grupo2/Juegos/Imagenes/banderaBuscaMinas.png";
		ImageIcon nuevoIcono = new ImageIcon(getClass().getResource(rutaImagen));
		Image imagenOriginal = nuevoIcono.getImage();
		Image imagenRedimensionada = imagenOriginal.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionado = new ImageIcon(imagenRedimensionada);
		for (int i = 0; i < botonesTablero.length; i++) {
			for (int j = 0; j < botonesTablero[i].length; j++) {
				if (tableroBuscaminas.getCasilla(i, j).isBandera()) {
					botonesTablero[i][j].setIcon(iconRedimensionado);
				}
			}
		}

	}

}
