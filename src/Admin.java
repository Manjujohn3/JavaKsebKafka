import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;
public class Admin {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb","root","");
        }
        catch (Exception e){
            System.out.println(e);
        }
        int choice;
        while (true) {
            System.out.println("select an option from the menu");
            System.out.println("1 add consumer");
            System.out.println("2 search consumer ");
            System.out.println("3 delete consumer");
            System.out.println("4 update consumer");
            System.out.println("5 view all consumer");
            System.out.println("6 generate bill");
            System.out.println("7 view bill");
            System.out.println("8 Top 2 high bill");
            System.out.println("9 exit");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("insert consumer selected");
                    int consumerid = sc.nextInt();
                    String name = sc.next();
                    String address = sc.next();
                    String phone = sc.next();

                    String email = sc.next();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb", "root", "");
                        String sql = "INSERT INTO `consumer`(`consumerid`, `name`, `address`, `phone`, `email`) VALUES (?,?,?,?,?)";
                        PreparedStatement stmt = con.prepareStatement(sql);
                        stmt.setInt(1,consumerid);
                        stmt.setString(2,name);
                        stmt.setString(3,address);
                        stmt.setString(4,phone);
                        stmt.setString(5,email);
                        stmt.executeUpdate();

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("search consumer selected");
                    System.out.println("Enter the Consumer Code/Name/Phone to search: ");
                    String searchOption = sc.next();
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb", "root", "");
                        String sql = "SELECT `consumerid`, `name`, `address`, `phone`, `email` FROM `consumer` WHERE `consumerid` ='"+searchOption+"'  OR `name`='"+searchOption+"' OR `phone` ='"+searchOption+"' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()){
                            String getConsumerid = rs.getString("consumerid");
                            String getName = rs.getString("name");
                            String getConsumerAddress = rs.getString("address");
                            String getConsumerPhone = rs.getString("phone");
                            String getConsumerEmail = rs.getString("email");

                            System.out.println("Consumer Code="+getConsumerid);
                            System.out.println("Consumer Name="+getName);
                            System.out.println("Consumer Phone="+getConsumerPhone);
                            System.out.println("Consumer Email="+getConsumerEmail);
                            System.out.println("Consumer Address="+getConsumerAddress);
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    System.out.println("delete consumer selected");
                    consumerid = sc.nextInt();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb", "root", "");
                        String sql = "DELETE FROM `consumer` WHERE `consumerid`='"+consumerid+"'";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("deleted successfully");

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 4:
                    System.out.println("update consumer selected");
                    consumerid = sc.nextInt();
                    name = sc.next();
                    address = sc.next();
                    phone = sc.next();
                    email = sc.next();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb", "root", "");
                        String sql = "UPDATE `consumer` SET `consumerid`='"+consumerid+"',`name`='"+name+"',`Address`='"+address+"',`Phone`='"+phone+"',`Email`='"+email+"' WHERE `consumerid`='"+consumerid+"'";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("Updated successfully");

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 5:
                    System.out.println("view all consumer selected");
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb","root","");
                        String sql="SELECT `id`, `consumerid`, `name`, `address`, `phone`, `email` FROM `consumer`";
                        Statement stmt = con.createStatement();
                        ResultSet rs= stmt.executeQuery(sql);
                        while (rs.next()){
                            String getconsumerid=rs.getString("consumerid");
                            String getname=rs.getString("name");
                            String getaddress=rs.getString("address");
                            String getphone=rs.getString("phone");
                            String getemail=rs.getString("email");

                            System.out.println("consumerid="+getconsumerid);
                            System.out.println("name="+getname);
                            System.out.println("address="+getaddress);
                            System.out.println("phone="+getphone);
                            System.out.println("email="+getemail);
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 6:
                    System.out.println("generate bill");
                    break;
                case 7:
                    System.out.println("view bill");
                    break;
                case 8:
                    System.out.println("Top 2 high bill");
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }
    }
}
