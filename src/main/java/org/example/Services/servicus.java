package org.example.Services;

import org.example.ressources.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class servicus extends Application {
  private Set<Object> sin=new HashSet<>();
  public servicus(){
	  this.sin.add(new UserResource());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}
