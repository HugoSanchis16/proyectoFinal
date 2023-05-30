package com.proyectoFinal.grupo2.Juegos;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.proyectoFinal.grupo2.Clases.BDUtils;
import com.proyectoFinal.grupo2.Clases.Casilla;
import com.proyectoFinal.grupo2.Clases.TableroBuscaminas;
import com.proyectoFinal.grupo2.Clases.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;

public class BuscaMinas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
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
	public Usuario usuarioBuscaMinas;

	/**
	 * Create the frame.
	 */
	public BuscaMinas(Usuario usuario) {

		usuarioBuscaMinas = usuario;
		String estiloCSS = "<html><body><p style='font-size: 16px;'>Selecciona un nivel</p></body></html>";
		JLabel label = new JLabel(estiloCSS);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		int seleccion = JOptionPane.showOptionDialog(null, label, "Buscaminas", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, niveles, niveles[0]);
		if (seleccion != -1) {
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

					BuscaMinas frame = new BuscaMinas(usuario);
					frame.setVisible(true);

				}
			});
			botonReset.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(botonReset);

			JButton botonGuardar = new JButton("Guardar Partida");
			botonGuardar.setFocusable(false);
			botonGuardar.setHorizontalAlignment(SwingConstants.CENTER);
			botonGuardar.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JPanel panel = new JPanel();
			        JLabel label = new JLabel("<html><body><p style='font-size: 15px; margin: 10px 20px;'>Introduce un nombre:</p></body></html>");
			        JTextField textField = new JTextField(10);
			        panel.add(label);
			        panel.add(textField);

			        JButton buttonGuardar = new JButton("Guardar");
			        buttonGuardar.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
			        int buttonMargin = 7;
			        buttonGuardar.setMargin(new Insets(buttonMargin, buttonMargin, buttonMargin, buttonMargin));


			        // Crear el diálogo sin barra de título
			        JDialog dialog = new JDialog((JFrame) null, "Guardar partida", true);
			        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        dialog.setResizable(false);

			        dialog.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			                dialog.dispose();
			            }
			        });

			        // Cambiar el layout del panel
			        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			        dialog.getContentPane().setLayout(new BorderLayout());
			        dialog.getContentPane().add(panel, BorderLayout.CENTER);
			        dialog.getContentPane().add(buttonGuardar, BorderLayout.SOUTH);

			        buttonGuardar.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                String nombrePartida = textField.getText();

			                if (!nombrePartida.isEmpty() && !nombrePartida.isBlank()) {
			                    if (BDUtils.guardarPartidaBuscaMinas(usuario.getCorreoElectronico(),
			                            serializarTablero(), nombrePartida)) {
			                        JOptionPane.showMessageDialog(null, "Partida guardada correctamente.", "Éxito",
			                                JOptionPane.INFORMATION_MESSAGE);
			                        dispose();
			                        dialog.dispose();
			                    }
			                }
			            }
			        });

			        dialog.pack();
			        dialog.setLocationRelativeTo(null);

			        dialog.setVisible(true);
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
				setVisible(true);
				break;
			case 1:
				tamanoTauler = 15;
				cantidadMinas = 15;
				this.setTitle("BuscaMinas - Medio");
				crearBotones(tamanoTauler);
				crearTablero();
				setVisible(true);
				break;
			case 2:
				tamanoTauler = 20;
				cantidadMinas = 30;
				this.setTitle("BuscaMinas - Dificil");
				crearBotones(tamanoTauler);
				crearTablero();
				setVisible(true);
				break;
			case 3:
				tamanoTauler = 1;
				cantidadMinas = 1;
				crearBotones(tamanoTauler);
				crearTablero();
				setVisible(false);
				mostrarPartidas(usuarioBuscaMinas.getCorreoElectronico());
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

	public void mostrarDialogoPartidaGanada() {
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
			BuscaMinas buscaMinas = new BuscaMinas(usuarioBuscaMinas);
			buscaMinas.setVisible(true);
		} else {
			dispose();
		}
	}

	public void mostrarDialogoPartidaPerdida() {
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
			BuscaMinas buscaMinas = new BuscaMinas(usuarioBuscaMinas);
			buscaMinas.setVisible(true);
		} else {
			dispose();
		}
	}

	public void mostrarPartidas(String email) {
		ArrayList<HashMap<String, String>> partidasArrayList = BDUtils.recuperarPartidasBuscaMinas(email);
		System.out.println(partidasArrayList);

		if (partidasArrayList.size() > 0) {
			JDialog dialogo = new JDialog();
			dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialogo.setUndecorated(true);

			JPanel panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout());

			JLabel label = new JLabel(
					"<html><body><p style='font-size: 15px; margin: auto 20px;'>¡Elige tu partida!</p></body></html>");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(new EmptyBorder(10, 0, 15, 0));
			panelPrincipal.add(label, BorderLayout.NORTH);

			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1.0;
			constraints.insets = new Insets(10, 10, 10, 10);

			for (HashMap<String, String> partida : partidasArrayList) {
				String nombrePartida = partida.get("nombre_partida");

				JButton boton = new JButton("<html><body><p style='font-size: 14px; font-weight: bold;'>"
						+ nombrePartida + "</p></body></html>");
				boton.setFocusPainted(false);
				boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

				String partidaElegida64 = partida.get("matriz"); // Capturar la partida actual
				boton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Has hecho clic en el botón: " + nombrePartida);
						cargarPartida(partidaElegida64); // Llamar a la función cargarPartida con el valor seleccionado
						dialogo.dispose();
						setVisible(true);
					}
				});

				constraints.gridy++; // Incrementar el índice de fila en el GridBagLayout

				panelBotones.add(boton, constraints);
			}

			panelPrincipal.add(panelBotones, BorderLayout.CENTER);
			dialogo.getContentPane().add(panelPrincipal);
			dialogo.pack();
			dialogo.setLocationRelativeTo(null);
			dialogo.setModal(true);
			dialogo.setVisible(true);

		} else {
			JDialog noPartidasDialogo = new JDialog();
			noPartidasDialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			noPartidasDialogo.setTitle("Aviso");
			noPartidasDialogo.setModal(true);
			JPanel panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout());

			JLabel label = new JLabel("No hay partidas guardadas.");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBorder(new EmptyBorder(15, 15, 15, 15));
			panelPrincipal.add(label, BorderLayout.CENTER);

			JButton botonAceptar = new JButton("Aceptar");
			botonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					noPartidasDialogo.dispose();
				}
			});

			JPanel panelBoton = new JPanel();
			panelBoton.add(botonAceptar);
			panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

			noPartidasDialogo.getContentPane().add(panelPrincipal);
			noPartidasDialogo.pack();
			noPartidasDialogo.setPreferredSize(new Dimension(300, 300));
			noPartidasDialogo.setLocationRelativeTo(null);
			noPartidasDialogo.setVisible(true);
		}
	}

	private void cargarPartida(String partida64) {

		try {

			TableroBuscaminas tablero = TableroBuscaminas.deserializeTablero(partida64);

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

	public String serializarTablero() {
		timer.stop();
		tableroBuscaminas.setSegundosPartida(segundosTrascurridos);
		String tableroSerializado = TableroBuscaminas.serializeTablero(tableroBuscaminas);
		System.out.println(tableroSerializado);
		return tableroSerializado;
	}

}
