package com.accenture.flowershop.be.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.castor.CastorMarshaller;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLConverter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void convertFromObjectToXML(Object object, String filepath) throws IOException, JAXBException {
        FileOutputStream os = new FileOutputStream(filepath);
        marshaller.marshal(object, new StreamResult(os));
    }

    public Object convertFromXMLToObject(String xmlfile) throws IOException, JAXBException {
        FileInputStream is = new FileInputStream(xmlfile);
        return unmarshaller.unmarshal(new StreamSource(is));
    }
}
