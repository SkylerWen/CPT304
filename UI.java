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
            System.out.println("请选择：1登录, 2注册");
            int choice1 = scanner.nextInt();
            if (choice1 == 1){
//                登录
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("请输入您的姓名：");
                String name = scanner2.nextLine();
                System.out.println("请选择您的身份：1表示Borrower, 2表示Librarian");
                int choice = scanner.nextInt();
                System.out.println("输入您的id:");
                int id = scanner.nextInt();
                System.out.println("输入您的password:");
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
                System.out.println("请输入您的姓名：");
                String name = scanner.nextLine();
                System.out.println("请输入您的id");
                int id = scanner.nextInt();
                System.out.println("请输入您的gender：");
                String inputGender = scanner.nextLine();
                char gender = inputGender.charAt(0);
                System.out.println("请输入您的telephone");
                String tele = scanner.nextLine();
                System.out.println("请输入您的address：");
                String address = scanner.nextLine();
                System.out.println("请输入您的password");
                String password = scanner.nextLine();

                boolean success =false;
                success = db.AddBorrower(id,name,gender,address,tele,password);
                if(success ==true ){
                    System.out.println("您已注册成功！");
                }
                login();
            }

        }
    }
