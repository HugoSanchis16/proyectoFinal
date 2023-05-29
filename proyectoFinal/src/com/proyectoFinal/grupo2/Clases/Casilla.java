package com.proyectoFinal.grupo2.Clases;

import java.io.Serializable;

<<<<<<< Updated upstream
public class Casilla implements Serializable {
=======
public class Casilla implements Serializable{
>>>>>>> Stashed changes
	private int posFila;
	private int posColumna;
	private boolean mina;
	private boolean abierta;
	private boolean bandera;
	private int numMinasAlrededor;

	public Casilla(int posFila, int posColumna) {
		this.posFila = posFila;
		this.posColumna = posColumna;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public int getPosFila() {
		return posFila;
	}

	public void setPosFila(int posFila) {
		this.posFila = posFila;
	}

	public int getPosColumna() {
		return posColumna;
	}

	public void setPosColumna(int posColumna) {
		this.posColumna = posColumna;
	}

	public boolean isMina() {
		return mina;
	}

	public void setMina(boolean mina) {
		this.mina = mina;
	}

	public int getNumMinasAlrededor() {
		return numMinasAlrededor;
	}

	public void setNumMinasAlrededor(int numMinasAlrededor) {
		this.numMinasAlrededor = numMinasAlrededor;
	}

	public void incrementarMinasAlrededor() {
		this.numMinasAlrededor++;
	}

	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

}
