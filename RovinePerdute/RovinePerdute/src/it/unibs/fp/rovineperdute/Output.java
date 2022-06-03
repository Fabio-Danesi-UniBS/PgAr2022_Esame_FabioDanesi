package it.unibs.fp.rovineperdute;

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

import java.io.*;
import java.util.List;

public class Output {
    
	public static void stampaXML(List<Citta> percorsoTonathiu, List<Citta> percorsoMetztli, int selettore) throws ParserConfigurationException, TransformerException{
		
		String[] outputFiles= {"Routes_5.xml", "Routes_12.xml", "Routes_50.xml", "Routes_200.xml", "Routes_2000.xml", "Routes_10000.xml"};
    	
        String consumo, numeroCitta, id, nome;

        System.out.println("Scrittura del percorso "+outputFiles[selettore]+" in corso...\n");
		
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	        Document doc = docBuilder.newDocument();
	        
	        Element rootElement = doc.createElement(outputFiles[selettore]);
	        doc.appendChild(rootElement);
	        
	        Element percorsi = doc.createElement("routes");
	        rootElement.appendChild(percorsi);
	        
	        	Element percorsoT = doc.createElement("route");
		        percorsi.appendChild(percorsoT);
		        
		        percorsoT.setAttribute("team", "Tonathiu");
		        consumo = String.valueOf(percorsoTonathiu.get(percorsoTonathiu.size() - 1).getDistanza());
		        percorsoT.setAttribute("cost", consumo);
		        numeroCitta = String.valueOf(percorsoTonathiu.size());
		        percorsoT.setAttribute("cities", numeroCitta);//ok
		        
		        for (int j=0; j < percorsoTonathiu.size(); j++){
		        	Element city = doc.createElement("city");
		        	percorsoT.appendChild(city);
			        id = String.valueOf(percorsoTonathiu.get(j).getId());
			        city.setAttribute("id", id);
			        nome = percorsoTonathiu.get(j).getNome();
			        city.setAttribute("name", nome);
			        
		        }

	        	Element percorsoM = doc.createElement("route");
		        percorsi.appendChild(percorsoM);
		        
		        percorsoM.setAttribute("team", "Metztli");
		        consumo = String.valueOf(percorsoMetztli.get(percorsoMetztli.size() - 1).getDistanza());
		        percorsoM.setAttribute("cost", consumo);
		        numeroCitta = String.valueOf(percorsoMetztli.size());
		        percorsoM.setAttribute("cities", numeroCitta);//ok
		        
		        for (int j=0; j < percorsoMetztli.size(); j++){
		        	Element city = doc.createElement("city");
		        	percorsoM.appendChild(city);
			        id = String.valueOf(percorsoMetztli.get(j).getId());
			        city.setAttribute("id", id);
			        nome = percorsoMetztli.get(j).getNome();
			        city.setAttribute("name", nome);
		        }

	        try (FileOutputStream output=new FileOutputStream(outputFiles[selettore])) {
	            writeXml(doc, output);
	        } catch (IOException e) {
	        	System.out.println("Errore nella scrittura!\n");
	            e.printStackTrace();
	        }

	        System.out.println("Scrittura del percorso "+outputFiles[selettore]+" completata!\n");
	}
	
	private static void writeXml(Document doc, OutputStream output)throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		
		transformer.transform(source, result);
		
	}
}
