package org.example.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;
@Provider
@Produces(MediaType.APPLICATION_JSON)
@XmlRootElement



@Data
@NoArgsConstructor

public class EmergySOS {


	public EmergySOS(String id, String createBy, String phoneNumber, String namePatient, String address) {
		this.id = id;
		this.createBy = createBy;
		this.phoneNumber = phoneNumber;
		this.namePatient = namePatient;
		this.address = address;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNamePatient() {
		return namePatient;
	}

	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String id;

    private String createBy;

    private String phoneNumber;



    @SerializedName("namePatient")
    private String namePatient;

    
    private String address;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static EmergySOS fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, EmergySOS.class);
    }
}
