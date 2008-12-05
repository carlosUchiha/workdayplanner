/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.settings;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.util.logging.Level;
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
    private static final String fileName = "src/META-INF/persistence.xml";
    private static Logger log = Logger.getLogger(PersistenceXmlDocument.class.getName());

    public PersistenceXmlDocument() throws ParserConfigurationException, SAXException, IOException {
        read();
    }

    void read() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);

        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource source = new InputSource(fileName);
        doc = builder.parse(source);
        Element root = doc.getDocumentElement(); //persistence
        Element persistenceUnit = (Element)root.getElementsByTagName("persistence-unit").item(0);
        Element properties = (Element)persistenceUnit.getElementsByTagName("properties").item(0);
        NodeList propList = properties.getElementsByTagName("property"); // lista wlasciwosci
        for(int i=0; i<propList.getLength(); i++) {
            Element property = (Element)propList.item(i);
            if(property.getAttribute("name").matches("toplink.jdbc.url")) {
                dbUrl = property.getAttribute("value").toString();
                log.warning("Znalazłem właściwość toplink.jdbc.url: "+dbUrl);
            }
        }
    }

    void save() {
        try {
            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputCharStream(new java.io.FileWriter(fileName));
            serializer.serialize(doc);
        } catch (IOException ex1) {
            log.log(Level.SEVERE, null, ex1);
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
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
        NodeList propList = properties.getElementsByTagName("property"); // lista wlasciwosci
        for(int i=0; i<propList.getLength(); i++) {
            Element property = (Element)propList.item(i);
            if(property.getAttribute("name").matches("toplink.jdbc.url")) {
                property.setAttribute("value", dbUrl);
                log.warning("Wpisałem właściwość toplink.jdbc.url: "+dbUrl);
            }
        }
        this.dbUrl = dbUrl;
    }

}
