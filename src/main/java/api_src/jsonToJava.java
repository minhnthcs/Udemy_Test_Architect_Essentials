package api_src;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class jsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=null;
        //Create an ArrayList to store all customer records
        ArrayList<CustomerDetails> customers = new ArrayList<CustomerDetails>();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","mason");

        //object of statement class will help us to execute queries
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where location = \"Asia\" and PurchasedDate=curdate()");
//        rs.next(); // setting pointer to particular row
        while (rs.next()){
            CustomerDetails customer = new CustomerDetails();
            customer.setCourseName(rs.getString(1));
            customer.setPurchasedDate(rs.getString(2));
            customer.setAmount(rs.getInt(3));
            customer.setLocation(rs.getString(4));
            customers.add(customer);
        }

        for (int i = 0; i < customers.size(); i++) {
            ObjectMapper ojm = new ObjectMapper();
            ojm.writeValue(new File("C:\\Users\\DELL\\Documents\\playground\\JsonJava\\customerInfo" + i + ".json"), customers.get(i));
        }
        conn.close();
    }
}
