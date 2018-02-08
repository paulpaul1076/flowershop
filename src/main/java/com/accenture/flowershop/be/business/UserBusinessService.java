package com.accenture.flowershop.be.business;

import com.accenture.flowershop.fe.dto.UserDTO;

public interface UserBusinessService {
    UserDTO login(String user, String password);
    UserDTO register(String user, String password, String address);
}
