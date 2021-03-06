package services;

import database.DB;
import entity.Product;
import entity.Users;
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

public class UserServices {
    private static final String GET_ONE_USER = "SELECT id, " +
            "username, \"fullName\", password, \"phoneNumber\", " +
            "email, created_at, updated_at, \"isActive\", " +
            "\"isDeleted\", \"isBlocked\"\n" +
            "\tFROM public.users where id=?;";
    private static final String DELETE_USER_BY_ID = "DELETE FROM public.users\n" +
            "\tWHERE id=?;";
    private static final String UPDATE_USER_BY_ID = "UPDATE public.users\n" +
            "\tSET  username='?', \"fullName\"='?', password='?', \"phoneNumber\"='?', email='?'\n" +
            "\tWHERE id=?;";
    private static final String SAVE_USERS = "INSERT INTO public.users(\n" +
            "\tusername, \"fullName\", password, \"phoneNumber\", email)\n" +
            "\tVALUES (?, ?, ?, ?,?);";
    private static final String GET_ALL_USERS = "SELECT id, username, \"fullName\"," +
            " password, \"phoneNumber\", email," +
            " created_at, updated_at, \"isActive\", \"isDeleted\", \"isBlocked\"\n" +
            "\tFROM public.users;";
    private static final String GET_AUTHENTICATE = "SELECT id, username," +
            " \"fullName\", password, \"phoneNumber\", email, \"createdTime\"\n" +
            "\tFROM public.users where username='?' and password='?'";
    private static final String GET_COUNT_FOR_USERS="SELECT count(*) as numbers\n" +
            "\tFROM public.users;";
    private static final String DELETE_USER_FOR_TIME = "UPDATE public.users\n" +
            "\tSET \"isDeleted\"='"+true+"'\n" +
            "\tWHERE id=?;";
    private static final String RECOVER_USER_FOR_TIME = "UPDATE public.users\n" +
            "\tSET \"isDeleted\"='" + false + "'\n" +
            "\tWHERE id=?;";

    public boolean validate(UserDto userDto) throws ClassNotFoundException {
        boolean status = false;
        try (Connection connection = DB.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement(GET_AUTHENTICATE)) {
            preparedStatement.setString(1, userDto.getUsername());
            preparedStatement.setString(2, userDto.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            DB.printSQLException(e);
        }
        return status;
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

    public Users getUser(ResultSet resultSet) throws SQLException, ParseException {
        DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.a");
        Users users = null;
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String fullName = resultSet.getString("fullName");
        String password = resultSet.getString("password");
        String phoneNumber = resultSet.getString("phoneNumber");
        String email = resultSet.getString("email");
        boolean isDeleted=resultSet.getBoolean("isDeleted");
        boolean isBlocked=resultSet.getBoolean("isBlocked");
        boolean isActive=resultSet.getBoolean("isActive");
        Date created_at = dateFormat.parse((dateFormat.format(resultSet.getTimestamp("created_at"))));
        Date updated_at = dateFormat.parse((dateFormat.format(resultSet.getTimestamp("updated_at"))));
        users = new Users(id,username,fullName,password,phoneNumber,email,
                isActive,isDeleted,isBlocked,created_at,updated_at);
        return users;
    }

    public int saveUser(Users users) {
        int result = 0;
        System.out.println(SAVE_USERS);
        try (Connection connection = DB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USERS)) {
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getFullName());
            preparedStatement.setString(3, users.getPassword());
            preparedStatement.setString(4, users.getPhoneNumber());
            preparedStatement.setString(5, users.getEmail());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return result;
    }
    public int  setFalse(long id)
    {
        int res = 0;
        try {
            PreparedStatement preparedStatement = getstatement(
                    "UPDATE public.users\n" +
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
                    "UPDATE public.users\n" +
                            "\tSET \"isBlocked\"='"+true+"'\n" +
                            "\tWHERE id="+idNum+";"
            );
            resutr = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return resutr;
    }

    public List<Users> getAllUser() {
        List<Users> usersList = new ArrayList<>();
        try {
            ResultSet rs = getresultSet(GET_ALL_USERS);
            while (rs.next()) {
                usersList.add(getUser(rs));
            }
        } catch (SQLException | ParseException exception) {
            DB.printSQLException((SQLException) exception);
        }
        return usersList;
    }
    public int getCount()
    {
        int number=0;
        try(PreparedStatement preparedStatement = getstatement(GET_COUNT_FOR_USERS);)
        {
         ResultSet resultSet= preparedStatement.executeQuery();
         while (resultSet.next())
         {
              number=resultSet.getInt("numbers");
         }
        }
        catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return number;
    }
    public Users getOneUser(long num) {
        Users users = null;
        try (PreparedStatement preparedStatement = getstatement(GET_ONE_USER)) {
            preparedStatement.setLong(1, num);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users = getUser(resultSet);
            }
        } catch (SQLException | ParseException exception) {
            DB.printSQLException((SQLException) exception);
        }
        return users;
    }
    public boolean deleteTemporaryPUsers(long id) {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(DELETE_USER_FOR_TIME);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;

    }public boolean recoverTemporaryPUsers(long id) {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(RECOVER_USER_FOR_TIME);) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;

    }
    public boolean deleteUser(long num) throws SQLException {
        boolean status = false;
        try (PreparedStatement preparedStatement = getstatement(DELETE_USER_BY_ID);) {
            preparedStatement.setLong(1, num);
            status = preparedStatement.executeUpdate() > 0;
        }
        return status;
    }

    public boolean updateUser(long userId, Users users) throws SQLException {
       boolean status=false;
        try {
            PreparedStatement statement = getstatement("UPDATE public.users\n" +
                    "\tSET  username='"+users.getUsername()+"',\n" +
                    "\t\"fullName\"='"+users.getFullName()+"', password='"+users.getPassword()+"', \n" +
                    "\t\"phoneNumber\"='"+users.getPhoneNumber()+"', email='"+users.getEmail()+"',\n" +
                    "\t\"isActive\"='"+false+"',\n" +
                    "\t\"isDeleted\"='"+false+"', \n" +
                    "\t\"isBlocked\"='"+false+"'\n" +
                    "\tWHERE id="+userId+"");
            int row=statement.executeUpdate();
           if(row==1)
           {
               status=true;
           }
        } catch (SQLException exception) {
            DB.printSQLException(exception);
        }
        return status;
    }


}
