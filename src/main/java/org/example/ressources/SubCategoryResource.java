
package org.example.ressources;

import org.example.model.SubCategory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("subcategories")
public class SubCategoryResource {
	  private static final String DB_URL = "jdbc:mysql://localhost:3306/samu";
      private static final String USER = "root";
      private static final String PASS = "";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddresses() throws ClassNotFoundException {
        List<SubCategory> subCategories = new ArrayList<>();
      
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM subcategory");

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                subCategories.add(new SubCategory(id, name, description));
            }

            return Response.ok(subCategories).build();
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
    
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createSubCategory(SubCategory subCategory) {
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                String query = "INSERT INTO subcategory (id, name, description) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, subCategory.getId());
                stmt.setString(2, subCategory.getName());
                stmt.setString(3, subCategory.getDescription());
                stmt.executeUpdate();

                return Response.status(Response.Status.CREATED).build();
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

        // Ajoutez les autres méthodes pour les requêtes GET, PUT et DELETE ici
    
@PUT
@Path("/{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response updateSubCategory(@PathParam("id") String id, SubCategory updatedSubCategory) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "UPDATE subcategory SET name = ?, description = ? WHERE id = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, updatedSubCategory.getName());
        stmt.setString(2, updatedSubCategory.getDescription());
        stmt.setString(3, id);
        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            return Response.ok().build();
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
    }}
@DELETE
@Path("/{id}")
public Response deleteSubCategory(@PathParam("id") String id) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "DELETE FROM subcategory WHERE id = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        int rowsDeleted = stmt.executeUpdate();

        if (rowsDeleted > 0) {
            return Response.ok().build();
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




    // Ajoutez les autres méthodes pour les requêtes GET par ID, POST, PUT et DELETE ici

