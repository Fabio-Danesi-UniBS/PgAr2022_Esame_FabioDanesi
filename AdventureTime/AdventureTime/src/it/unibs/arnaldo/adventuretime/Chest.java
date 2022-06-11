package it.unibs.arnaldo.adventuretime;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Chest
 * @author Fabio Danesi
 *
 */
public class Chest {

	private Oggetto oggetto;
	private TipoOggetto tipo;
	
	/**
	 * Costruttore
	 * @param oggetto
	 * @param tipo
	 */
	public Chest(Oggetto oggetto, TipoOggetto tipo) {
		super();
		this.oggetto = oggettoRandom();
		this.tipo = oggetto.getTipo();
	}
	
	/**
	 * Costruttore alternativo
	 */
	public Chest() {
		super();
		this.oggetto = oggettoRandom();
		this.tipo = oggetto.getTipo();
	}
	
	/**
	 * Metodo per riempire una chest con un oggetto random
	 * @return
	 */
	public Oggetto oggettoRandom() {
		Random rd= new Random();
		int fato=-1;
		
		ArrayList<Oggetto> o= new ArrayList<Oggetto>();
		Oggetto oggetto=Oggetto.NULLA;
		
		int caso=rd.nextInt(1, 101);
		
		if(caso>=1 && caso<=40) {//armi
			o=Oggetto.spade();
			fato=rd.nextInt(0, o.size()); 
			oggetto=o.get(fato);
		}else if(caso>40 && caso<=75) {//scudi
			o=Oggetto.scudi();
			fato=rd.nextInt(0, o.size());
			oggetto=o.get(fato);
		}else if(caso>75 && caso<=100) {//pozioni
			o=Oggetto.pozioni();
			fato=rd.nextInt(0, o.size());
			oggetto=o.get(fato);
		}
		return oggetto;
		
	}

	public Oggetto getOggetto() {
		return oggetto;
	}
	
	public void setOggetto(Oggetto oggetto) {
		this.oggetto = oggetto;
	}
	
	public TipoOggetto getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoOggetto tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Chest oggetto=" + oggetto + ", tipo=" + tipo;
	}
	
	
	
	
}
