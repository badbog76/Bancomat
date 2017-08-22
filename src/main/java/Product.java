import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Product {

    private long id;
    private String nume;
    private double bani;
    private double tip;
    private String data;
    private double sold;
    private double achitat;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public double getAchitat() {
        return achitat;
    }

    public void setAchitat(double achitat) {
        this.achitat = achitat;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getBani() {
        return bani;
    }

    public void setBani(double bani) {
        this.bani = bani;
    }
}
