package library;
import java.util.Scanner;


public class UI {

        public static void main(String[] args) {
            UI ui = new UI();
            ui.myLogin();

        }
        public void myLogin() {
            login();
        }

        private void login() {

            Library LibObj = new Library ();
            dbConnectivity db = new dbConnectivity();


            Scanner scanner = new Scanner(System.in);//键盘输入
            System.out.println("Please enter your choice: 1 for Login, 2 for Register");
            int choice1 = scanner.nextInt();
            if (choice1 == 1){
//                登录
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Please enter your name:");
                String name = scanner2.nextLine();
                System.out.println("Please select your identity: 1 for Borrower, 2 for Librarian.");
                int choice = scanner.nextInt();
                System.out.println("Please enter your id:");
                int id = scanner.nextInt();
                System.out.println("Please enter your password:");
                int password = scanner.nextInt();

                if(choice == 2)
                {

                    LibrarianUI.Librarian(id , LibObj);

                }
                if(choice == 1)
                {

                    BorrowerUI.Borrower(id , LibObj);

                }

            }else if (choice1 == 2){
//                注册
                Scanner scanner3 = new Scanner(System.in);
                System.out.println("Please enter your name:");
                String name = scanner3.nextLine();
                System.out.println("Please enter your id:");
                int id = scanner3.nextInt();
                System.out.println("Please enter your gender:");
                String inputGender = scanner3.nextLine();
                char gender = inputGender.charAt(0);
                System.out.println("Please enter your telephone:");
                String tele = scanner3.nextLine();
                System.out.println("Please enter your address:");
                String address = scanner3.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner3.nextLine();

                boolean success =false;
                success = db.AddBorrower(id,name,gender,address,tele,password);
                if(success ==true ){
                    System.out.println("You have registered successfully!");
                }
                login();
            }

        }
    }
