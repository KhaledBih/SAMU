package org.example.Services;

import org.example.ressources.EmergySOSResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class EmergySOSServic extends Application {
  private Set<Object> sin=new HashSet<>();
  public EmergySOSServic(){
	  this.sin.add(new EmergySOSResource());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}



