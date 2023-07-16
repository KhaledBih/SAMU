package org.example.Services;

import org.example.ressources.AddressResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class Adressservic extends Application {
  private Set<Object> sin=new HashSet<>();
  public Adressservic(){
	  this.sin.add(new AddressResource());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}


