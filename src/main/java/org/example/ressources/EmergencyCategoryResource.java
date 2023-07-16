package org.example.ressources;



import lombok.Data;
import org.example.model.EmergencyCategory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Data

@Path("Emcatagorie")
public class EmergencyCategoryResource {

    private static List<EmergencyCategory> emergencyCategories = new ArrayList<>();

    static {
        List<EmergencyCategory.SubCategory> subCategories1 = new ArrayList<>();
        subCategories1.add(new EmergencyCategory.SubCategory("1", "Subcategory 1", "Description 1"));
        subCategories1.add(new EmergencyCategory.SubCategory("2", "Subcategory 2", "Description 2"));
        emergencyCategories.add(new EmergencyCategory("1", "Emergency Category 1", "Description 1", subCategories1));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmergencyCategories() {
        return Response.ok(emergencyCategories).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmergencyCategoryById(@PathParam("id") String id) {
        for (EmergencyCategory emergencyCategory : emergencyCategories) {
            if (emergencyCategory.getId().equals(id)) {
                return Response.ok(emergencyCategory).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmergencyCategory(EmergencyCategory emergencyCategory) {
        emergencyCategories.add(emergencyCategory);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmergencyCategory(@PathParam("id") String id, EmergencyCategory updatedEmergencyCategory) {
        for (EmergencyCategory emergencyCategory : emergencyCategories) {
            if (emergencyCategory.getId().equals(id)) {
                emergencyCategory.setName(updatedEmergencyCategory.getName());
                emergencyCategory.setDescription(updatedEmergencyCategory.getDescription());
                emergencyCategory.setSubCategories(updatedEmergencyCategory.getSubCategories());
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmergencyCategory(@PathParam("id") String id) {
        EmergencyCategory emergencyCategoryToRemove = null;
        for (EmergencyCategory emergencyCategory : emergencyCategories) {
            if (emergencyCategory.getId().equals(id)) {
                emergencyCategoryToRemove = emergencyCategory;
                break;
            }
        }
        if (emergencyCategoryToRemove != null) {
            emergencyCategories.remove(emergencyCategoryToRemove);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
