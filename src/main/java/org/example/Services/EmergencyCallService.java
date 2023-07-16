package org.example.Services;

import org.example.ressources.ControllerEmergencyCall;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class EmergencyCallService extends Application {
    private Set<Object> sin=new HashSet<>();
    public EmergencyCallService(){
        this.sin.add(new ControllerEmergencyCall());
    }

    @Override
    public   Set<Object> getSingletons(){
        return sin;
    }
}