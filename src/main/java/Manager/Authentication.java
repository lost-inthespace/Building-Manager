package Manager;

import java.util.Random;
import java.util.Scanner;

public class Authentication extends Helper{
    public static class login{
        String username;
        String password;
        public login() {
            this.username = "admin";
            this.password = "admin";
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Username: ");
            username = input.next();
            System.out.print("Enter Password: ");
            password = input.next();
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Login Successful");
            } else {
                System.out.println("Login Failed");
                System.exit(0);
            }
        }
    }

    public class otpCode {
        int authOTP;
        public int code (){
            Random rand = new Random();
            return authOTP = rand.nextInt(999999);
        }
        public boolean auth(int code){
            return code == authOTP;
        }
    }
}
