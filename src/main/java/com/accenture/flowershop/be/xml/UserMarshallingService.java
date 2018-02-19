package com.accenture.flowershop.be.xml;

import com.accenture.flowershop.be.entity.User;

public interface UserMarshallingService {
    String marshal(User user, String directoryPath);
}
