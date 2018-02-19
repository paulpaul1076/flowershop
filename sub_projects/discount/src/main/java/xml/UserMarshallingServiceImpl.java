package xml;


import entity.User;

public class UserMarshallingServiceImpl implements UserMarshallingService {
    private XMLConverter xmlConverter;

    public UserMarshallingServiceImpl(XMLConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    public String marshal(User user, String fileName) {
        return xmlConverter.convertFromObjectToXML(user, fileName);
    }
}
