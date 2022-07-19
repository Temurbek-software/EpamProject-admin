package services;

import database.DB;
import entity.Product;
import entity.Publisher;
import entity.Users;
import org.mindrot.jbcrypt.BCrypt;
import payload.PublisherDto;
import payload.UserDto;

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
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static services.PublisherValidation.*;

public class PublisherService {
    private static final String GET_PUBLISHER_BY_ID = "SELECT id, \"nameOfCompany\", " +
            "address, description, phonenumber,\n" +
            "email, password, created_at, updated_at, username, \"isActive\", \"isBlocked\"\n" +
            "\tFROM public.publisher where id=?";
    private static final String GET_ALL_PUBLISHER = "SELECT id," +
            " \"nameOfCompany\", address, description, phonenumber," +
            " email, password, created_at, updated_at, username, " +
            "\"isActive\", \"isBlocked\"\n" +
            "\tFROM public.publisher;";
    private static final String SAVE_PUBLISHER = "INSERT INTO public.publisher(\n" +
            "\t\"nameOfCompany\", address, description, phonenumber," +
            " email, password, username, \"isActive\", \"isBlocked\")\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, "+false+", "+false+");";
    private static final String VALIDATE_PUBLISHER = "SELECT id, \"nameOfCompany\", address, description, phonenumber, email, password, created_at, updated_at, username\n" +
            "\tFROM public.publisher where username='?' and password='?'";
 private static final String DELETE_PUBLISHER = "DELETE FROM public.publisher\n" +
         "\tWHERE id=?;";

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
    public void saveNoisActive(Publisher publisher) {
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.publisher\n" +
                            "\tSET \"isActive\"='"+false+"'\n" +
                            "\tWHERE id="+publisher.getId()+";"
            );
            int resultSet = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
    }

    public Publisher getPulisher(ResultSet resultSet) throws SQLException, ParseException {
        DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.a");
        Publisher publisher = null;
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String nameOfCompany = resultSet.getString("nameOfCompany");
        String address = resultSet.getString("address");
        String phoneNumber = resultSet.getString("phoneNumber");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String description = resultSet.getString("description");
        boolean isActive=resultSet.getBoolean("isActive");
        boolean isBlocked=resultSet.getBoolean("isBlocked");
        Date created_at = dateFormat.parse((dateFormat.format((resultSet.getTimestamp("created_at")))));
        Date updated_at = dateFormat.parse((dateFormat.format((resultSet.getDate("updated_at")))));
        publisher = new Publisher(id, username, nameOfCompany, address, phoneNumber,
                email, password, description,isActive,isBlocked,
                created_at, updated_at);
        return publisher;
    }

    public Publisher getPublisherById(long id) {
        Publisher publishersPublisher = new Publisher();
        try (PreparedStatement preparedStatement = getstatement(GET_PUBLISHER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publishersPublisher.setId(resultSet.getLong("id"));
                publishersPublisher.setNameOfCompany(resultSet.getString("username"));
                publishersPublisher.setNameOfCompany(resultSet.getString("nameOfCompany"));
                publishersPublisher.setAddress(resultSet.getString("address"));
                publishersPublisher.setPhoneNumber(resultSet.getString("phoneNumber"));
                publishersPublisher.setDescription(resultSet.getString("description"));
                publishersPublisher.setEmail(resultSet.getString("email"));
                publishersPublisher.setPassword(resultSet.getString("password"));
                publishersPublisher.setBlocked(resultSet.getBoolean("isBlocked"));
            }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return publishersPublisher;
    }

    public List<Publisher> getAlPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        try {
            ResultSet rs = getresultSet(GET_ALL_PUBLISHER);
            while (rs.next()) {
                publishers.add(getPulisher(rs));
            }
        } catch (SQLException | ParseException exception) {
            DB.printSQLException((SQLException) exception);
        }
        return publishers;
    }

    public String isPublisherExist(Publisher publisher) {
        PublisherValidation.ValidResult validResult = isEmailValid()
                .and(isNameValid())
                .and(isUsernameValid())
                .and(isPhoneNumberValid())
                .apply(publisher);
        String resulting = validResult.name();
        return resulting;
    }

    public int updatePublisher(Publisher publisher)
    {
        int resul = 0;
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.publisher\n" +
                            "\tSET \"nameOfCompany\"='"+publisher.getNameOfCompany()+"'," +
                            " address='"+publisher.getAddress()+"',\n" +
                            "\tdescription='"+publisher.getDescription()+"'," +
                            " phonenumber='"+publisher.getPhoneNumber()+"',\n" +
                            "\temail='"+publisher.getEmail()+"'," +
                            " password='"+publisher.getPassword()+"', " +
                            "username='"+publisher.getUsername()+"'\n" +
                            "\tWHERE id="+publisher.getId()+";"
            );
            resul = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return resul;
    }
    public int  setFalse(long id)
    {
        int res = 0;
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.publisher\n" +
                            "\tSET \"isBlocked\"='"+false+"'\n" +
                            "\tWHERE id="+id+";"
            );
            res  = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return res;
    }
    public int setTrue(long idNum)
    {
        int resutr=0;
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.publisher\n" +
                            "\tSET \"isBlocked\"='"+true+"'\n" +
                            "\tWHERE id="+idNum+";"
            );
            resutr = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return resutr;
    }
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public int savePublisher(Publisher publisher) {
        int result = 0;
        System.out.println(SAVE_PUBLISHER);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PUBLISHER)) {
            preparedStatement.setString(1, publisher.getNameOfCompany());
            preparedStatement.setString(2, publisher.getAddress());
            preparedStatement.setString(3, publisher.getPhoneNumber());
            preparedStatement.setString(4, publisher.getEmail());
            preparedStatement.setString(5,hashPassword(publisher.getPassword()));
            preparedStatement.setString(6, publisher.getDescription());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return result;
    }

    public boolean validate(PublisherDto publisherDto)
    {
        boolean status2 = false;
        try (PreparedStatement preparedStatement = getstatement("SELECT id, \"nameOfCompany\", address, description, phonenumber, email, password, created_at, updated_at, username\n" +
                "\tFROM public.publisher" +
                " where username='"+publisherDto.getUsername()+"' and" +
                " password='"+publisherDto.getPassword()+"'"))
        {
            ResultSet rs = preparedStatement.executeQuery();
            status2 = rs.next();
        } catch (SQLException e) {
            DB.printSQLException(e);
        }
        return status2;
    }
    public void saveisActive(Publisher publisher) {
        boolean status = true;
        long idUser = publisher.getId();
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.publisher\n" +
                            "\tSET \"isActive\"="+status+"\n" +
                            "\tWHERE id="+idUser+";"
            );
            int resultSet = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
    }
    public boolean deletePublisher(long id) {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(DELETE_PUBLISHER);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }
    public Publisher getPublisher(PublisherDto publisherDto)
    {
        Publisher publisher = new Publisher();
        try (PreparedStatement preparedStatement = getstatement("SELECT id, " +
                "\"nameOfCompany\", address, description, phonenumber, email, password, created_at, updated_at, username\n" +
                "\tFROM public.publisher where username='"+publisherDto.getUsername()+"' and password='"+publisherDto.getPassword()+"'"))
        {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                publisher.setId(rs.getLong("id"));
                publisher.setUsername(rs.getString("username"));
                publisher.setNameOfCompany(rs.getString("nameOfCompany"));
                publisher.setAddress(rs.getString("address"));
                publisher.setPhoneNumber(rs.getString("phoneNumber"));
                publisher.setEmail(rs.getString("email"));
                publisher.setPassword(rs.getString("password"));
                publisher.setDescription(rs.getString("description"));
                publisher.setActive(true);
            }
        } catch (SQLException e) {
            // process sql exception
            DB.printSQLException(e);
        }
        return publisher;
    }
}
