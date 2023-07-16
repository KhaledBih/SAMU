package org.example.ressources;

import org.example.model.MedicalRecord;
import org.example.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/samu";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    public List<UserModel> getUsersWithMedicalRecords() {
        List<UserModel> users = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "SELECT t2.id, t2.email, t1.id AS medicalRecordId, t1.patientName FROM medicalrecord AS t1 JOIN usermodel AS t2 ON t1.iduser = t2.id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String userId = rs.getString("id");
                String email = rs.getString("email");

                String medicalRecordId = rs.getString("medicalRecordId");
                String patientName = rs.getString("patientName");

                UserModel user = new UserModel(userId, email);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setId(medicalRecordId);
                medicalRecord.setPatientName(patientName);

               // user.setMedicalRecord(medicalRecord);
                users.add(user);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    @POST
    public Response createUser(UserModel user) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "INSERT INTO usermodel (id, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, UserModel updatedUser) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "UPDATE usermodel SET email = ?, password = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, updatedUser.getEmail());
            stmt.setString(2, updatedUser.getPassword());
            stmt.setString(3, id);
            int rowsAffected = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if (rowsAffected > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "DELETE FROM usermodel WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if (rowsAffected > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
