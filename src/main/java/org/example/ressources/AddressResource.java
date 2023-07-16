package org.example.ressources;

import com.google.gson.Gson;
import org.example.model.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("addresses")
public
class AddressResource {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/samu";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddresses() {
        List<Address> addresses = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM address")) {

            while (rs.next()) {

                String street = rs.getString("address");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                double accuracy = rs.getDouble("accuracy");
                addresses.add(new Address(street, latitude, longitude, accuracy));
            }

            return Response.ok(addresses).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAddress(Address address) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO address (address, latitude, longitude, accuracy) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, address.getAddress());
            stmt.setDouble(2, address.getLatitude());
            stmt.setDouble(3, address.getLongitude());
            stmt.setDouble(4, address.getAccuracy());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAddress(@PathParam("id") int id, Address updatedAddress) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE address SET address = ?, latitude = ?, longitude = ?, accuracy = ? WHERE id = ?")) {

            stmt.setString(1, updatedAddress.getAddress());
            stmt.setDouble(2, updatedAddress.getLatitude());
            stmt.setDouble(3, updatedAddress.getLongitude());
            stmt.setDouble(4, updatedAddress.getAccuracy());
            stmt.setInt(5, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
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
    public Response deleteAddress(@PathParam("id") int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM address WHERE id = ?")) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
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
