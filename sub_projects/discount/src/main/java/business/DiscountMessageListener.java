package business;

import entity.Discount;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class DiscountMessageListener {

    @Autowired
    @Qualifier("esbToMcpRequestQueue")
    private Queue outQueue;
    @Autowired
    @Qualifier("esbToMcpRequestQueue1")
    private Queue inQueue;
    @Autowired
    private ConnectionFactory connectionFactory;

    private Session session;
    private Connection connection;
    private MessageConsumer messageConsumer;
    private MessageProducer messageProducer;

    @Autowired
    private Jaxb2Marshaller marshaller;

    public DiscountMessageListener(){

    }

    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(outQueue);
            messageProducer = session.createProducer(inQueue);
            connection.start();
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String xml = message.getStringProperty("userxml");
                        try(ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
                            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                            //from xml to object
                            User user = (User)marshaller.unmarshal(new StreamSource(in));
                            Discount discount = new Discount();
                            discount.setLogin(user.getLogin());
                            discount.setDiscountAmount(10);

                            System.out.println("Users login is: " + user.getLogin());

                            //from object to xml
                            marshaller.marshal(discount, new StreamResult(out));
                            String stringResult = out.toString();

                            Message response = session.createTextMessage("discountxml");
                            response.setStringProperty("discountxml", stringResult);
                            messageProducer.send(response);
                            System.out.println("Response sent");
                        } catch (IOException e) {
                            System.out.println("IOException in onMessage: " + e.getMessage());
                        }
                    } catch (JMSException e) {
                        System.out.println("JMS outer exception: " + e.getMessage());
                    }
                }
            });
        } catch (JMSException e) {
            System.out.println("JMS outer exception: " + e.getMessage());
        }
    }


}
