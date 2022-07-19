package controller;

import database.DB;
import entity.Product;
import entity.Users;
import services.ProductServices;
import services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/addUser",
        "/editUsers",
        "/createUsers", "/userList",
        "/deleteUsers", "/updateUsers",
        "/deletedUsersList", "/recoverUsers",
        "/deleteUsersForTime", "/blockUsers","/blockUsersInDeleted"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserServices userServices;

    public void init() {
        userServices = new UserServices();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/deleteUsers":
                    deleteUser(request, response);
                    break;
                case "/recoverUsers":
                    recoverUser(request, response);
                    break;
                case "/deleteUsersForTime":
                    deletedUserTime(request, response);
                    break;
                case "/deletedUsersList":
                    deletedList(request, response);
                    break;
                case "/editUsers":
                    editUser(request, response);
                    break;
                case "/addUser":
                    addingPage(request, response);
                    break;
                case "/createUsers":
                    insertUSer(request, response);
                    break;
                case "/updateUsers":
                    updateUser(request, response);
                    break;
                case "/userList":
                    displayUser(request, response);
                    break;
                case "/blockUsers":
                    blockUsers(request, response);
                    break;
                case "/blockUsersInDeleted":
                    blockUsersDeleted(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void displayUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/ManagaUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void recoverUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (userServices.recoverTemporaryPUsers(id)) {
            request.setAttribute("msgRecoveredUser", "User recovered successfully");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/ManagaUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void blockUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Users users = userServices.getOneUser(id);
        if (users.isBlocked()) {
            userServices.setFalse(users.getId());
        } else {
            userServices.setTrue(users.getId());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/ManagaUsers.jsp");
        dispatcher.forward(request, response);
    }  private void blockUsersDeleted(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Users users = userServices.getOneUser(id);
        if (users.isBlocked()) {
            userServices.setFalse(users.getId());
        } else {
            userServices.setTrue(users.getId());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/DeletedUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void deletedList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/DeletedUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void addingPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("entityList/AddUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userServices.deleteUser(id);
        request.setAttribute("msgDeleteComplete", "Successfully deleted");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/DeletedUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void deletedUserTime(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (userServices.deleteTemporaryPUsers(id)) {
            request.setAttribute("msgDeleteUser", "User deleted successfully");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/ManagaUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Users currentUser = userServices.getOneUser(id);
        request.setAttribute("currentUser", currentUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("entityList/AddUsers.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUSer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Users users = new Users(username, fullName, password, phoneNumber, email);
        userServices.saveUser(users);
        response.sendRedirect("/users");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        long id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Users users = new Users();
        users.setUsername(username);
        users.setFullName(fullName);
        users.setPassword(password);
        users.setPhoneNumber(phoneNumber);
        users.setEmail(email);
        if (userServices.updateUser(id, users)) {
            request.setAttribute("msgUpdate", "User has been successfully updated");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/ManagaUsers.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("msgUpdate", "Failed updating");
            RequestDispatcher dispatcher = request.getRequestDispatcher("entityList/AddUsers.jsp");
            dispatcher.forward(request, response);
        }

    }
}
