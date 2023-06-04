package com.proyectoFinal.grupo2.Juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import com.proyectoFinal.grupo2.Clases.BDUtils;
import com.proyectoFinal.grupo2.Clases.TableroBuscaminas;
import com.proyectoFinal.grupo2.Clases.Usuario;

import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

public class PixelArt extends JFrame {

	private static JPanel contentPane;
	private static JPanel panel = new JPanel();
	private static boolean ratonPulsado = false;
	private static int filas = 60;
	private static int columnas = 60;
	private static int width = 12;
	private static Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	private static JLabel[][] matriz = new JLabel[filas][columnas];
	private static PixelArt frame;
	private static Color colorFons = Color.BLACK;
	private static String nombre = "";
	private static JPanel panelPrincipal = new JPanel();
	private static JPanel panelTemporal = new JPanel();
	private static JPanel panelBotones = new JPanel();
	private static String nombreUsuario = "";
	public static int iteracionActual;
	private String[] opcionesDialogo = { "Si", "No" };
	public static ArrayList<HashMap<String, String>> partidas = BDUtils.recuperarPartidasPixelArt(nombre);

	/**
	 * Create the frame.
	 */
	public PixelArt(Usuario usuario) {
		nombreUsuario = usuario.getNombre();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PixelArt");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JFrame elegirTamano = new JFrame();
		// Diàleg de confirmació
		Object[] opcions = { "Pequeño", "Mediano", "Grande" };
		ImageIcon icona = new ImageIcon("pregunta.png");
		String estiloCSS = "<html><body><p style='font-size: 16px;'>Elija el tamaño del tablero</p></body></html>";
		int resposta = JOptionPane.showOptionDialog(elegirTamano, // contenidor d'alt nivell
				estiloCSS, // text
				"Tamaño del tablero", // títol del diàleg
				JOptionPane.YES_NO_CANCEL_OPTION, // Botons
				JOptionPane.QUESTION_MESSAGE, // Tipus de diàleg: icona
				icona, // Icona personalitzada
				opcions, // Textos dels botons personalitzats
				opcions[0]); // Opció seleccionada per defecte
		// acció segons la resposta
		switch (resposta) {
		case JOptionPane.YES_OPTION:
			filas = 30;
			columnas = 30;
			break;
		case JOptionPane.NO_OPTION:
			filas = 40;
			columnas = 40;
			break;
		default:
			filas = 60;
			columnas = 60;
		}

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ratonPulsado = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				ratonPulsado = false;
			}
		});

		addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				String estiloCSS = "<html><body><p style='font-size: 16px;'>¿Desea guardar partida?</p></body></html>";
				JLabel label = new JLabel(estiloCSS);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				int seleccion = JOptionPane.showOptionDialog(null, label, "PixelArt", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, opcionesDialogo, opcionesDialogo[0]);
				if (seleccion == 0) {
					mostrarDialogoNombrePartida();
				}
				panelTemporal.removeAll();
				panelBotones.removeAll();
				panelPrincipal.removeAll();
				removeAll();
				dispose();

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

		});

		panel = pintarPA();
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane.add(panel);
		pack();
	}

	public static JPanel pintarPA() {

		// Dimensiones de la imagen

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				matriz[i][j] = new JLabel();
				matriz[i][j].setName(i + "-" + j);
				matriz[i][j].setPreferredSize(new Dimension(width, width));
				matriz[i][j].setFocusable(false);
				matriz[i][j].setBorder(border);
				matriz[i][j].setBackground(new ColorUIResource(220, 220, 220));
				matriz[i][j].setOpaque(true);

				matriz[i][j].addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						JLabel pixel = (JLabel) e.getComponent();
						if (ratonPulsado) {
							if (SwingUtilities.isRightMouseButton(e)) { // Boton derecho
								pixel.setBackground(new ColorUIResource(220, 220, 220));
								pixel.setOpaque(true);
							} else { // Boton izquierdo
								pixel.setBackground(colorFons);
								pixel.setOpaque(true);
							}
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
						ratonPulsado = true;
						JLabel pixel = (JLabel) e.getComponent();
						if (SwingUtilities.isRightMouseButton(e)) { // Boton derecho
							pixel.setBackground(new ColorUIResource(220, 220, 220));
							pixel.setOpaque(true);
						} else { // Boton izquierdo
							pixel.setBackground(colorFons);
							pixel.setOpaque(true);
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						ratonPulsado = false;
					}
				});

				panelTemporal.add(matriz[i][j]);
			}
		}

		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelTemporal.setLayout(new GridLayout(filas, columnas, 0, 0));
		panelTemporal.setBackground(new Color(47, 52, 63));

		panelPrincipal.add(panelTemporal);

		panelPrincipal.add(panelBotones);

		JButton limpiar = new JButton("Limpiar");
		panelBotones.add(limpiar);

		limpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < filas; i++) {
					for (int j = 0; j < columnas; j++) {
						matriz[i][j].setBackground(new ColorUIResource(220, 220, 220));
					}
				}
			}
		});

		JButton elegirColor = new JButton("Elegir Color");
		panelBotones.add(elegirColor);

		elegirColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame frameColor = new JFrame();
				colorFons = JColorChooser.showDialog(frameColor, "Elige el color principal", Color.WHITE);

				if (colorFons == null) {
					// apliquem el color triat al contenidor de l'etiqueta
					colorFons = Color.BLACK;
				}
			}
		});

		JButton guardar = new JButton("Guardar");
		panelBotones.add(guardar);

		guardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JLabel[][] matrizCopia = matriz.clone();
				String base64String = serializeTablero(matrizCopia);
				for (int i = 0; i < filas; i++) {
					for (int j = 0; j < columnas; j++) {
						if (("" + matriz[i][j].getBackground())
								.equalsIgnoreCase("javax.swing.plaf.ColorUIResource[r=47,g=52,b=63]")) {
							matriz[i][j].setBackground(new ColorUIResource(220, 220, 220));
						}
					}
				}
				JFrame frameNombrePartida = new JFrame();
				// Diàleg de confirmació
				// JComponent.setDefaultLocale(Locale.GERMAN);
				String resposta = "";
				while (resposta == null || resposta.isBlank()) {
					resposta = JOptionPane.showInputDialog(frame, // contenidor d'alt nivell
							"Introduce el nombre de la partida"); // text
				}
				BDUtils.guardarPartidaPixelArt(nombreUsuario, resposta, base64String);
			}
		});

		JButton continuar = new JButton("Continuar partida anterior");
		panelBotones.add(continuar);

		JButton exportar = new JButton("Exportar a PNG");
		panelBotones.add(continuar);

		exportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int width = panel.getWidth();
				int height = panel.getHeight();
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = image.createGraphics();
				panel.paint(g2d);
				g2d.dispose();
				try {
					ImageIO.write(image, "png", new File("imagen.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});

		panelBotones.add(limpiar);
		panelBotones.add(guardar);
		panelBotones.add(continuar);
		panelBotones.add(exportar);
		panelBotones.setBackground(new Color(47, 52, 63));

		return panelPrincipal;
	}

	public void mostrarDialogoNombrePartida() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(
				"<html><body><p style='font-size: 15px; margin: 10px 20px;'>Introduce un nombre:</p></body></html>");
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
					if (BDUtils.guardarPartidaPixelArt(nombreUsuario, nombrePartida, matrizABase64(matriz))) {
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

	public static void cargarPartida(String nombre, String fecha, String partidaSerializada) {
		panelTemporal.removeAll();
		contentPane.remove(panelTemporal);
		matriz = deserializeTablero(partidaSerializada);

		panelTemporal.add(recorrerMatriz(matriz));
		panelTemporal.setLayout(new BoxLayout(panelTemporal, BoxLayout.Y_AXIS));
		panelPrincipal.add(panelBotones);

		panelPrincipal.repaint();
		panelPrincipal.revalidate();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public static JPanel recorrerMatriz(JLabel[][] matrizPrueba) {
		int widthTemp = 0;
		JPanel panelTemporal = new JPanel();

		for (int i = 0; i < matrizPrueba.length; i++) {
//			System.out.println("Max j: " + matrizPrueba[i].length);
			for (int j = 0; j < matrizPrueba[i].length; j++) {
//				System.out.println(i + "  " + j);
				if (matriz[i][j] != null) {
					matriz[i][j].setName(i + "-" + j);
					matriz[i][j].setPreferredSize(new Dimension(width, width));
					matriz[i][j].setFocusable(false);
					matriz[i][j].setBorder(border);
					matriz[i][j].setOpaque(true);
					if (("" + matriz[i][j].getBackground())
							.equalsIgnoreCase("javax.swing.plaf.ColorUIResource[r=47,g=52,b=63]")) {
						matriz[i][j].setBackground(new ColorUIResource(220, 220, 220));
					}
					matriz[i][j].addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent e) {
							JLabel pixel = (JLabel) e.getComponent();
							if (ratonPulsado) {
								if (SwingUtilities.isRightMouseButton(e)) { // Boton derecho
									pixel.setBackground(new ColorUIResource(220, 220, 220));
									pixel.setOpaque(true);
								} else { // Boton izquierdo
									pixel.setBackground(colorFons);
									pixel.setOpaque(true);
								}

							}
						}

						@Override
						public void mousePressed(MouseEvent e) {
							ratonPulsado = true;
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							ratonPulsado = false;
						}
					});
					panelTemporal.add(matriz[i][j]);
					if (widthTemp < i) {
						widthTemp = i;
					}
				}
			}
		}
		widthTemp++;

		panelTemporal.setLayout(new GridLayout(widthTemp, widthTemp, 0, 0));

		return panelTemporal;
	}

	public static String matrizABase64(JLabel[][] matrizPrueba) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(matrizPrueba);
			objectOutputStream.close();
			return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JLabel[][] base64AMatriz(String base64String) {
		try {
			byte[] data = Base64.getDecoder().decode(base64String);
			ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
			JLabel[][] matriz = (JLabel[][]) objectInputStream.readObject();
			objectInputStream.close();
			return matriz;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String serializeTablero(JLabel[][] matrizPrueba) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(matrizPrueba);
			oos.close();
			byte[] bytes = baos.toByteArray();
			return java.util.Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JLabel[][] deserializeTablero(String tableroSerializado) {
		try {

			byte[] bytes = Base64.getDecoder().decode(tableroSerializado);
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			JLabel[][] tablero = (JLabel[][]) ois.readObject();
			ois.close();
			bis.close();
			return tablero;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
