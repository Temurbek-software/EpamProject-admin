package services;

import database.DB;
import entity.Category;
import entity.Product;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class CategoryServices {
    private static final String GET_ONE_PRODUCT = "SELECT id, titles,  description, sourcelinkto, photofile, created_at,\n" +
            "updated_at FROM public.product where category_id=?";
    private static final String GET_CATEGORY_LIST = "SELECT id, name\n" +
            "\tFROM public.category;";
    private static final String GET_ID_BY_NAME = "SELECT category.name\n" +
            "\tFROM public.product inner join category on product.category_id=category.id where product.id=?";

    public static String getCategoryByName(long id)
    {
         String categoryName="";
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_NAME);) {
            System.out.println(preparedStatement);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                 categoryName = rs.getString("name");
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return categoryName;

    }
    public static List<Product> getProductByCategory(int num) {
        List<Product> categoryList = new ArrayList<>();
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_PRODUCT);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String titles = rs.getString("titles");
                String description = rs.getString("description");
                String sourcelinkTo = rs.getString("sourcelinkTo");
                byte[] photofile = rs.getBytes("sourcelinkTo");
                Date created_at = rs.getDate("created_at");
                Date updated_at = rs.getDate("updated_at");
                categoryList.add(new Product(id, titles, description, sourcelinkTo, photofile,
                        created_at, updated_at));
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return categoryList;
    }
    public static List<Category> categoryList() {
        List<Category> categoryList = new ArrayList<>();
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_LIST);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                categoryList.add(new Category(id, name));
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return categoryList;

    }
    public static int getCategoryId(String que)
    { 
        int num = 0;
        try {
            Connection connection = DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,  name\n" +
                    "\tFROM public.category where name='"+que+"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id=resultSet.getInt("id");
            num=id;
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return num;
    }
}
