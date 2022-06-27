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

public class ProductServices {
    private static final String GET_ALL_PRODUCTS = "SELECT id, titles, textdata, description, \"sourcelinkTo\", photofile, created_at, updated_at\n" +
            "\tFROM public.product";
    private static final String GET_PRODUCT_BY_ID = "";
    private static final String GET_PRODUCT_LATEST_ONE = "SELECT id, titles, description, \"sourcelinkTo\", \"createdTime\", photofile, category_id\n" +
            "\tFROM public.product where \"createdTime\"=(select max(\"createdTime\") from product)";
    private static final String GET_CATEGORY_NAME = "SELECT name\tFROM public.product inner join category on category.id=product.category_id\n" +
            "where product.category_id=?";
    private static final String SAVE_PRODUCT = "";
    private static final String UPDATE_PRO = "";
    private static final String DELETE_PRO = "";

    public Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = null;
        Long id = resultSet.getLong("id");
        String titles = resultSet.getString("titles");
        String textData = resultSet.getString("textData");
        String description = resultSet.getString("description");
        String sourcelinkTo = resultSet.getString("sourcelinkTo");
        byte[] photofile = resultSet.getBytes("photofile");
        Date created_at = resultSet.getDate("created_at");
        Date updated_at = resultSet.getDate("updated_at");
        product = new Product(id, titles, textData, description, sourcelinkTo, photofile, created_at, updated_at);
        return product;
    }

    public ResultSet getresultSet(String query) throws SQLException {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        return rs;
    }

    public PreparedStatement getstatement(String query) throws SQLException {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }


    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        System.out.println(GET_ALL_PRODUCTS);
        try {
            ResultSet resultSet = getresultSet(GET_ALL_PRODUCTS);
            while (resultSet.next()) {
                productList.add(getProduct(resultSet));
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return productList;
    }

    public void saveProduct(Product product) {
        try {
            PreparedStatement preparedStatement = getstatement(SAVE_PRODUCT);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getTitles());
            preparedStatement.setString(3, product.getTextData());
            preparedStatement.setString(4, product.getSourcelinkTo());
            preparedStatement.setBytes(5, product.getPhotofile());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
    }
    public boolean deleteProduct(long productId) throws SQLException {
        boolean status = false;
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRO);) {
            preparedStatement.setLong(1, productId);
            status = preparedStatement.executeUpdate() > 0;
        }
        return status;
    }

}
