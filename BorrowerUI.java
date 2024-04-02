package library;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowerUI {
    public static void Borrower(int id , Library Obj) {
        System.out.println("==================================");
        System.out.println("欢迎" + id + "登录图书管理系统");
        System.out.println("1.借出（借书）");
        System.out.println("2.还书");
        System.out.println("3.查看用户罚款");

        System.out.println("==================================");
        System.out.println("请输入您的选择:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("请输入userid：");
            int userid = scanner.nextInt();
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            Obj.LibrarianCheckOutItem(book_id, userid, id);
            System.out.println("Item Loaned");
        } else if (choice == 2) {
            System.out.println("请输入userid：");
            int userid = scanner.nextInt();
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            System.out.println("请输入return_date：");
            String returndate = scanner.nextLine();
            Obj.LibrarianCheckInItem(returndate, book_id, userid, id);
            System.out.println("Item Returned");
        } else if (choice == 3) {
            System.out.println("请输入userid：");
            int user_id = scanner.nextInt();
            Obj.LibrarianRecordFine(user_id, id);
            System.out.println("FineRecorded");

/////////////要返回UI类开始

        }
    }
}
