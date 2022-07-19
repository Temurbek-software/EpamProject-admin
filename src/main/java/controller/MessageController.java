package controller;

import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/contactMessage", "/complainMessage","/deleteContactMessage"})
public class MessageController extends HttpServlet {
    private ProductServices productService;
    private CategoryServices categoryServices;
    private UserServices userServices;
    private PublisherService publisherService;
    private CookieService cookieService;
    private MessageService messageService;

    public void init() {
        userServices = new UserServices();
        productService = new ProductServices();
        categoryServices = new CategoryServices();
        publisherService = new PublisherService();
        cookieService = new CookieService();
        messageService = new MessageService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/contactMessage":
                displayMessage(request, response);
                break;
            case "/complainMessage":
                displayComplainMessage(request, response);
                break;
            case "/deleteContactMessage":
                deleteContactMessage(request, response);
                break;
        }
    }

    private void displayMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("message/contactMessage.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteContactMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        if (messageService.deleteContactMsg(id)) {
            request.setAttribute("msg", "Contact msg has been successfully deleted");
        } else {
            request.setAttribute("msg", "Something wrong");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("message/contactMessage.jsp");
        dispatcher.forward(request, response);
    }

    private void displayComplainMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("message/complainMessage.jsp");
        dispatcher.forward(request, response);
    }


}
