package com.proyectoFinal.main;

import com.proyectoFinal.clases.BD;
import com.proyectoFinal.clases.Usuario;
import com.proyectoFinal.utils.BDUtils;

public class Main {

	public static void main(String[] args) {
		Usuario usuario = new Usuario(1, "", "", "", "", "", "");

		BDUtils pr = new BDUtils();
		System.out.println(BD.getHost());
		BDUtils.comprobarLogin(usuario);

	}

}
