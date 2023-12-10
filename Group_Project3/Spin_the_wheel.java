package JDBC.src.Group_Project3;


import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class Spin_the_wheel
{
    public static String sts = null;
    public static void main(String[] args) throws Exception {
        FileReader fr1=new FileReader("D:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
        BufferedReader bf1=new BufferedReader(fr1);
        String line=bf1.readLine();
        int count=0;
        while (line!=null) 
        {
            count++;
            line=bf1.readLine();    
        }       
        int min=1;
        int num=0;
        int max=count;
        int range=max-min+1;
        for (int i = 1; i <= max; i++) 
        {
            num=(int)(Math.random()*range)+min;
                
        }
        FileReader fr2=new FileReader("D:\\Java language\\java programs\\src\\JDBC\\src\\Group_Project3\\COUPONS.txt");
        BufferedReader bf2=new BufferedReader(fr2);
        String line2;
        int  temp=0;
        
        
        while (temp!=num) {
            line2 = bf2.readLine();
            temp++;
            sts=line2;
        }
        if(sts.equals(""))
        JOptionPane.showMessageDialog(Shopping_template1.frame, "Unforunately you won nothing");
        else{
            ImageIcon party_emoji = new ImageIcon("D:\\Java language\\java programs\\src\\JDBC\\src\\Party_Face_Emoji.png");
            JOptionPane.showMessageDialog(Shopping_template1.frame,party_emoji);
        }
        bf1.close();
        bf2.close();
    }
}
