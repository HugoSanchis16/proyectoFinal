package com.proyectoFinal.grupo2.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.proyectoFinal.grupo2.Clases.BDUtils;
import com.proyectoFinal.grupo2.Clases.Usuario;

public class Registre extends JFrame {

	JFileChooser fileChooser = new JFileChooser();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField apellidosField;
	private JTextField poblacioField;
	private JTextField correoField;
	private JPasswordField contrasenya;
	private JPasswordField contrasenya2;
	private JLabel errorNombre;

	/**
	 * Create the frame.
	 */
	public Registre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 835);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(90, 98, 167));
		panel.setBounds(124, 0, 478, 762);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel nomUsuari = new JLabel("REGISTRE");
		nomUsuari.setBounds(139, 0, 196, 45);
		panel.add(nomUsuari);
		nomUsuari.setFont(new Font("Yu Gothic UI", Font.PLAIN, 23));
		nomUsuari.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(248, 710, 196, 38);
		panel.add(btnRegistrarse);
		btnRegistrarse.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));

		textField = new JTextField();
		textField.setBounds(37, 75, 407, 31);
		panel.add(textField);
		textField.setToolTipText("");
		textField.setColumns(10);

		JLabel lblContrasenya = new JLabel("COGNOMS");
		lblContrasenya.setBounds(37, 131, 187, 31);
		panel.add(lblContrasenya);
		lblContrasenya.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenya.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));

		correoField = new JTextField();
		correoField.setToolTipText("");
		correoField.setColumns(10);
		correoField.setBounds(37, 449, 407, 31);
		panel.add(correoField);

		JLabel errorImatge = new JLabel("");
		errorImatge.setForeground(new Color(222, 31, 33));
		errorImatge.setFont(new Font("Arial", Font.BOLD, 13));
		errorImatge.setBounds(37, 282, 407, 14);
		panel.add(errorImatge);

		JButton btnNewButton = new JButton("Insereix");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File(""));

				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					// Verificar la extensión del archivo seleccionado
					String extension = getExtension(selectedFile);
					if (!isValidExtension(extension)) {
						errorImatge.setText("Formato de imagen incorrecto");
						return;
					} else if (fileChooser.getSelectedFile() == null) {
						errorImatge.setText("Formato de imagen incorrecto");
						return;
					} else {
						errorImatge.setText("");
					}
				} else if (result == JFileChooser.CANCEL_OPTION) {
					// Acciones cuando se cancela la selección del archivo
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		btnNewButton.setBounds(139, 240, 305, 31);
		panel.add(btnNewButton);

		JLabel lblNom = new JLabel("NOM");
		lblNom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNom.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblNom.setBounds(37, 44, 187, 31);
		panel.add(lblNom);

		apellidosField = new JTextField();
		apellidosField.setToolTipText("");
		apellidosField.setColumns(10);
		apellidosField.setBounds(37, 173, 407, 31);
		panel.add(apellidosField);

		poblacioField = new JTextField();
		poblacioField.setToolTipText("");
		poblacioField.setColumns(10);
		poblacioField.setBounds(37, 349, 407, 31);
		panel.add(poblacioField);

		JLabel lblImatge = new JLabel("IMATGE");
		lblImatge.setHorizontalAlignment(SwingConstants.LEFT);
		lblImatge.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblImatge.setBounds(37, 241, 98, 31);
		panel.add(lblImatge);

		JLabel lblPoblacio = new JLabel("POBLACIO");
		lblPoblacio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoblacio.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblPoblacio.setBounds(37, 307, 136, 31);
		panel.add(lblPoblacio);

		JLabel lblContrasenya_2 = new JLabel("CONTRASENYA");
		lblContrasenya_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenya_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblContrasenya_2.setBounds(37, 504, 199, 31);
		panel.add(lblContrasenya_2);

		contrasenya = new JPasswordField();
		contrasenya.setToolTipText("");
		contrasenya.setColumns(10);
		contrasenya.setBounds(37, 546, 407, 31);
		panel.add(contrasenya);

		JLabel lblContrasenya_2_1 = new JLabel("REPETEIX CONTRASENYA");
		lblContrasenya_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenya_2_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblContrasenya_2_1.setBounds(37, 601, 274, 31);
		panel.add(lblContrasenya_2_1);

		contrasenya2 = new JPasswordField();
		contrasenya2.setToolTipText("");
		contrasenya2.setColumns(10);
		contrasenya2.setBounds(37, 643, 407, 31);
		panel.add(contrasenya2);

		JLabel lblContrasenya_2_2 = new JLabel("CORREU");
		lblContrasenya_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenya_2_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblContrasenya_2_2.setBounds(37, 407, 149, 31);
		panel.add(lblContrasenya_2_2);

		errorNombre = new JLabel("");
		errorNombre.setFont(new Font("Arial", Font.BOLD, 13));
		errorNombre.setForeground(new Color(222, 31, 33));
		errorNombre.setBounds(37, 117, 407, 14);
		panel.add(errorNombre);

		JLabel errorApellidos = new JLabel("");
		errorApellidos.setForeground(new Color(222, 31, 33));
		errorApellidos.setFont(new Font("Arial", Font.BOLD, 13));
		errorApellidos.setBounds(37, 215, 407, 14);
		panel.add(errorApellidos);

		JLabel errorPoblacio = new JLabel("");
		errorPoblacio.setForeground(new Color(222, 31, 33));
		errorPoblacio.setFont(new Font("Arial", Font.BOLD, 13));
		errorPoblacio.setBounds(37, 391, 407, 14);
		panel.add(errorPoblacio);

		JLabel errorCorreo = new JLabel("");
		errorCorreo.setForeground(new Color(222, 31, 33));
		errorCorreo.setFont(new Font("Arial", Font.BOLD, 13));
		errorCorreo.setBounds(37, 491, 407, 14);
		panel.add(errorCorreo);

		JLabel errorContrasena1 = new JLabel("");
		errorContrasena1.setForeground(new Color(222, 31, 33));
		errorContrasena1.setFont(new Font("Arial", Font.BOLD, 13));
		errorContrasena1.setBounds(37, 588, 407, 14);
		panel.add(errorContrasena1);

		JLabel errorContrasena2 = new JLabel("");
		errorContrasena2.setForeground(new Color(222, 31, 33));
		errorContrasena2.setFont(new Font("Arial", Font.BOLD, 13));
		errorContrasena2.setBounds(37, 685, 407, 14);
		panel.add(errorContrasena2);

		JLabel errorGeneral = new JLabel("");
		errorGeneral.setFont(new Font("Arial", Font.BOLD, 15));
		errorGeneral.setForeground(new Color(222, 31, 33));
		errorGeneral.setHorizontalAlignment(SwingConstants.CENTER);
		errorGeneral.setBounds(10, 40, 458, 14);
		panel.add(errorGeneral);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
				setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		btnLogin.setBounds(37, 710, 196, 38);
		panel.add(btnLogin);
		setResizable(false);

		btnRegistrarse.addActionListener(e -> {
			String nombre = textField.getText();
			String apellidos = apellidosField.getText();

			File selectedFile = fileChooser.getSelectedFile();
			File imageFile = new File(selectedFile.getAbsolutePath());

			byte[] blobData = null;
			if (selectedFile != null) {
			    try (FileInputStream fis = new FileInputStream(selectedFile);
			            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			        byte[] buffer = new byte[4096];
			        int bytesRead;
			        while ((bytesRead = fis.read(buffer)) != -1) {
			            bos.write(buffer, 0, bytesRead);
			        }
			        blobData = bos.toByteArray();
			    } catch (IOException e1) {
			        e1.printStackTrace();
			    }
			}

			String poblacio = poblacioField.getText();
			String correo = correoField.getText();
			String contra = String.valueOf(contrasenya.getPassword());
			String contra2 = String.valueOf(contrasenya2.getPassword());

			if (validarNombre(nombre)) {
				errorNombre.setText("Nombre de registro Incorrecto");
			} else {
				errorNombre.setText("");
			}

			if (validarNombre(apellidos)) {
				errorApellidos.setText("Apellido de registro Incorrecto");
			} else {
				errorApellidos.setText("");
			}

			if (validarNombre(poblacio)) {
				errorPoblacio.setText("Poblacion de registro Incorrecta");

			} else {
				errorPoblacio.setText("");
			}

			if (validarCorreo(correo)) {
				errorCorreo.setText("Correo Incorrecto");

			} else {
				errorCorreo.setText("");
			}

			if (validarContrasena(contra)) {
				errorContrasena1.setText("Debe tener 8 caracteres una Mayuscula y un digito");
			} else {
				errorContrasena1.setText("");
			}

			if (nombre.isEmpty() || apellidos.isEmpty() || poblacio.isEmpty() || contra.isEmpty()) {
				errorGeneral.setText("Todos los campos son obligatorios");
			} else {
				errorGeneral.setText("");

			}

			// Validar contraseñas iguales
			if (!contra.equals(contra2)) {
				errorContrasena2.setText("Las contraseñas no coinciden");
			} else {
				errorContrasena2.setText("");
			}

			if (errorNombre.getText().equals("") && errorApellidos.getText().equals("")
					&& errorImatge.getText().equals("") && errorPoblacio.getText().equals("")
					&& errorCorreo.getText().equals("") && errorContrasena1.getText().equals("")
					&& errorContrasena2.getText().equals("")) {
				// Lanzar funcion para escribir todo en la base de datos
				if (!BDUtils.usuarioExisteRegistro(correo)) {
					try {
						Usuario usuarioRegistrado = new Usuario(nombre, apellidos, blobData, poblacio, correo, contra);
						if (BDUtils.registrarUsuario(usuarioRegistrado)) {
							menuPrincipal menu = new menuPrincipal(usuarioRegistrado);
							menu.setVisible(true);
							setVisible(false);
							dispose();
						} else {
							System.out.println("Error");
						}
					} catch (Exception e2) {
					}
				} else {
					errorGeneral.setText("El usuario ya existe");
				}
			}

		});

		setLocationRelativeTo(null);

	}

	private String getExtension(File file) {
		String name = file.getName();
		int lastDotIndex = name.lastIndexOf(".");
		if (lastDotIndex != -1 && lastDotIndex < name.length() - 1) {
			return name.substring(lastDotIndex + 1).toLowerCase();
		}
		return "";
	}

	private boolean isValidExtension(String extension) {
		String[] validExtensions = { "jpg", "jpeg", "png" };
		for (String validExtension : validExtensions) {
			if (validExtension.equals(extension)) {
				return true;
			}
		}
		return false;
	}

	public boolean validarNombre(String nombre) {
		if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\\\s]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarCorreo(String correo) {
		if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarContrasena(String contrasena) {
		if (!contrasena.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
			return true;
		} else {
			return false;
		}
	}
}