package library;

import java.util.Date;

public class HasFineState implements LoanState{
    public void payFine(ContextLoan context, int user_id){
        Library library = new Library();
        Loan L = library.getFinedLoanByUserID(user_id);
            if (L != null){
                L.SetFineStatus("Paid");
                for (Users b : library.UsersList) {
                    int book_id = L.GetaBookId();
                    if (b.getId() == user_id) {
                        b.SetFineAmount(0);
                        b.SetFineStatus(false);
                        //b.UpdateLoanInfo(L, book_id);
                        break;
                    }
                }
        }
        System.out.println("the fine has been paid successfully");
        context.setState(new NoFineState());
    }
    public void leave(ContextLoan context){
        context.setState(new NothingState());
    }
    //================================================
    public void borrowBook(ContextLoan context, int book_id, int borrower_id){
        System.out.println("you have a fine and cannot borrow a book.");
    }
    public void returnBook(ContextLoan context, String ret_date ,int book_id, int borrower_id){
        System.out.println("you haven't borrow any book");
    }
    public void renewDueTime(ContextLoan context, Date newDate){
        System.out.println("you haven't borrow any book");
    }

    public void checkFine(ContextLoan context, Users user){
        System.out.println("fine has been checked and you have a fine");
    }
    public void checkLoan(ContextLoan context, int user_id, int book_id){}

}
