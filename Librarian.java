package library;
import java.util.Scanner;
import java.util.ArrayList;
public class Librarian extends Users{
    private Librarian(LibrarianBuilder builder){
        super(builder);
    }

    public static class LibrarianBuilder extends Users.Builder<Librarian.LibrarianBuilder>{
        //add more attrs here
        private String address;

        @Override
        public Librarian build(){
            return new Librarian(this);
        }
        @Override
        protected Librarian.LibrarianBuilder self(){
            return this;
        }

        public Librarian.LibrarianBuilder address(String s){
            this.address = s;
            return self();
        }
    }

    public void RenewItem(int book_id, ArrayList<Books> BooksList, int type) {
        for (Books b : BooksList) {
            if (b.getBook_id() == book_id) {
                if (type == 1) {
                    b.IncreaseQuantity();
                } else {
                    b.DecreaseQuantity();
                }
                break;
            }
        }
    }
    public boolean CheckOutItem(int book_id, Borrower borrower, ArrayList<Books> bookList, Loan Current_Loan, ArrayList<Loan> LoanList) {
        if (borrower.GetFineStatus() == true) {
            System.out.println("You must pay the fine before you can borrow another book.");
        } else {
            Books loaned_book;
            for (Books b : bookList) { ////??????
                if (b.getBook_id() == book_id) {
                    RenewItem(book_id, bookList, 2);
                    Current_Loan.SetaBook(b);
                    Current_Loan.SetaBorrower(borrower);
                    borrower.AddLoanInfo(Current_Loan);
                    LoanList.add(Current_Loan);
                    dbConnectivity db = new dbConnectivity();
                    return (db.AddNewLoan(Current_Loan));
                }
            }
        }
        return false;
    }

    public void AddBook(ArrayList<Books> BooksList , String NewType, String NewAuthor , String NewTitle , int quantity) {

        Books LastBook = BooksList.get(BooksList.size()-1);
        int book_id = LastBook.getBook_id();
        book_id += 1;

        Books NewBook = new Books(book_id,NewType, NewTitle, NewAuthor, NewSubject, quantity);
        dbConnectivity db = new dbConnectivity();
        AddBooks.AddNewBook(book_id, NewType, NewTitle, NewAuthor, NewSubject, quantity);
        BooksList.add(NewBook);

    }

    public boolean DeleteBook(ArrayList<Books> BooksList, int book_id) {

        boolean deleted=false;
        Books ToDelete=new Books();
        dbConnectivity db = new dbConnectivity();
        boolean result =DeleteBooks.DeleteABook(book_id);
        if(result == true) {
            for (Books B : BooksList) {
                if (B.getBook_id() == book_id) {

                    ToDelete=B;
                    break;
                }
            }

            BooksList.remove(ToDelete);
            deleted=true;
        }
        return deleted;
    }

    public void ChangeInfo(ArrayList<Books> BooksList, int book_id , int command , String NewInfo , int Quantity) {

        System.out.print("Enter the id of the book you want to update");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        for (Books b : BooksList) {
            if (b.getBook_id() == id) {

                System.out.print("Press \n 1.to change title \n 2. to change author \n 3.to change subject \n 4.to change quantity");

                int cmd = input.nextInt();
                if (cmd == 1) {
                    System.out.print("Enter the new title ");
                    String Title = input.next();
                    b.setTitle(NewInfo);
                } else if (cmd == 2) {
                    System.out.print("Enter the new type ");
                    String NewType = input.next();
                    b.setAuthor(NewInfo);

                } else if (cmd == 3) {
                    System.out.print("Enter the new  author name ");
                    String A_name = input.next();
                    b.setAuthor(NewInfo);
                } else if (cmd == 4) {

                    System.out.print("Enter the new  subject ");
                    String subject = input.next();
                    b.setSubject(NewInfo);
                } else if (cmd == 5) {
                    System.out.print("Enter the new  quantity  ");
                    int quantity = input.nextInt();
                    b.setQuantity(Quantity);

                } else {
                    System.out.print("You pressed invalid key ! Can't perform any operation ");

                }
            }

        }

    }}
