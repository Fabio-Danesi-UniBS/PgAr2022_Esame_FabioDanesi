package it.unibs.fp.grafo0;

import java.util.*;
import it.unibs.fp.mylib.*;
/**
 * Classe Tamagolem.
 * @author Fabio Danesi, Davide Branchetti, Brilant Gashi.
 *
 */
public class Tamagolem {

	private String nome;
	private int vita;
	private ArrayList<String> pietre;
	
	public Tamagolem(ArrayList<String> pietreScelte) {
		//super();
		if(pietreScelte.size()>0) {
		this.nome= InputDati.leggiStringaNonVuota("Inserisci il nome del tamagolem: ");
		this.vita = 100;
		this.pietre = pietreScelte;
		}else {
			System.out.println("Errorrrrrrrrrrrrrr");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public ArrayList<String> getPietre() {
		return pietre;
	}

	public void setPietre(ArrayList<String> pietre) {
		this.pietre = pietre;
	}

	/**
	 * Metodo per la gestione del combattimento tra 2 tamagolem.
	 * @param tama
	 * @param equi
	 */
	public void combatti(Tamagolem tama, Equilibrio equi) {
		String elementoTama=pietre.get(0).toString();
		String elementoTama2=tama.pietre.get(0).toString();
		
		int ele=equi.visualizzaValori(elementoTama, elementoTama2);
		int ele2=equi.visualizzaValori(elementoTama2, elementoTama);
		
		if(ele>ele2) {
			//vince il primo tama e fà danno sul secondo!
			tama.setVita(tama.getVita() - ele);
		}else if(ele<ele2) {
			//vince il secondo tama e fà danno sul primo!
			this.setVita(getVita() - ele2);
		}
		//cercare gli elementi nell'equilibrio(e1->e2 , e2->e1) e verificare quale ha il valore piï¿½ alto; sottrarre alla vita del perdente il valore dell'elemento piï¿½ alto
		
		ruotaPietre(tama);
		
	}
	
	/**
	 * Metodo per ciclare le pietre di un tamagolem durante un combattimento.
	 * @param tama
	 */
	public void ruotaPietre(Tamagolem tama) {
		String pietreT=pietre.get(0);
		pietre.remove(0);
		pietre.add(pietreT);
		
		String pietreT2=tama.pietre.get(0);
		tama.pietre.remove(0);
		tama.pietre.add(pietreT2);
	}
	
	/**
	 * Metodo per verificare la sconfitta di un tamagolem.
	 * @return
	 */
	public boolean sconfitta() {
		boolean morto=false;
		
		if(getVita()<=0) {
			morto=true;
			
		}
		return morto;
	}
	
	/**
	 * Metodo per ricavare il numero di tamagolem disponibili per giocatore.
	 * @return
	 */
	public static int tamaNum() {
		int numPietre=(int)Math.ceil((Elementi.contaElementi()+1)/3)+1;//P
		int numGolem=(int)Math.ceil(((Elementi.contaElementi()-1)*(Elementi.contaElementi()-2))/(2*numPietre));//G
		return numGolem;
	}
	
	@Override
	public String toString() {
		return "Tamagolem [nome=" + nome + ", vita=" + vita + ", pietre=" + pietre + "]";
	}
	
	
}
