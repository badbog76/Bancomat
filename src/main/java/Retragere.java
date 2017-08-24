import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet("/retragere")
public class Retragere extends HttpServlet {


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        ProductsServlet a= new ProductsServlet();
        LoginServlet ht= new LoginServlet();
        String nameOfProduct = ht.getHt();
        String priceOfProduct = request.getParameter("bani");
        System.out.println(priceOfProduct);
        DbAccessProduct db = new DbAccessProduct();
        Product insert = new Product();
        insert.setNume(nameOfProduct);
        insert.setBani(Double.parseDouble(priceOfProduct));
        insert.setTip(1);
        insert.setAchitat(0);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        java.util.Date date = new java.util.Date();
        insert.setData(dateFormat.format(date));
        db.insertProduct(insert);
        db.updateretragere(insert);
        response.sendRedirect("meniu.html");
    }
}





