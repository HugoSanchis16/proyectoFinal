package com.proyectoFinal.grupo2.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.proyectoFinal.grupo2.Clases.BDUtils;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class RankingBuscaMinas extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RankingBuscaMinas() {
		setTitle("Ranking BuscaMinas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JInternalFrame ventanaFacil = new JInternalFrame("");
		tabbedPane.addTab("Facil", null, ventanaFacil, null);
		ventanaFacil.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblTopMejoresFacil = new JLabel(
				"<html><div style='text-align:center; margin-top:10px; margin-bottom:10px;'>TOP 5 MEJORES JUGADORES</div></html>");
		lblTopMejoresFacil.setFont(new Font("Century Schoolbook L", Font.BOLD, 18));
		lblTopMejoresFacil.setHorizontalAlignment(SwingConstants.CENTER);
		ventanaFacil.getContentPane().add(lblTopMejoresFacil, BorderLayout.NORTH);

		JPanel panelFacil = new JPanel();
		panelFacil.setLayout(new GridLayout(5, 1)); // Establecer un GridLayout con 5 filas y 1 columna

		ArrayList<HashMap<String, String>> MejoresJugadoresFacil = BDUtils.obtenerMejoresUsuariosFacil();
		int contFacil = 1;
		for (HashMap<String, String> hashMap : MejoresJugadoresFacil) {
			String email = hashMap.get("email");
			String tiempo = hashMap.get("tiempo");

			JLabel label = new JLabel(contFacil + " - " + email + " - " + tiempo + " sec");
			label.setFont(new Font("Century Schoolbook L", Font.CENTER_BASELINE, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			panelFacil.add(label);
			contFacil++;
		}

		if (contFacil == 1) {
			JLabel noJugadoresFacil = new JLabel(
					"<html><div style='text-align:center; margin-top:10px; margin-bottom:10px;'>Aún no hay ningun jugador en el Top</div></html>");
			noJugadoresFacil.setFont(new Font("Century Schoolbook L", Font.BOLD, 15));
			noJugadoresFacil.setHorizontalAlignment(SwingConstants.CENTER);
			panelFacil.add(noJugadoresFacil);
		}

		ventanaFacil.getContentPane().add(panelFacil, BorderLayout.CENTER);

		JInternalFrame ventanaMedio = new JInternalFrame("");
		tabbedPane.addTab("Medio", null, ventanaMedio, null);
		ventanaMedio.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblTopMejoresMedio = new JLabel(
				"<html><div style='text-align:center; margin-top:10px; margin-bottom:10px;'>TOP 5 MEJORES JUGADORES</div></html>");
		lblTopMejoresMedio.setFont(new Font("Century Schoolbook L", Font.BOLD, 18));
		lblTopMejoresMedio.setHorizontalAlignment(SwingConstants.CENTER);
		ventanaMedio.getContentPane().add(lblTopMejoresMedio, BorderLayout.NORTH);

		JPanel panelMedio = new JPanel();
		panelMedio.setLayout(new GridLayout(5, 1)); // Establecer un GridLayout con 5 filas y 1 columna

		ArrayList<HashMap<String, String>> MejoresJugadoresMedio = BDUtils.obtenerMejoresUsuariosMedio();
		int contMedio = 1;
		for (HashMap<String, String> hashMap : MejoresJugadoresMedio) {
			String email = hashMap.get("email");
			String tiempo = hashMap.get("tiempo");

			JLabel label = new JLabel(contMedio + " - " + email + " - " + tiempo + " sec");
			label.setFont(new Font("Century Schoolbook L", Font.BOLD, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			panelMedio.add(label);
			contMedio++;
		}

		if (contMedio == 1) {
			JLabel noJugadoresMedio = new JLabel(
					"<html><div style='text-align:center; margin-top:10px; margin-bottom:10px;'>Aún no hay ningun jugador en el Top</div></html>");
			noJugadoresMedio.setFont(new Font("Century Schoolbook L", Font.BOLD, 15));
			noJugadoresMedio.setHorizontalAlignment(SwingConstants.CENTER);
			panelMedio.add(noJugadoresMedio);
		}

		ventanaMedio.getContentPane().add(panelMedio, BorderLayout.CENTER);

		JInternalFrame ventanaDificil = new JInternalFrame("");
		tabbedPane.addTab("Dificil", null, ventanaDificil, null);

		JLabel lblTopMejoresDificil = new JLabel(
				"<html><div style='text-align:center; margin-top:10px; margin-bottom:10px;'>TOP 5 MEJORES JUGADORES</div></html>");
		lblTopMejoresDificil.setFont(new Font("Century Schoolbook L", Font.BOLD, 18));
		lblTopMejoresDificil.setHorizontalAlignment(SwingConstants.CENTER);
		ventanaDificil.getContentPane().add(lblTopMejoresDificil, BorderLayout.NORTH);

		JPanel panelDificil = new JPanel();
		panelDificil.setLayout(new GridLayout(5, 1)); // Establecer un GridLayout con 5 filas y 1 columna

		ArrayList<HashMap<String, String>> MejoresJugadoresDificil = BDUtils.obtenerMejoresUsuariosDificil();
		int contDificil = 1;
		for (HashMap<String, String> hashMap : MejoresJugadoresDificil) {
			String email = hashMap.get("email");
			String tiempo = hashMap.get("tiempo");

			JLabel label = new JLabel(contDificil + " - " + email + " - " + tiempo + " sec");
			label.setFont(new Font("Century Schoolbook L", Font.BOLD, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			panelDificil.add(label);
			contDificil++;
		}

		if (contDificil == 1) {
			JLabel noJugadoresDificil = new JLabel(
					"<html><div style='text-align:center; margin-top:10px; margin-bottom:10px;'>Aún no hay ningun jugador en el Top</div></html>");
			noJugadoresDificil.setFont(new Font("Century Schoolbook L", Font.BOLD, 15));
			noJugadoresDificil.setHorizontalAlignment(SwingConstants.CENTER);
			panelDificil.add(noJugadoresDificil);
		}

		ventanaDificil.getContentPane().add(panelDificil, BorderLayout.CENTER);

		ventanaDificil.setVisible(true);
		ventanaMedio.setVisible(true);
		ventanaFacil.setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(400, 500);
	}

}