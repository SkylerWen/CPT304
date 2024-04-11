package library;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianUI {

    public static void Librarian(int id , Library Obj) {

        System.out.println("==================================");
        System.out.println("Welcome" + id + "to the Library Management System");
        System.out.println("1. Add new books to the library");
        System.out.println("2. Delete books");
        System.out.println("3. Update book information");
        System.out.println("==================================");
        System.out.println("Please enter your choice:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice ==1)
        {

            System.out.println("Please enter NewType:");
            String NewType = scanner.nextLine();
            System.out.println("Please enter NewAuthor：");
            String NewAuthor = scanner.nextLine();
            System.out.println("Please enter NewTitle：");
            String NewTitle = scanner.nextLine();
            System.out.println("Please enter NewSubject：");
            String NewSubject = scanner.nextLine();
            System.out.println("Please enter quantity：");
            int quantity = scanner.nextInt();
            Obj.LibrarianAddNewBook(NewType,NewAuthor , NewTitle , NewSubject , quantity , id);
            // String NewType, String NewAuthor ,String NewTitle ,String NewSubject , int quantity , int lib_id
            System.out.println("New Book Added Successfully");
        }

        else if(choice ==2)
        {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Please enter your book_id：");
            int book_id = scanner1.nextInt();
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
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("==================================");
            System.out.println("1.Update Title");
            System.out.println("2.Update Author");
            System.out.println("3.Update Subject");
            System.out.println("4.Update quantity");
            System.out.println("==================================");
            System.out.println("Please enter your choice:");
            int option = scanner2.nextInt();
            System.out.println("Please enter your bookID：");
            int book_id = scanner2.nextInt();
            System.out.println("Please enter the updated Info:");
            String NewInfo = scanner2.nextLine();
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
                System.out.println("Please enter quantity:");
                int quantity = scanner.nextInt();
                Obj.LibrarianUpdateBookInfo(book_id , id ," ",quantity, option );
            }
            System.out.println("Updated Successfully");
        }



    }


}




