package library;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianUI {

    public static void Librarian(int id , Library Obj) {

        System.out.println("==================================");
        System.out.println("欢迎" + id + "登录图书管理系统");
        System.out.println("1.添加新书籍到图书馆");
        System.out.println("2.删除书籍");
        System.out.println("3.更新书籍信息");
        System.out.println("==================================");
        System.out.println("请输入您的选择:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice ==1)
        {
            System.out.println("请输入NewType：");
            String NewType = scanner.nextLine();
            System.out.println("请输入NewAuthor：");
            String NewAuthor = scanner.nextLine();
            System.out.println("请输入NewTitle：");
            String NewTitle = scanner.nextLine();
            System.out.println("请输入NewSubject：");
            String NewSubject = scanner.nextLine();
            System.out.println("请输入quantity：");
            int quantity = scanner.nextInt();
            Obj.LibrarianAddNewBook(NewType,NewAuthor , NewTitle , NewSubject , quantity , id);
            // String NewType, String NewAuthor ,String NewTitle ,String NewSubject , int quantity , int lib_id
            System.out.println("New Book Added Successfully");
        }

        else if(choice ==2)
        {
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            boolean result= Obj.LibrarianDeleteBook(book_id, id);
            if(result ==true )
            {
                System.out.println("BookDeleted");
            }
            else
            {
                System.out.println("Command Failed");
            }
        }

        else if (choice ==3)
        {
            System.out.println("==================================");
            System.out.println("1.更新Title");
            System.out.println("2.更新Author");
            System.out.println("3.更新Subject");
            System.out.println("4.更新quantity");
            System.out.println("==================================");
            System.out.println("请输入您的选择:");
            int option = scanner.nextInt();
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            System.out.println("请输入更新后的Info：");
            String NewInfo = scanner.nextLine();
            if(option == 2)
            {
                Obj.LibrarianUpdateBookInfo(book_id , id ,NewInfo ,0,option);

            }
            else if(option == 1)
            {
                Obj.LibrarianUpdateBookInfo(book_id , id ,NewInfo ,0, option);
            }

            else if(option == 3)
            {
                Obj.LibrarianUpdateBookInfo(book_id , id ,NewInfo ,0,option);
            }

            else if(option == 4)
            {
                System.out.println("请输入quantity：");
                int quantity = scanner.nextInt();
                Obj.LibrarianUpdateBookInfo(book_id , id ," ",quantity, option );
            }
            System.out.println("Updated Successfully");
        }


    }


}




