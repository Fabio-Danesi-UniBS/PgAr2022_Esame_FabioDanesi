package it.unibs.arnaldo.adventuretime;

import java.util.Random;

/**
 * Classe astratta Creatura
 * @author Fabio Danesi
 *
 */
public abstract class Creatura {

	private String nome;
	private double vita;
	private Oggetto oggettoEquip;
	private int attacco;
	private int difesa;
	
	/**
	 * Costruttore
	 * @param nome
	 * @param vita
	 * @param oggettoEquip
	 * @param attacco
	 * @param difesa
	 */
	public Creatura(String nome, double vita, Oggetto oggettoEquip, int attacco, int difesa) {
		super();
		this.nome = nome;
		this.vita = vita;
		this.oggettoEquip = oggettoEquip;
		this.attacco = attacco;
		this.difesa = difesa;
	}

	public Oggetto getOggettoEquip() {
		return oggettoEquip;
	}

	public void setOggettoEquip(Oggetto oggettoEquip) {
		this.oggettoEquip = oggettoEquip;
	}
	
	public double getVita() {
		return vita;
	}

	public void setVita(double vita) {
		this.vita = vita;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getAttacco() {
		return attacco;
	}
	
	public void setAttacco(int attacco) {
		this.attacco = attacco;
	}
	
	public int getDifesa() {
		return difesa;
	}
	
	public void setDifesa(int difesa) {
		this.difesa = difesa;
	}
	
	/**
	 * Controllo morte
	 * @return
	 */
	public boolean isMorto() {
		boolean morto=false;
		if(this.getVita()<=0) {
			morto=true;
		}
		return morto;
	}
	
	/**
	 * Combattimento generalizzato per giocatore e mostri
	 * @param creatura
	 * @param flagMostro
	 */
	public void combatti(Creatura creatura, boolean flagMostro) {
		Random rd= new Random();
		double prob=rd.nextDouble(1, 101);
		double mod=1;
		
		if(flagMostro==true && prob<=7.5) {
			mod=1.5;
		}
		
		double danno=(( (2*this.getOggettoEquip().getAttacco()*this.getAttacco())/(double)(25*this.getDifesa()))+2)*mod;
		creatura.setVita(creatura.getVita()-danno);
		System.out.println(this.getNome()+" f� "+ danno+ " danno/i a " +creatura.getNome()+"!");
	}

	@Override
	public String toString() {
		return "nome=" + nome + ", vita=" + vita + ", oggettoEquip=" + oggettoEquip + ", attacco=" + attacco
				+ ", difesa=" + difesa;
	}
	
	
	
}
