package com.accenture.flowershop.be.xml;

import com.accenture.flowershop.be.entity.User;

public interface UserMarshallingService {
    void marshall(User user, String directoryPath);
}
