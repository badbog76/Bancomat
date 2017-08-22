import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by icondor on 22/07/2017.
 */

@WebServlet("/productsurl")
public class ProductsServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("intra in servlet uite ma ");

        String action = request.getParameter("action");
        if(action!=null &&action.equals("list")) {
            JSONObject json = new JSONObject();
            DbAccessProduct db = new DbAccessProduct();

            json.put("productsjson", db.getAllProducts());

            String result = json.toString();
            System.out.println("result products:" + result);
            returnJsonResponse(response, result);
        }
        else

        if(action!=null &&action.equals("insert"))
        {
            System.out.println("intra pe insert");
            HttpSession s = request.getSession();
            String val = (String)s.getAttribute("isLoggedIn");
            if(val!=null && val.equals("brrr")) {

                System.out.println("esti logat");
                LoginServlet ht= new LoginServlet();
                String nameOfProduct = ht.getHt();
                String priceOfProduct = request.getParameter("bani");

                DbAccessProduct db = new DbAccessProduct();

                Product insert = new Product();
                insert.setNume(nameOfProduct);
                insert.setBani(Double.parseDouble(priceOfProduct));
                insert.setTip(0);
                insert.setAchitat(0);
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = new java.util.Date();
                insert.setData(dateFormat.format(date));
                db.insertProduct(insert);
                db.updateproducts(insert);
            }
            else
            {
                System.out.println("nu esti logat frate");
            }

        }


    }


    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }


}
