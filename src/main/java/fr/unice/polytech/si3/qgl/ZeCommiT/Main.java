package fr.unice.polytech.si3.qgl.ZeCommiT;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter json");
        String res = "";
        while((myObj.nextLine())!=null) {

            res += myObj.nextLine();  // Read user input

        }
        System.out.println("Json is: " + res);  // Output user input



    }
}
