package com.proyectoFinal.grupo2.Clases;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.proyectoFinal.grupo2.Main.menuPrincipal;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.SwingConstants;

public class VerPerfil extends JFrame {

	/**
	 * 
	 */
	private JLabel imagenPerfilLabel;
	private JLabel nomLabel;
	private JLabel poblacioLabel;
	private JLabel correuLabel;

	/**
	 * Create the frame.
	 */
	public VerPerfil(Usuario usuario) {
		// Configuración de la ventana
		setTitle("Perfil");
		setSize(500, 500);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);

		// Panel principal
		JPanel panel = new JPanel();

		// Imagen de perfil
		ImageIcon imagenPerfil = new ImageIcon(menuPrincipal.class.getResource("./Imagenes/perfil.png"));
		; // Ruta de la imagen de perfil
		Image imagenRedimensionada = imagenPerfil.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		imagenPerfilLabel = new JLabel(new ImageIcon(imagenRedimensionada));
		imagenPerfilLabel.setBounds(12, 12, 486, 210);
		imagenPerfilLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		imagenPerfilLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Información del perfil
		JPanel infoPanel = new JPanel(new GridLayout(5, 1));
		infoPanel.setBounds(0, 220, 498, 230);
		nomLabel = new JLabel("Nombre: " + usuario.getNombre() + " " + usuario.getApellidos());
		poblacioLabel = new JLabel("Población: " + usuario.getPoblacion());
		correuLabel = new JLabel("Correo electrónico: " + usuario.getCorreoElectronico());

		// Botón "Atrás"
		JButton atrasButton = new JButton("Atrás");
		atrasButton.setBounds(0, 450, 498, 25);
		panel.setLayout(null);

		// Agregar componentes al panel principal
		panel.add(imagenPerfilLabel);
		infoPanel.add(nomLabel);
		infoPanel.add(poblacioLabel);
		infoPanel.add(correuLabel);
		panel.add(infoPanel);
		panel.add(atrasButton);

		getContentPane().add(panel);
	}

}

//menuPrincipal.class.getResource("./Imagenes/perfil.png")));
