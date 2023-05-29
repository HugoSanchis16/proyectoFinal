package com.proyectoFinal.grupo2.Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.proyectoFinal.grupo2.Clases.Usuario;
import com.proyectoFinal.grupo2.Clases.VerPerfil;
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
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class menuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String nomUsuari;
	private BuscaMinas ventanaBuscaMinas;
	private JocDeLaVida ventanaJuegoDeLaVida;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//
//		try {
//			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					menuPrincipal frame = new menuPrincipal("Jose");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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

		JMenuItem mntmNewMenuItem = new JMenuItem("Eliminar cuenta");
		ImageIcon imagen = new ImageIcon(menuPrincipal.class.getResource("./Imagenes/cruzRoja.png"));
		Image imageRedimensionada = imagen.getImage().getScaledInstance(25, -1, Image.SCALE_SMOOTH);
		ImageIcon fotoRedimensionada = new ImageIcon(imageRedimensionada);
		JMenuItem verperfil= new JMenuItem("Ver Perfil");
		verperfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			VerPerfil verperfil= new VerPerfil(usuario);
			verperfil.setVisible(true);
			}
		});
		ImageIcon imagenPerfil = new ImageIcon(menuPrincipal.class.getResource("./Imagenes/perfil.png"));
		Image imageRedimensionada2 = imagenPerfil.getImage().getScaledInstance(25, -1, Image.SCALE_SMOOTH);
		ImageIcon fotoRedimensionada2 = new ImageIcon(imageRedimensionada2);
		mntmNewMenuItem.setIcon(fotoRedimensionada);
		mnNewMenu.add(mntmNewMenuItem);
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
		panelJuegos.add(botonPixelArt);

		JButton botonBuscaMinas = new JButton("");
		botonBuscaMinas.setOpaque(false);
		botonBuscaMinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ventanaBuscaMinas == null || !ventanaBuscaMinas.isVisible()) {
					ventanaBuscaMinas = new BuscaMinas();
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
		botonCerrarSesion.setOpaque(false);
		botonCerrarSesion.setBorderPainted(false);
		botonCerrarSesion.setFocusPainted(false);
		botonCerrarSesion.setContentAreaFilled(false);
		botonCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonCerrarSesion.setForeground(Color.WHITE);
		botonCerrarSesion.setBackground(new Color(153, 0, 0)); // Cambia el color de fondo a rojo
		botonCerrarSesion.setFont(new Font("Arial", Font.BOLD, 15));
		panelBotones.add(botonCerrarSesion);
	}

}
