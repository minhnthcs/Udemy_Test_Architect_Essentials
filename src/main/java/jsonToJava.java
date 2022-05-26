package main.java;

import java.sql.*;

import static java.lang.Class.forName;

public class jsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        System.out.println(forName("com.mysql.cj.jdbc.Driver"));
        Connection conn=null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","mason");

        //object of statement class will help us to execute queries
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where location = \"Asia\" and PurchasedDate=curdate()");
//        rs.next(); // setting pointer to particular row
        while (rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getString(4));
        }
        conn.close();
    }
}
