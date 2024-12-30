package db;

import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class DBConnection {

    private static DBConnection instance;

    List<Customer> connetion;

    private DBConnection(){
        connetion = new ArrayList<>();
    }

    public List<model.Customer> getConnetion(){
        return connetion;
    }
    public static DBConnection getInstance(){
        return  instance == null?instance = new DBConnection():instance;



    }
}
