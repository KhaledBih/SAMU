package org.example.Services;

import org.example.ressources.AttendancRs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class AtendanceSer extends Application {
  private Set<Object> sin=new HashSet<>();
  public void AttendancRs(){
	  this.sin.add(new AttendancRs());
  }
  
  @Override
 public   Set<Object> getSingletons(){
	  return sin;
  }
  
  
}


