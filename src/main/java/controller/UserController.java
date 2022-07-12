package controller;

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

@WebServlet(urlPatterns = {"/users","/create","/delete","/update"})
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
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    editUser(request, response);
                    break;
                case "/create":
                    insertUSer(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    getAllUsers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void getAllUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Users> usersList = userServices.getAllUser();
        request.setAttribute("usersList", usersList);
        System.out.println("Product list");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/entityList/UserList.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userServices.deleteUser(id);
        response.sendRedirect("/users");
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Users currentUser = userServices.getOneUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        request.setAttribute("currentUser", currentUser);
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
        String password=request.getParameter("password");
        String phoneNumber=request.getParameter("phoneNumber");
        String email=request.getParameter("email");
        Users users= new Users(username,fullName,password,phoneNumber,email);
        userServices.updateUser(id,users);
        response.sendRedirect("/users");
    }
}
