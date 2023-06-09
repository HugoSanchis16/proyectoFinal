package com.proyectoFinal.grupo2.Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.proyectoFinal.grupo2.Clases.BDUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField textFieldCorreo;
	private JPasswordField contrasenya;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		// Configure the FlatLaf theme
		try {
			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nomUsuari = new JLabel("Correo");
		nomUsuari.setFont(new Font("Yu Gothic UI", Font.PLAIN, 23));
		nomUsuari.setHorizontalAlignment(SwingConstants.LEFT);
		nomUsuari.setBounds(161, 126, 98, 45);
		contentPane.add(nomUsuari);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setToolTipText("");
		textFieldCorreo.setBounds(161, 173, 407, 38);
		contentPane.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);

		JButton btnNewButton = new JButton("Iniciar sesió");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(161, 344, 407, 38);
		contentPane.add(btnNewButton);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registre registre = new Registre();
				registre.setVisible(true);
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrarse.setBounds(161, 404, 407, 38);
		contentPane.add(btnRegistrarse);

		contrasenya = new JPasswordField();
		contrasenya.setToolTipText("");
		contrasenya.setColumns(10);
		contrasenya.setBounds(161, 278, 407, 38);
		contentPane.add(contrasenya);

		JLabel lblContrasenya = new JLabel("Contrasenya");
		lblContrasenya.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenya.setFont(new Font("Yu Gothic UI", Font.PLAIN, 23));
		lblContrasenya.setBounds(161, 232, 187, 45);
		contentPane.add(lblContrasenya);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(90, 98, 167));
		panel.setBounds(126, 50, 477, 508);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel error = new JLabel("");
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setFont(new Font("Arial", Font.BOLD, 15));
		error.setForeground(new Color(222, 31, 33));
		error.setBounds(10, 414, 457, 14);
		panel.add(error);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contra = String.valueOf(contrasenya.getPassword());
				String correo = textFieldCorreo.getText();
				if (BDUtils.usuarioExiste(correo, contra)) {
					menuPrincipal menu = new menuPrincipal(BDUtils.comprobarLogin(correo, contra));
					menu.setVisible(true);
					setVisible(false);
					dispose();
				} else {
					error.setText("El usuario o contraseña no existe");
				}
			}
		});
//		
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
