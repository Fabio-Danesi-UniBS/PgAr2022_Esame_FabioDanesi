package it.unibs.fp.grafo0;

import java.util.*;

/**
 * Classe Pietra.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Pietra {
	private int numPietreScorta;
	private int numPietreScortaAttuali;
	private ArrayList<Elementi> scortaPietre;
	private ArrayList<Elementi> scortaPietreScelte;
	
	public int getNumPietreScortaAttuali() {
		return numPietreScortaAttuali;
	}

	public void setNumPietreScortaAttuali(int numPietreScortaAttuali) {
		this.numPietreScortaAttuali = numPietreScortaAttuali;
	}

	public ArrayList<Elementi> getScortaPietreScelte() {
		return scortaPietreScelte;
	}

	public void setScortaPietreScelte(ArrayList<Elementi> scortaPietreScelte) {
		this.scortaPietreScelte = scortaPietreScelte;
	}

	private static final int numElementi= Elementi.contaElementi();//N
	
	/**
	 * Costruttore della classe Pietra con attributo formale (int numeroTama).
	 * @param numeroTama
	 */
	public Pietra(int numeroTama) {//Numero tama=> 12/6=2
		super();
		this.numPietreScorta = numPietre(numeroTama);
		this.numPietreScortaAttuali=numPietreScorta;
		this.scortaPietre = creaScorta(numeroTama);
		this.scortaPietreScelte=new ArrayList<Elementi>();
		
	}
	
	/**
	 * Estrazione di elementi dalla riserva comune.
	 * @param daCercare
	 * @return
	 */
	public  String estrai(Elementi daCercare) {
		String ritorna="NON TROVATO!";

		boolean trovato=false;

		for(int i=0; i<scortaPietre.size() && trovato!=true; i++) {
			//System.out.println(scortaPietre.get(i).toString());
			if(scortaPietre.get(i).equals(daCercare)) {
				Elementi elem=scortaPietre.get(i);
				ritorna=elem.toString();
				scortaPietreScelte.add(elem);
				scortaPietre.remove(i);
				numPietreScortaAttuali--;
				trovato=true;
			}
		}
		return ritorna;
	}
	
	/**
	 * Immissione di elementi (previo presi) dalla riserva comune.
	 * @param daCercare
	 * @return
	 */
	public String rimetti(Elementi daCercare) {
		String ritorna="NON TROVATO!";
		
		boolean trovato=false;

		for(int i=0; i<scortaPietreScelte.size() && scortaPietreScelte.size()!=0 &&trovato!=true; i++) {
			//System.out.println(scortaPietre.get(i).toString());
			if(scortaPietreScelte.get(i).equals(daCercare)) {
				Elementi elem=scortaPietreScelte.get(i);
				ritorna=elem.toString();
				scortaPietre.add(elem);
				scortaPietreScelte.remove(i);
				numPietreScortaAttuali++;
				trovato=true;
			}
		}
		return ritorna;
	}
	
	/**
	 * Metodo per creare una scorta di pietre.
	 * @param numeroTama
	 * @return
	 */
	public ArrayList<Elementi> creaScorta(int numeroTama){
		
		ArrayList<Elementi> pietre= new ArrayList<Elementi>();
		int numPietrePerElemento = (int)numPietreScorta/numElementi;
		
		for(Elementi ele : Elementi.values()) {
			for(int i=0; i<numPietrePerElemento; i++) {
				pietre.add(ele);
			}
		}
		return pietre;
	}
	public static int numPietrePerGolem(int numeroTama) {
		int numPietre=(int)Math.ceil((Elementi.contaElementi()+1)/3)+1;//P
		return numPietre;
	}
	/**
	 * Metodo per ricavare il numero di pietre.
	 * @param numeroTama
	 * @return
	 */
	public static int numPietre(int numeroTama){
		
		int numPietre=numPietrePerGolem(numeroTama);
		int numPietreScortaComune=(int)Math.ceil((numPietre*numeroTama*2)/numElementi)*numElementi;
		return numPietreScortaComune;
	}
	
	public int getNumPietreScorta() {
		return numPietreScorta;
	}

	public void setNumPietreScorta(int numPietreScorta) {
		this.numPietreScorta = numPietreScorta;
	}

	public ArrayList<Elementi> getScortaPietre() {
		return scortaPietre;
	}

	public void setScortaPietre(ArrayList<Elementi> scortaPietre) {
		this.scortaPietre = scortaPietre;
	}

	@Override
	public String toString() {
		return "Pietra [numPietreScortaAttuali=" + numPietreScortaAttuali + ", scortaPietre=" + scortaPietre + "]";
	}
	
	public String toStringAlter() {
		return ""+scortaPietre+"\n";
	}
	
	
	
}
