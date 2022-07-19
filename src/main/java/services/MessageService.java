package services;

import database.DB;
import entity.Complain;
import entity.Contacts;
import entity.Publisher;
import org.apache.tomcat.util.codec.binary.Base64;
import payload.ComplainDTO;

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

public class MessageService {
    private static final String GET_ALL_CONTACT_MSG = "SELECT id, username, email, textresponse, created_at, updated_at\n" +
            "\tFROM public.contacts;";
    private static final String DELETE_MSG_CONTACT = "\tDELETE FROM public.contacts\n" +
            "\t\tWHERE id=?;";
    private static final String GET_ALL_COMPLAIN_MSG = "SELECT complain.id," +
            " complain.message,users.username, " +
            "\"nameOfCompany\", complain.created_at\n" +
            "\tFROM public.publisher inner join complain\n" +
            "\ton complain.publisher_id=publisher.id inner " +
            "join users on users.id=complain.user_id";

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

    public List<Contacts> getAllMsg() {
        List<Contacts> contactsList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.a");
        try {
            ResultSet rs = getresultSet(GET_ALL_CONTACT_MSG);
            while (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String textResponse = rs.getString("textResponse");
                Date created_at = dateFormat.parse((dateFormat.format(rs.getTimestamp("created_at"))));
                Date updated_at = dateFormat.parse((dateFormat.format(rs.getTimestamp("updated_at"))));
                contactsList.add(new Contacts(id, username, email,
                        textResponse, created_at, updated_at));
            }
        } catch (SQLException | ParseException exception) {
            DB.printSQLException((SQLException) exception);
        }
        return contactsList;
    }

    public List<ComplainDTO> getAllComplainMsg() {
        List<ComplainDTO> complains = new ArrayList<>();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.a");
        try {
            ResultSet rs = getresultSet(GET_ALL_COMPLAIN_MSG);
            while (rs.next()) {
                long id = rs.getLong("id");
                String message = rs.getString("message");
                String username = rs.getString("username");
                String nameOfCompany = rs.getString("nameOfCompany");
                Date created_at = dateFormat2.parse((dateFormat2.
                        format(rs.getTimestamp("created_at"))));
                complains.add(new ComplainDTO(id, message, username,
                        nameOfCompany, created_at));
            }
        } catch (SQLException | ParseException exception) {
            DB.printSQLException((SQLException) exception);
        }
        return complains;
    }
    public boolean deleteContactMsg(long id) {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(DELETE_MSG_CONTACT);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }
}
