package library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowedState implements LoanState {
    public void returnBook(ContextLoan context, String ret_date ,int book_id, int borrower_id) {
        Library library = new Library();
        Loan L = library.getLoanID(book_id, borrower_id);
        if (L != null) {

            String expectedPattern = "yyyy/MM/dd";
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
            //String return_date = ret_date;
            Date date = new Date();
            try {
                date = formatter.parse(ret_date);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("the date formatter is wrong");
                return;
            }

            L.SetReturnedDate(date);
            double user_fine = L.CalculateFine();
            if (user_fine > 0.0) {
                L.SetFineStatus("Fined");
                Users current_borrower = library.getUserByID(borrower_id);
                current_borrower.SetFineStatus(true);
                current_borrower.SetFineAmount(user_fine);

                library.RenewBook(book_id, 1);
                current_borrower.UpdateLoanInfo(L, book_id);

            }
            L.SetReturnStatus(true);
            context.setState(new NothingState());
        }
    }
    public void renewDueTime(ContextLoan context, Date newDate){
        Loan loan = context.getLoan();
        loan.SetReturnedDate(newDate);
    }

    //================================================
    public void borrowBook(ContextLoan context, int book_id, int borrower_id){
        System.out.println("already borrowed");
    }
    public void checkFine(ContextLoan context, Users user){
        System.out.println("Fine has already been checked");
    }
    public void payFine(ContextLoan context, int user_id){
        System.out.println("no fine needs to be paid before you return the book");
    }
    public void leave(ContextLoan context){
        System.out.println("");
    }
    public void checkLoan(ContextLoan context, int user_id, int book_id){}
}
