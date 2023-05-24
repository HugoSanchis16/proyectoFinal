package com.proyectoFinal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.proyectoFinal.clases.BD;
import com.proyectoFinal.clases.Usuario;
import com.proyectoFinal.clases.BD;

public class BDUtils {
	private static String urlBaseDades = "jdbc:mysql://" + BD.getHost() + ":3306/1daw02_pro";
	private static String usuariBD = BD.getUsuario();
	private static String contrasenyaBD = BD.getContrasena();

	public static Usuario comprobarLogin(String email, String contrasena) {
		/**
		 * Para comprobar hay que pasarle al metodo el email entero y un string del hash de la contraseña
		 */
		
		// Variables
		boolean usuarioEncontrado = false;
		
		
		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(
					"SELECT * FROM `usuarios` WHERE correo='" + email + "'");

			while (r.next()) {
				if ((r.getString("correo").equals(email))
						&& (r.getString("contrasena").equals(contrasena))) {
					Usuario usuario = new Usuario(r.getInt("id"), r.getString("nombre"), r.getString("apellidos"), r.getString("imagen"), r.getString("poblacion"), r.getString("correo"), r.getString("contrasena"));
					
					c.close();
					return usuario;
				}
			}


//			while (r.next()) {
//				System.out.println("Total Alumnes: "+r.getInt("TOTAL"));
//			}
//			
//			s = c.createStatement();
//			r = s.executeQuery("SELECT * FROM alumne");
//			

			// Tancar la connexió

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	// Crealo así
//		Usuario usuariotemp = new Usuario("", "", "", "", "", "");
//		Usuario usuario = BDUtils.registrarUsuario(usuariotemp);
	public static void registrarUsuario(Usuario usuario) {
		/**
		 * Crear constructor sin id
		 */
		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			// Establir la connexió

			// Enviar una sentència SQL per recuperar els clients
			Statement s = c.createStatement();
			int r = s.executeUpdate(
					"INSERT INTO `usuarios` (`id`, `nombre`, `apellidos`, `imagen`, `poblacion`, `correo`, `contrasena`) "
							+ "VALUES (NULL, '" + usuario.getNombre() + "', '" + usuario.getApellidos() + "', '"
							+ usuario.getImagen() + "', '" + usuario.getPoblacion() + "', '"
							+ usuario.getCorreoElectronico() + "', '" + usuario.getContrasena() + "')");

			// Tancar la connexió
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void elegirClaseBD() {
		try {

			if (BD.isEsMySQL()) {
				Class.forName("org.mariadb.jdbc.Driver");
			} else {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int comprobarNumUsuariosCreados() {
		int numero = 0;
		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			// Establir la connexió

			// Enviar una sentència SQL per recuperar els clients
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT COUNT(*) FROM `usuarios`");

			while (r.next()) {
				numero = r.getInt("COUNT(*)");
			}

			// Tancar la connexió
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numero;
	}
}
