package com.proyectoFinal.grupo2.Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

public class Registre extends JFrame {
	JFileChooser fileChooser = new JFileChooser();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField apellidosField;
	private JTextField poblacioField;
	private JTextField textField_3;
	private JPasswordField contrasenya;
	private JPasswordField contrasenya2;

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
					Registre frame = new Registre();
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
			public Registre() {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 735, 835);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
			
				
		JPanel panel = new JPanel();
				panel.setBackground(new Color(90, 98, 167));
				panel.setBounds(126, 37, 477, 701);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel nomUsuari = new JLabel("REGISTRE");
				nomUsuari.setBounds(181, 10, 98, 45);
				panel.add(nomUsuari);
				nomUsuari.setFont(new Font("Yu Gothic UI", Font.PLAIN, 23));
				nomUsuari.setHorizontalAlignment(SwingConstants.CENTER);
				
				JButton btnRegistrarse = new JButton("Registrarse");
				btnRegistrarse.setBounds(37, 490, 407, 38);
				panel.add(btnRegistrarse);
				btnRegistrarse.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
				
				textField = new JTextField();
				textField.setBounds(37, 87, 407, 31);
				panel.add(textField);
				textField.setToolTipText("");
				textField.setColumns(10);
				
				JLabel lblContrasenya = new JLabel("COGNOMS");
				lblContrasenya.setBounds(37, 123, 187, 31);
				panel.add(lblContrasenya);
				lblContrasenya.setHorizontalAlignment(SwingConstants.LEFT);
				lblContrasenya.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				
				
				
				JButton btnNewButton = new JButton("Insereix");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				        fileChooser.setCurrentDirectory(new File(""));

				        int result = fileChooser.showOpenDialog(null);
				        if (result == JFileChooser.APPROVE_OPTION) {
				            File selectedFile = fileChooser.getSelectedFile();
				            System.out.println("Archivo seleccionado: " + selectedFile.getName());
				        } else if (result == JFileChooser.CANCEL_OPTION) {
				            System.out.println("Selección de archivos cancelada");
				        }
				    }
					
				});
				btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
				btnNewButton.setBounds(139, 212, 305, 31);
				panel.add(btnNewButton);
				
				JLabel lblNom = new JLabel("NOM");
				lblNom.setHorizontalAlignment(SwingConstants.LEFT);
				lblNom.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblNom.setBounds(37, 56, 187, 31);
				panel.add(lblNom);
				
				apellidosField = new JTextField();
				apellidosField.setToolTipText("");
				apellidosField.setColumns(10);
				apellidosField.setBounds(37, 154, 407, 31);
				panel.add(apellidosField);
				
				poblacioField = new JTextField();
				poblacioField.setToolTipText("");
				poblacioField.setColumns(10);
				poblacioField.setBounds(37, 286, 407, 31);
				panel.add(poblacioField);
				
				JLabel lblImatge = new JLabel("IMATGE");
				lblImatge.setHorizontalAlignment(SwingConstants.LEFT);
				lblImatge.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblImatge.setBounds(37, 213, 98, 31);
				panel.add(lblImatge);
				
				JLabel lblPoblacio = new JLabel("POBLACIO");
				lblPoblacio.setHorizontalAlignment(SwingConstants.LEFT);
				lblPoblacio.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblPoblacio.setBounds(37, 256, 98, 31);
				panel.add(lblPoblacio);
				
				JLabel lblContrasenya_2 = new JLabel("CONTRASENYA");
				lblContrasenya_2.setHorizontalAlignment(SwingConstants.LEFT);
				lblContrasenya_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblContrasenya_2.setBounds(37, 327, 136, 31);
				panel.add(lblContrasenya_2);
				
				contrasenya = new JPasswordField();
				contrasenya.setToolTipText("");
				contrasenya.setColumns(10);
				contrasenya.setBounds(37, 357, 407, 31);
				panel.add(contrasenya);
				
				JLabel lblContrasenya_2_1 = new JLabel("REPETEIX CONTRASENYA");
				lblContrasenya_2_1.setHorizontalAlignment(SwingConstants.LEFT);
				lblContrasenya_2_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblContrasenya_2_1.setBounds(37, 398, 242, 31);
				panel.add(lblContrasenya_2_1);
				
				contrasenya2 = new JPasswordField();
				contrasenya2.setToolTipText("");
contrasenya2.setColumns(10);
				contrasenya2.setBounds(37, 439, 407, 31);
				panel.add(contrasenya2);
				
				JLabel Errors = new JLabel("");
				Errors.setForeground(Color.RED);
				Errors.setHorizontalAlignment(SwingConstants.LEFT);
				Errors.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
				Errors.setBounds(37, 538, 407, 153);
				panel.add(Errors);
//				
				setResizable(false);
			
			
				btnRegistrarse.addActionListener(e -> {
					String nombre = textField.getText();
					String apellidos = apellidosField.getText();
//					String imagen = textField_2.getText();
					String poblacio = poblacioField.getText();
					String contra =  String.valueOf(contrasenya.getPassword());
					String contra2 =  String.valueOf(contrasenya2.getPassword());
					String error="";
					
					
					if(nombre.isEmpty()) {
						textField.setBackground(new Color(255, 51, 51));
					}
					else {
						textField.setBackground(null);
					}
					
					if(apellidos.isEmpty()) {
						apellidosField.setBackground(new Color(255, 51, 51));
					}
					else {
						apellidosField.setBackground(null);
					}
					
					if(poblacio.isEmpty()) {
						poblacioField.setBackground(new Color(255, 51, 51));
					}
					else {
						poblacioField.setBackground(null);
					}
					
					if(contra.isEmpty()) {
						contrasenya.setBackground(new Color(255, 51, 51));
					}
					else {
						contrasenya.setBackground(null);
					}
					
					// Validar campos vacíos
					if (nombre.isEmpty() || apellidos.isEmpty() || poblacio.isEmpty() || contra.isEmpty()
							|| contra2.isEmpty()) {
						error="Todos los campos son obligatorios <br>";
						System.out.println("Todos los campos son obligatorios");
					}



					 if (contra.length() < 8 || !contra.matches(".*[A-Z].*") || !contra.matches(".*\\d.*")) {
							error+="La contraseña debe tener al menos 8 caracteres, una letra mayúscula y un dígito <br>";
					        System.out.println("La contraseña debe tener al menos 8 caracteres, una letra mayúscula y un dígito");
							contrasenya.setBackground(new Color(255, 51, 51));
					    }
					 else {
							contrasenya.setBackground(null);
					 }
					 
						// Validar contraseñas iguales
						if (!contra.equals(contra2)) {
							error+="Las contraseñas no coinciden <br>";
							System.out.println("Las contraseñas no coinciden");
							contrasenya2.setBackground(new Color(255, 51, 51));
						}
						else {
							contrasenya2.setBackground(null);
						}
						
						if (fileChooser.getSelectedFile() == null) {
					        error += "No se ha seleccionado un archivo <br>";
					        System.out.println("No se ha seleccionado un archivo");
					        btnNewButton.setBackground(new Color(255, 51, 51)); 
					    }
						else{
					        btnNewButton.setBackground(null); 

						}
					Errors.setText("<html>"+error+"</html>");
					 
						if(error=="") {
							//Lanzar funcion para escribir todo en la base de datos
							
//							Usuario usuarioRegistrado = new Usuario();
						}

				});
			
			
			
			
			
			
			
			}
		}
