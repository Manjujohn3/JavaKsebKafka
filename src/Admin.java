import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
                    GregorianCalendar date = new GregorianCalendar();
                    int currentMonth = date.get(Calendar.MONTH);
                    int currentYear = date.get(Calendar.YEAR);
                    currentMonth = currentMonth+1;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb", "root", "");
                        String sql = "DELETE FROM `bills` WHERE `month`= '" + currentMonth + "'AND `year`= '" + currentYear + "'";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("Previous data deleted");
                        String sql1 = "SELECT `id` FROM `consumer` ";
                        Statement stmt1 = con.createStatement();
                        ResultSet rs = stmt1.executeQuery(sql1);
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String sql2 = "select SUM(`unit`) from usages where month(date) = '"+currentMonth+"' AND year(date) = '"+currentYear+"' AND `consumerid` ='"+id+"'";
                            Statement stmt2 = con.createStatement();
                            ResultSet rs1 = stmt2.executeQuery(sql2);
                            while (rs1.next()) {
                                int add = rs1.getInt("SUM(`Unit`)");
                                int status = 0;
                                int totalBill = add * 5;
                                //generating random number for invoice
                                int min = 10000;
                                int max = 99999;
                                int invoice = (int)(Math.random() * (max - min + 1) + min);
                                String sql3 = "INSERT INTO `bills`(`consumerid`, `month`, `year`, `bill`, `paidstatus`, `billdate`, `totalunit`, `duedate`,`invoice`) VALUES (?,?,?,?,?,now(),?,now()+ interval 14 day,?)";
                                PreparedStatement stmt3 = con.prepareStatement(sql3);
                                stmt3.setInt(1, id);
                                stmt3.setInt(2, currentMonth);
                                stmt3.setInt(3, currentYear);
                                stmt3.setInt(4, totalBill);
                                stmt3.setInt(5, 0);
                                stmt3.setInt(6, add);
                                stmt3.setInt(7, invoice);
                                stmt3.executeUpdate();
                            }
                        }

                    }
                    catch(Exception e){
                        System.out.println(e);
                    }

                    break;
                case 7:
                    System.out.println("view bill");
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb","root","");
                        String sql = "SELECT  b.`consumerid`, b.`month`, b.`year`, b.`bill`, b.`paidstatus`, b.`billdate`, b.`totalunit`, b.`duedate`, b.`invoice`,c.consumerid,c.name FROM `bills` b JOIN consumer c ON b.consumerid=c.id";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()){
                            String getConsumerId = rs.getString("consumerid");
                            String getMonth= rs.getString("month");
                            String getYear = rs.getString("year");
                            String getBill = rs.getString("bill");
                            String getPaidStatus = rs.getString("paidstatus");
                            String getbilldate = rs.getString("billdate");
                            String getTotalUnit = rs.getString("totalunit");
                            String getDueDate = rs.getString("duedate");
                            String getInvoice = rs.getString("invoice");
                            String getConsumerCode = rs.getString("consumerid");
                            String getConsumerName = rs.getString("name");
                            System.out.println("Consumer Id="+getConsumerId);
                            System.out.println("Month="+getMonth);
                            System.out.println("Year="+getYear);
                            System.out.println("Bill ="+getBill);
                            System.out.println("PaidStatus ="+getPaidStatus);
                            System.out.println("Bill Date ="+getbilldate);
                            System.out.println("Total Unit ="+getTotalUnit);
                            System.out.println("Due Date ="+getDueDate);
                            System.out.println("Invoice ="+getInvoice);
                            System.out.println("Consumer Code ="+getConsumerCode);
                            System.out.println("Consumer Name="+getConsumerName+"\n");
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                    break;
                case 8:
                    System.out.println("Top 2 high bill");
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksebdb", "root", "");
                        String sql = "SELECT c.name,c.address,b.`bill`, b.`totalunit` FROM `bills` b JOIN consumer c ON b.consumerid=c.id ORDER BY b.`bill`DESC LIMIT 2";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            name = rs.getString("c.name");
                            address = rs.getString("c.address");
                            int bill = rs.getInt("b.bill");
                            int total = rs.getInt("totalunit");
                            System.out.println("name ="+name);
                            System.out.println("address ="+address);
                            System.out.println("total bill = "+bill);
                            System.out.println("total unit ="+total+'\n');
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;

                case 9:
                    System.exit(0);
                    break;
            }
        }
    }
}
