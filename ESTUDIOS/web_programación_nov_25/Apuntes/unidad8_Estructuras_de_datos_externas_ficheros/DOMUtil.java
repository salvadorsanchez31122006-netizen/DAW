package utilidadesdom ;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * Utilidades para pasar árboles DOM a documentos XML y viceversa.
 * @author Salvador Romero Villegas
 */
public class DOMUtil {
    
    /**
     * Al ser una clase de utilidades que solo contiene métodos estáticos no
     * se deberían poder instanciar objetos de la misma ya que no tiene sentido
     * y no es una buena práctica de programación. Por ello se añade 
     * el constructor por defecto privado.
     */
    private DOMUtil() {       
    }

    /**
     * Carga un archivo con un documento XML a un árbol DOM.      
     * @param CaminoAlArchivoXml puede ser un archivo local de tu disco duro
     * o una URI de Internet (http://...).
     * @return el documento DOM o null si no se ha podido cargar el documento.
     */    
    public static Document xmlToDom (String CaminoAlArchivoXml) {
        Document doc=null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc=db.parse(CaminoAlArchivoXml);            
           
        } catch (Exception ex) {
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return doc;
    }

    /**
     * Convierte una cadena que contiene un documento XML a un árbol DOM.
     * @param documentoXML cadena que contiene el documento XML.
     * @return El árbol DOM o null si no se ha podido convertir.
     */
    public static Document stringToDom (String documentoXML)  {
        ByteArrayInputStream bais=new ByteArrayInputStream(documentoXML.getBytes());
        Document doc=null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc=db.parse(bais);            
           
        } catch (Exception ex) {
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return doc;
    }
    
    /**
     * Convierte un árbol DOM a una cadena que contiene un documento XML.
     * @param doc árbol DOM.
     * @return null si no se ha podido convertir o la cadena con el documento
     * en XML si se ha podido convertir.
     */
    public static String domToXml (Document doc)  {
        String xmlString=null;
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            tFactory.setAttribute("indent-number", new Integer(2));
            Transformer transformer = tFactory.newTransformer();
       
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            
            StreamResult result = new StreamResult(new StringWriter());
             
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            xmlString = result.getWriter().toString();
        } catch (TransformerException ex) {
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, ex);
            xmlString=null;
        }
        return xmlString;
    }
    
    /**
     * Convierte un árbol DOM a XML y lo guarda en un archivo.
     * @param doc Documento a convertir en XML.
     * @param CaminoAlArchivoXML Camino o path para llegar al archivo en el
     * disco.
     * @return true si se ha podido convertir y false en cualquier otra
     *   situación.
     */
    public static boolean domToXml (Document doc, String CaminoAlArchivoXML) {
        try {
            File f=new File(CaminoAlArchivoXML);
            TransformerFactory tFactory=TransformerFactory.newInstance();
            tFactory.setAttribute("indent-number", new Integer(4));
            Transformer transformer = tFactory.newTransformer();
           
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           
            FileOutputStream fos =new FileOutputStream(f);
            StreamResult result = new StreamResult(new OutputStreamWriter(fos,"UTF-8"));  
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);         
            return true;
        } catch (TransformerException ex) {
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, ex);            
        } catch (UnsupportedEncodingException uee){
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, uee);
        } catch (FileNotFoundException fnfe){
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, fnfe);
        }
        return false;
    }
    
    /**
     * Crea un árbol DOM vacío.
     * @param etiquetaRaiz Nombre de la etiqueta raíz del árbol DOM, donce
     * estarÃ¡ contenida el resto del documento.
     * @return Retornará el documento creado o null si se ha producido algún 
     * error.
     */    
    public static Document crearDomVacio(String etiquetaRaiz) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document d = null ;
        try {
            db = dbf.newDocumentBuilder() ;
            d = db.newDocument() ;
            d.appendChild(d.createElement(etiquetaRaiz));
            return d;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d ;
    }
    
}
