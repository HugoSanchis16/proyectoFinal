package com.proyectoFinal.grupo2.Juegos;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class buscaMinas extends JFrame {

	private JPanel contentPane;
	private String[] niveles = { "Fácil", "Medio", "Difícil" };
	private int tamanoTauler;
	private int cantidadMinas;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton botonReset = new JButton("Nueva Partida");
		botonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				buscaMinas buscaMinas = new buscaMinas();
				buscaMinas.setVisible(true);
			}
		});
		botonReset.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(botonReset);

		JButton botonGuardar = new JButton("Guardar Partida");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		panel_1.add(botonGuardar);

		JButton botonSalir = new JButton("Salir");
		botonSalir.setHorizontalAlignment(SwingConstants.RIGHT);
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(botonSalir);
		int seleccion = JOptionPane.showOptionDialog(null, "Selecciona un nivel", "Buscaminas",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, niveles, niveles[0]);

		setSize(new Dimension(400, 400));

		switch (seleccion) {
		case 0:
			tamanoTauler = 5;
			cantidadMinas = 5;
			break;
		case 1:
			tamanoTauler = 8;
			cantidadMinas = 15;
			break;
		case 2:
			tamanoTauler = 10;
			cantidadMinas = 30;
			break;
		default:
			tamanoTauler = 5;
			cantidadMinas = 5;

			break;
		}

		panel.setLayout(new GridLayout(tamanoTauler, tamanoTauler));
		crearBotones(tamanoTauler, panel);
	}

	public void crearBotones(int tamanoTauler, JPanel panel) {
		int tamanoMatriz = tamanoTauler * tamanoTauler;
		for (int i = 0; i < tamanoMatriz; i++) {
			panel.add(new JButton());
		}

	}

}
