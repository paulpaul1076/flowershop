package com.accenture.flowershop.be.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


public class XMLConverter {
    private Jaxb2Marshaller marshaller;

    public XMLConverter(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public <T> void convertFromObjectToXML(T object, String filepath){
        try (FileOutputStream os = new FileOutputStream(filepath)){
            marshaller.marshal(object, new StreamResult(os));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
