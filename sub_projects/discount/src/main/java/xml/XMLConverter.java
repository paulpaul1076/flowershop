package xml;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class XMLConverter {
    private Jaxb2Marshaller marshaller;

    public XMLConverter(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public <T> String convertFromObjectToXML(T object, String filepath){
        try (FileOutputStream os = new FileOutputStream(filepath);
             ByteArrayOutputStream os2 = new ByteArrayOutputStream()){
            marshaller.marshal(object, new StreamResult(os));
            marshaller.marshal(object, new StreamResult(os2));
            return os2.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public <T> T convertFromXMLToObject(String xmlfile) {
        try(FileInputStream is = new FileInputStream(xmlfile)) {
            return  (T) marshaller.unmarshal(new StreamSource(is));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
