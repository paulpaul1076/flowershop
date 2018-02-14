package com.accenture.flowershop.be.xml;

import com.accenture.flowershop.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;

import javax.xml.bind.JAXBException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserMarshallingServiceImpl implements UserMarshallingService {
    private XMLConverter xmlConverter;
    private Logger LOG = LoggerFactory.getLogger(UserMarshallingServiceImpl.class);

    public UserMarshallingServiceImpl(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    public void marshall(User user, String directoryPath) {
        try {
            System.out.println("In marshall(User user, String directoryPath) begin");
            System.out.println("xmlConverter = " + xmlConverter);
            xmlConverter.convertFromObjectToXML(user, "C:\\" + user.getLogin() + ".xml");
            System.out.println("In marshall(User user, String directoryPath) end");
        }catch (IOException e) {
            System.out.println("caught IOException");
            System.out.println("exception : " + e.getMessage());
        } catch (JAXBException e) {
            System.out.println("caught JAXBException");
            System.out.println("exception : " + e.getMessage());
        }
    }
}
