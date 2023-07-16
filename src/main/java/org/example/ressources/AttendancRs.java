package org.example.ressources;

import org.example.model.Attendance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("AT")
public class AttendancRs {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/samu";
    private static final String USER = "root";
    private static final String PASS = "";

    // Méthode GET pour récupérer toutes les présences
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAttendances() {
        List<Attendance> attendances = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Attendance");

            while (rs.next()) {
                String id = rs.getString("id");
                Timestamp clockIn = rs.getTimestamp("clockIn");
                Timestamp clockOut = rs.getTimestamp("clockOut");
                String duration = rs.getString("duration");
                String idUser = rs.getString("idUser");
                boolean status = rs.getBoolean("status");
                String nameUser = rs.getString("nameUser");
                String phoneUser = rs.getString("phoneUser");
                String position = rs.getString("position");

                Attendance attendance = new Attendance();
                attendance.setId(id);
                attendance.setClockIn(clockIn);
                attendance.setClockOut(clockOut);
                attendance.setDuration(duration);
                attendance.setIdUser(idUser);
                attendance.setStatus(status);
                attendance.setNameUser(nameUser);
                attendance.setPhoneUser(phoneUser);
                attendance.setPosition(position);

                attendances.add(attendance);
            }

            return Response.ok(attendances).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Méthode POST pour créer une nouvelle présence
    // Code pour enregistrer une nouvelle présence dans la base de données
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAttendance(Attendance attendance) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "INSERT INTO Attendance (id, clockIn, clockOut, duration, idUser, status, nameUser, phoneUser, position) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);

            stmt.setString(1, attendance.getId());
            stmt.setTimestamp(2, new java.sql.Timestamp(attendance.getClockIn().getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(attendance.getClockOut().getTime()));


            stmt.setString(4, attendance.getDuration());
            stmt.setString(5, attendance.getIdUser());
            stmt.setBoolean(6, attendance.getStatus());
            stmt.setString(7, attendance.getNameUser());
            stmt.setString(8, attendance.getPhoneUser());
            stmt.setString(9, attendance.getPosition());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Méthode PUT pour mettre à jour une présence existante
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAttendance(@PathParam("id") String id, Attendance updatedAttendance) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "UPDATE Attendance SET clockIn=?, clockOut=?, duration=?, idUser=?, status=?, nameUser=?, phoneUser=?, position=? WHERE id=?";
            stmt = conn.prepareStatement(query);

            stmt.setTimestamp(1, (Timestamp) updatedAttendance.getClockIn());
            stmt.setTimestamp(2, (Timestamp) updatedAttendance.getClockOut());
            stmt.setString(3, updatedAttendance.getDuration());
            stmt.setString(4, updatedAttendance.getIdUser());
            stmt.setBoolean(5, updatedAttendance.getStatus());
            stmt.setString(6, updatedAttendance.getNameUser());
            stmt.setString(7, updatedAttendance.getPhoneUser());
            stmt.setString(8, updatedAttendance.getPosition());
            stmt.setString(9, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Méthode DELETE pour supprimer une présence par ID
    @DELETE
    @Path("/{id}")
    public Response deleteAttendance(@PathParam("id") String id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "DELETE FROM Attendance WHERE id=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
