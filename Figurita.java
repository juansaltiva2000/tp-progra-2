import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Figurita {
	//private Random random;
	private int numeroID;
	private int valorBase;
	private String nombrePais;
	private int numeroJugador; // 0 al 12
	


	public Figurita(int numeroID, int valorBase, String pais, int numJug) {
		// TODO Auto-generated constructor stub
		this.numeroID = numeroID;
		this.valorBase = valorBase;
		this.nombrePais = pais;
		this.numeroJugador=numJug;
	}

	

	public int getNumeroID() {
		return numeroID;
	}

	public void setNumeroID(int numero) {
		this.numeroID = numero;
	}


	public int getValorBase() {
		return valorBase;
	}


	public void setValorBase(int valorBase) {
		this.valorBase = valorBase;
	}


	public String getNombrePais() {
		return nombrePais;
	}


	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}


	double calcularValorFigurita(int numero) {
		return 0.0;
		
	}
	
	static int aleatorio(int minimo, int maximo) {
		return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}



	public int getNumeroJugador() {
		return numeroJugador;
	}



	public void setNumeroJugador(int numeroJugador) {
		this.numeroJugador = numeroJugador;
	} 



	
}
