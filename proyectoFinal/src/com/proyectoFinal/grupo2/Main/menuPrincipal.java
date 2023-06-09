package com.proyectoFinal.grupo2.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.proyectoFinal.grupo2.Clases.BDUtils;
import com.proyectoFinal.grupo2.Clases.Usuario;
import com.proyectoFinal.grupo2.Juegos.*;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class menuPrincipal extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private BuscaMinas ventanaBuscaMinas;
	private JocDeLaVida ventanaJuegoDeLaVida;
	private VerPerfil verPerfil;
	private PixelArt ventanaPixelArt;
	private String[] opcionesDialogo = { "Si", "No" };

	/**
	 * Create the frame.
	 */
	public menuPrincipal(Usuario usuario) {
		super("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 600);

		JMenuBar menuAjustes = new JMenuBar();
		setJMenuBar(menuAjustes);

		JMenu mnNewMenu = new JMenu("Ajustes");
		menuAjustes.add(mnNewMenu);

		JMenuItem verperfil = new JMenuItem("Ver Perfil");
		verperfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verPerfil == null || !verPerfil.isVisible()) {
					verPerfil = new VerPerfil(usuario, menuPrincipal.this);
					verPerfil.setVisible(true);
				} else {
					verPerfil.setVisible(true);

				}
			}
		});

		ImageIcon imagenPerfil = new ImageIcon(menuPrincipal.class.getResource("./Imagenes/perfil.png"));
		Image imageRedimensionada2 = imagenPerfil.getImage().getScaledInstance(25, -1, Image.SCALE_SMOOTH);
		ImageIcon fotoRedimensionada2 = new ImageIcon(imageRedimensionada2);

		verperfil.setIcon(fotoRedimensionada2);
		mnNewMenu.add(verperfil);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelBienvenida = new JPanel();
		contentPane.add(panelBienvenida, BorderLayout.NORTH);
		panelBienvenida.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel labelBienvenida = new JLabel("Bienvenido, " + usuario.getNombre());
		labelBienvenida.setBackground(new Color(255, 255, 255));
		labelBienvenida.setFont(new Font("Arial", Font.BOLD, 30));
		labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		labelBienvenida.setHorizontalTextPosition(SwingConstants.LEADING);
		panelBienvenida.add(labelBienvenida);

		JLabel labelSubtitulo = new JLabel("¿Te apetece jugar?");
		labelSubtitulo.setBackground(new Color(255, 255, 255));
		labelSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelSubtitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelBienvenida.add(labelSubtitulo);

		JPanel panelJuegos = new JPanel();
		contentPane.add(panelJuegos, BorderLayout.CENTER);
		panelJuegos.setLayout(new GridLayout(0, 3, 0, 0));

		JButton botonPixelArt = new JButton("");
		botonPixelArt.setOpaque(false);
		botonPixelArt.setIcon(new ImageIcon(menuPrincipal.class.getResource("./Imagenes/pixelArtLogo.JPG")));
		botonPixelArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ventanaPixelArt == null || !ventanaPixelArt.isVisible()) {
					ventanaPixelArt = new PixelArt(usuario);
				}else {
					ventanaPixelArt.setVisible(true);
				}
			}

		});
		panelJuegos.add(botonPixelArt);

		JButton botonBuscaMinas = new JButton("");
		botonBuscaMinas.setOpaque(false);
		botonBuscaMinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ventanaBuscaMinas == null || !ventanaBuscaMinas.isVisible()) {
					ventanaBuscaMinas = new BuscaMinas(usuario);
				}else {
					ventanaBuscaMinas.setVisible(true);
				}
			}
		});
		botonBuscaMinas.setIcon(new ImageIcon(menuPrincipal.class.getResource("./Imagenes/buscaminas.jpg")));
		panelJuegos.add(botonBuscaMinas);

		JButton botonJuegoDeLaVida = new JButton("");
		botonJuegoDeLaVida.setOpaque(false);
		botonJuegoDeLaVida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ventanaJuegoDeLaVida == null || !ventanaJuegoDeLaVida.isVisible()) {
					ventanaJuegoDeLaVida = new JocDeLaVida();
					ventanaJuegoDeLaVida.mostrarMenutamaño();
				}else {
					ventanaJuegoDeLaVida.setVisible(true);
				}
			}

		});
		botonJuegoDeLaVida.setIcon(new ImageIcon(menuPrincipal.class.getResource("./Imagenes/juegoDeLaVida.jpg")));
		panelJuegos.add(botonJuegoDeLaVida);

		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelBotones.setBackground(new Color(255, 255, 255));
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		JButton botonCerrarSesion = new JButton("Cerrar Sesión");
		botonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ventanaBuscaMinas != null && ventanaBuscaMinas.isVisible()) {
					String estiloCSS = "<html><body><p style='font-size: 16px;'>¿Desea guardar partida?</p></body></html>";
					JLabel label = new JLabel(estiloCSS);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					int seleccion = JOptionPane.showOptionDialog(null, label, "Buscaminas", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, opcionesDialogo, opcionesDialogo[0]);
					if (seleccion == 0) {
						ventanaBuscaMinas.mostrarDialogoNombrePartida();

					}
					ventanaBuscaMinas.dispose();
				}

				if (ventanaPixelArt != null && ventanaPixelArt.isVisible()) {
					String estiloCSS = "<html><body><p style='font-size: 16px;'>¿Desea guardar partida?</p></body></html>";
					JLabel label = new JLabel(estiloCSS);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					int seleccion = JOptionPane.showOptionDialog(null, label, "PixelArt", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, opcionesDialogo, opcionesDialogo[0]);
					if (seleccion == 0) {
						ventanaPixelArt.mostrarDialogoNombrePartida();
					}
					ventanaPixelArt.dispose();
				}

				if (ventanaJuegoDeLaVida != null && ventanaJuegoDeLaVida.isVisible()) {
					ventanaJuegoDeLaVida.dispose();
				}
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});

		addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (ventanaBuscaMinas != null && ventanaBuscaMinas.isVisible()) {
					String estiloCSS = "<html><body><p style='font-size: 16px;'>¿Desea guardar partida?</p></body></html>";
					JLabel label = new JLabel(estiloCSS);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					int seleccion = JOptionPane.showOptionDialog(null, label, "Buscaminas", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, opcionesDialogo, opcionesDialogo[0]);
					if (seleccion == 0) {
						ventanaBuscaMinas.mostrarDialogoNombrePartida();

					}
					ventanaBuscaMinas.dispose();
				}

				if (ventanaPixelArt != null && ventanaPixelArt.isVisible()) {
					String estiloCSS = "<html><body><p style='font-size: 16px;'>¿Desea guardar partida?</p></body></html>";
					JLabel label = new JLabel(estiloCSS);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					int seleccion = JOptionPane.showOptionDialog(null, label, "PixelArt", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, opcionesDialogo, opcionesDialogo[0]);
					if (seleccion == 0) {
						ventanaPixelArt.mostrarDialogoNombrePartida();
					}
					ventanaPixelArt.dispose();
				}
				if (ventanaJuegoDeLaVida != null && ventanaJuegoDeLaVida.isVisible()) {
					ventanaJuegoDeLaVida.dispose();
				}
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

		botonCerrarSesion.setBorderPainted(false);
		botonCerrarSesion.setFocusPainted(false);
		botonCerrarSesion.setContentAreaFilled(true);
		botonCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCerrarSesion.setForeground(Color.WHITE);
		botonCerrarSesion.setBackground(new Color(153, 0, 0)); // Cambia el color de fondo a rojo
		botonCerrarSesion.setFont(new Font("Arial", Font.BOLD, 15));
		panelBotones.add(botonCerrarSesion);
		setLocationRelativeTo(null);

	}

}
