package it.unibs.arnaldo.adventuretime;

import java.util.ArrayList;
import java.util.Random;

public enum Oggetto {

	NULLA("NULLA", TipoOggetto.NULLA, "Nulla", 1, 0),
	SPADA("SPADA", TipoOggetto.SPADA, "Desc Spada", Oggetto.attaccoRandom(), 0),
	SPADA_FORTISSIMA("SPADA_FORTISSIMA", TipoOggetto.SPADA, "Desc Spada FORTISSIMAAAAAAA", 70, 0),
	SCUDO("SCUDO", TipoOggetto.SCUDO, "Desc Scudo", 0, 5),
	SCUDO_TOP("SCUDO_TOP", TipoOggetto.SCUDO, "Desc Scudo TOPPPPP", 0, 10),
	POZIONE("POZIONE", TipoOggetto.POZIONE, "Desc Pozione", 0, 0);
	
	private Oggetto(String nome, TipoOggetto tipo, String descrizione, int attacco, int difesa) {
		this.nome = nome;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.attacco = attacco;
		this.difesa = difesa;
	}

	public static ArrayList<Oggetto> pozioni(){
		
		ArrayList<Oggetto> pozioni=new ArrayList<Oggetto>();
		
		for(Oggetto o: Oggetto.values()) {
			if(o.getTipo().equals(TipoOggetto.POZIONE)) {
				pozioni.add(o);
			}
		}
		return pozioni;
	}
	
	public static ArrayList<Oggetto> scudi(){
		
		ArrayList<Oggetto> scudi=new ArrayList<Oggetto>();
		
		for(Oggetto o: Oggetto.values()) {
			if(o.getTipo().equals(TipoOggetto.SCUDO)) {
				scudi.add(o);
			}
		}
		return scudi;
	}
	
	public static ArrayList<Oggetto> spade(){
		
		ArrayList<Oggetto> spade=new ArrayList<Oggetto>();
		
		for(Oggetto o: Oggetto.values()) {
			if(o.getTipo().equals(TipoOggetto.SPADA)) {
				spade.add(o);
			}
		}
		return spade;
	}
	
	public static int attaccoRandom() {
		Random rd=new Random();
		int val=rd.nextInt(35, 56);
		
		return val;
	}

	private String nome;
	private TipoOggetto tipo;
	private String descrizione;
	private int attacco;
	private int difesa;//vita dello scudo!
	
	public int getAttacco() {
		return attacco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoOggetto getTipo() {
		return tipo;
	}

	public void setTipo(TipoOggetto tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getDifesa() {
		return difesa;
	}

	public void setDifesa(int difesa) {
		this.difesa = difesa;
	}

	public void setAttacco(int attacco) {
		this.attacco = attacco;
	}
	
	

}
