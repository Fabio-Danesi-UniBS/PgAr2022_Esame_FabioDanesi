package it.unibs.arnaldo.codicefis;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcessazioneDati {

	private File comuni;
	private File inputPersone;
	private File codiciFiscali;
	private File output;
	
	public ProcessazioneDati() {
		super();
		this.comuni= new File("");
		this.inputPersone= new File("");
		this.codiciFiscali= new File("");
		this.output= new File("");
	}
	
	public void esegui() {
		//da implementare...
		if(controlloCodice()==true) {
			System.out.println("OK!");
		}else {
			System.out.println("ERRORE!");
		}
	}
	
	public boolean controlloCodice() {//da mettere boolean al posto di void!
		CodiceFiscale codice;
		
		String string;//per prova al posto di un codice letto da file!
		
		//NON HO PROVATO TUTTI I CASI POSSIBILI/IMMAGINABILI, CHE SIA CHIARO! :)
		//string="FSTPLA98M01B157E";//codice corretto!
		//string="FSTA1398M01B157E";//codice non corretto!
		string="FSTPLA96B29B157E";//codice corretto!
		//string="FSTPLA93B29B157E";//codice non corretto!
		//string="FSTPLA96B30B157E";//codice non corretto!
		//string="1111111111111111";//codice non corretto!
		//string="AAAAAAAAAAAAAAAA";//codice non corretto!
		//string="FST1LAA8M01B157EAA";//codice troppo lungo!
		
		String cognome, nome, anno, mese, giorno, luogo, controllo;
		//String annoMod;
		//StringBuffer verificaAnno=new StringBuffer();
		string=string.trim();
		
		if(string.length()==16) {
			cognome=string.substring(0, 3);
			nome=string.substring(3, 6);
			
			anno=string.substring(6, 8);
			
			/*if(anno.matches("[0-9]+")) {
				verificaAnno.append("19"+anno);
				annoMod=verificaAnno.toString();
				bisestile(annoMod);
			}*/
			
			mese=string.substring(8, 9);
			giorno=string.substring(9, 11);
			luogo=string.substring(11, 15);
			controllo=string.substring(15, 16);
			codice=new CodiceFiscale(cognome, nome, anno, mese, giorno,luogo, controllo);
			if(codice.takeAControl()==true) {
				System.out.println("SCRITTO IN ELENCO DATI");
				return true;
				//scrivi
			}else {
				System.out.println("SCRITTO IN SPAIATI");
				//scrivi in spaiati
			}
			
		}else {
			System.out.println(string+" non è corretto!");
		}
		return false;
	}
}
