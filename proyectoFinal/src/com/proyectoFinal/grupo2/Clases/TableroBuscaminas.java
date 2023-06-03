package com.proyectoFinal.grupo2.Clases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class TableroBuscaminas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Casilla[][] casillas;

	int numFilas;
	int numColumnas;
	int numMinas;
	int numBanderas;
	int numCasillasAbiertas;
	int segundosPartida;
	boolean casillaAbierta = false;
	boolean juegoTerminado = false;

	ArrayList<String> minasEnCasillas;

	public TableroBuscaminas(int numFilas, int numColumnas, int numMinas) {
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.numMinas = numMinas;
		this.inicializarCasillas();
	}

	public void inicializarCasillas() {
		casillas = new Casilla[this.numFilas][this.numColumnas];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla(i, j);
			}
		}
		generarMinas();
	}

	private void generarMinas() {
		Random random = new Random();
		int minasGeneradas = 0;
		while (minasGeneradas < numMinas) {
			int posTmpFila = random.nextInt(casillas.length);
			int posTmpColumna = random.nextInt(casillas[0].length);
			if (!casillas[posTmpFila][posTmpColumna].isMina()) {
				casillas[posTmpFila][posTmpColumna].setMina(true);
				minasGeneradas++;
			}
		}
		actualizarNumMinasAlrededor();
	}

	public void actualizarNumMinasAlrededor() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				if (casillas[i][j].isMina()) {
					ArrayList<Casilla> casillasAlrededor = casillasAlrededor(i, j);
					for (Casilla casilla : casillasAlrededor) {
						casilla.incrementarMinasAlrededor();
					}
				}
			}
		}
	}

	private ArrayList<Casilla> casillasAlrededor(int posicionFila, int posicionColumna) {
		ArrayList<Casilla> listaCasillasAlrededor = new ArrayList<>();
		// Hacemos bucle de 8 porque son las 8 posiciones que puede tener alrededor
		for (int i = 0; i < 8; i++) {
			int posTmpFila = posicionFila;
			int posTmpColumna = posicionColumna;
			switch (i) {
			case 0:
				posTmpFila--;
				break; // Arriba
			case 1:
				posTmpFila--;
				posTmpColumna++;
				break; // Arriba Derecha
			case 2:
				posTmpColumna++;
				break; // Derecha
			case 3:
				posTmpColumna++;
				posTmpFila++;
				break; // Derecha Abajo
			case 4:
				posTmpFila++;
				break; // Abajo
			case 5:
				posTmpFila++;
				posTmpColumna--;
				break; // Abajo Izquierda
			case 6:
				posTmpColumna--;
				break; // Izquierda
			case 7:
				posTmpFila--;
				posTmpColumna--;
				break; // Izquierda Arriba
			}

			if (posTmpFila >= 0 && posTmpFila < this.casillas.length && posTmpColumna >= 0
					&& posTmpColumna < this.casillas[0].length) {
				listaCasillasAlrededor.add(this.casillas[posTmpFila][posTmpColumna]);
			}

		}
		return listaCasillasAlrededor;
	}

	public boolean hayCasillasAdyacentesSinMina(int posFila, int posColumna) {
		ArrayList<Casilla> casillasAdyacentes = casillasAlrededor(posFila, posColumna);
		for (Casilla casilla : casillasAdyacentes) {
			if (!casilla.isMina() && !casilla.isAbierta()) {
				return true;
			}
		}
		return false;
	}

	public void marcarCasillaAbierta(int posFila, int posColumna) {
		if (!this.casillas[posFila][posColumna].isAbierta()) {
			numCasillasAbiertas++;
			this.casillas[posFila][posColumna].setAbierta(true);
			int numMinasAlrededor = this.casillas[posFila][posColumna].getNumMinasAlrededor();
			if (numMinasAlrededor == 0 && hayCasillasAdyacentesSinMina(posFila, posColumna)) {
				ArrayList<Casilla> casillasAdyacentes = casillasAlrededor(posFila, posColumna);
				for (Casilla casilla : casillasAdyacentes) {
					if (!casilla.isMina() && !casilla.isAbierta() && !casilla.isBandera()) {
						marcarCasillaAbierta(casilla.getPosFila(), casilla.getPosColumna());
					}
				}
			}
		}
	}

	public boolean tieneBanderaCasilla(int posFila, int posColumna) {
		if (!this.casillas[posFila][posColumna].isBandera()) {
			return false;
		}
		return true;
	}

	public void ponerBanderaCasilla(int posFila, int posColumna) {
		if (!this.casillas[posFila][posColumna].isAbierta()) {
			numBanderas++;
			this.casillas[posFila][posColumna].setBandera(true);
		}
	}

	public void quitarBanderaCasilla(int posFila, int posColumna) {
		if (!this.casillas[posFila][posColumna].isAbierta()) {
			numBanderas--;
			this.casillas[posFila][posColumna].setBandera(false);
		}
	}

	public void imprimirTablero() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				System.out.print(casillas[i][j].isMina() ? "*" : "0");
			}
			System.out.println("");
		}
	}

	public boolean partidaGanada() {
		if (this.numCasillasAbiertas >= (numFilas * numColumnas) - numMinas) {
			return true;
		}
		return false;
	}

	public ArrayList<Casilla> getCasillasCerradas() {
		ArrayList<Casilla> casillasCerradas = new ArrayList<>();
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				Casilla casilla = casillas[i][j];
				if (!casilla.isAbierta()) {
					casillasCerradas.add(casilla);
				}
			}
		}
		return casillasCerradas;
	}

	public int getNumBanderas() {
		return numBanderas;
	}

	public void setNumBanderas(int numBanderas) {
		this.numBanderas = numBanderas;
	}

	public int getNumCasillasAbiertas() {
		return numCasillasAbiertas;
	}

	public void setNumCasillasAbiertas(int numCasillasAbiertas) {
		this.numCasillasAbiertas = numCasillasAbiertas;
	}

	public boolean isCasillaAbierta() {
		return casillaAbierta;
	}

	public void setCasillaAbierta(boolean casillaAbierta) {
		this.casillaAbierta = casillaAbierta;
	}

	public boolean isJuegoTerminado() {
		return juegoTerminado;
	}

	public void setJuegoTerminado(boolean juegoTerminado) {
		this.juegoTerminado = juegoTerminado;
	}

	public boolean casillaContieneMina(int posFila, int posColumna) {
		return casillas[posFila][posColumna].isMina();
	}

	public Casilla getCasilla(int fila, int columna) {
		return casillas[fila][columna];
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public int getNumFilas() {
		return numFilas;
	}

	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}

	public int getNumColumnas() {
		return numColumnas;
	}

	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}

	public int getNumMinas() {
		return numMinas;
	}

	public void setNumMinas(int numMinas) {
		this.numMinas = numMinas;
	}
	

	public int getSegundosPartida() {
		return segundosPartida;
	}

	public void setSegundosPartida(int segundosPartida) {
		this.segundosPartida = segundosPartida;
	}

	public ArrayList<String> getMinasEnCasillas() {
		return minasEnCasillas;
	}

	public void setMinasEnCasillas(ArrayList<String> minasEnCasillas) {
		this.minasEnCasillas = minasEnCasillas;
	}

	public static String serializeTablero(TableroBuscaminas tablero) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(tablero);
			oos.close();
			byte[] bytes = baos.toByteArray();
			return java.util.Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static TableroBuscaminas deserializeTablero(String tableroSerializado) {
		try {
			byte[] bytes = Base64.getDecoder().decode(tableroSerializado);
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			TableroBuscaminas tablero = (TableroBuscaminas) ois.readObject();
			ois.close();
			bis.close();
			return tablero;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
