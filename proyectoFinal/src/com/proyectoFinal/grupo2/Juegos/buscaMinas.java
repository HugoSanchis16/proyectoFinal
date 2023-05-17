package com.proyectoFinal.grupo2.Juegos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class buscaMinas extends JFrame {

	private JPanel contentPane;
	private String[] niveles = { "Fácil", "Medio", "Difícil" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buscaMinas frame = new buscaMinas();
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
	public buscaMinas() {
		super("PescaMines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		int seleccion = JOptionPane.showOptionDialog(null, "Selecciona un nivel", "Buscaminas",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, niveles, niveles[0]);

		int tamañoTauler;
		int cantidadMinas;

		switch (seleccion) {
		case 0:
			tamañoTauler = 5;
			cantidadMinas = 5;
			break;
		case 1:
			tamañoTauler = 8;
			cantidadMinas = 15;
			break;
		case 2:
			tamañoTauler = 10;
			cantidadMinas = 30;
			break;
		default:
			tamañoTauler = 5;
			cantidadMinas = 5;
			break;
		}

	}

}
