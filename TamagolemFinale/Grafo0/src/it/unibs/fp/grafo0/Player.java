package it.unibs.fp.grafo0;

import java.util.*;

/**
 * Classe Player.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Player {

	private String nome;
	private int numeroDiTamagolem;
	private ArrayList<Tamagolem> tama;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroDiTamagolem() {
		return numeroDiTamagolem;
	}

	public void setNumeroDiTamagolem(int numeroDiTamagolem) {
		this.numeroDiTamagolem = numeroDiTamagolem;
	}

	public ArrayList<Tamagolem> getTama() {
		return tama;
	}

	public void setTama(ArrayList<Tamagolem> tama) {
		this.tama = tama;
	}

	/**
	 * Costruttore della classe Player con attributi formali (ArrayList<String> pietre, int numeroDiTamagolem).
	 * @param pietre
	 * @param numeroDiTamagolem
	 */
	public Player(ArrayList<String> pietre, int numeroDiTamagolem) {
		super();
		this.nome = InputDati.leggiStringaNonVuota("Inserisci il nome del giocatore: ");
		this.numeroDiTamagolem = numeroDiTamagolem-1;
		this.tama = new ArrayList<Tamagolem>();

		creaNuovoTamagolem(pietre);
		//Tamagolem creati singolarmente!
	}
	
	public Player() {
	}
	
	/**
	 * Metodo per creare un nuovo tamagolem.
	 * @param pietre
	 */
	public void creaNuovoTamagolem(ArrayList<String> pietre){

		if(numeroDiTamagolem>=0) {
			tama.add(new Tamagolem(pietre));

		}
	}
	public void rimuovoTamagolemMorto(){

			tama.remove(0);

	}
	
	/**
	 * Metodo per cambiare un tamagolem sconfitto con uno nuovo.
	 * @param pietre
	 */
	public void cambiaTamagolem(ArrayList<String> pietre){

			creaNuovoTamagolem(pietre);
			rimuovoTamagolemMorto();
	}
	
	/**
	 * Metodo per verificare la fine della partita!
	 * @return
	 */
	public boolean fine() {
		if(numeroDiTamagolem<=0 && tama.get(0).getVita()<=0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Player [nome=" + nome + ", numeroDiTamagolem=" + numeroDiTamagolem + ", tama=" + tama + "]";
	}
	
}
