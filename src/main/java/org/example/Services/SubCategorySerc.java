package org.example.Services;


import org.example.ressources.SubCategoryResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class SubCategorySerc extends Application {
  private Set<Object> sin=new HashSet<>();
  public SubCategorySerc(){
	  this.sin.add(new SubCategoryResource());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}


