package it.unibs.arnaldo.adventuretime;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Mostro
 * @author Fabio Danesi
 *
 */
public class Mostro extends Creatura {

	private static final int _1 = 1;
	private static final int _26 = 26;
	private static final int _15 = 15;
	private static final int _40 = 40;
	private static final int _10 = 10;
	private static final int _5 = 5;
	private static final String DIJKSTRA = "Dijkstra";

	/**
	 * Costruttore
	 * @param vita
	 * @param oggettoEquip
	 * @param attacco
	 * @param difesa
	 */
	public Mostro(double vita, Oggetto oggettoEquip, int attacco, int difesa) {
		super(Mostro.generaNome(), vita, oggettoEquip, attacco, difesa);
		
	}
	
	/**
	 * Costruttore alternativo
	 */
	public Mostro() {
		
		super(Mostro.generaNome(), Mostro.generaVita(), Oggetto.SPADA, _5, _5);
		
		//test
		//super(Mostro.generaNome(), Mostro.generaVita(), Oggetto.NULLA, 5, 5);
	}
	
	/**
	 * Costruttore molto più alternativo
	 * @param nome
	 * @param vita
	 * @param oggetto
	 * @param attacco
	 * @param difesa
	 */
	public Mostro(String nome, double vita, Oggetto oggetto, int attacco, int difesa) {
		
		//test
		//super(Mostro.generaNome(), Mostro.generaVita(), Oggetto.SPADA, 5, 5, coordinate);
		//super("Dijkstra!", 40, Oggetto.SPADA_FORTISSIMA, 10, 10);
		
		super(nome, vita, oggetto, attacco, difesa);
	}
	
	/**
	 * Metodo per creare Dijkstra
	 * @return
	 */
	public static Mostro diQualcosa() {
		return new Mostro(DIJKSTRA, _40, Oggetto.SPADA_FORTISSIMA, _10, _10);
	}
	
	/**
	 * Metodo per generare casualmente la vita dei nemici
	 * @return
	 */
	public static int generaVita() {
		Random rd=new Random();
		int vita=rd.nextInt(_15, _26);
		return vita;
	}

	@Override
	public String toString() {
		return "Mostro [getOggettoEquip()=" + getOggettoEquip() + ", getVita()=" + getVita() + ", getNome()="
				+ getNome() + ", getAttacco()=" + getAttacco() + ", getDifesa()=" + getDifesa() + ", isMorto()=" + isMorto() + "]";
	}
	
	/**
	 * Metodo per generare i nomi dei nemici
	 * @return
	 */
	public static String generaNome() {
		Random rd=new Random();
		
		ArrayList<String> nomi=permutazioni(DIJKSTRA);
		int caso= rd.nextInt(0, nomi.size());
		
		return nomi.get(caso);
	}
	
	/**
	 * Metodo per listare le permutazioni della parola Dijkstra
	 * @param daPermutare
	 * @return
	 */
	public static ArrayList<String> permutazioni(String daPermutare)
	{
		ArrayList<String> prodotto = new ArrayList<String>();
	if (daPermutare.length() <= _1)
		prodotto.add(daPermutare);
	else
	{
		for (int i = 0; i < daPermutare.length(); i++)
		{
			char iniziale = daPermutare.charAt(i);
			String unoDiMeno=daPermutare.substring(0,i)+
					daPermutare.substring(i+_1);
			ArrayList<String> subProdotto = permutazioni(unoDiMeno);
			for (String subPermutazione : subProdotto) {
				prodotto.add(iniziale + subPermutazione);
			}
				
		}
	}
	return prodotto;
	}

}
