package library;

import java.util.ArrayList;
import java.util.Date;

public class NothingState implements LoanState {
    public void checkFine(ContextLoan context, Users user){
        if (user.GetFineStatus()){// this user has fine
            context.setState(new HasFineState());
        }
        else{
            context.setState(new NoFineState());
        }
    }
    public void checkLoan(ContextLoan context, int user_id, int book_id){
        Library library = new Library();
        Loan loan = library.getLoanID(user_id, book_id);
        if(loan != null){
            context.setState(new BorrowedState());
        }
        else{
            context.setState(new NothingState());
        }
    }
    //================================================
    public void borrowBook(ContextLoan context, int book_id, int borrower_id){
        System.out.println("you cannot borrow a book before checking fine");
    }
    public void returnBook(ContextLoan context, String ret_date ,int book_id, int borrower_id){
        System.out.println("you haven't borrow any book yet OR you have returned this book");
    }
    public void renewDueTime(ContextLoan context, Date newDate){
        System.out.println("you haven't borrow any book yet");
    }
    public void payFine(ContextLoan context, int user_id){
        System.out.println("you haven't check the fine");
    }
    public void leave(ContextLoan context){}
}
