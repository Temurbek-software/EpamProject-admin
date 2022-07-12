import controller.ProductController;
import entity.Product;
import entity.Users;
import services.ProductServices;
import services.UserServices;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServices userServices= new UserServices();
        System.out.println(userServices.updateUser(39,new Users("Zarifov","asdas","asda","asdas","asdas")));
    }
}
