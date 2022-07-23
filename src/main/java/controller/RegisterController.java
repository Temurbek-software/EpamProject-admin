package controller;

import entity.Users;
import payload.UserDto;
import services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserServices userServices;

    public void init() {
        userServices = new UserServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.sendRedirect("register/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        register(req, resp);
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        Users users = new Users();
        users.setUsername(username);
        users.setFullName(fullname);
        users.setPassword(password);
        users.setEmail(email);
        users.setPhoneNumber(phoneNumber);
//        try {
//            int result = users.registerEmployee(employee);
//            if(result == 1) {
//                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
//            }
//            response.sendRedirect("");
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
//        dispatcher.forward(request, response);
    }

}
