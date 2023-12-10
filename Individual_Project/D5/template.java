package JDBC.src.Individual_Project.D5;
import java.util.Scanner;
 class template1 {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        if(sc.nextInt()>10){
            int number = sc.nextInt();
            System.out.println(number);
            System.out.println("error");
        }
        if(sc.nextInt()>10){
            System.out.println("error");
        }
    }
}
