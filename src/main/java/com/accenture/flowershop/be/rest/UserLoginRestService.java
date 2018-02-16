package com.accenture.flowershop.be.rest;

import com.accenture.flowershop.be.access.UserAccessService;
import com.accenture.flowershop.be.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/userLogin")
public class UserLoginRestService {
    @Autowired
    private UserAccessService userAccessService;

    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("Web service constructed");
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/doesLoginExist/{login}")
    public String doesLoginExist(@PathParam("login") String login) {
        System.out.println("Login isssss : " + login);
        return String.format("%b", userAccessService.doesLoginExist(login));
    }


}
