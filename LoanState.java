package library;

import java.util.Date;

public interface LoanState {

    public void borrowBook(ContextLoan context, int book_id, int borrower_id);
    public void returnBook(ContextLoan context, String ret_date ,int book_id, int borrower_id);
    public void renewDueTime(ContextLoan context, Date newDate);
    public void checkFine(ContextLoan context, Users user);
    public void payFine(ContextLoan context, int user_id);
    public void leave(ContextLoan context);
    public void checkLoan(ContextLoan context, int user_id, int book_id);

}