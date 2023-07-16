package org.example.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Provider
@Produces(MediaType.APPLICATION_JSON)
@XmlRootElement

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyCategory {
   
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	private String id;

    private String name;

    private String description;

    private List<SubCategory> subCategories;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static EmergencyCategory fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, EmergencyCategory.class);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubCategory {

		private String id;

        private String name;

        private String description;
    }
}
