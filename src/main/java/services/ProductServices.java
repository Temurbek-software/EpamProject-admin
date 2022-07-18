package services;

import database.DB;
import entity.Product;
import entity.Users;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServices {
    private static final String GET_ALL_PRODUCTS = "SELECT id, titles, textdata, description, sourcelinkto, photofile, created_at, updated_at, category_id, publisher_id, \"counterOfView\", \"isDeleted\"\n" +
            "\tFROM public.product;";
    private static final String GET_PRODUCT_BY_ID = "SELECT " +
            "titles, textdata, description, sourcelinkto, photofile, category_id\n" +
            "\tFROM public.product where id=?";
    private static final String GET_PRODUCT_LATEST_ONE = "SELECT id, titles, description, \"sourcelinkTo\", \"createdTime\", photofile, category_id\n" +
            "\tFROM public.product where \"createdTime\"=(select max(\"createdTime\") from product)";
    private static final String GET_CATEGORY_NAME = "SELECT name\tFROM public.product inner join category on category.id=product.category_id\n" +
            "where product.category_id=?";
    private static final String SAVE_PRODUCT = "INSERT INTO public.product(\n" +
            "\ttitles, textdata, description, sourcelinkto," +
            " photofile, category_id, publisher_id, \n" +
            "\t\"isDeleted\")\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_PRODUCT = "UPDATE public.product\n" +
            "\tSET  titles=?, textdata=?, description=?, sourcelinkto=?, photofile=?, category_id=?\n" +
            "\tWHERE id=?;";
    private static final String DELETE_PRO = "DELETE FROM public.product\n" +
            "\tWHERE id=?;";
    private static final String RECOVER_PRODUCT = "UPDATE public.product\n" +
            "\tSET \"isDeleted\"=false\n" +
            "\tWHERE id=?;";
    private static final String DELETING_FOR_NOW_PRODUCT = "UPDATE public.product\n" +
            "\tSET \"isDeleted\"=true\n" +
            "\tWHERE id=?;";

    public Product getProduct(ResultSet resultSet) throws SQLException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.a");

        Product product;
        Long id = resultSet.getLong("id");
        String titles = resultSet.getString("titles");
        String textData = resultSet.getString("textData");
        String description = resultSet.getString("description");
        String sourcelinkTo = resultSet.getString("sourcelinkto");
        byte[] photofile = resultSet.getBytes("photofile");
        Date created_at = dateFormat.parse((dateFormat.format(resultSet.getDate("created_at"))));
        Date updated_at = dateFormat.parse((dateFormat.format(resultSet.getDate("updated_at"))));
        boolean isDeleted = resultSet.getBoolean("isDeleted");
        product = new Product(id, titles, textData, description, sourcelinkTo, photofile, created_at, updated_at, isDeleted);
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

    public boolean deletePost(long id) {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(DELETE_PRO);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }
    public boolean recoverPost(long id)
    {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(RECOVER_PRODUCT);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;

    }
    public boolean deleteTemporaryPost(long id) {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(DELETING_FOR_NOW_PRODUCT);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;

    }
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        System.out.println(GET_ALL_PRODUCTS);
        try {
            ResultSet resultSet = getresultSet(GET_ALL_PRODUCTS);
            while (resultSet.next()) {
                productList.add(getProduct(resultSet));
            }
        } catch (SQLException | ParseException exception) {
            DB.printSQLException((SQLException) exception);
        }
        return productList;
    }

    public int updatePost(Product product) {
        int resul = 0;
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.product\n" +
                            "\tSET  titles=" + product.getTitles() + ", " +
                            "textdata=" + product.getTextData() + ", " +
                            "description=" + product.getDescription() + "," +
                            " sourcelinkto=" + product.getSourcelinkTo() + ", " +
                            "photofile=" + product.getPhotofile() + "," +
                            " category_id=" + product.getCategory_id() + "\n" +
                            "\tWHERE id=" + product.getId() + ""
            );
            resul = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return resul;
    }

    public int saveProduct(Product product, long id) {
        int num = 0;
        System.out.println("SAve product");
        try {
            PreparedStatement preparedStatement = getstatement(SAVE_PRODUCT);
            preparedStatement.setString(1, product.getTitles());
            preparedStatement.setString(2, product.getTextData());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getSourcelinkTo());
            preparedStatement.setBytes(5, product.getPhotofile());
            preparedStatement.setInt(6, product.getCategory_id());
            preparedStatement.setLong(7, id);
            preparedStatement.setBoolean(8, false);
            num = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return num;
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

    public Product getProductByID(long id) throws ParseException {
        Product product = new Product();
        try (PreparedStatement preparedStatement = getstatement(GET_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setTitles(resultSet.getString("titles"));
                product.setTextData(resultSet.getString("textData"));
                product.setDescription(resultSet.getString("description"));
                product.setSourcelinkTo(resultSet.getString("sourcelinkto"));
                product.setPhotofile(resultSet.getBytes("photofile"));
                product.setCategory_id(resultSet.getInt("category_id"));
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return product;
    }
}
