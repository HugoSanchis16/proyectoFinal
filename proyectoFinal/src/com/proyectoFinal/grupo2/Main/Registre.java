package com.proyectoFinal.grupo2.Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

public class Registre extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_3;

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
		setBounds(100, 100, 735, 769);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
				panel.setBackground(new Color(90, 98, 167));
				panel.setBounds(126, 37, 477, 610);
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
				btnNewButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
				btnNewButton.setBounds(139, 212, 305, 31);
				panel.add(btnNewButton);
				
				JLabel lblNom = new JLabel("NOM");
				lblNom.setHorizontalAlignment(SwingConstants.LEFT);
				lblNom.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblNom.setBounds(37, 56, 187, 31);
				panel.add(lblNom);
				
				textField_1 = new JTextField();
				textField_1.setToolTipText("");
				textField_1.setColumns(10);
				textField_1.setBounds(37, 154, 407, 31);
				panel.add(textField_1);
				
				textField_2 = new JTextField();
				textField_2.setToolTipText("");
				textField_2.setColumns(10);
				textField_2.setBounds(37, 286, 407, 31);
				panel.add(textField_2);
				
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
				
				textField_5 = new JTextField();
				textField_5.setToolTipText("");
				textField_5.setColumns(10);
				textField_5.setBounds(37, 357, 407, 31);
				panel.add(textField_5);
				
				JLabel lblContrasenya_2_1 = new JLabel("REPETEIX CONTRASENYA");
				lblContrasenya_2_1.setHorizontalAlignment(SwingConstants.LEFT);
				lblContrasenya_2_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
				lblContrasenya_2_1.setBounds(37, 398, 242, 31);
				panel.add(lblContrasenya_2_1);
				
				textField_3 = new JTextField();
				textField_3.setToolTipText("");
				textField_3.setColumns(10);
				textField_3.setBounds(37, 439, 407, 31);
				panel.add(textField_3);
//				
		setResizable(false);
	}
}
