package com.proyectoFinal.grupo2.Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class BDUtils {
	private static String urlBaseDades = "jdbc:mysql://" + BD.getHost() + ":3306/1daw02_pro";
	private static String usuariBD = BD.getUsuario();
	private static String contrasenyaBD = BD.getContrasena();

	public static boolean usuarioExisteRegistro(String email) {
		/**
		 * Para comprobar hay que pasarle al metodo el email entero y un string del hash
		 * de la contraseña
		 */

		// Variables

		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `usuarios` WHERE correo='" + email + "'");

			while (r.next()) {
				if ((r.getString("correo").equals(email))) {
					c.close();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean usuarioExiste(String email, String contrasena) {
		/**
		 * Para comprobar hay que pasarle al metodo el email entero y un string del hash
		 * de la contraseña
		 */

		// Variables

		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `usuarios` WHERE correo='" + email + "'");

			while (r.next()) {
				if ((r.getString("correo").equals(email)) && (r.getString("contrasena").equals(contrasena))) {
					c.close();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Usuario comprobarLogin(String email, String contrasena) {
		/**
		 * Para comprobar hay que pasarle al metodo el email entero y un string del hash
		 * de la contraseña
		 */

		// Variables
		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `usuarios` WHERE correo='" + email + "'");

			while (r.next()) {
				if ((r.getString("correo").equals(email)) && (r.getString("contrasena").equals(contrasena))) {
					Usuario usuario = new Usuario(r.getInt("id"), r.getString("nombre"), r.getString("apellidos"),
							r.getBytes("imagen"), r.getString("poblacion"), r.getString("correo"),
							r.getString("contrasena"));

					c.close();
					return usuario;
				}
			}
			// Tancar la connexió

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean guardarPartidaBuscaMinas(String email, String base64Partida, String nombrePartida,
			int segundos, String dificultad) {
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
					"INSERT INTO `buscaminas` (`id_juego`, `usuario`, `matriz`, `nombre_partida` , `tiempo` , `dificultad`) VALUES (NULL, '"
							+ email + "', '" + base64Partida + "', '" + nombrePartida + "', '" + segundos + "', '" + dificultad + "')");

			if (r > 0) {
				// Se ha guardado correctamente la partida
				// Tancar la connexió
				c.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public static ArrayList<HashMap<String, String>> obtenerMejorPartidaPorUsuario(String email) {
	    ArrayList<HashMap<String, String>> partidas = new ArrayList<>();

	    try {
	        elegirClaseBD();

	        Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

	        Statement s = c.createStatement();
	        ResultSet r = s.executeQuery("SELECT * FROM `buscaminas` b " +
	                "WHERE b.usuario='" + email + "' " +
	                "AND b.tiempo = (SELECT MIN(tiempo) FROM `buscaminas` WHERE usuario='" + email + "')");

	        while (r.next()) {
	            HashMap<String, String> temp = new HashMap<>();
	            temp.put("email", r.getString("usuario"));
	            temp.put("tiempo", r.getString("nombre_partida"));

	            partidas.add(temp);
	        }

	        c.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return partidas;
	}

	public static ArrayList<HashMap<String, String>> recuperarPartidasBuscaMinas(String email) {
		ArrayList<HashMap<String, String>> partidas = new ArrayList<>();

		try {
			elegirClaseBD();

			Connection c = DriverManager.getConnection(urlBaseDades, usuariBD, contrasenyaBD);

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM `buscaminas` WHERE usuario='" + email + "'");

			while (r.next()) {
				HashMap<String, String> temp = new HashMap<>();
				temp.put("id_juego", r.getString("id_juego"));
				temp.put("email", r.getString("usuario"));
				temp.put("matriz", r.getString("matriz"));
				temp.put("nombre_partida", r.getString("nombre_partida"));

				partidas.add(temp);
			}

			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return partidas;
	}

	// Crealo así
//		Usuario usuariotemp = new Usuario("", "", "", "", "", "");
//		Usuario usuario = BDUtils.registrarUsuario(usuariotemp);
	public static boolean registrarUsuario(Usuario usuario) {
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

			if (r > 0) {
				// Se ha registrado correctamente al usuario
				c.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
