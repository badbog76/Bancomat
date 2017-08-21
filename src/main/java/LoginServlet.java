import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static String ht;

    public static String getHt() {
        return ht;
    }


    public static void setHt(String ht) {
        LoginServlet.ht = ht;
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // read user and password introduced by the user in the UI
        String user = request.getParameter("u");
        String passwd = request.getParameter("p");
        ht = user;
        UserDBAccess udb = new UserDBAccess();
        int gasit;
        gasit = udb.isUserPwdOK(user, passwd);

        if (gasit != -1) {
            System.out.println("LoginServlet: user found");

            // userul exista in db, deci il autentific
            HttpSession session = request.getSession(true);
            session.setAttribute(LogoutServlet.USERNAME, user);
            session.setAttribute(LogoutServlet.USERNAMEID, gasit);
            response.sendRedirect("meniu.html");
        } else {
            System.out.println("LoginServlet: user/pwd NOT FOUND in db, retry a new one on the UI ");
            String back = "/login.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }


    }
}
