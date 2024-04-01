package library;

import java.util.Date;

public class NoFineState implements LoanState {
    public void borrowBook(ContextLoan context, int book_id, int borrower_id){
        Library library = new Library();
        //get the borrower obj
        Users borrower = library.getUserByID(borrower_id);
        //get the book obj
        Books loaned_book = library.getBookByID(book_id);
        //get a new loan obj
        int index=library.getNewLoanID();
        Loan Current_Loan= new Loan(index);

        if(loaned_book != null){
            library.RenewBook(book_id, 2);
            //
            Current_Loan.SetaBook(loaned_book);
            Current_Loan.SetaBorrower(borrower);
            //
            //borrower.AddLoanInfo(Current_Loan);
            library.LoanList.add(Current_Loan); //
            context.setLoan(Current_Loan);

            dbConnectivity db = new dbConnectivity ();
            db.AddNewLoan(Current_Loan);
        }
        else{
            System.out.println("no such a book");
        }

        context.setState(new BorrowedState());
    }
    public void leave(ContextLoan context){
        context.setState(new NothingState());
    }
    //================================================
    public void returnBook(ContextLoan context, String ret_date ,int book_id, int borrower_id){
        System.out.println("you haven't borrow any book");
    }
    public void renewDueTime(ContextLoan context, Date newDate){
        System.out.println("you haven't borrow any book");
    }
    public void checkFine(ContextLoan context, Users user){
        System.out.println("fine has been checked and no fine");
    }
    public void payFine(ContextLoan context, int user_id){
        System.out.println("you don't have a fine to pay");
    }
    public void checkLoan(ContextLoan context, int user_id, int book_id){}
}
