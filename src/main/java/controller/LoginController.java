package controller;


import entity.Publisher;
import entity.Users;
import payload.ProductDetails;
import payload.PublisherDto;
import payload.UserDto;
import services.CookieService;
import services.PublisherService;
import services.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/loginPage",
        "/regis",
        "/registration", "/login",
        "/logout"})
public class LoginController extends HttpServlet {
    private UserServices userServices;
    private PublisherService publisherService;

    public void init() {
        userServices = new UserServices();
        publisherService = new PublisherService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/loginPage":
                    getLoginpage(request, response);
                    break;
                case "/login":
                    authenticate(request, response);
                    break;
                case "/logout":
                    stopAuthenticate(request, response);
                    break;
                case "/registration":
                    registration(request, response);
                    break;
                case "/regis":
                    getRegister(request, response);
                    break;
            }
        } catch (SQLException exception) {
            throw new ServletException(exception);
        }
    }

    private void getLoginpage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
        dispatcher.forward(request, response);
    }

    private void getRegister(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        String nameOfCompany = request.getParameter("nameOfCompany");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String description = request.getParameter("description");
        Publisher publisher = new Publisher();
        publisher.setNameOfCompany(nameOfCompany);
        publisher.setUsername(username);
        publisher.setAddress(address);
        publisher.setPhoneNumber(phoneNumber);
        publisher.setEmail(email);
        publisher.setPassword(password);
        publisher.setDescription(description);
        System.out.println(description);
        try {
            if (publisherService.isPublisherExist(publisher) != "SUCCESS") {
                String nm = publisherService.isPublisherExist(publisher);
                if (nm.equals("NAME_NOT_VALID")) {
                    request.setAttribute("msg", " Name has already taken");
                }
                if (nm.equals("PHONE_NUMBER_NOT_VALID")) {
                    request.setAttribute("msg", " Phone has already taken");
                }
                if (nm.equals("EMAIL_NOT_VALID")) {
                    request.setAttribute("msg", " Email has already taken");
                }
                if (nm.equals("USERNAME_NOT_VALID")) {
                    request.setAttribute("msg", " Username has already taken");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/registration");
                dispatcher.forward(request, response);
            } else {
                int result = publisherService.savePublisher(publisher);
                if (result == 1) {
                    publisherService.saveisActive(publisher);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("publisherSession", publisher);
                    Cookie cookieUser = new Cookie("Username", username);
                    Cookie cookiePas = new Cookie("Password", password);
                    cookieUser.setMaxAge(60 * 60);
                    cookiePas.setMaxAge(60 * 60);
                    response.addCookie(cookieUser);
                    response.addCookie(cookiePas);
                    httpSession.setAttribute("sessUser", username.trim());
                    response.sendRedirect("/");
                } else {
                    request.setAttribute("result", false);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/");
                    dispatcher.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registration(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String remember = request.getParameter("remember");
        PublisherDto publisher = new PublisherDto();
        publisher.setUsername(username);
        publisher.setPassword(password);
        if (username.equals("admin") && password.equals("1")) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("adminSession", publisher);
            Cookie cookieUser = new Cookie("Username", username);
            Cookie cookiePas = new Cookie("Password", password);
            Cookie cookieRem = new Cookie("RememberMe", remember);
            cookieUser.setMaxAge(60 * 60);
            cookiePas.setMaxAge(60 * 60);
            cookieRem.setMaxAge(60 * 60);
            response.addCookie(cookieUser);
            response.addCookie(cookiePas);
            response.addCookie(cookieRem);
            httpSession.setAttribute("sessUser", username.trim());
            response.sendRedirect("/");

        } else {
            if (publisherService.validate(publisher)) {
                Publisher publisher1 = publisherService.getPublisher(publisher);
                if (!publisher1.isBlocked()) {
                    publisherService.saveisActive(publisher1);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("publisherSession", publisher1);
                    Cookie cookieUser = new Cookie("Username", username);
                    Cookie cookiePas = new Cookie("Password", password);
                    Cookie cookieRem = new Cookie("RememberMe", remember);
                    cookieUser.setMaxAge(60 * 60);
                    cookiePas.setMaxAge(60 * 60);
                    cookieRem.setMaxAge(60 * 60);
                    response.addCookie(cookieUser);
                    response.addCookie(cookiePas);
                    response.addCookie(cookieRem);
                    httpSession.setAttribute("sessUser", username.trim());
                    response.sendRedirect("/");
                } else {
                    request.setAttribute("falseUser", "You are blocked");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage");
                    dispatcher.forward(request, response);

                }
            } else {
                request.setAttribute("falseUser", "Authentication failure");
                RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage");
                dispatcher.forward(request, response);
            }
        }

    }

    private void stopAuthenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CookieService cookieService = new CookieService();
        Publisher publisher = cookieService.getPublisher(request);
        publisherService.saveNoisActive(publisher);
        Cookie cookieUser = new Cookie("Username", null);
        Cookie cookiePas = new Cookie("Password", null);
        Cookie cookieRem = new Cookie("RememberMe", null);
        cookieUser.setMaxAge(0);
        cookiePas.setMaxAge(0);
        cookieRem.setMaxAge(0);
        response.addCookie(cookieUser);
        response.addCookie(cookiePas);
        response.addCookie(cookieRem);
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        request.setAttribute("msg", "You have successfully logged out.");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage");
        requestDispatcher.forward(request, response);
    }


}
