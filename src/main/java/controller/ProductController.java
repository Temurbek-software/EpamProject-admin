package controller;


import database.DB;
import entity.Category;
import entity.Product;
import entity.Publisher;
import org.apache.tomcat.util.codec.binary.Base64;
import payload.ProductDetails;
import services.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/", "/deletedNews",
        "/createNews", "/editNewsForm", "/showNewsForm"
        ,"/deleteNews","/recover","/deleteNewsTemporary","/updateNews"})
@MultipartConfig(maxFileSize = 16177215)
public class ProductController extends HttpServlet {
    private ProductServices productService;
    private CategoryServices categoryServices;
    private UserServices userServices;
    private PublisherService publisherService;
    private CookieService cookieService;

    public void init() {
        userServices = new UserServices();
        productService = new ProductServices();
        categoryServices = new CategoryServices();
        publisherService = new PublisherService();
        cookieService = new CookieService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/createNews":
                    insertNews(request, response);
                    break;
                case "/updateNews":
                    updateNews(request, response);
                    break;
                case "/displayNews":
                    displayNews(request, response);
                    break;
                case "/showNewsForm":
                    newsForm(request, response);
                    break;
                case "/editNewsForm":
                    updateNewsForm(request, response);
                    break;
                case "/deletedNews":
                    deletedPostList(request, response);
                    break;
                case "/deleteNews":
                    deletePost(request, response);
                    break;
                case "/deleteNewsTemporary":
                    deletePostForTime(request, response);
                    break;
                case "/recover":
                    recoverPost(request, response);
                    break;

                default:
                    getStatistic(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }



    private void deletePostForTime(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        if (productService.deleteTemporaryPost(id))
        {
            request.setAttribute("msgDelete","Post recovered successfully");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/ManageNews.jsp");
        dispatcher.forward(request, response);
    }

    private void recoverPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        if (productService.recoverPost(id))
        {
             request.setAttribute("msgRecover","Post deleted successfully");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/deletetNews.jsp");
        dispatcher.forward(request, response);
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        if (productService.deletePost(id))
        {
            request.setAttribute("msg","Post has been deleted successfully");
        }
        else
        {
            request.setAttribute("msg1","Sorry, Unfortunately post not deleted, please check again");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/deletetNews.jsp");
        dispatcher.forward(request, response);
    }

    private void deletedPostList(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/deletetNews.jsp");
        dispatcher.forward(request, response);
    }
    private void displayNews(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, ParseException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/ManageNews.jsp");
        dispatcher.forward(request, response);
    }


    public void getStatistic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberOfRegisteredUser = userServices.getCount();
        request.setAttribute("numOfUsers", numberOfRegisteredUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void getAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> categoryList = categoryServices.categoryList();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/AddNews.jsp");
        dispatcher.forward(request, response);
    }


    private void newsForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ParseException {
        List<Category> categoryList = categoryServices.categoryList();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/AddNews.jsp");
        dispatcher.forward(request, response);
    }

    private void updateNewsForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ParseException {
        List<Category> categoryList = categoryServices.categoryList();
        request.setAttribute("categoryList", categoryList);
        long id = Integer.parseInt(request.getParameter("id"));
        String name = CategoryServices.getCategoryByName(id);
        request.setAttribute("nameof", name);
        Product product = productService.getProductByID(id);
        String file=new String(Base64.encodeBase64((product.getPhotofile())),"UTF-8");

        request.setAttribute("productCurrent", product);
        request.setAttribute("file",file);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/AddNews.jsp");
        dispatcher.forward(request, response);
    }

    private void insertNews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String titles = request.getParameter("titles");
        String description = request.getParameter("description");
        String textData = request.getParameter("editor");
        String sourcelinkTo = request.getParameter("sourcelinkTo");
        Part filePart = request.getPart("photofile");
        String categoryName = request.getParameter("name1");
        Product product = new Product();
        product.setTitles(titles);
        product.setDescription(description);
        product.setTextData(textData);
        product.setSourcelinkTo(sourcelinkTo);
        product.setPhotofile(filePart.getInputStream().readAllBytes());
        product.setCategory_id(Integer.parseInt(categoryName));
        CookieService cookieService = new CookieService();
        long id = cookieService.getPublisher(request).getId();
        int i = productService.saveProduct(product, id);
        if (i != 0) {
            request.setAttribute("msg", "New post saved successfully");
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayNews");
            dispatcher.forward(request, response);
        }
        else

        {
            request.setAttribute("msg","Sorry something wrong");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showNewsForm");
            dispatcher.forward(request, response);
        }


    }

    private void updateNews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String titles = request.getParameter("titles");
        String description = request.getParameter("description");
        String textData = request.getParameter("editor");
        String sourcelinkTo = request.getParameter("sourcelinkTo");
        Part filePart = request.getPart("photofile");
        String categoryName = request.getParameter("name1");
        Product product = new Product();
        product.setId(id);
        product.setTitles(titles);
        product.setDescription(description);
        product.setTextData(textData);
        product.setSourcelinkTo(sourcelinkTo);
        product.setPhotofile(filePart.getInputStream().readAllBytes());
        product.setCategory_id(Integer.parseInt(categoryName));
        CookieService cookieService = new CookieService();
        long pubid = cookieService.getPublisher(request).getId();
        int i = productService.updatePost(product, id,pubid);
        if (i != 0) {
            request.setAttribute("msg", "Current post updated successfully");
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayNews");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("msg","Sorry something wrong");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showNewsForm");
            dispatcher.forward(request, response);
        }


    }

}
