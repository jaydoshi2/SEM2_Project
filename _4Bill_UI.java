package JDBC.src.Group_Project3;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class _4Bill_UI {
    static int total = 0;
    static Connection con = null;
    static Scanner sc = new Scanner(System.in);

    _4Bill_UI() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234");
    }

    public static void display_cart() throws Exception {
        boolean flag = true;
        while (flag) {
            try {
                new _4Bill_UI();
                System.out.println("Displaying your cart");
                String print_Shopping_cart = "Select * from " + _1Users_UI.user_id1;
                PreparedStatement ps = con.prepareStatement(print_Shopping_cart);
                ResultSet rs = ps.executeQuery();
                System.out.println("DISPLAYING THE CART");
                while (rs.next()) {
                    System.out.println("cart_id " + rs.getInt(1) + " id:" + rs.getInt(2) + " Subcategory: "
                            + rs.getString(3) + " Product: "
                            + rs.getString(4) + " Gender: " + rs.getString(5) + " Brand: " + rs.getString(6)
                            + " Size: " + rs.getString(7) + " Color: " + rs.getString(8) + " Prize: "
                            + rs.getDouble(9));
                    System.out.println();
                }
                ResultSet rs2 = ps.executeQuery();
                total = 0;
                while (rs2.next()) {
                    total += rs2.getDouble(9);
                }
                System.out.println("The total of all products is: " + total);
                System.out.println("Do you want to remove any item?");
                System.out.println("1)Yes");
                System.out.println("2)No");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    String delete_item = "Delete from " + _1Users_UI.user_id1 + " where product_id = ?";
                    PreparedStatement pst = con.prepareStatement(delete_item);
                    System.out.println("Enter the item you want to delete");
                    int id = sc.nextInt();
                    pst.setInt(1, id);
                    if (pst.executeUpdate() > 0) {
                        System.out.println("Item is deleted successfully");
                        System.out.println();
                    } else {
                        System.out.println("Such item does not present");
                        System.out.println();
                    }
                } else {
                    flag = false;
                    payment();
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input");
            }
        }
    }

    public static void payment() throws Exception {
        System.out.println("Do you want to make payement??(Enter yes/no)");
        String reply = sc.nextLine();
        File f = new File(_1Users_UI.user_id1);
        boolean flag = (f.exists()) ? true : f.mkdir();
        if (reply.equals("yes")) {
            if (flag) {
                LocalDateTime currentdatetime = LocalDateTime.now();
                String CurrentDATETIME = currentdatetime.format(DateTimeFormatter.ISO_DATE_TIME);
                String temp[] = CurrentDATETIME.split("T");
                System.out.println(temp[0]);
                String result = temp[1].replace(":", "_");
                System.out.println(temp[1]);
                String filename = temp[0] + "[" + result + "]";
                new _4Bill_UI();
                FileWriter fw = new FileWriter(
                        "C:\\Java language\\java programs\\src\\JDBC\\" + _1Users_UI.user_id1 + "\\ " + filename
                                + ".txt");
                fw.write("your bill details \n");
                System.out.println("Displaying your cart");
                String print_Shopping_cart = "Select * from " + _1Users_UI.user_id1;
                PreparedStatement ps = con.prepareStatement(print_Shopping_cart);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    fw.write(
                            "------------------------------------------------------------------------------------- \n");
                    fw.write("id:" + rs.getInt(1) + "Product_id: " + rs.getInt(2) + " Subcategory: " + rs.getString(3)
                            + " Product: "
                            + rs.getString(4) + " Gender: " + rs.getString(5) + " Brand: " + rs.getString(6)
                            + " Size: " + rs.getString(7) + " Color: " + rs.getString(8) + " Prize: "
                            + rs.getDouble(9) + "\n");
                    fw.write(
                            "------------------------------------------------------------------------------------\n");
                }
                String amount = Integer.toString(total);
                fw.write("your total amount is " + amount +"\n");
                System.out.println("CHECK YOUR LUCK");
                Spin_the_wheel.main(null);
                String coupun = Spin_the_wheel.sts;
                if (coupun.equals("Brad's deal") || coupun.equals("60% off official")
                        || coupun.equals("Shopper Appraise")) {
                    total = (int) (total - (0.6 * total));
                } else if (coupun.equals("Backpage Frebbies") || coupun.equals("Mommy saves big")) {
                    total = (int) (total - (0.4 * total));
                } else if (coupun.equals("Flat 90% Off") || coupun.equals("Luxurious Frugal")) {
                    total = (int) (total - (0.9 * total));
                } else if (coupun.equals("INR 250 Off")) {
                    total = (int) (total - 250);
                } else if (coupun.equals("Flat 70% off (Fastrack)") || coupun.equals("Global15")) {
                    total = (int) (total - (0.7 * total));
                } else if (coupun.equals("Flat 30") || coupun.equals("Fare Borrow")) {
                    total = (int) (total -(0.3*total));
                }
                fw.write("Applied coupun is "+ coupun+"\n");
                fw.write("-----------------------------\n");
                String amount1 = Integer.toString(total);
                fw.write("your final amount is " + amount1);

                fw.close();
            } else {
                System.out.println("File is not created");
            }
        } else {
            System.out.println("Thank you");
        }
    }
}
