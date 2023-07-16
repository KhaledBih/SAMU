package org.example.ressources;

import org.example.model.MedicalRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("medicalrecords")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/samu";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    public Response getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "SELECT * FROM MedicalRecord";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String id = rs.getString("id");
                String patientName = rs.getString("patientName");
                Date dateOfBirth = rs.getDate("dateOFBirth");
                String bloodType = rs.getString("bloodType");
                String iduser = rs.getString("iduser");
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setId(id);
                medicalRecord.setPatientName(patientName);
                medicalRecord.setDateOfBirth(dateOfBirth);
                medicalRecord.setBloodType(bloodType);
                medicalRecord.setIduser(iduser);

                medicalRecords.add(medicalRecord);
            }

            rs.close();
            stmt.close();
            conn.close();

            return Response.ok(medicalRecords).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "SELECT * FROM MedicalRecord WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String patientName = rs.getString("patient_name");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String bloodType = rs.getString("blood_type");

                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setId(id);
                medicalRecord.setPatientName(patientName);
                medicalRecord.setDateOfBirth(dateOfBirth);
                medicalRecord.setBloodType(bloodType);
                rs.close();
                stmt.close();
                conn.close();

                return Response.ok(medicalRecord).build();
            } else {
                rs.close();
                stmt.close();
                conn.close();

                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    public Response createMedicalRecord(MedicalRecord medicalRecord) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "INSERT INTO MedicalRecord (id, patient_name, date_of_birth, blood_type) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, medicalRecord.getId());
            stmt.setString(2, medicalRecord.getPatientName());
            stmt.setDate(3, new java.sql.Date(medicalRecord.getDateOfBirth().getTime()));
            stmt.setString(4, medicalRecord.getBloodType());
            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") String id, MedicalRecord updatedMedicalRecord) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "UPDATE MedicalRecord SET patient_name = ?, date_of_birth = ?, blood_type = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, updatedMedicalRecord.getPatientName());
            stmt.setDate(2, new java.sql.Date(updatedMedicalRecord.getDateOfBirth().getTime()));
            stmt.setString(3, updatedMedicalRecord.getBloodType());
            stmt.setString(4, id);
            int rowsAffected = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if (rowsAffected > 0) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "DELETE FROM MedicalRecord WHERE id = ?";
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
