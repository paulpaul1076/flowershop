package xml;


import entity.User;

public interface UserMarshallingService {
    String marshal(User user, String directoryPath);
}
