package org.example.ressources;

import com.google.gson.Gson;


import org.example.connexion.DatabaseConnector;
import org.example.model.Address;
import org.example.model.EmergencyCall;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("emergency-calls")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerEmergencyCall {

    @GET
    public Response getAllEmergencyCalls() {
        List<EmergencyCall> emergencyCalls = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM emergencyCalls";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmergencyCall emergencyCall = mapResultSetToEmergencyCall(resultSet);
                emergencyCalls.add(emergencyCall);
            }

            if (emergencyCalls.isEmpty())
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(emergencyCalls).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getEmergencyCallByIdStream(@PathParam("id") String id) {

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM emergencyCalls WHERE createBy = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            Stream<EmergencyCall> emergencyCallStream = Stream.generate(() -> {
                try {
                    if (resultSet.next()) {
                        return mapResultSetToEmergencyCall(resultSet);
                    } else {
                        resultSet.close();
                        statement.close();
                        connection.close();
                        return null;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Error while streaming ResultSet: " + e.getMessage(), e);
                }
            }).takeWhile(emergencyCall -> emergencyCall != null);

            List<EmergencyCall> emergencyCalls = emergencyCallStream.collect(Collectors.toList());

            if (emergencyCalls.isEmpty())
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(emergencyCalls).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    private EmergencyCall mapResultSetToEmergencyCall(ResultSet resultSet) throws SQLException {
        EmergencyCall emergencyCall = new EmergencyCall();
        emergencyCall.setId(resultSet.getString("id"));
        emergencyCall.setPatientName(resultSet.getString("patientName"));
        emergencyCall.setDescription(resultSet.getString("description"));
        emergencyCall.setStatus(EmergencyCall.EmergencyStatus.valueOf(resultSet.getString("status")));
        emergencyCall.setCreateAt(resultSet.getString("createAt"));
        emergencyCall.setByCall(resultSet.getBoolean("isByCall"));
        emergencyCall.setAddBy(EmergencyCall.UserRole.valueOf(resultSet.getString("addBy")));
        emergencyCall.setCreateBy(resultSet.getString("createBy"));
        emergencyCall.setPhoneNumber(resultSet.getString("phoneNumber"));

        emergencyCall.setAddress(Address.fromJson(resultSet.getString("address")));

        emergencyCall.setSex(resultSet.getString("sex"));
        emergencyCall.setPatientCount(resultSet.getInt("patientCount"));
        emergencyCall.setIdCategory(resultSet.getString("idCategory"));
        emergencyCall.setSubCategoryId(resultSet.getString("subCategoryId"));
        emergencyCall.setTokenNotification(resultSet.getString("tokenNotification"));

        return emergencyCall;
    }


    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createEmergencyCall(EmergencyCall emergencyCall) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO emergencyCalls (id, patientName, description, status, createAt, isByCall, addBy, createBy, phoneNumber, address, sex, patientCount, idCategory, subCategoryId, tokenNotification) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emergencyCall.getId());
            statement.setString(2, emergencyCall.getPatientName());
            statement.setString(3, emergencyCall.getDescription());
            statement.setString(4, emergencyCall.getStatus().toString());
            statement.setString(5, emergencyCall.getCreateAt());
            statement.setBoolean(6, emergencyCall.isByCall());
            statement.setString(7, emergencyCall.getAddBy().toString());
            statement.setString(8, emergencyCall.getCreateBy());
            statement.setString(9, emergencyCall.getPhoneNumber());
            statement.setString(10, emergencyCall.getAddress().toJson());
            statement.setString(11, emergencyCall.getSex());
            statement.setInt(12, emergencyCall.getPatientCount());
            statement.setString(13, emergencyCall.getIdCategory());
            statement.setString(14, emergencyCall.getSubCategoryId());
            statement.setString(15, emergencyCall.getTokenNotification());

            statement.executeUpdate();

            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateEmergencyCall(@PathParam("id") String id, EmergencyCall updatedEmergencyCall) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE emergencyCalls SET patientName = ?, description = ?, status = ?, createAt = ?, isByCall = ?, addBy = ?, createBy = ?, phoneNumber = ?, address = ?, sex = ?, patientCount = ?, idCategory = ?, subCategoryId = ?, tokenNotification = ? " +
                    "WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedEmergencyCall.getPatientName());
            statement.setString(2, updatedEmergencyCall.getDescription());
            statement.setString(3, updatedEmergencyCall.getStatus().toString());
            statement.setString(5, updatedEmergencyCall.getCreateAt());
            statement.setBoolean(5, updatedEmergencyCall.isByCall());
            statement.setString(6, updatedEmergencyCall.getAddBy().toString());
            statement.setString(7, updatedEmergencyCall.getCreateBy());
            statement.setString(8, updatedEmergencyCall.getPhoneNumber());
            statement.setString(9, updatedEmergencyCall.getAddress().toJson());
            statement.setString(10, updatedEmergencyCall.getSex());
            statement.setInt(11, updatedEmergencyCall.getPatientCount());
            statement.setString(12, updatedEmergencyCall.getIdCategory());
            statement.setString(13, updatedEmergencyCall.getSubCategoryId());
            statement.setString(14, updatedEmergencyCall.getTokenNotification());
            statement.setString(15, id);

            statement.executeUpdate();

            return Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmergencyCall(@PathParam("id") String id) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM emergencyCalls WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);

            statement.executeUpdate();

            return Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
