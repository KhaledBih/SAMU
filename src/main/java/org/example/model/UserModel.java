package org.example.model;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@Provider
@Produces(MediaType.APPLICATION_JSON)
@XmlRootElement

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
 
    private String id;

    private String email;

    private String password;

    public String getPassword() {
		return password;
	}

	public UserModel(String id, String email) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.token = token;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	private String fullName;

    private String phoneNumber;

    private UserRole role;

    private String token;

  
    private Date createAt;

   
    private Date updateAt;



	public static UserModel fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, UserModel.class);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public enum UserRole {
        user("user"),
        doctor("doctor"),
        operator("operator"),
        nurse("nurse"),
        admin("admin");

        private final String name;

        UserRole(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static UserRole parseRole(String role) {
            switch (role) {
                case "user":
                    return user;
                case "doctor":
                    return doctor;
                case "operator":
                    return operator;
                case "nurse":
                    return nurse;
                case "admin":
                    return admin;
                default:
                    throw new IllegalArgumentException("Invalid role value");
            }
        }
    }


}
