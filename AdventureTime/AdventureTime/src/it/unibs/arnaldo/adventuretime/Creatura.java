package it.unibs.arnaldo.adventuretime;

import java.util.Random;

/**
 * Classe astratta Creatura
 * @author Fabio Danesi
 *
 */
public abstract class Creatura {

	private static final int _2 = 2;
	private static final int _25 = 25;
	private static final double _1_5 = 1.5;
	private static final double _7_5 = 7.5;
	private static final int _1 = 1;
	private static final int _101 = 101;
	private static final int _0 = 0;
	private static final String FÀ = " fà ";
	private static final String DANNO_I_A = " danno/i a ";
	private static final String STRING = "!";
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
		if(this.getVita()<=_0) {
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
		double prob=rd.nextDouble(_1, _101);
		double mod=_1;
		
		if(flagMostro==true && prob<=_7_5) {
			mod=_1_5;
		}
		
		double danno=(( (_2*this.getOggettoEquip().getAttacco()*this.getAttacco())/(double)(_25*this.getDifesa()))+_2)*mod;
		creatura.setVita(creatura.getVita()-danno);
		System.out.println(this.getNome()+FÀ+ danno+ DANNO_I_A +creatura.getNome()+STRING);
	}

	@Override
	public String toString() {
		return "nome=" + nome + ", vita=" + vita + ", oggettoEquip=" + oggettoEquip + ", attacco=" + attacco
				+ ", difesa=" + difesa;
	}
	
	
	
}
