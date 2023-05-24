package com.proyectoFinal.grupo2.Clases;

//public class Usuario {
//	private int id;
//	private String nombre;
//	private String apellidos;
//	private String imagen;
//	private String poblacion;
//	private String correoElectronico;
//	private String contrasena;
//	
//	public Usuario(int id, String nombre, String apellidos, String imagen, String poblacion, String correoElectronico,
//			String contrasena) {
//		this.id = id;
//		this.nombre = nombre;
//		this.apellidos = apellidos;
//		this.imagen = imagen;
//		this.poblacion = poblacion;
//		this.correoElectronico = correoElectronico;
//		this.contrasena = contrasena;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getApellidos() {
//		return apellidos;
//	}
//
//	public void setApellidos(String apellidos) {
//		this.apellidos = apellidos;
//	}
//
//	public String getImagen() {
//		return imagen;
//	}
//
//	public void setImagen(String imagen) {
//		this.imagen = imagen;
//	}
//
//	public String getPoblacion() {
//		return poblacion;
//	}
//
//	public void setPoblacion(String poblacion) {
//		this.poblacion = poblacion;
//	}
//
//	public String getCorreoElectronico() {
//		return correoElectronico;
//	}
//
//	public void setCorreoElectronico(String correoElectronico) {
//		this.correoElectronico = correoElectronico;
//	}
//
//	public String getContrasena() {
//		return contrasena;
//	}
//
//	public void setContrasena(String contrasena) {
//		this.contrasena = contrasena;
//	}
//
//}





public class Usuario {
	private int id;
	private String nombre;
	private String apellidos;
	private String imagen;
	private String poblacion;
	private String correoElectronico;
	private String contrasena;
	
	public Usuario(int id, String nombre, String apellidos, String imagen, String poblacion, String correoElectronico,
			String contrasena) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.imagen = imagen;
		this.poblacion = poblacion;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
	}
	public Usuario(String nombre, String apellidos, String imagen, String poblacion, String correoElectronico,
			String contrasena) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.imagen = imagen;
		this.poblacion = poblacion;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", imagen=" + imagen
				+ ", poblacion=" + poblacion + ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena
				+ "]";
	}

}