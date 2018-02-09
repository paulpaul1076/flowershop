package com.accenture.flowershop.be.xml;

import com.accenture.flowershop.be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;

import java.io.FileOutputStream;
import java.io.IOException;

public class UserMarshallingServiceImpl implements UserMarshallingService {
    private Logger LOG = LoggerFactory.getLogger(UserMarshallingServiceImpl.class);

    public void marshall(User user, String directoryPath) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");
        LOG.debug("app context found = {}", new java.io.File("application-context.xml").exists());
        XMLConverter converter = (XMLConverter)appContext.getBean("XMLConverter");
        try {
            converter.convertFromObjectToXML(user, "C:\\" + user.getLogin() + ".xml");
        }catch (Exception e){}
    }
}
