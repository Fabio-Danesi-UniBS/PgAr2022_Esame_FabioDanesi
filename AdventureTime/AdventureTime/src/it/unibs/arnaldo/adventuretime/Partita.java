package it.unibs.arnaldo.adventuretime;

import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

/**
 * Classe Partita
 * @author Fabio Danesi
 *
 */
public class Partita {

	private static final String[] VOCI= {"Muovi", "Apri chest", "Scegli dall'inventario", "Stato giocatore"};
	private static final String[] VOCI2= {"Visualizza dettagli", "Usa"};
	
	/**
	 * Costruttore di Partita
	 */
	public Partita() {
		start();
	}
	
	/**
	 * Metodo principale per la gestione della partita
	 */
	public void start() {
		MyMenu menu=new MyMenu("Opzioni", VOCI);
		MyMenu menu2=new MyMenu("Cosa vuoi fare?", VOCI2);
		
		System.out.println("Adventure time!\n");
		Giocatore g=creaGiocatore();
		
		Mappa mappa= new Mappa(g);
		
		System.out.println(mappa.toString());
		
		int scegli=-1;
		int scegli2=-1;
		do {
			
			scegli=menu.scegli();
			switch(scegli) {
			case 1:
				String digitaMossa=InputDati.leggiStringaNonVuota("W per Su, A per Sinistra, S per Basso e D per Destra: ");
				mappa.muovi(digitaMossa);
				break;
			case 2:
				String apri=InputDati.leggiStringaNonVuota("E per aprire la chest sotto cui stai: ");
				mappa.apri(apri);
				break;
			case 3:
				System.out.println(mappa.getGiocatore().getInventario().toString());
				scegli2=menu2.scegli();
				
				switch(scegli2) {
				case 1:
					String vedi=InputDati.leggiStringaNonVuota("Cosa vuoi visualizzare? ");
					vedi=vedi.trim().toUpperCase();
					
					for(int i=0; i<mappa.getGiocatore().getInventario().size(); i++) {
						if(mappa.getGiocatore().getInventario().get(i).getNome().equals(vedi)) {
							System.out.println(mappa.getGiocatore().getInventario().get(i).getDescrizione());
						}
					}
					break;
				case 2:
					String usa=InputDati.leggiStringaNonVuota("Cosa vuoi usare? ");
					usa=usa.trim().toUpperCase();
					
					boolean find=false;
					
					for(int i=0; i<mappa.getGiocatore().getInventario().size() && find!=true; i++) {
						if(mappa.getGiocatore().getInventario().get(i).getNome().equals(usa)) {
							
							if(mappa.getGiocatore().getInventario().get(i).equals(Oggetto.POZIONE)) {
								
								mappa.getGiocatore().setVita(Math.min(mappa.getGiocatore().getVitaMax(), mappa.getGiocatore().getVita()+(mappa.getGiocatore().getVitaMax()*(50/100))));
								System.out.println("Curato di"+(mappa.getGiocatore().getVitaMax()*(50/(double)100))+"!");
							}else {
								Oggetto ogg=mappa.getGiocatore().getInventario().get(i);
								mappa.getGiocatore().setOggettoEquip(ogg);
								mappa.getGiocatore().setAttacco(mappa.getGiocatore().getAttacco()+ogg.getAttacco());
								mappa.getGiocatore().setVita(mappa.getGiocatore().getVita()+ogg.getDifesa());
								
							}
							mappa.getGiocatore().getInventario().remove(i);
							find=true;
						}
					}
					break;
					
				}
				break;
				
			case 4:
				System.out.println("Lo stato attuale del giocatore "+ mappa.getGiocatore().getNome()+" è: ");
				System.out.println(mappa.getGiocatore().toString());
				break;
			}
			System.out.println(mappa.toString());
			
		}while(g.isMorto()!=true && mappa.fine(g.getCoordinate())!=true && scegli!=0);
		System.out.println("GAME OVER!");
	}

	/**
	 * Metodo per creare un nuovo giocatore
	 * @return
	 */
	private Giocatore creaGiocatore() {
		ArrayList<Oggetto> inventario= new ArrayList<Oggetto>();
		Oggetto oggettoIni=Oggetto.POZIONE;
		
		String nome=InputDati.leggiStringaNonVuota("Inserisci il tuo nome, giocatore: ");
		inventario.add(oggettoIni);
		inventario.add(oggettoIni);
		inventario.add(oggettoIni);
		
		Oggetto oggettoEqui=Oggetto.NULLA;
		
		return new Giocatore(nome, inventario, oggettoEqui);
	}
	
}
