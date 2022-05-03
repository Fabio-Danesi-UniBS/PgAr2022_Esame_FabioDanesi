package it.unibs.arnaldo.codicefis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcessazioneDati {

	public static ArrayList<Comune> iComuni=new ArrayList<Comune>();
	public static ArrayList<Codice> iCodici=new ArrayList<Codice>();
	public static ArrayList<Codice> iNonCodici=new ArrayList<Codice>();
	public static ArrayList<Persona> lePersone=new ArrayList<Persona>();
	
	private File comuni;
	private File inputPersone;
	private File codiciFiscali;
	private File output;
	
	public ProcessazioneDati() {
		super();
		this.comuni= new File("src/comuni.xml");
		this.inputPersone= new File("src/inputPersone.xml");
		this.codiciFiscali= new File("src/codiciFiscali.xml");
		this.output= new File("src/output.xml");
	}
	
	public void esegui() throws ParserConfigurationException, SAXException {
		letturaComuni();
		System.out.println(iComuni.toString());
		letturaCodici();
		controlloCodice();
		System.out.println("Codici:\nCorretti:\n"+iCodici.toString());
		System.out.println("Non Codici:\n"+iNonCodici.toString());
		letturaDatiPersone();
		System.out.println("Codici:\nCorretti:\n"+lePersone.toString());
		
		System.out.println("SIZE COMUNI: "+iComuni.size());
		System.out.println("SIZE CODICI: "+iCodici.size());
		System.out.println("SIZE NON CODICI: "+iNonCodici.size());
		System.out.println("SIZE PERSONE: "+lePersone.size());
		//da implementare...
		
		/*if(controlloCodice()==true) {
			System.out.println("OK!");
		}else {
			System.out.println("ERRORE!");
		}*/
	}
	
	public void letturaComuni() throws ParserConfigurationException, SAXException{
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(comuni);
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("comune");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String nome=eElement.getElementsByTagName("nome").item(0).getTextContent();
                    String codice=eElement.getElementsByTagName("codice").item(0).getTextContent();

                    iComuni.add(new Comune(nome, codice));
                }
            }
            System.out.println("Fine lettura comuni.xml.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
	}
	
	public void letturaDatiPersone() throws ParserConfigurationException, SAXException{
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(inputPersone);
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("persona");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String nome=eElement.getElementsByTagName("nome").item(0).getTextContent();
                    String cognome=eElement.getElementsByTagName("cognome").item(0).getTextContent();
                    String sesso=eElement.getElementsByTagName("sesso").item(0).getTextContent();
                    String comuneNascita=eElement.getElementsByTagName("comune_nascita").item(0).getTextContent();
                    String dataNascita=eElement.getElementsByTagName("data_nascita").item(0).getTextContent();

                    lePersone.add(new Persona(nome, cognome, sesso, comuneNascita, dataNascita));
                }
            }
            System.out.println("Fine lettura inputPersone.xml.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
	}
	//
	
	public void letturaCodici() throws ParserConfigurationException, SAXException{
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(codiciFiscali);
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
            NodeList nList = document.getElementsByTagName("codice");//
            System.out.println(nList.getLength());
            //System.out.println("----------------------------");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = (Node)nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    String codice=nNode.getTextContent();
                    System.out.println(codice.toString());
                    iCodici.add(new Codice(codice));
                }
            }
            System.out.println("Fine lettura codiciFiscali.xml.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
	}
	
	public void controlloCodice() {//da mettere boolean al posto di void!
		CodiceFiscale codice;
		
		String string;
		String cognome, nome, anno, mese, giorno, luogo, controllo;
		
		//NON HO PROVATO TUTTI I CASI POSSIBILI/IMMAGINABILI, CHE SIA CHIARO! :)
		//string="FSTPLA98M01B157E";//codice corretto!
		//string="FSTA1398M01B157E";//codice non corretto!
		//string="FSTPLA96B29B157E";//codice corretto!
		//string="FSTPLA93B29B157E";//codice non corretto!
		//string="FSTPLA96B30B157E";//codice non corretto!
		//string="1111111111111111";//codice non corretto!
		//string="AAAAAAAAAAAAAAAA";//codice non corretto!
		//string="FST1LAA8M01B157EAA";//codice troppo lungo!
		
		for(int i=0; i<iCodici.size(); i++) {
			string=iCodici.get(i).toString().trim();
			
			if(string.length()==16) {
				cognome=string.substring(0, 3);
				nome=string.substring(3, 6);
				
				anno=string.substring(6, 8);
				
				mese=string.substring(8, 9);
				giorno=string.substring(9, 11);
				luogo=string.substring(11, 15);
				controllo=string.substring(15, 16);
				codice=new CodiceFiscale(cognome, nome, anno, mese, giorno,luogo, controllo);
				if(codice.takeAControl()!=true) {
					System.out.println("SCRITTO IN ELENCO CODICI ERRATI");//iNonCodici
					//return true;
					iNonCodici.add(new Codice(string));
					iCodici.remove(i);
				}
			}else {
				System.out.println(string+" non è corretto!");
				iNonCodici.add(new Codice(string));
				iCodici.remove(i);
			}
			//return false;
		}
	}
}
