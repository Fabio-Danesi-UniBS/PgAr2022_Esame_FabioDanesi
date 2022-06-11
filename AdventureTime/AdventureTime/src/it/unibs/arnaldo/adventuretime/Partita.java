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

	private static final String OPZIONI = "\tOpzioni";
	private static final String COSA_VUOI_FARE = "\tCosa vuoi fare?";
	private static final String ADVENTURE_TIME = "Adventure time!\n";
	private static final String INSERISCI_IL_TUO_NOME_GIOCATORE = "Inserisci il tuo nome, giocatore: ";
	private static final String GAME_OVER = "GAME OVER!";
	private static final String È = " è: ";
	private static final String LO_STATO_ATTUALE_DEL_GIOCATORE = "Lo stato attuale del giocatore ";
	private static final String CURATO_DI = "Curato di";
	private static final String STRING = "!";
	private static final String COSA_VUOI_USARE_DIGITANE_IL_NOME = "Cosa vuoi usare? Digitane il nome: ";
	private static final String COSA_VUOI_VISUALIZZARE = "Cosa vuoi visualizzare? ";
	private static final String E_PER_APRIRE_LA_CHEST_SOTTO_CUI_STAI = "E per aprire la chest sotto cui stai: ";
	private static final String W_PER_SU_A_PER_SINISTRA_S_PER_BASSO_E_D_PER_DESTRA = "W per Su, A per Sinistra, S per Basso e D per Destra: ";
	
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
		MyMenu menu=new MyMenu(OPZIONI, VOCI);
		MyMenu menu2=new MyMenu(COSA_VUOI_FARE, VOCI2);
		
		System.out.println(ADVENTURE_TIME);
		Giocatore g=creaGiocatore();
		
		Mappa mappa= new Mappa(g);
		
		System.out.println(mappa.toString());
		
		int scegli=-1;
		int scegli2=-1;
		do {
			
			scegli=menu.scegli();
			switch(scegli) {
			case 1:
				String digitaMossa=InputDati.leggiStringaNonVuota(W_PER_SU_A_PER_SINISTRA_S_PER_BASSO_E_D_PER_DESTRA);
				mappa.muovi(digitaMossa);
				break;
			case 2:
				String apri=InputDati.leggiStringaNonVuota(E_PER_APRIRE_LA_CHEST_SOTTO_CUI_STAI);
				mappa.apri(apri);
				break;
			case 3:
				System.out.println(mappa.getGiocatore().getInventario().toString());
				scegli2=menu2.scegli();
				
				switch(scegli2) {
				case 1:
					String vedi=InputDati.leggiStringaNonVuota(COSA_VUOI_VISUALIZZARE);
					vedi=vedi.trim().toUpperCase();
					
					for(int i=0; i<mappa.getGiocatore().getInventario().size(); i++) {
						if(mappa.getGiocatore().getInventario().get(i).getNome().equals(vedi)) {
							System.out.println(mappa.getGiocatore().getInventario().get(i).getDescrizione());
						}
					}
					break;
				case 2:
					String usa=InputDati.leggiStringaNonVuota(COSA_VUOI_USARE_DIGITANE_IL_NOME);
					usa=usa.trim().toUpperCase();
					
					boolean find=false;
					
					for(int i=0; i<mappa.getGiocatore().getInventario().size() && find!=true; i++) {
						if(mappa.getGiocatore().getInventario().get(i).getNome().equals(usa)) {
							
							if(mappa.getGiocatore().getInventario().get(i).equals(Oggetto.POZIONE)) {
								
								mappa.getGiocatore().setVita(Math.min(mappa.getGiocatore().getVitaMax(), mappa.getGiocatore().getVita()+(mappa.getGiocatore().getVitaMax()*(50/100))));
								System.out.println(CURATO_DI+(mappa.getGiocatore().getVitaMax()*(50/(double)100))+STRING);
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
				System.out.println(LO_STATO_ATTUALE_DEL_GIOCATORE+ mappa.getGiocatore().getNome()+È);
				System.out.println(mappa.getGiocatore().toString());
				break;
			}
			System.out.println(mappa.toString());
			
		}while(g.isMorto()!=true && mappa.fine(g.getCoordinate())!=true && scegli!=0);
		System.out.println(GAME_OVER);
	}

	/**
	 * Metodo per creare un nuovo giocatore
	 * @return
	 */
	private Giocatore creaGiocatore() {
		ArrayList<Oggetto> inventario= new ArrayList<Oggetto>();
		Oggetto oggettoIni=Oggetto.POZIONE;
		
		String nome=InputDati.leggiStringaNonVuota(INSERISCI_IL_TUO_NOME_GIOCATORE);
		inventario.add(oggettoIni);
		inventario.add(oggettoIni);
		inventario.add(oggettoIni);
		
		Oggetto oggettoEqui=Oggetto.NULLA;
		
		return new Giocatore(nome, inventario, oggettoEqui);
	}
	
}
