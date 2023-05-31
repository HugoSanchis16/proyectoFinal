package com.proyectoFinal.grupo2.Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

public class RankingBuscaMinas extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RankingBuscaMinas frame = new RankingBuscaMinas();
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
    public RankingBuscaMinas() {
        setTitle("Ranking BuscaMinas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JInternalFrame ventanaFacil = new JInternalFrame("Facil");
        tabbedPane.addTab("Facil", null, ventanaFacil, null);
        ventanaFacil.getContentPane().setLayout(new BorderLayout(0, 0));

        JLabel lblTopMejores = new JLabel("TOP 5 MEJORES JUGADORES");
        lblTopMejores.setFont(new Font("Century Schoolbook L", Font.BOLD, 15));
        lblTopMejores.setHorizontalAlignment(SwingConstants.CENTER);
        ventanaFacil.getContentPane().add(lblTopMejores, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Establecer un GridLayout con 5 filas y 1 columna

        for (int i = 0; i < 10; i++) {
            JLabel label = new JLabel("Hola");
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        ventanaFacil.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JInternalFrame ventanaMedio = new JInternalFrame("Medio");
        tabbedPane.addTab("Medio", null, ventanaMedio, null);

        JInternalFrame ventanaDificil = new JInternalFrame("Dificil");
        tabbedPane.addTab("Dificil", null, ventanaDificil, null);

        ventanaDificil.setVisible(true);
        ventanaMedio.setVisible(true);
        ventanaFacil.setVisible(true);
    }
}