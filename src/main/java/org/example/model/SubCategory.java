package org.example.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SubCategory {

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

	private String id;

    private String name;

    private String description;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static SubCategory fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, SubCategory.class);
    }
}
