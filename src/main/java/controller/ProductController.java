package controller;


import entity.Category;
import entity.Product;
import payload.ProductDetails;
import services.CategoryServices;
import services.ProductServices;
import services.UserServices;

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
import java.util.List;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/","/createNews","/showNewsForm"})
@MultipartConfig(maxFileSize = 16177215)
public class ProductController extends HttpServlet
{
    private ProductServices productService;
    private CategoryServices categoryServices;
    private UserServices userServices;

    public void init()
    {
        userServices = new UserServices();
        productService = new ProductServices();
        categoryServices = new CategoryServices();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
//                case "/edit":
//                    listOfProducts(request, response);
//                    break;
                case "/createNews":
                    insertNews(request, response);
                    break;
                case "/showNewsForm":
                    newsForm(request, response);
                    break;
                case "/":
                    getStatistic(request, response);
                    break;
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void getStatistic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberOfRegisteredUser = userServices.getCount();
        request.setAttribute("numOfUsers",numberOfRegisteredUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
    private void getAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        List<Product> productList = productService.getAllProduct();
//        request.setAttribute("productList", productList);
//        System.out.println("Product list");
        List<Category> categoryList = categoryServices.categoryList();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/AddNews.jsp");
        dispatcher.forward(request, response);
    }

    private void newsForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        List<Product> productList = productService.getAllProduct();
//        request.setAttribute("productList", productList);
//        System.out.println("Product list");
        List<Category> categoryList = categoryServices.categoryList();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/AddNews.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormOfNews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException
    {
        List<Category> categoryList = categoryServices.categoryList();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/AddNews.jsp");
        dispatcher.forward(request, response);
    }
    private void insertNews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String titles = request.getParameter("titles");
        String description = request.getParameter("description");
        String textData = request.getParameter("textData");
        System.out.println(textData);
        String sourcelinkTo = request.getParameter("sourcelinkTo");
        Part filePart = request.getPart("photofile");
        String categoryName = request.getParameter("name1");
        Product product = new Product();
        product.setTitles(titles);
        product.setDescription(description);
        product.setTextData(textData);
        System.out.println(product.getTextData());
        product.setSourcelinkTo(sourcelinkTo);
        product.setPhotofile(filePart.getInputStream().readAllBytes());
        product.setCategory_id(Integer.parseInt(categoryName));
//        System.out.println(product.getCategory_id());
//        System.out.println(product.getDescription());
        System.out.println(product.getTextData());
        int i = productService.saveProduct(product);
        System.out.println("just watch error");
        if (i!=0){
            response.sendRedirect("showNewsForm");
        }
//        response.sendRedirect("/");
        // inputP stream of the upload file

    }

}
