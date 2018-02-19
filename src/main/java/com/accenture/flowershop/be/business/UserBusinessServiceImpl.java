package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.UserAccessService;
import com.accenture.flowershop.be.entity.Discount;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.xml.UserMarshallingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserBusinessServiceImpl implements UserBusinessService {

    private UserAccessService dao;
    private UserMarshallingService userMarshallingService;
    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    @Qualifier("esbToMcpRequestQueue")
    private Queue outQueue;

    @Autowired
    @Qualifier("esbToMcpRequestQueue1")
    private Queue inQueue;

    @Autowired
    private Jaxb2Marshaller marshaller;

    private Session session;
    private Connection connection;
    private MessageConsumer messageConsumer;

    @PostConstruct
    public void init()  {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(inQueue);
            connection.start();
            messageConsumer.setMessageListener(new MessageListener() {
                 @Override
                public void onMessage(Message message) {
                    try {
                        String xml = message.getStringProperty("discountxml");
                        try(ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
                            ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                            Discount discount = (Discount)marshaller.unmarshal(new StreamSource(in));
                            String login = discount.getLogin();
                            Integer discountAmount = discount.getDiscountAmount();
                            User user = dao.getUserByLogin(login);
                            user.setDiscount(discountAmount);
                            updateUser(user);
                        } catch (IOException e) {
                            System.out.println("IOException in onMessage: " + e.getMessage());
                        }
                    } catch (JMSException e) {
                        System.out.println("JMSException in onMessage: " + e.getMessage());
                    }
                }
            });
        } catch (JMSException e) {

        }

    }

    public UserBusinessServiceImpl(UserAccessService dao, UserMarshallingService userMarshallingService) {
        this.dao = dao;
        this.userMarshallingService = userMarshallingService;


    }

    @Override
    public User login(String login, String password) {
        User user = dao.getUser(login, password);
        if(user != null) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        boolean success = dao.putUser(user);
        String fileName = "C:\\flowershop_users\\" + user.getLogin() + ".xml";
        String userXmlRep = userMarshallingService.marshal(user, fileName);

        //jms stuff goes
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Message message = session.createTextMessage();
            message.setStringProperty("userxml", userXmlRep);

            MessageProducer producer = session.createProducer(outQueue);
            producer.send(message);

            connection.close();
        } catch (JMSException e) {
            LOG.debug("jms exception: {}", e.getMessage());
        }

        return success;
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return dao.getUserByLogin(login);
    }


}
