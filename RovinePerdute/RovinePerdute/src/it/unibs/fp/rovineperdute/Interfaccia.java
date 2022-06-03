package it.unibs.fp.rovineperdute;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import java.util.*;

public class Interfaccia {

	public Interfaccia() throws XMLStreamException{
		start();
	}

	private void start() throws XMLStreamException{
		
		String[] inputFiles= {"PgAr_Map_5.xml", "PgAr_Map_12.xml", "PgAr_Map_50.xml", "PgAr_Map_200.xml", "PgAr_Map_2000.xml", "PgAr_Map_10000.xml"};
		
		System.out.println("Benvenuto alle ROVINE PERDUTE!\n");
		
		for(int i=0; i<inputFiles.length; i++) {
			
			System.out.println("Lettura della mappa "+inputFiles[i]+"...\n");
			
			HashMap<Integer, Citta> listaCittaDistanza=new HashMap<Integer, Citta>();
			
			try {
				listaCittaDistanza = Input.leggiInputCitta(inputFiles[i]);
			} catch (ParserConfigurationException | SAXException e1) {
				e1.printStackTrace();
			}
	        HashMap<Integer, Citta> listaCittaAltitudine=new HashMap<Integer, Citta>();
	        
			try {
				listaCittaAltitudine = Input.leggiInputCitta(inputFiles[i]);
			} catch (ParserConfigurationException | SAXException e1) {
				e1.printStackTrace();
			}
	        
	        System.out.println("Lettura della mappa "+inputFiles[i]+" completata!\n");
	        
	        Mappa mappaSorgente = new Mappa(listaCittaDistanza);
	        Mappa mappaAltitudine = new Mappa(listaCittaAltitudine);
	        Mappa mappaDistanza = new Mappa(listaCittaDistanza);


	        mappaSorgente.matriceDistanze();


	        mappaAltitudine.percorsoAltitudine(mappaSorgente);
	        mappaAltitudine.percorsoCorto(mappaAltitudine.listaCitta.get(0));


	        mappaDistanza.percorsoPlanare(mappaSorgente);
	        mappaDistanza.percorsoCorto(mappaDistanza.listaCitta.get(0));


	        int numCittaDistanza=listaCittaDistanza.size()-1;
	        int numCittaAltitudine=listaCittaAltitudine.size()-1;
	        
	        List<Citta> percorsoTonathiu = mappaDistanza.restituisciPercorsoPiuCorto(listaCittaDistanza.get(numCittaDistanza));
	        List<Citta> percorsoMetztli = mappaAltitudine.restituisciPercorsoPiuCorto(listaCittaAltitudine.get(numCittaAltitudine));

	        try {
				Output.stampaXML(percorsoTonathiu, percorsoMetztli, i);
			} catch (ParserConfigurationException | TransformerException e) {
				System.out.println("Errore nella scrittura!\n");
				e.printStackTrace();
			}
		}
		
		System.out.println("Tutti i percorsi mappati!");
		
	}
}
