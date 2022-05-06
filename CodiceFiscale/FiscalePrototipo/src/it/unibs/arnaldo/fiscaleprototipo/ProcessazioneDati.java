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

	public static ArrayList<Comune> iComuni=new ArrayList<Comune>();
	
	public static ArrayList<CodiceFiscale> iCodici=new ArrayList<CodiceFiscale>();
	public static ArrayList<CodiceFiscale> iNonCodici=new ArrayList<CodiceFiscale>();
	
	public static ArrayList<Persona> lePersone=new ArrayList<Persona>();
	
	private File comuni;
	private File inputPersone;
	private File codiciFiscali;
	
	public ProcessazioneDati() {
		super();
		this.comuni= new File("src/comuni.xml");
		this.inputPersone= new File("src/inputPersone.xml");
		this.codiciFiscali= new File("src/codiciFiscali.xml");
	}
	
	public void esegui() throws ParserConfigurationException, SAXException, TransformerException {
		letturaComuni();
		System.out.println(iComuni.toString());
		letturaCodici();
		controlloCodice();//sempre prima di letturaCodici(); e letturaComuni();
		System.out.println("\nCodici:\nCorretti:\n"+iCodici.toString());
		System.out.println("Non Codici:\n"+iNonCodici.toString());
		letturaDatiPersone();
		System.out.println("Codici:\nCorretti:\n"+lePersone.toString());
		
		System.out.println("\nSIZE COMUNI: "+iComuni.size());
		System.out.println("SIZE CODICI: "+iCodici.size());
		System.out.println("SIZE NON CODICI: "+iNonCodici.size());
		System.out.println("SIZE PERSONE: "+lePersone.size()+"\n");
		scrivi();
		System.out.println("\nNUOVA SIZE CODICI: "+iCodici.size());
		System.out.println("NUOVA SIZE NON CODICI: "+iNonCodici.size());
		System.out.println("Esecuzione terminata!");

	}
	
	private void writeXml(Document doc, OutputStream output)throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		// pretty print XML
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		
		transformer.transform(source, result);
		
	}
	
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
	        
	        for(int i=0; i<lePersone.size(); i++) {
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
		        
		        for(int j=0; j<iCodici.size() && fine!=true; j++) {
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
	        
	        for(int i=0; i<iNonCodici.size(); i++) {
	        	
		        
		        Element codice = doc.createElement("codice");
		        codice.setTextContent(iNonCodici.get(i).toString());
		        invalidi.appendChild(codice);
		        
	        }
	        
	        Element spaiati = doc.createElement("spaiati");
	        spaiati.setAttribute("numero", iCodici.size()+"");
	        codici.appendChild(spaiati);
	        
	        for(int i=0; i<iCodici.size(); i++) {
	        	
		        
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
                    iCodici.add(new CodiceFiscale(codice));
                }
            }
            System.out.println("Fine lettura codiciFiscali.xml.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
	}
	
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
				if(codice.takeAControl(iComuni)!=true) {
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
