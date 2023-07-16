package org.example.Services;

import org.example.ressources.EmergencyCategoryResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class EmergencyCategoryServic extends Application {
  private Set<Object> sin=new HashSet<>();
  public EmergencyCategoryServic(){
	  this.sin.add(new EmergencyCategoryResource());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}


