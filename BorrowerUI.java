package library;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowerUI {
    public static void Borrower(int id , Library Obj) {
        System.out.println("==================================");
        System.out.println("Welcome" + id + "to the Library Management System");
        System.out.println("1. Check out (borrow books)");
        System.out.println("2.Return books");
        System.out.println("3.View user fines");

        System.out.println("==================================");
        System.out.println("Please enter your choice:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Please enter your userID：");
            int userid = scanner.nextInt();
            System.out.println("Please enter your bookID：");
            int book_id = scanner.nextInt();
            Obj.LibrarianCheckOutItem(book_id, userid, id);
            System.out.println("Item Loaned");
        } else if (choice == 2) {
            System.out.println("Please enter your userID：");
            int userid = scanner.nextInt();
            System.out.println("Please enter your bookID：");
            int book_id = scanner.nextInt();
            System.out.println("Please enter your return_date：");
            String returndate = scanner.nextLine();
            Obj.LibrarianCheckInItem(returndate, book_id, userid, id);
            System.out.println("Item Returned");
        } else if (choice == 3) {
            System.out.println("Please enter your userID：");
            int user_id = scanner.nextInt();
            Obj.LibrarianRecordFine(user_id, id);
            System.out.println("Fine Recorded");

/////////////要返回UI类开始

        }
    }
}
