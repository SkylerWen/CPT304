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
            boolean flag =false;

            Scanner scanner = new Scanner(System.in);//键盘输入
            System.out.println("请输入您的姓名：");
            String name = scanner.nextLine();
            System.out.println("请选择您的身份：2表示Librarian，1表示Borrower，0表示Clerk");
            int choice = scanner.nextInt();
            System.out.println("输入您的id:");
            int id = scanner.nextInt();
//            System.out.println("输入您的性别:男 or 女");
//            String input = scanner.nextLine();
//            char gender = input.charAt(0);

            if(choice == 2)
            {
                flag=LibObj.IsLibrarianPresent(id);
            }
            else if (choice == 1)
            {
                flag=LibObj.IsBorrowerPresent(id);
            }
            else if (choice == 0)
            {
                flag=LibObj.IsClerkPresent(id);
            }

///////////////////////////此去其他ui类//////////////////////////////
            if(flag==true && choice == 2)
            {
                new LibrarianMenu(id , LibObj).setVisible(true);

            }
            if(flag==true && choice == 1)
            {
                new StudentMenuUI(id).setVisible(true);

            }
            if(flag==true && choice == 0)
            {
                new ClerkMenuUI(id, LibObj).setVisible(true);
            }


        }
    }
