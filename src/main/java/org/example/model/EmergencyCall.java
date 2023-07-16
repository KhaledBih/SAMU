package org.example.model;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Address;
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
public class EmergencyCall {
	@SerializedName("id")
	private String id;
	@SerializedName("patientName")
	private String patientName;
	@SerializedName("description")
	private String description;
	@SerializedName("status")
	private EmergencyStatus status;
	@SerializedName("createAt")
	private String createAt;
	@SerializedName("isByCall")
	private boolean isByCall;

	@SerializedName("addBy")
	private UserRole addBy;
	@SerializedName("createBy")
	private String createBy;
	@SerializedName("phoneNumber")
	private String phoneNumber;
	@SerializedName("address")
	public Address address;
	@SerializedName("sex")
	private String sex;
	@SerializedName("patientCount")
	private Integer patientCount;
	@SerializedName("idCategory")
	private String idCategory;
	@SerializedName("subCategoryId")
	private String subCategoryId;
	@SerializedName("tokenNotification")
	private String tokenNotification;

	public static EmergencyCall fromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, EmergencyCall.class);
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public enum EmergencyStatus {
		pending,
		inProgress,
		completed,
		cancelled,
		rejected,
	}

	public enum UserRole {
		user,
		doctor,
		operator,
		nurse,
		driver,
		admin,
	}
}

