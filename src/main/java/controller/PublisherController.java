package controller;

import entity.Publisher;
import entity.Users;
import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {
        "/managePublishers",
        "/displayPublisher",
        "/addPub", "/deletePub",
        "/editPub", "/updatePub",
        "/blockPublisher", "/messaging"})
public class PublisherController extends HttpServlet {
    private PublisherService publisherService;

    public void init() {
        publisherService = new PublisherService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/managePublishers":
                listOfPublisher(request, response);
                break;
            case "/displayPublisher":
                showPublisher(request, response);
                break;
            case "/addPub":
                insertPub(request, response);
                break;
            case "/editPub":
                editPublisher(request, response);
                break;
            case "/updatePub":
                updatePublisher(request, response);
                break;
            case "/deletePub":
                deletePublisher(request, response);
                break;
            case "/messaging":
                getMsg(request, response);
                break;
            case "/blockPublisher":
                blocking(request, response);
                break;
        }
    }

    private void editPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Publisher publisher = publisherService.getPublisherById(id);
        request.setAttribute("currentPub", publisher);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/displayPublisher");
        dispatcher.forward(request, response);
    }

    private void getMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("chatting/ChatBox.jsp");
        dispatcher.forward(request, response);
    }


    private void blocking(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Publisher publisher = publisherService.getPublisherById(id);
        if (publisher.isBlocked()) {
            publisherService.setFalse(publisher.getId());
        } else {
            publisherService.setTrue(publisher.getId());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/managePublishers");
        dispatcher.forward(request, response);
    }

    private void updatePublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nameOfCompany = request.getParameter("nameOfCompany");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String description = request.getParameter("editor") == null ? "" : request.getParameter("editor");
        Publisher publisher = new Publisher(id, username, nameOfCompany,
                address, phoneNumber, email, password, description);
        CookieService cookieService = new CookieService();
        Publisher publisher2 = cookieService.getPublisher(request);
        if (publisher2.getId() == id) {
            if (publisherService.updatePublisher(publisher) > 0) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/logout");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("msgUpdatePub", "In updatingFailed updating please try again or check it out");
                RequestDispatcher dispatcher = request.getRequestDispatcher("displayPublisher");
                dispatcher.forward(request, response);
            }
        } else {
            if (publisherService.updatePublisher(publisher) > 0) {
                request.setAttribute("msgUpdatePublisher", "Publisher has been successfully updated");
                RequestDispatcher dispatcher = request.getRequestDispatcher("publisher/managePublisher.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("msgUpdatePub", "Failed updating please try again or check it out");
                RequestDispatcher dispatcher = request.getRequestDispatcher("displayPublisher");
                dispatcher.forward(request, response);
            }
        }

    }

    private void deletePublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        if (publisherService.deletePublisher(id)) {
            request.setAttribute("msgPublisher", "Post has been deleted successfully");
        } else {
            request.setAttribute("msgPublisher1", "Sorry, Unfortunately post not deleted, please check again");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("publisher/managePublisher.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPub(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String nameOfCompany = request.getParameter("nameOfCompany");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String description = request.getParameter("textData");
        Publisher publisher = new Publisher();
        publisher.setNameOfCompany(nameOfCompany);
        publisher.setUsername(username);
        publisher.setAddress(address);
        publisher.setPhoneNumber(phoneNumber);
        publisher.setEmail(email);
        publisher.setPassword(password);
        publisher.setDescription(description);
        try {
            if (publisherService.isPublisherExist(publisher) != "SUCCESS") {
                String nm = publisherService.isPublisherExist(publisher);
                if (nm.equals("NAME_NOT_VALID")) {
                    request.setAttribute("msg", "Name has already taken");
                }
                if (nm.equals("PHONE_NUMBER_NOT_VALID")) {
                    request.setAttribute("msg", "Phone has already taken");
                }
                if (nm.equals("EMAIL_NOT_VALID")) {
                    request.setAttribute("msg", "Email has already taken");
                }
                if (nm.equals("USERNAME_NOT_VALID")) {
                    request.setAttribute("msg", "USername has already taken");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/displayPublisher");
                dispatcher.forward(request, response);
            } else {
                int result = publisherService.savePublisher(publisher);
                if (result == 1) {
                    request.setAttribute("result", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/displayPublisher");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("result", false);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/displayPublisher");
                    dispatcher.forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listOfPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("publisher/managePublisher.jsp");
        dispatcher.forward(request, response);
    }

    private void showPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("publisher/addPublisher.jsp");
        dispatcher.forward(request, response);
    }


}
