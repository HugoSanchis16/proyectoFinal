package com.proyectoFinal.grupo2.Clases;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class BD {

	private static String usuario = "1daw02_pro";
	private static String contrasena = "TJmPTUs6cO";
	private static String host = "";
	public static boolean esMySQL; 

	static {
		elegirBD();
	}

	public static String getUsuario() {
		return usuario;
	}

	public static String getContrasena() {
		return contrasena;
	}

	public static String getHost() {
		return host;
	}

	private static void setHost(String host) {
		BD.host = host;
	}

	public static boolean isEsMySQL() {
		return esMySQL;
	}

	private static void setEsMySQL(boolean esMySQL) {
		BD.esMySQL = esMySQL;
	}

	private static boolean comprobarSiEsIPDeClase() throws SocketException {
		
		/**
		 * Si devuelve false es IP de casa, si devuelve true es de clase
		 * 
		 */
		
		boolean contieneIPClase = false;
//		try {
//			ip = Inet4Address.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}

		Enumeration e;
		try {
			e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = (InetAddress) ee.nextElement();
					if (i.getHostAddress().contains("192.168.14.")) {
						contieneIPClase = true;
					}
				}
			}

		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return contieneIPClase;
	}

	private static void elegirBD() {
		boolean esIPClase = false;
		
		try {
			esIPClase = comprobarSiEsIPDeClase();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (esIPClase) {
			BD.setHost("192.168.14.200");
			BD.setEsMySQL(false);
		} else {
			BD.setHost("ticsimarro.org");
			BD.setEsMySQL(true);
		}

	}

}
