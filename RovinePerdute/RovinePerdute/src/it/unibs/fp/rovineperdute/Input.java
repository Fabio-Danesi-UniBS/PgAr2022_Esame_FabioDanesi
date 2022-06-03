package it.unibs.fp.rovineperdute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;

public class Input {

    /**
     * crazione Persona
     *
     * @throws XMLStreamException
     * @ArrayList
     */
	public static HashMap<Integer, Citta> leggiInputCitta(String fileName) throws ParserConfigurationException, SAXException{
		HashMap<Integer, Citta> elenco_citta = new HashMap<>();
		
		Citta citta = null;
        Citta cittaCollegata = null;
        Archi arco = null;
		
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            document.getDocumentElement().normalize();
            
            NodeList nList = document.getElementsByTagName("city");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                
                citta = new Citta();
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    int id = Integer.parseInt(eElement.getAttribute("id"));
                    citta.setId(id);
                    String nome=eElement.getAttribute("name");
                    citta.setNome(nome);
                    int x = Integer.parseInt(eElement.getAttribute("x"));
                    citta.setX(x);
                    int y = Integer.parseInt(eElement.getAttribute("y"));
                    citta.setY(y);
                    int h = Integer.parseInt(eElement.getAttribute("h"));
                    citta.setAltitudine(h);
                    
                    for(int i=0; i<eElement.getElementsByTagName("link").getLength(); i++){
                    	
                    	cittaCollegata = new Citta();
                        arco = new Archi();
                        
                        NodeList linkNodeList = eElement.getElementsByTagName("link");
                        
                        int to = Integer.parseInt(linkNodeList.item(i).getAttributes().getNamedItem("to").getTextContent());
                        
                        cittaCollegata.setId(to);

                        arco.setCittaPartenza(citta);

                        arco.setCittaArrivo(cittaCollegata);
                        citta.getCittaCollegate().add(arco);
                        
                    }
                    
                }
                elenco_citta.put(citta.getId(), citta);
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
		return elenco_citta;
	}

}