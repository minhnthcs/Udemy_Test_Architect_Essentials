package api_src;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jsonToJava {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "mason");

        ObjectMapper mapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray();

        //Create an ArrayList to store all customer records
        ArrayList<CustomerDetails> customers = new ArrayList<>();


        //object of statement class will help us to execute queries
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where location = \"Asia\" and PurchasedDate=curdate()");
//        rs.next(); // setting pointer to particular row
        while (rs.next()) {
            CustomerDetails customer = new CustomerDetails();
            customer.setCourseName(rs.getString(1));
            customer.setPurchasedDate(rs.getString(2));
            customer.setAmount(rs.getInt(3));
            customer.setLocation(rs.getString(4));
            customers.add(customer);
        }

        for (int i = 0; i < customers.size(); i++) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(new File("C:\\Users\\DELL\\Documents\\playground\\JsonJava\\customerInfo" + i + ".json"), customers.get(i));
            // Create Json string from java object
            Gson gson = new Gson();
            String jsonString = gson.toJson(customers.get(i));
            jsonArray.add(jsonString);
//            System.out.println(jsonArray);
        }

        // JSON Simple
        JSONObject jsonObject = new JSONObject();
//        System.out.println(jsonObject);
        jsonObject.put("data", jsonArray);
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.toJSONString());
        String unescapeString = StringEscapeUtils.unescapeJava(jsonObject.toJSONString());
//        System.out.println(unescapeString);
        String string1 = unescapeString.replace("\"{", "{");
        String finalString = string1.replace("}\"", "}");
//        System.out.println(finalString);

        try (FileWriter file = new FileWriter("C:\\Users\\DELL\\Documents\\playground\\JsonJava\\customersInfo.json");) {
            file.write(finalString);
        }

        // Convert json array back to java object
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\DELL\\Documents\\playground\\JsonJava\\customersInfo.json"));
            jsonArray = (JSONArray)jsonObject.get("data");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Read data from saved json file
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<CustomerDetails>>(){}.getType();

        ArrayList<CustomerDetails> customerDetails = gson.fromJson(jsonArray.toJSONString(), userListType);

        for(CustomerDetails customer : customerDetails) {
            System.out.println(customer.getAmount());
            System.out.println(customer.getCourseName());
            System.out.println(customer.getLocation());
            System.out.println(customer.getPurchasedDate());
        }
    }
}
