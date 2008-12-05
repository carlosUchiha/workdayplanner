/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.settings;

import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author robson
 */
public class PersistenceXmlDocument {

    private Document doc = null;
    private String dbUrl = null;
    private static Logger log = Logger.getLogger(PersistenceXmlDocument.class.getName());

    public PersistenceXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        read();
    }

    void read() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);

        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource source = new InputSource("persistence.xml");
        doc = builder.parse(source);
        Element root = doc.getDocumentElement(); //persistence
        Element persistenceUnit = (Element)root.getElementsByTagName("persistence-unit").item(0);
        Element properties = (Element)persistenceUnit.getElementsByTagName("properties").item(0);
        NodeList propList = properties.getChildNodes();
        for(int i=0; i<propList.getLength(); i++) {
            Element property = (Element)propList.item(i);
            if(property.getAttribute("name").matches("toplink.jdbc.url")) {
                dbUrl = property.getAttribute("value").toString();
                log.fine("Znalazłem właściwość toplink.jdbc.url: "+dbUrl);
            }
        }
    }

    void save() {
        // czy w ogóle coś trzeba tu robić??
    }

    /**
     * 
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * Writes new URL to XML doc, but not saves it.
     * @param dbUrl the dbUrl to set
     */
    public void setDbUrl(String dbUrl) {
        Element root = doc.getDocumentElement(); //persistence
        Element persistenceUnit = (Element)root.getElementsByTagName("persistence-unit").item(0);
        Element properties = (Element)persistenceUnit.getElementsByTagName("properties").item(0);
        NodeList propList = properties.getChildNodes();
        for(int i=0; i<propList.getLength(); i++) {
            Element property = (Element)propList.item(i);
            if(property.getAttribute("name").matches("toplink.jdbc.url")) {
                property.setAttribute("value", dbUrl);
                log.fine("Wpisałem właściwość toplink.jdbc.url: "+dbUrl);
            }
        }
        this.dbUrl = dbUrl;
    }

}
