package org.example.ressources;

import org.example.model.Address;
import org.example.model.EmergySOS;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("emergysos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmergySOSResource {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/samu";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmergySOS() throws ClassNotFoundException {
        List<EmergySOS> emergySOSList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM EmergySOS");

            while (rs.next()) {
                String id = rs.getString("id");
                String createBy = rs.getString("createBy");
                String phoneNumber = rs.getString("phoneNumber");
                String namePatient = rs.getString("namePatient");


               String address = rs.getString("address");


                EmergySOS emergySOS = new EmergySOS();
                emergySOS.setId(id); // Set the id field with the retrieved id value
                emergySOS.setCreateBy(createBy);
                emergySOS.setPhoneNumber(phoneNumber);
                emergySOS.setNamePatient(namePatient);
                emergySOS.setAddress(address);

                emergySOSList.add(emergySOS);
            }


            return Response.ok(emergySOSList).build();
        } catch (SQLException e) {
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


    @GET
    @Path("/{id}")
    public Response getEmergySOSById(@PathParam("id") String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "SELECT * FROM EmergySOS WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String createBy = rs.getString("createBy");
                String phoneNumber = rs.getString("phoneNumber");
                String namePatient = rs.getString("namePatient");

                // Retrieve the address information from the database
                String addressId = rs.getString("addressId");


                EmergySOS emergySOS = new EmergySOS(id, createBy, phoneNumber, namePatient, addressId);
                rs.close();
                stmt.close();
                conn.close();

                return Response.ok(emergySOS).build();
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
    public Response createEmergySOS(EmergySOS emergySOS) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Insert the address information into the database and retrieve the generated addressId


            String query = "INSERT INTO EmergySOS (id, createBy, phoneNumber, namePatient, addressId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, emergySOS.getId());
            stmt.setString(2, emergySOS.getCreateBy());
            stmt.setString(3, emergySOS.getPhoneNumber());
            stmt.setString(4, emergySOS.getNamePatient());
            stmt.setString(5, emergySOS.getAddress());
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
    public Response updateEmergySOS(@PathParam("id") String id, EmergySOS updatedEmergySOS) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Update the address information in the database


            String query = "UPDATE EmergySOS SET createBy = ?, phoneNumber = ?, namePatient = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, updatedEmergySOS.getCreateBy());
            stmt.setString(2, updatedEmergySOS.getPhoneNumber());
            stmt.setString(3, updatedEmergySOS.getNamePatient());
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
    public Response deleteEmergySOS(@PathParam("id") String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "DELETE FROM EmergySOS WHERE id = ?";
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

    private Address getAddressById(String addressId) {
        // Retrieve the address information from the database using the addressId
        // Implement the logic to fetch the address data based on the addressId from the database
        // Return the Address object
        return null;
    }

    private String insertAddress(Address address) {
        // Insert the address information into the database
        // Implement the logic to insert the address data into the database
        // Return the generated addressId
        return null;
    }

    private void updateAddress(Address address) {
        // Update the address information in the database
        // Implement the logic to update the address data in the database
    }
}
