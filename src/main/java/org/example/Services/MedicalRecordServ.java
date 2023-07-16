package org.example.Services;

import org.example.ressources.MedicalRecordResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class MedicalRecordServ extends Application {
  private Set<Object> sin=new HashSet<>();
  public MedicalRecordServ(){
	  this.sin.add(new MedicalRecordResource());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}



