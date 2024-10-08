package JDBC.src.Group_Project3;


import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class _1Users_UI {
    static Scanner sc = new Scanner(System.in);
    public static String user_id1;
    public static int wrong_count = 0;

    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Online_Shopping_System", "postgres", "1234")) {
            boolean flag1 = true;
            System.out.println();
            while (flag1) {
                try {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Enter the option you want to perform");
                    System.out.println("1) To Login");
                    System.out.println("2) To Create a new account");
                    int option = sc.nextInt();
                    sc.nextLine();

                    switch (option) {
                        case 1:

                            System.out.println("Enter the user id");
                            user_id1 = sc.nextLine();
                            System.out.println("Enter the password");
                            String password1 = sc.nextLine();
                            String sql = "select * from account_details where user_name = ? and password = ?";
                            PreparedStatement preparedStatement1 = con.prepareStatement(sql);
                            preparedStatement1.setString(1, user_id1);
                            preparedStatement1.setString(2, password1);
                            ResultSet rs = preparedStatement1.executeQuery();
                            boolean flag = false;
                            if (rs.next()) {
                                flag = true;
                            }
                            if (flag) {
                                System.out.println("Login success");
                                System.out.println(" ");
                                System.out.println("LOADING");
                                for (int i = 0; i < 10; i++) {
                                    System.out.print("*");
                                    Thread.sleep(1000);
                                }
                                System.out.println("");
                                _2Shopping_Centre_UI.shopping_Center();
                                flag1 = false;
                            } else {
                                System.out.println("Login Failed invalid User or password");
                                wrong_count++;
                                if (wrong_count > 2) {
                                    sc.nextLine();
                                    System.out.println("FORGOT PASSWORD");
                                    System.out.println("DO YOU WANT TO UPDATE PASSWORD (YES/NO)");
                                    String choice = sc.nextLine();
                                    if (choice.equalsIgnoreCase("yes")) {
                                        System.out.println("GIVE THE ANSWERS OF FOLWING QUESTIONS ");
                                        System.out.println("WHAT IS YOUR FAVOURITE COLOR");
                                        String answer1 = sc.nextLine();
                                        System.out.println("WHAT IS YOUR FAVOURITE DESTINATION");
                                        String answer2 = sc.nextLine();
                                        System.out.println("ENTER YOUR NEPHEW NAME");
                                        String answer3 = sc.nextLine();
                                        String check_ans = "Select question1, question2, question3 from account_details  where user_name = ? group by question1,question2,question3;";
                                        PreparedStatement ps = con.prepareStatement(check_ans);
                                        ps.setString(1, user_id1);
                                        ResultSet rs1 = ps.executeQuery();
                                        while (rs1.next()) {
                                            if (answer1.equalsIgnoreCase(rs1.getString(1))
                                                    && answer2.equalsIgnoreCase(rs1.getString(2))
                                                    && answer3.equalsIgnoreCase(rs1.getString(3))) {
                                                System.out.println("ENTER THE NEW PASSWORD");
                                                String new_pass = "";
                                                boolean check3 = true;
                                                do {
                                                    int k, upper = 0, lower = 0, digit = 0, space = 0, symbol = 0;

                                                    new_pass = sc.nextLine();
                                                    if (new_pass.length() >= 8 && new_pass.length() <= 15) {
                                                        for (k = 0; k < new_pass.length(); k++) {
                                                            if (new_pass.charAt(k) >= 'A'
                                                                    && new_pass.charAt(k) <= 'Z') {
                                                                upper = 1;
                                                            }
                                                            if (new_pass.charAt(k) >= 'a'
                                                                    && new_pass.charAt(k) <= 'z') {
                                                                lower = 1;
                                                            }
                                                            if (new_pass.charAt(k) >= '0'
                                                                    && new_pass.charAt(k) <= '9') {
                                                                digit = 1;
                                                            }
                                                            if (new_pass.charAt(k) == ' ') {
                                                                space = 1;
                                                            }
                                                        }
                                                    }
                                                    if (upper == 1 && lower == 1 && digit == 1 && space != 1) {
                                                        check3 = false;
                                                    }
                                                    if (check3 == true) {
                                                        System.out.println("Enter valid password ");
                                                    }

                                                } while (check3);
                                              
                                                    String update_password = "UPDATE account_details SET password = ? where user_name =?";
                                                    PreparedStatement pst = con.prepareStatement(update_password);
                                                    pst.setString(1, new_pass);
                                                    pst.setString(2,user_id1);
        
                                                    if(pst.executeUpdate()>0){

                                                        System.out.println("PASSWORD UPDATE SUCCESFULLY");
                                                    }
                                                
                                            }
                                        }
                                    } else {
                                        System.out
                                                .println("YOU HAVE ENTERED WRONG ANSWER SO FAILED TO CHANGE PASSWORD");
                                    }
                                }
                            }
                            System.out.println("");

                            break;

                        case 2:
                            String insert_values = "INSERT INTO account_details (user_name, password, email, phone_no, city, state, pincode,dob,question1,question2,question3) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                            try (PreparedStatement preparedStatement = con.prepareStatement(insert_values)) {
                                boolean flag2 = true;
                                while (flag2) {
                                    System.out.println("Enter the phone number ");
                                    Long phoneno = sc.nextLong();
                                    String temp = Long.toString(phoneno);
                                    sc.nextLine();
                                    if (temp.length() == 10) {
                                        System.out.println("Enter the email");
                                        boolean emailValid = false;
                                        String email = "";
                                        while (!emailValid) {

                                            email = sc.nextLine();

                                            if (email == null || email.isEmpty()) {
                                                System.out.println("Invalid email address: " + email);
                                                System.out.println("Enter Again.");
                                            } else {
                                                int atIndex = email.indexOf('@');
                                                int dotIndex = email.lastIndexOf('.');

                                                if (atIndex > 0 && dotIndex > atIndex
                                                        && dotIndex < email.length() - 1) {
                                                    emailValid = true;
                                                } else {
                                                    System.out.println("Invalid email address: " + email);
                                                    System.out.println("Enter Again.");
                                                }
                                            }
                                        }

                                        System.out.println("Enter the city");
                                        String city = sc.nextLine();
                                        System.out.println("Enter the state");
                                        String state = sc.nextLine();
                                        System.out.println("Enter the pincode");
                                        long pincode = sc.nextLong();
                                        sc.nextLine();
                                        System.out.print("Enter your date of birth (yyyy-MM-dd): ");
                                        String dobStr = sc.nextLine();
                                        Date dob = null;
                                        System.out.println("Enter your favourite color (Do not enter statement)");
                                        String question1 = sc.nextLine();
                                        System.out.println("Enter your favourite destination (Do not enter statement)");
                                        String question2 = sc.nextLine();
                                        System.out.println("Enter your nephew name or NA (Do not enter statement)");
                                        String question3 = sc.nextLine();
                                        try {
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            dob = dateFormat.parse(dobStr);
                                        } catch (ParseException e) {
                                            System.err.println("Invalid date format. Please use yyyy-MM-dd.");
                                            sc.close();
                                            return;
                                        }

                                        System.out.println("Enter the account username ");
                                        user_id1 = sc.nextLine();

                                        System.out.println("Enter the password ");
                                        String pass = "";
                                        boolean check3 = true;
                                        do {
                                            int k, upper = 0, lower = 0, digit = 0, space = 0, symbol = 0;

                                            pass = sc.nextLine();
                                            if (pass.length() >= 8 && pass.length() <= 15) {
                                                for (k = 0; k < pass.length(); k++) {
                                                    if (pass.charAt(k) >= 'A' && pass.charAt(k) <= 'Z') {
                                                        upper = 1;
                                                    }
                                                    if (pass.charAt(k) >= 'a' && pass.charAt(k) <= 'z') {
                                                        lower = 1;
                                                    }
                                                    if (pass.charAt(k) >= '!' && pass.charAt(k) <= '+') {
                                                    symbol = 1;
                                                    }
                                                    if (pass.charAt(k) >= '0' && pass.charAt(k) <= '9') {
                                                        digit = 1;
                                                    }
                                                    if (pass.charAt(k) == ' ') {
                                                        space = 1;
                                                    }
                                                }
                                            }
                                            if (upper == 1 && lower == 1 && digit == 1 && space != 1) {
                                                check3 = false;
                                            }
                                            if (check3 == true) {
                                                System.out.println("Enter valid password ");
                                            }

                                        } while (check3);

                                        if (check3 == false) {
                                            preparedStatement.setString(1, user_id1);
                                            preparedStatement.setString(2, pass);
                                            preparedStatement.setString(3, email);
                                            preparedStatement.setLong(4, phoneno);
                                            preparedStatement.setString(5, city);
                                            preparedStatement.setString(6, state);
                                            preparedStatement.setLong(7, pincode);
                                            preparedStatement.setDate(8, new java.sql.Date(dob.getTime()));
                                            preparedStatement.setString(9, question1);
                                            preparedStatement.setString(10, question2);
                                            preparedStatement.setString(11, question3);
                                            if (preparedStatement.executeUpdate() > 0) {
                                                System.out.println("data is inputted succesfully");
                                                String modifiedUsername = user_id1.replaceAll("[^a-zA-Z0-9_]", "_");

                                                String createUsertable = "CREATE TABLE " + modifiedUsername
                                                        + " (cart_id SERIAL PRIMARY KEY, product_id INT, subcategory VARCHAR(255), product  VARCHAR(255), gender VARCHAR(10), brand VARCHAR(255), size VARCHAR(10), color  VARCHAR(255), price DECIMAL(10, 2));";

                                                PreparedStatement ps2 = con.prepareStatement(createUsertable);
                                                ps2.execute();

                                                flag2 = false;
                                            }
                                        }

                                    } else {
                                        System.out.println("Enter the valid phone number ");
                                        flag = true;
                                    }

                                }
                                System.out.println("Your account is created");
                                System.out.println(" ");
                                System.out.println("LOADING");
                                for (int i = 0; i < 10; i++) {
                                    System.out.print("*");
                                    Thread.sleep(1000);
                                }
                                System.out.println("");
                                _2Shopping_Centre_UI.shopping_Center();
                                flag1 = false;
                            } catch (SQLException e) {
                                System.out.println("Error in database operation: " + e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("Enter a valid choice ");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter valid input");
                    flag1 = false;
                } catch (Exception p) {
                    p.printStackTrace();
                    System.out.println("Such user does not exist");
                    flag1 = true;
                }
            }
        } catch (

        SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } catch (NoSuchElementException n) {
            System.out.println("Thank you and please visit again");
        } catch (Exception p) {
            System.out.println("Thank you please visit again");
        }
    }
}