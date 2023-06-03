package com.proyectoFinal.grupo2.Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.proyectoFinal.grupo2.Clases.Usuario;

public class BDUtils {
	private static String urlBaseDades = "jdbc:mysql://" + BD.getHost() + ":3306/1daw02_pro";
	private static String usuariBD = BD.getUsuario();
	private static String contrasenyaBD = BD.getContrasena();

	public static Usuario comprobarLogin(String email, String contrasena) {
		/**
		 * Para comprobar hay que pasarle al metodo el email entero y un string del hash
		 * de la contraseña
		 */

		// Variables
		boolean usuarioEncontrado = false;

		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `usuarios` WHERE correo='" + email + "'");

			while (r.next()) {
				if ((r.getString("correo").equals(email)) && (r.getString("contrasena").equals(contrasena))) {
					Usuario usuario = new Usuario(r.getInt("id"), r.getString("nombre"), r.getString("apellidos"),
							r.getString("imagen"), r.getString("poblacion"), r.getString("correo"),
							r.getString("contrasena"));

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

	public static void guardarPartida(String email, String base64Partida , String nombrePartida) {
		/**
		 * Para comprobar hay que pasarle al metodo el email entero y un string del hash
		 * de la contraseña
		 */

		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			// Enviar una sentència SQL per recuperar els clients
			Statement s = c.createStatement();
			int r = s.executeUpdate(
					"INSERT INTO `buscaminas` (`id_juego`, `usuario`, `matriz`, `nombre_partida`) VALUES (NULL, '" + email + "', '" + base64Partida + "', '" + nombrePartida + "')");

			// Tancar la connexió
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<HashMap<String, String>> recuperarPartidas(String email) {
		ArrayList<HashMap<String, String>> partidas = null;
		HashMap<String, String> temp = null;
		try {
			elegirClaseBD();
			

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `usuarios` WHERE usuario='" + email + "'");

			while (r.next()) {
				temp.put("id_juego", r.getString("correo"));
				temp.put("email", r.getString("usuario"));
				temp.put("matriz", r.getString("matriz"));
				temp.put("nombre_partida", r.getString("nombre_partida"));
				
				partidas.add(temp);
				
				temp.clear();
			}

			c.close();
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

		return partidas;

	}
	
	public static ArrayList<HashMap<String, String>> recuperarPartidasPixelArt(String nombre) {
		ArrayList<HashMap<String, String>> partidas = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> temp = new HashMap<String, String>();
		try {
			elegirClaseBD();
			
			
			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);
			
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `pixelart` WHERE usuario='" + nombre + "'");
			
			while (r.next()) {
				temp.put("usuario", r.getString("usuario"));
				temp.put("nombre", r.getString("nombre"));
				temp.put("matriz", r.getString("matriz"));
				temp.put("fecha", r.getString("fecha"));
				
				partidas.add(temp);
				System.out.println("sdfdg" + temp.toString());
				
				temp.clear();
			}
			
			c.close();
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
		
		return partidas;
		
	}
	
	public static void guardarPartidaPixelArt(String nombre, String nombrePartida, String base64Partida) {
		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			// Enviar una sentència SQL per recuperar els clients
			Statement s = c.createStatement();
			int r = s.executeUpdate(
					"INSERT INTO `pixelart` (`id`, `usuario`, `nombre`, `fecha`, `matriz`) VALUES (NULL, '" + nombre + "', '" + nombrePartida + "', NULL, '" + base64Partida + "')");

			// Tancar la connexió
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
