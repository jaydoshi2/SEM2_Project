package JDBC.src.Group_Project3;


import java.util.*;

import java.sql.*;

public class _2Shopping_Centre_UI {

    static Scanner sc = new Scanner(System.in);

    public static void shopping_Center() throws Exception {
        System.out.println("Welcomsuhafjasdhfjksdhfjkasde to the Shopping Centre of Lj ");
        System.out.println("Enter the Gender dfsdklfj");
        String Gender = sc.nextLine();
        choosing_category_display(Gender);
    }

    public static void choosing_category_display(String Gender) throws Exception {
        boolean flag = true;
        while (flag) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose the CATEGORY1");
            System.out.println("1)Clothing");
            System.out.println("2)Accessories");
            System.out.println("3)Footwear");
            System.out.println("4)back to login page");
            System.out.println("5)straight to billing");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    new Clothing(Gender);
                    break;
                case 2:
                    new Accessories(Gender);
                    break;
                case 3:
                    new Footwear(Gender);
                    break;
                case 4:
                    _1Users_UI.main(null);
                    flag = false;
                    break;
                case 5:
                    _4Bill_UI.display_cart();
                    flag = false;
                    sc.close();
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        }
    }
}

interface select_category1 {
    void selecting_category2(String category1name, String gender);
}

class Category1 implements select_category1 {
    Scanner sc = new Scanner(System.in);
    Connection con = null;

    @Override
    public void selecting_category2(String category1, String Gender) {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres",
                    "1234");
            String selectiog_product = "select * from " + category1 + " where gender = ? ";
            PreparedStatement ps2 = con.prepareStatement(selectiog_product);
            ps2.setString(1, Gender);
            _3Cart_UI.printData2(ps2);
     
            System.out.println();
            System.out.println("Enter the choice");
            System.out.println("1) To Choose item");
            System.out.println("2) To go back");
            int choice1 = sc.nextInt();
            switch (choice1) {
                case 1:
                    System.out.println();
                    System.out.println("Enter the choice");
                    System.out.println("1)To filter items");
                    System.out.println("2)To buy item directly");
                    int choice2 = sc.nextInt();

                    sc.nextLine();
                    switch (choice2) {
                        case 1:
                            _3Cart_UI.filter(category1, Gender);
                            break;
                        case 2:
                            new _3Cart_UI(_1Users_UI.user_id1, category1);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    _2Shopping_Centre_UI.shopping_Center();
                    break;
                default:
                    break;
            }
        } catch (NoSuchElementException e) {
          System.out.println("thank you");
        }catch(Exception e1){

        }
    }
}

class Clothing extends Category1 {
    public Clothing(String gender) {
        super.selecting_category2("Clothing", gender);
    }
}

class Accessories extends Category1 {
    public Accessories(String gender) {
        super.selecting_category2("Accessories", gender);
    }
}

class Footwear extends Category1 {
    public Footwear(String gender) {
        super.selecting_category2("Footwear", gender);
    }
}
