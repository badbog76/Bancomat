import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DbAccessProduct {

    public List<Product> getAllProducts() {
        LoginServlet ht= new LoginServlet();
        /* se va conecta la db si va returna o lista de produse */
        List<Product> listaDinDB = new ArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/6Bogdan";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            // 4. create a query statement
            Statement st = conn.createStatement();

            // 5. execute a query

            ResultSet rs1 = st.executeQuery("SELECT * FROM useri_banca");

            // 6. iterate the result set and print the values
            String item;
            Product p1= new Product();
            while (rs1.next()) {
                {
                    if (ht.getHt().replaceAll("\\s+","").equals(rs1.getString("nume").replaceAll("\\s+",""))) {
                        p1.setNume(rs1.getString("nume"));
                        p1.setSold(rs1.getDouble("sold"));
                    }
                }


            }
            ResultSet rs = st.executeQuery("SELECT * FROM banca");
            while (rs.next()) {
                {
                    Product p = new Product();
                    if (ht.getHt().replaceAll("\\s+","").equals(rs.getString("nume").replaceAll("\\s+",""))) {
                        p.setNume(p1.getNume());
                        p.setSold(p1.getSold());
                        p.setId(rs.getLong("id"));
                        p.setBani(rs.getDouble("bani"));
                        p.setTip(rs.getDouble("tip"));
                        p.setData(rs.getString("data"));
                        listaDinDB.add(p);
                    }
                }



            }


            // 7. close the objects
            rs.close();
            rs1.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDinDB;
    }


    public void insertProduct(Product p) {

        try {
            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/6Bogdan";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO banca (nume, bani, tip, achitat, data ) VALUES (?,?,?,?,?)");

            pSt.setString(1, p.getNume());
            pSt.setDouble(2, p.getBani());
            pSt.setDouble(3, p.getTip());
            pSt.setDouble(4, p.getAchitat());
            pSt.setString(5, p.getData());

            int rowsInserted = pSt.executeUpdate();


            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateproducts(Product p) {

        try {
            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/6Bogdan";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";
            System.out.println(1);
            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(2);
            Statement st= conn.createStatement();
            System.out.println(3);
            ResultSet rs= st.executeQuery("SELECT * from useri_banca");
            LoginServlet ht= new LoginServlet();
            int total=0;
            while (rs.next()) {

                    if (ht.getHt().replaceAll("\\s+","").equals(rs.getString("nume").replaceAll("\\s+","")))
                      total = (int)rs.getDouble("sold") + (int)p.getBani();
            }


            System.out.println(total);
            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("UPDATE useri_banca SET sold='"+total+"'  where REPLACE(nume,' ','')=REPLACE('"+p.getNume()+"',' ','')");


            int rowsInserted = pSt.executeUpdate();


            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateretragere(Product p) {

        try {
            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/6Bogdan";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";
            System.out.println(1);
            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(2);
            Statement st= conn.createStatement();
            System.out.println(3);
            ResultSet rs= st.executeQuery("SELECT * from useri_banca");
            LoginServlet ht= new LoginServlet();
            int total=0;
            while (rs.next()) {

                if (ht.getHt().replaceAll("\\s+","").equals(rs.getString("nume").replaceAll("\\s+","")))
                    total = (int)rs.getDouble("sold") - (int)p.getBani();
            }


            System.out.println(total);
            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("UPDATE useri_banca SET sold='"+total+"'  where REPLACE(nume,' ','')=REPLACE('"+p.getNume()+"',' ','')");


            int rowsInserted = pSt.executeUpdate();


            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    }
