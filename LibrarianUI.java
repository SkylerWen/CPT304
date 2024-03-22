package library;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianUI {
    int id;
    Library Obj;
    public LibrarianUI(int  id , Library Obj) {
        Librarian();
        this.id=id;
        this.Obj=Obj;
    }
    public static void main(String args[]) {

    }

    private void Librarian(){

        System.out.println("==================================");
        System.out.println("欢迎" + id + "登录图书管理系统");
        System.out.println("1.按书籍标题搜索书籍");
        System.out.println("2.按作者名搜索书籍");
        System.out.println("3.按书籍的主题进行搜索");
        System.out.println("4.查询用户的借书记录");
        System.out.println("5.添加新borrower");
        System.out.println("6.更新用户信息");
        System.out.println("7.借出（借书）");
        System.out.println("8.还书");
        System.out.println("9.记录用户罚款");
        System.out.println("10.更新书籍库存数量");
        System.out.println("11.添加新书籍到图书馆");
        System.out.println("12.删除书籍");
        System.out.println("13.更新书籍信息");
        System.out.println("==================================");
        System.out.println("请输入您的选择:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("请输入书籍标题：");
            String title = scanner.nextLine();
            String PrintingMessage = "No Record found";
            ArrayList<Books> SelectedBooks = Obj.LibrarianSearchBookbyTitle(title, id);
            if (SelectedBooks.isEmpty() == false) {
                PrintingMessage = " ";
                for (int counter = 0; counter < SelectedBooks.size(); counter++) {
                    Books B = SelectedBooks.get(counter);
                    PrintingMessage = PrintingMessage + "   " + B.GetBookId() + "  " + B.GetTitle() + "    " + B.GetAuthor() + "  "
                            + B.GetSubject() + "\n";
                }
            }
            System.out.println(PrintingMessage);
        }

        else if(choice == 2)
        {
            System.out.println("请输入作者名：");
            String author = scanner.nextLine();
            String PrintingMessage = "No Record found";
            ArrayList<Books> SelectedBooks = Obj.LibrarianSearchBookbyAuthor(author, id);
            if (SelectedBooks.isEmpty() == false) {
                PrintingMessage = " ";
                for (int counter = 0; counter < SelectedBooks.size(); counter++) {
                    Books B = SelectedBooks.get(counter);

                    PrintingMessage = PrintingMessage + "   " + B.GetBookId() + "  " + B.GetTitle() + "    " + B.GetAuthor() + "  "
                            + B.GetSubject() + "\n";
                }
            }
            System.out.println(PrintingMessage);
        }

        else if(choice == 3)
        {
            System.out.println("请输入书籍的主题：");
            String subject = scanner.nextLine();
            String PrintingMessage = "No Record found";
            ArrayList<Books> SelectedBooks = Obj.LibrarianSearchBookbySubject(subject, id);
            if (SelectedBooks.isEmpty() == false) {
                PrintingMessage = " ";
                for (int counter = 0; counter < SelectedBooks.size(); counter++) {
                    Books B = SelectedBooks.get(counter);

                    PrintingMessage = PrintingMessage + "   " + B.GetBookId() + "  " + B.GetTitle() + "    " + B.GetAuthor() + "  "
                            + B.GetSubject() + "\n";
                }
            }
            System.out.println(PrintingMessage);
        }
        else if(choice ==4)
        {
            System.out.println("请输入userid：");
            int userid = scanner.nextInt();
            String PrintingMessage=Obj.LibrarianCheckLoanofUser(userid, id);
            System.out.println(PrintingMessage);
        }

        else if(choice ==5)
        {
            System.out.println("请输入borrower_name：");
            String borrower_name = scanner.nextLine();
            System.out.println("请输入gender:男 or 女");
            String input = scanner.nextLine();
            char gender = input.charAt(0);
            System.out.println("请输入tel_num：");
            String tel_num = scanner.nextLine();
            System.out.println("请输入address：");
            String address = scanner.nextLine();

            boolean result= Obj.AddNewBorrowerLibrarian(borrower_name , gender  , tel_num , address, id);
            if(result ==true )
            {
                System.out.println("New User added");
            }
            else
            {
                System.out.println("Command Failed");
            }
        }

        else if (choice == 6)
        {

            System.out.println("请输入userid：");
            int user_id = scanner.nextInt();
            System.out.println("==================================");
            System.out.println("1.更新Name");
            System.out.println("2.更新Gender");
            System.out.println("==================================");
            System.out.println("请输入您的选择:");
            int Change = scanner.nextInt();
            System.out.println("请输入Info：");
            String Info = scanner.nextLine();
            boolean result=false;
            if( Change == 1 )
            {
                result= Obj.LibrarianUpdatingInfo(Info, Change, id, user_id);
            }
            else if(Change == 2)
            {
                result=Obj.LibrarianUpdatingInfo(Info, Change, id, user_id);

            }
            if(result==false)
            {
                System.out.println("Command Failed");
            }
            else
            {
                System.out.println("Updated Successfully");
            }
        }
        else if (choice ==7)
        {
            System.out.println("请输入userid：");
            int userid = scanner.nextInt();
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            Obj.LibrarianCheckOutItem( book_id , userid, id);
            System.out.println("Item Loaned");
        }
        else if (choice ==8)
        {
            System.out.println("请输入userid：");
            int userid = scanner.nextInt();
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            System.out.println("请输入return_date：");
            String returndate = scanner.nextLine();
            Obj.LibrarianCheckInItem(returndate, book_id, userid, id);
            System.out.println("Item Returned");
        }
        else if(choice ==9)
        {
            System.out.println("请输入userid：");
            int user_id = scanner.nextInt();
            Obj.LibrarianRecordFine(user_id , id);
            System.out.println("FineRecorded");
        }
        else if (choice ==10)
        {
            System.out.println("请输入book_id：");
            int book_id = scanner.nextInt();
            System.out.println("==================================");
            System.out.println("1.增加库存");
            System.out.println("2.减少库存？");///////////////////////////////////////////////////////?????
            System.out.println("==================================");
            System.out.println("请输入您的选择:");
            int option = scanner.nextInt();
            Obj.LibrarianRenewItem(book_id , option , id);
            System.out.println("Quantity Updated");
        }
        else if(choice ==11)
        {
            System.out.println("请输入New_Author：");
            String NewAuthor = scanner.nextLine();
            System.out.println("请输入NewTitle：");
            String NewTitle = scanner.nextLine();
            System.out.println("请输入NewSubject：");
            String NewSubject = scanner.nextLine();
            System.out.println("请输入quantity：");
            int quantity = scanner.nextInt();
            Obj.LibrarianAddNewBook(NewAuthor , NewTitle , NewSubject , quantity , id);
            System.out.println("New Book Added Successfully");
        }
        else if(choice ==12)
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

        else if (choice ==13)
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
