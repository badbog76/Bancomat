import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by icondor on 22/07/2017.
 */

@WebServlet("/productsurl")
public class ProductsServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) {

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

                String nameOfProduct = request.getParameter("nume");
                String priceOfProduct = request.getParameter("bani");

                DbAccessProduct db = new DbAccessProduct();

                Product insert = new Product();
                insert.setNume(nameOfProduct);
                insert.setBani(Double.parseDouble(priceOfProduct));
                insert.setTip(1);
                insert.setAchitat(0);


                db.insertProduct(insert);
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
