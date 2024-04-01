import java.util.Scanner;

public class Librarian extends User{
    private Librarian(LibrarianBuilder builder){
        super(builder);
    }

    public static class LibrarianBuilder extends User.Builder<Librarian.LibrarianBuilder>{
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

    public boolean CheckOutItem(int book_id, User borrower, ArrayList<Books> bookList, Loan Current_Loan, ArrayList<Loan> LoanList) {
        if (borrower.GetFineStatus() == true) {
            System.out.println("You must pay the fine before you can borrow another book.");
        } else {
            Books loaned_book;
            for (Books b : bookList) { ////??????
                if (b.GetBookId() == book_id) {
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

    }

    public void AddBook(ArrayList<Books> BooksList ,String NewAuthor ,String NewTitle ,String NewSubject , int quantity) {

        Books LastBook = BooksList.get(BooksList.size()-1);
        int book_id = LastBook.GetBookId();
        book_id += 1;

        Books NewBook = new Books(book_id, NewTitle, NewAuthor, NewSubject, quantity);
        dbConnectivity db = new dbConnectivity();
        db.AddNewBook(book_id, NewTitle, NewAuthor, NewSubject, quantity);
        BooksList.add(NewBook);

    }

    public boolean DeleteBook(ArrayList<Books> BooksList, int book_id) {

        boolean deleted=false;
        Books ToDelete=new Books();
        dbConnectivity db = new dbConnectivity();
        boolean result =db.DeleteABook(book_id);
        if(result == true) {
            for (Books B : BooksList) {
                if (B.GetBookId() == book_id) {

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
            if (b.GetBookId() == id) {

                System.out.print("Press \n 1.to change title \n 2. to change author \n 3.to change subject \n 4.to change quantity");

                int cmd = input.nextInt();
                if (cmd == 1) {
                    System.out.print("Enter the  new title ");
                    String Title = input.next();
                    b.SetTitle(NewInfo);
                } else if (cmd == 2) {
                    System.out.print("Enter the new  author name ");
                    String A_name = input.next();
                    b.SetAuthor(NewInfo);
                } else if (cmd == 3) {

                    System.out.print("Enter the new  subject ");
                    String subject = input.next();
                    b.SetSubject(NewInfo);
                } else if (cmd == 4) {
                    System.out.print("Enter the new  quantity  ");
                    int quantity = input.nextInt();
                    b.SetQuantity(Quantity);

                } else {
                    System.out.print("You pressed invalid key ! Can't perform any operation ");

                }
            }

        }

}
