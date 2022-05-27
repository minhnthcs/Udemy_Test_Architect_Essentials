package api;
import java.sql.*;


public class jsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        System.out.println(forName("com.mysql.cj.jdbc.Driver"));
        Connection conn=null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","mason");

        //object of statement class will help us to execute queries
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where location = \"Asia\" and PurchasedDate=curdate() limit 1");
//        rs.next(); // setting pointer to particular row
        while (rs.next()){
            CustomerDetails customer = new CustomerDetails();
//            System.out.println(rs.getString(1));
            customer.setCourseName(rs.getString(1));
            customer.setPurchasedDate(rs.getString(2));
            customer.setAmount(rs.getInt(3));
            customer.setLocation(rs.getString(4));
        }
        conn.close();
    }
}
