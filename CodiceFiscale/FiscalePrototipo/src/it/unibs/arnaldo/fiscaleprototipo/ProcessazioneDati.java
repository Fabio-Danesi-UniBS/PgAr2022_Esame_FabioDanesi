package it.unibs.arnaldo.fiscaleprototipo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcessazioneDati {
	
	public static ArrayList<Comune> iComuni=new ArrayList<Comune>();//contiene i comuni imporati dal file XML
	
	public static ArrayList<CodiceFiscale> iCodici=new ArrayList<CodiceFiscale>();//contiene tutti i quanti codici fiscali importati da XML  e  che sono corretti sintatticamente
	public static ArrayList<CodiceFiscale> iNonCodici=new ArrayList<CodiceFiscale>();//contiene tutti i quanti codici fiscali importati da XML  e  che non sono corretti sintatticamente
	
	public static ArrayList<Persona> lePersone=new ArrayList<Persona>();//contiene i dati delle persone importati dal file XML
	
	private File comuni;//memorizza l'indirizzo di memoria in cui leggere e scrivere e punta a comuni.xml
	private File inputPersone;//memorizza l'indirizzo di memoria in cui leggere e scrivere e punta a inputPersone.xml
	private File codiciFiscali;//memorizza l'indirizzo di memoria in cui leggere e scrivere e punta a codiciFiscali.xml
	
	public ProcessazioneDati() {
		super();
		this.comuni= new File("src/comuni.xml");
		this.inputPersone= new File("src/inputPersone.xml");
		this.codiciFiscali= new File("src/codiciFiscali.xml");
	}
	/**
	 * è un metodo che richiama tutti gli altri metodi, in un ordine rispettato in modo rigoroso restituendo feedback video
	 */
	public void esegui() throws ParserConfigurationException, SAXException, TransformerException {
		letturaComuni();
		System.out.println(iComuni.toString());
		letturaCodici();
		controlloCodice();//sempre prima di letturaCodici(); e letturaComuni();
		System.out.println("\nCodici:\nCorretti:\n"+iCodici.toString());
		System.out.println("Non Codici:\n"+iNonCodici.toString());
		letturaDatiPersone();
		System.out.println("Codici:\nCorretti:\n"+lePersone.toString());
		
		scrivi();
		System.out.println("\nCodici fiscali spaiati: "+iCodici.size());
		System.out.println("Codici fiscali non corretti: "+iNonCodici.size());
		System.out.println("Esecuzione terminata!");

	}
	/**
	 * questo metodo permette di scrivere sul file codiciPersone.xml 
	 */
	private void writeXml(Document doc, OutputStream output)throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		// pretty print XML (formatta il codice restituendo così il file .xml ordinato secondo la sua gerarchia 
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		
		transformer.transform(source, result);
		
	}
	/**
	 * è il metodo che genera il file codiciPersone.xml in uscita.
	 */
	private void scrivi() throws ParserConfigurationException, TransformerException{
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	        // root elements
	        Document doc = docBuilder.newDocument();
	        
	        Element rootElement = doc.createElement("output");
	        doc.appendChild(rootElement);
	        
	        Element persone = doc.createElement("persone");
	        rootElement.appendChild(persone);
	        
	        persone.setAttribute("numero", lePersone.size()+"");
	        
	        for(int i=0; i<lePersone.size(); i++) { //questo ciclo for acquisce i dati e li formatta in modo corretto per un file .xml
	        	Element persona = doc.createElement("persona");
		        persone.appendChild(persona);
		        
		        persona.setAttribute("id", i+"");
		        
		        Element nome = doc.createElement("nome");
		        nome.setTextContent(lePersone.get(i).getNome()+"");
		        persona.appendChild(nome);
		        
		        Element cognome = doc.createElement("cognome");
		        cognome.setTextContent(lePersone.get(i).getCognome()+"");
		        persona.appendChild(cognome);
		        
		        Element sesso = doc.createElement("sesso");
		        sesso.setTextContent(lePersone.get(i).getSesso()+"");
		        persona.appendChild(sesso);
		        
		        Element comune_nascita = doc.createElement("comune_nascita");
		        comune_nascita.setTextContent(lePersone.get(i).getComuneDiNascita()+"");
		        persona.appendChild(comune_nascita);
		        
		        Element data_nascita = doc.createElement("data_nascita");
		        data_nascita.setTextContent(lePersone.get(i).getDataDiNascita()+"");
		        persona.appendChild(data_nascita);
		        
		        Element codice_fiscale = doc.createElement("codice_fiscale");
		        
		        Persona p=new Persona(lePersone.get(i).getNome(), lePersone.get(i).getCognome(), lePersone.get(i).getSesso(), lePersone.get(i).getComuneDiNascita(), lePersone.get(i).getDataDiNascita());
		        String s=p.generaCodiceFiscale(iComuni);
		        //System.out.println(s.toString());
		        String fis="ASSENTE";
		        boolean fine=false;
		        
		        for(int j=0; j<iCodici.size() && fine!=true; j++) {//questo for abbina i codici fiscali corretti ai dati delle persone
		        	if(s.equals(iCodici.get(j).toString())) {
		        		fis=s;
		        		iCodici.remove(j);
		        		fine=true;
		        	}
		        }
		        codice_fiscale.setTextContent(fis);
		        
		        persona.appendChild(codice_fiscale);
	        }
	        
	        Element codici = doc.createElement("codici");
	        rootElement.appendChild(codici);
	        
	        Element invalidi = doc.createElement("invalidi");

	        invalidi.setAttribute("numero", iNonCodici.size()+"");
	        codici.appendChild(invalidi);
	        
	        for(int i=0; i<iNonCodici.size(); i++) {//questo for separa i codici fiscali non corretti dall'Arraylist contentente tutti i codici fiscali
	        	
		        
		        Element codice = doc.createElement("codice");
		        codice.setTextContent(iNonCodici.get(i).toString());
		        invalidi.appendChild(codice);
		        
	        }
	        
	        Element spaiati = doc.createElement("spaiati");
	        spaiati.setAttribute("numero", iCodici.size()+"");
	        codici.appendChild(spaiati);
	        
	        for(int i=0; i<iCodici.size(); i++) { //questo for separa i codici fiscali spaiati dall'Arraylist contentente tutti i codici fiscali
	        	
		        
		        Element codice = doc.createElement("codice");
		        codice.setTextContent(iCodici.get(i).toString());
		        spaiati.appendChild(codice);
		        
	        }
	        
	        
	        //...create XML elements, and others...
	        // write dom document to a file
	        try (FileOutputStream output =
	                     new FileOutputStream("src/codiciPersone.xml")) {
	            writeXml(doc, output);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Scrittura del file output.xml in corso...");
	        System.out.println("Scrittura del file output.xml completata");
	}
	/**
	 * prende tutti i dati presenti nel file comuni.xml e li trasferisce dentro l'arrayList comuni
	 */
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
	/**
	 * prende tutti i dati presenti nel file inputPersone.xml e li trasferisce dentro l'arrayList lePersone
	 */
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
	/**
	 * prende tutti i dati presenti nel file codiciFiscali.xml e li trasferisce dentro l'arrayList iCodici
	 */
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
                    iCodici.add(new CodiceFiscale(codice));
                }
            }
            System.out.println("Fine lettura codiciFiscali.xml.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
	}
	/**
	 * è il metodo che richiama tutti i controlli inerenti a un codice fiscale
	 */
	public void controlloCodice() {
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
				if(codice.takeAControl(iComuni)!=true) {//dipendentemente dal risultato del controllo un codice fiscale che non ha rispettato il controlo, viene spostato nell'ArrayList iNonCodici in quanto incorretto
					System.out.println("SCRITTO IN ELENCO CODICI ERRATI");//iNonCodici
					iNonCodici.add(new CodiceFiscale(string));
					iCodici.remove(i);
				}
			}else {
				System.out.println(string+" non è corretto!");
				iNonCodici.add(new CodiceFiscale(string));
				iCodici.remove(i);
			}
		}
	}
}
