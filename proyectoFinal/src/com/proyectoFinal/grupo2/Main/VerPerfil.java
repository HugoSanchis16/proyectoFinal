package com.proyectoFinal.grupo2.Main;

import javax.swing.*;

import com.proyectoFinal.grupo2.Clases.BDUtils;
import com.proyectoFinal.grupo2.Clases.Usuario;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerPerfil extends JFrame {

	boolean usuarioEliminadoCorrecto = false;

	public VerPerfil(Usuario usuario, menuPrincipal menuPrincipal) {
		setTitle("Perfil - " + usuario.getCorreoElectronico());
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton botonEliminar = new JButton("Eliminar Cuenta");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int opcion = JOptionPane.showOptionDialog(null, "¿Estás seguro de que deseas eliminar tu cuenta?",
						"Confirmar eliminación de cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
						new Object[] { "Sí", "No" }, "No");

				if (opcion == JOptionPane.YES_OPTION) {
					String correo = usuario.getCorreoElectronico();
					boolean eliminada = BDUtils.eliminarCuenta(correo);
					if (eliminada) {
						JOptionPane.showMessageDialog(null, "Tu cuenta ha sido eliminada correctamente.");
						Login login = new Login();
						login.setVisible(true);
						setUsuarioEliminadoCorrecto(true);
						menuPrincipal.dispose(); // Cerrar el JFrame menuPrincipal
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar tu cuenta.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		botonEliminar.setForeground(new Color(255, 255, 255));
		botonEliminar.setFont(new Font("Arial", Font.BOLD, 15));
		botonEliminar.setBackground(new Color(191, 0, 0));
		botonEliminar.setBounds(22, 510, 194, 41);
		panel.add(botonEliminar);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(88, 94, 170));
		panel_2.setBounds(10, 11, 476, 552);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBounds(122, 22, 241, 221);
		panel_2.add(layeredPane);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		if (BDUtils.cogerFotoPerfil(usuario.getCorreoElectronico()) != null) {
			ImageIcon perfilIcon = BDUtils.cogerFotoPerfil(usuario.getCorreoElectronico());
			Image perfilImage = perfilIcon.getImage();
			int width = perfilImage.getWidth(null);
			int height = perfilImage.getHeight(null);
			int targetWidth = 206;
			int targetHeight = 198;
			double aspectRatio = (double) width / height;

			// Calcula el nuevo tamaño manteniendo la relación de aspecto
			int newWidth = targetWidth;
			int newHeight = (int) (targetWidth / aspectRatio);

			// Verifica si la altura calculada es mayor que el alto objetivo
			// y ajusta el tamaño en consecuencia
			if (newHeight > targetHeight) {
				newWidth = (int) (targetHeight * aspectRatio);
				newHeight = targetHeight;
			}

			// Redimensiona la imagen
			Image resizedImage = perfilImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			ImageIcon resizedIcon = new ImageIcon(resizedImage);
			lblNewLabel.setIcon(resizedIcon);

			lblNewLabel.setBounds(10, 11, 206, 198);
			lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
			lblNewLabel.setVerticalAlignment(JLabel.CENTER);

			layeredPane.add(lblNewLabel, JLayeredPane.DEFAULT_LAYER);

			JLabel imagenCirculo = new JLabel();
			imagenCirculo.setOpaque(false);
			imagenCirculo.setIcon(new ImageIcon(
					VerPerfil.class.getResource("/com/proyectoFinal/grupo2/Main/Imagenes/CirculoSinFondo.PNG")));
			imagenCirculo.setBounds(10, 0, 231, 221);
			layeredPane.add(imagenCirculo, JLayeredPane.PALETTE_LAYER);
		}

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(10, 262, 444, 221);
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));

		JLabel nomLabel = new JLabel("Nombre: " + usuario.getNombre() + " " + usuario.getApellidos());
		panel_1.add(nomLabel);
		nomLabel.setFont(new Font("Arial", Font.BOLD, 17));
		nomLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel correuLabel = new JLabel("Correo electrónico: " + usuario.getCorreoElectronico());
		panel_1.add(correuLabel);
		correuLabel.setFont(new Font("Arial", Font.BOLD, 17));
		correuLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel poblacioLabel = new JLabel("Población: " + usuario.getPoblacion());
		panel_1.add(poblacioLabel);
		poblacioLabel.setFont(new Font("Arial", Font.BOLD, 17));
		poblacioLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
				JButton atrasButton = new JButton("Atrás");
				atrasButton.setBounds(265, 499, 189, 41);
				panel_2.add(atrasButton);
				atrasButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				atrasButton.setFont(new Font("Arial", Font.BOLD, 15));
	}

	public boolean isUsuarioEliminadoCorrecto() {
		return usuarioEliminadoCorrecto;
	}

	public void setUsuarioEliminadoCorrecto(boolean usuarioEliminadoCorrecto) {
		this.usuarioEliminadoCorrecto = usuarioEliminadoCorrecto;
	}

}