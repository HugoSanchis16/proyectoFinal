package com.proyectoFinal.grupo2.Clases;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import com.proyectoFinal.grupo2.Main.menuPrincipal;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerPerfil extends JFrame {

	private RoundedPanel imagenPerfilPanel;
	private JLabel nomLabel;
	private JLabel poblacioLabel;
	private JLabel correuLabel;

	public VerPerfil(Usuario usuario) {
		setTitle("Perfil");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel panel = new JPanel();

		// Imagen de perfil
		ImageIcon imagenPerfil = new ImageIcon(menuPrincipal.class.getResource("./Imagenes/perfil.png"));
		Image imagenRedimensionada = imagenPerfil.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		imagenPerfilPanel = new RoundedPanel();
		imagenPerfilPanel.setBackgroundImage(new ImageIcon(imagenRedimensionada));
		imagenPerfilPanel.setSize(200, 200);
		imagenPerfilPanel.setLocation(150, 20);
		imagenPerfilPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		imagenPerfilPanel.setLayout(new GridLayout(1, 1));

		JPanel infoPanel = new JPanel(new GridLayout(5, 1));
		infoPanel.setBounds(0, 220, 498, 230);
		nomLabel = new JLabel("Nombre: " + usuario.getNombre() + " " + usuario.getApellidos());
		poblacioLabel = new JLabel("Población: " + usuario.getPoblacion());
		correuLabel = new JLabel("Correo electrónico: " + usuario.getCorreoElectronico());
		panel.setLayout(null);

		panel.add(imagenPerfilPanel);
		infoPanel.add(nomLabel);
		infoPanel.add(poblacioLabel);
		infoPanel.add(correuLabel);
		panel.add(infoPanel);

		JButton atrasButton = new JButton("Atrás");
		atrasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		infoPanel.add(atrasButton);

		getContentPane().add(panel);
	}

	private class RoundedPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ImageIcon imagenPerfil;

		public void setBackgroundImage(ImageIcon backgroundImage) {
			this.imagenPerfil = backgroundImage;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (imagenPerfil != null) {
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int width = getWidth();
				int height = getHeight();

				int x = (width - imagenPerfil.getIconWidth()) / 2;
				int y = (height - imagenPerfil.getIconHeight()) / 2;

				imagenPerfil.paintIcon(this, g2d, x, y);

				g2d.dispose();
			}
		}
	}
}