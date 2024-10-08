package JDBC.src.Group_Project3;


import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class _3Cart_UI {
    static Scanner sc = new Scanner(System.in);

    static Connection con = null;
    static ArrayList<Integer> ids;

    public _3Cart_UI() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234");
        ids = new ArrayList<>();
    }

    static void filter(String category, String Gender) throws Exception {

        boolean flag = true;
        while (flag != false) {
            System.out.println("How do you want to filter the products or sort products");
            System.out.println("1)By subcategories");
            System.out.println("2)By prize");
            System.out.println("3)By Brand");
            System.out.println("4)By Size");
            System.out.println("5)By color");
            System.out.println("6)To see cart");
            System.out.println("7)To go back");
            System.out.println("8)To logout");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    display_subcatgory(Gender, category);
                    break;
                case 2:
                    sort(category, "prize", Gender);
                    add_to_cart(_1Users_UI.user_id1, category);
                    break;
                case 3:
                    sort(category, "brand", Gender);
                    add_to_cart(_1Users_UI.user_id1, category);
                    break;
                case 4:
                    sort(category, "size", Gender);
                    add_to_cart(_1Users_UI.user_id1, category);
                    break;
                case 5:
                    sort(category, "color", Gender);
                    add_to_cart(_1Users_UI.user_id1, category);
                    break;
                case 6:
                    _4Bill_UI.display_cart();
                    flag = false;
                    break;
                case 7:
                    _2Shopping_Centre_UI.shopping_Center();
                    flag = false;
                    break;
                case 8:
                    flag = false;
                    break;
                default:
                    System.out.println("Enter a vlaid choice");
                    break;
            }
        }
    }

    public _3Cart_UI(String username, String category) throws Exception {
        int item_id = 0;
        String storedProcedure = null;
        boolean flag = true;
        System.out.println(ids);
        while (flag) {
            System.out.println("Choose the ID of the item you want to buy");
            item_id = sc.nextInt();
            if (ids.contains(item_id)) {
                flag = false;
            } else {
                System.out.println("Enter a valid id ");
            }
        }
        if (category.equals("Clothing")) {
            storedProcedure = " CALL insert_clothing(?,?) ";
        } else if (category.equals("Accessories")) {
            storedProcedure = " CALL insert_accessories(?,?); ";
        } else {
            storedProcedure = " CALL insert_footwear(?,?) ";
        }

        new _3Cart_UI();
        CallableStatement callableStatement = null;
        try {
            callableStatement = con.prepareCall(storedProcedure);
            callableStatement.setInt(2, item_id);
            callableStatement.setString(1, _1Users_UI.user_id1);
            callableStatement.execute();

            System.out.println("Item added to the cart successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
        }
    }

    public static void add_to_cart(String username, String category) throws Exception {
        System.out.println("Do you want to add an item to the cart?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        int choice2 = sc.nextInt();

        if (choice2 == 1) {
            new _3Cart_UI(username, category);
        } else {
            System.out.println("Continue shopping");
        }
    }

    public static void displaying_items_by_subcategory(String Category, String subcategores, String Gender)
            throws Exception {
        new _3Cart_UI();
        String subcategory_ouput = "Select *from " + Category + " where subcategory = ? and gender = ?";
        PreparedStatement ps = con.prepareStatement(subcategory_ouput);
        ps.setString(1, subcategores);
        ps.setString(2, Gender);
        printData2(ps);
        sc.nextLine();
    }

    public static void display_subcatgory(String gender, String Category) throws Exception {
        new _3Cart_UI();
        String show_sub = "select subcategory  from " + Category + " where gender=? group by subcategory";
        PreparedStatement ps = con.prepareStatement(show_sub);
        ps.setString(1, gender);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> subcat = new ArrayList<>();
        while (rs.next()) {
            subcat.add(rs.getString(1));
        }
        boolean mflag = true;
        while (mflag) {
            System.out.println(subcat);
            System.out.println("Enter the subcategory you want to choose ");
            String subcategory = sc.nextLine();
            boolean flag = true;
            for (int i = 0; i < subcat.size(); i++) {
                if (subcategory.equalsIgnoreCase(subcat.get(i))) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
            if (flag == true) {
                displaying_items_by_subcategory(Category, subcategory, gender);
                add_to_cart(_1Users_UI.user_id1, Category);
                mflag = false;
            } else {
                System.out.println("Enter subcategory again");
                mflag = true;
            }
        }
    }

    public static void sort(String Category, String attribute, String Gender) throws Exception {
        new _3Cart_UI();
        System.out.println("1) Top to bottom");
        System.out.println("2) Bottom to top ");
        int choice = sc.nextInt();
        String sort_by_attribute = (choice == 1)
                ? "SELECT * FROM " + Category + " WHERE gender = ? ORDER BY " + attribute
                : "SELECT * FROM " + Category + " WHERE gender = ? ORDER BY " + attribute + " DESC";
        PreparedStatement ps7 = con.prepareStatement(sort_by_attribute);
        ps7.setString(1, Gender);
        printData2(ps7);
        sc.nextLine();

        if (attribute.equals("prize")) {
            System.out.println("The ranges of prize are ");
            System.out.println("1)Below 500");
            System.out.println("3)Betn 500 and 2000");
            System.out.println("2)Above 5000");
            int range_choice = sc.nextInt();
            switch (range_choice) {
                case 1:
                    String print_by_range = "select * from " + Category
                            + " where gender = ? and prize < 500 ;";
                    PreparedStatement ps2 = con.prepareStatement(print_by_range);
                    ps2.setString(1, Gender);
                    printData2(ps2);
                    break;

                case 2:
                    String print_by_range2 = "select * from " + Category
                            + " where gender = ? and prize Between 500 and 2000;";
                    PreparedStatement ps3 = con.prepareStatement(print_by_range2);
                    ps3.setString(1, Gender);
                    printData2(ps3);
                    break;

                case 3:
                    String print_by_range3 = "select * from " + Category
                            + " where gender = ? and prize > 2000;";
                    PreparedStatement ps4 = con.prepareStatement(print_by_range3);
                    ps4.setString(1, Gender);
                    printData2(ps4);
                    break;

                default:
                    System.out.println("failed to enter a valid choice");
                    break;
            }
        } else {
            System.out.println("Which " + attribute + " you want to filter?");
            String valuesString = sc.nextLine();
            String to_filter_items = "SELECT * FROM " + Category + " WHERE " + attribute + " = ?";
            PreparedStatement preparedStatement1 = con.prepareStatement(to_filter_items);
            preparedStatement1.setString(1, valuesString); // Set the parameter value
            printData2(preparedStatement1);
        }
    }

    public static void printData2(PreparedStatement printing_Statement) throws Exception {
        new _3Cart_UI();
        ResultSet rs = printing_Statement.executeQuery();
        while (rs.next()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("id: %5d Subcategory: %-15s Product: %-20s Gender: %-10s Brand: %-15s Size: %-10s Color: %-10s Prize: %.2f%n",
            rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
            rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8));
    
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ids.add(rs.getInt(1));
            System.out.println();
        }
    }
}

