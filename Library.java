package library;
import java.util.*;

public class Library {

    public static ArrayList<Users> UsersList;
    public static ArrayList<Books> BooksList;
    public static ArrayList<Librarian> LibrarianList;
    public static ArrayList<Loan> LoanList;
    Library()
    {
        dbConnectivity db = new dbConnectivity();
        UsersList = db.LoadAllBorrowers();
        BooksList = db.LoadAllBooks();
        LibrarianList = db.LoadAllLibrarians();
        LoanList = db.LoadLoanList();

        for (int i=0; i<UsersList.size() ; i++)
        {
            System.out.println("\n");

        }
        for (int i=0; i<BooksList.size() ; i++)
        {
            Books B= BooksList.get(i);
            B.PrintInformation();
            System.out.println("\n");

        }
        for (int i=0; i<LibrarianList.size(); i++)
        {
            System.out.println("\n");

        }
        for (int i=0; i<LoanList.size() ; i++)
        {
            Loan L= LoanList.get(i);
            L.PrintLoanInfo();
            System.out.println("\n");
        }
    }

    public Loan getLoanID(int book_id, int user_id){
        Loan L = new Loan();
        for (int counter=LoanList.size()-1 ; counter >=0; counter--){
            L = LoanList.get(counter);
            if ((L.GetaBookId() == book_id) && (L.GetaBorrowerId() == user_id) && !L.GetStatus()) {return L;}
        }
        return null;
    }
    public Loan getFinedLoanByUserID(int user_id){
        Loan L = new Loan();
        for (int counter=LoanList.size()-1 ; counter >=0; counter--){
            L = LoanList.get(counter);
            if (L.GetaBorrowerId() == user_id && L.GetFineStatus().equals("Fined")) {return L;}
        }
        return null;
    }

    public void RenewBook(int book_id, int type) {
        if (type == 1) {
            getBookByID(book_id).IncreaseQuantity();
        } else {
            getBookByID(book_id).DecreaseQuantity();
        }
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String LibrarianCheckLoanofUser (int user_id, int clerk_id)
    {
        String Str="";
        for(int i=0; i< LibrarianList.size() ; i++)
        {
            Librarian L = LibrarianList.get(i);
            if(clerk_id == L.getId())
            {

                Str = Str + L.viewInformation(LoanList, user_id);
                break;

            }
        }
        return Str;
    }



    ArrayList <Books>LibrarianSearchBookbyTitle(String title, int clerk_id)
    {
        ArrayList <Books> SelectedBooks = new ArrayList <>();

        for (int i=0; i < LibrarianList.size() ; i++)
        {

            Librarian L=LibrarianList.get(i);
            if(L.getId()==clerk_id)
            {
                SelectedBooks= SearchBooks.SearchBookbyTitle(title);
            }


        }

        return SelectedBooks ;

    }



    ArrayList <Books>LibrarianSearchBookbyAuthor(String author, int clerk_id)
    {
        ArrayList <Books> SelectedBooks = new ArrayList <>();

        for (int i=0; i < LibrarianList.size() ; i++)
        {

            Librarian L=LibrarianList.get(i);
            if(L.getId()==clerk_id)
            {
                SelectedBooks= SearchBooks.SearchBookbyAuthor(author);
            }


        }

        return SelectedBooks ;

    }

    ArrayList <Books>LibrarianSearchBookbySubject(String subject, int clerk_id)
    {
        ArrayList <Books> SelectedBooks = new ArrayList <>();

        for (int i=0; i < LibrarianList.size() ; i++)
        {

            Librarian L=LibrarianList.get(i);
            if(L.getId()==clerk_id)
            {
                SelectedBooks= SearchBooks.SearchBookbySubject(subject);
            }


        }

        return SelectedBooks ;

    }


    boolean  AddNewBorrowerLibrarian(String borrower_name ,char gender  ,String tel_num ,String address, int clerk_id)
    {
        boolean result = false;
        for(int i=0; i < LibrarianList.size() ; i++)
        {
            Librarian L=LibrarianList.get(i);
            if(L.getId()== clerk_id)
            {
         dbConnectivity dbConnectivity = new dbConnectivity();
                result = dbConnectivity.AddBorrower(clerk_id, borrower_name, gender, tel_num, address);
                result = true;
                break;

            }


        }
        return result;

    }



    Boolean LibrarianUpdatingInfo( String Info , int choice, int clerk_id, int user_id)
    {


        boolean result=false;
        for(int i=0; i < LibrarianList.size() ; i++)
        {
            Librarian L = LibrarianList.get(i);
            if(L.getId()== clerk_id)
            {
                result= L.UpdatePerosnalInformation(UsersList , Info , choice, user_id);

                break;

            }


        }
        return result;


    }



    int getNewLoanID(){
        int size=LoanList.size();
        Loan L=LoanList.get(size-1);
        int index=L.GetLoanId() + 1;
        return index;
    }
    public Books getBookByID(int book_id){
        for (Books b : BooksList) {
            if (b.getBook_id() == book_id) {
                return b;
            }
        }
        return null;
    }
    public Users getUserByID(int user_id){
        for (Users u : UsersList) {
            if (u.getId() == user_id) {
                return u;
            }
        }
        return null;
    }
    void LibrarianRecordFine(int user_id ,int clerk_id) {

        for(int i=0; i < LibrarianList.size() ; i++)
        {
            Librarian L=LibrarianList.get(i);
            if(L.getId()== clerk_id)
            {
                Loan loan = new Loan();
                ContextLoan context = new ContextLoan(new NothingState(),loan);
                context.payFine(user_id);
            }


        }



    }
    void LibrarianCheckInItem(String ret_date, int book_id, int borrower_id, int lib_id) {

        for (int i = 0; i < LibrarianList.size(); i++) {

            Librarian L= LibrarianList.get(i);
            if (L.getId() == lib_id) {
                for (int j = 0; j < UsersList.size(); j++) {
                    Users U = UsersList.get(j);
                    if (borrower_id == U.getId()) {
                        Loan loan = new Loan();
                        ContextLoan context = new ContextLoan(new NothingState(),loan);
                        context.returnBook(ret_date,book_id,borrower_id);

                    }

                }

            }

        }
    }
    void LibrarianCheckOutItem( int book_id, int borrower_id, int lib_id) {

        for (int i = 0; i < LibrarianList.size(); i++) {

            Librarian L1 = LibrarianList.get(i);
            if (L1.getId() == lib_id) {
                for (int j = 0; j < UsersList.size(); j++) {
                    Users U = UsersList.get(j);
                    if (borrower_id == U.getId()) {
                        Loan loan = new Loan();
                        ContextLoan context = new ContextLoan(new NothingState(),loan);
                        context.borrowBook(book_id,borrower_id);

                    }

                }

            }

        }
    }


    void LibrarianRenewItem(int book_id ,int option ,int lib_id)
    {

        for (int i = 0; i < LibrarianList.size(); i++) {

            Librarian L = LibrarianList.get(i);
            if (L.getId() == lib_id) {
                L.RenewItem(book_id, BooksList, option);
            }

        }

    }

    void LibrarianAddNewBook( String NewAuthor ,String NewTitle ,String NewSubject , int quantity , int lib_id)
    {
        for (int i = 0; i < LibrarianList.size(); i++) {

            Librarian L = LibrarianList.get(i);
            if (L.getId() == lib_id) {
                L.AddBook(BooksList, NewAuthor, NewTitle, NewSubject, quantity);
            }

        }


    }


    boolean LibrarianDeleteBook(int book_id , int lib_id)
    {
        boolean result=false;

        for (int i = 0; i < LibrarianList.size(); i++) {

            Librarian L = LibrarianList.get(i);
            if (L.getId() == lib_id) {
                result= L.DeleteBook(BooksList ,book_id);
            }

        }

        return result;
    }



    void LibrarianUpdateBookInfo(int book_id , int lib_id , String NewInfo, int newquantity, int command)
    {


        for (int i = 0; i < LibrarianList.size(); i++) {

            Librarian L = LibrarianList.get(i);
            if (L.getId() == lib_id) {
                L.ChangeInfo(BooksList, book_id,command, NewInfo, newquantity);
            }

        }
    }


}
