package services;

import database.DB;
import entity.Product;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServices
{
    private static final String GET_ALL_USERS = "SELECT id, username, \"fullName\", password, \"phoneNumber\", email, \"createdTime\"\n" +
            "\tFROM public.users";
//    private static final String GET_PRODUCT_LATEST_ONE="SELECT id, titles, description, \"sourcelinkTo\", \"createdTime\", photofile, category_id\n" +
//            "\tFROM public.product where \"createdTime\"=(select max(\"createdTime\") from product)";
//    private static final String GET_CATEGORY_NAME = "SELECT name\tFROM public.product inner join category on category.id=product.category_id\n" +
//            "where product.category_id=?";
//    private static final String SAVE_PRODUCT = "";
//    private static final String UPDATE_PRO="";
//    private static final String DELETE_PRO="";

    public static List<Users> getAllUsers() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Users> usersList = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DB.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                Date createdTime = rs.getDate("createdTime");

                usersList.add(new Users(id,username, fullName, password,
                        phoneNumber, email,createdTime));
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return usersList;

}
}
