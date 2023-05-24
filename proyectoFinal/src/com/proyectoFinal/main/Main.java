package com.proyectoFinal.main;

import com.proyectoFinal.clases.Usuario;
import com.proyectoFinal.utils.BDUtils;

public class Main {

	public static void main(String[] args) {
		String email = "admin@gmail.com";
		String contrasena = "Admin1";

		Usuario usuario = null;
		usuario = BDUtils.comprobarLogin(email, contrasena);
		
		
		System.out.println("Comprobación login retorna un objeto Usuario si el login es correcto\n" + usuario.toString());
		
		Usuario usuarioACrear = new Usuario("Cde", "Cde", "Cde", "Cde", "Cde", "Cde");
		BDUtils.registrarUsuario(usuarioACrear);
		
		System.out.println("Total Usuarios Creados: " + BDUtils.comprobarNumUsuariosCreados());

	}

}
