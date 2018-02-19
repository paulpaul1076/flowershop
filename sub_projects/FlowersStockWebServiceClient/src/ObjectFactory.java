import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.accenture.flowershop.be.business package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IncreaseFlowersStockSizeResponse_QNAME = new QName("http://business.be.flowershop.accenture.com/", "increaseFlowersStockSizeResponse");
    private final static QName _IncreaseFlowersStockSize_QNAME = new QName("http://business.be.flowershop.accenture.com/", "increaseFlowersStockSize");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.accenture.flowershop.be.business
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IncreaseFlowersStockSize }
     * 
     */
    public IncreaseFlowersStockSize createIncreaseFlowersStockSize() {
        return new IncreaseFlowersStockSize();
    }

    /**
     * Create an instance of {@link IncreaseFlowersStockSizeResponse }
     * 
     */
    public IncreaseFlowersStockSizeResponse createIncreaseFlowersStockSizeResponse() {
        return new IncreaseFlowersStockSizeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncreaseFlowersStockSizeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://business.be.flowershop.accenture.com/", name = "increaseFlowersStockSizeResponse")
    public JAXBElement<IncreaseFlowersStockSizeResponse> createIncreaseFlowersStockSizeResponse(IncreaseFlowersStockSizeResponse value) {
        return new JAXBElement<IncreaseFlowersStockSizeResponse>(_IncreaseFlowersStockSizeResponse_QNAME, IncreaseFlowersStockSizeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncreaseFlowersStockSize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://business.be.flowershop.accenture.com/", name = "increaseFlowersStockSize")
    public JAXBElement<IncreaseFlowersStockSize> createIncreaseFlowersStockSize(IncreaseFlowersStockSize value) {
        return new JAXBElement<IncreaseFlowersStockSize>(_IncreaseFlowersStockSize_QNAME, IncreaseFlowersStockSize.class, null, value);
    }

}
