package library;

import java.util.Date;

public class ContextLoan {
    private LoanState state;
    private Loan loan;

    public ContextLoan(LoanState state, Loan loan){
        this.state = state;
        this.loan = loan;
    }

    //SET & GET
    public void setState(LoanState state){
        this.state = state;
        loan.SetState(state);
    }
    public void setLoan(Loan loan){
        this.loan = loan;
    }
    public LoanState getState(){
        return state;
    }
    public Loan getLoan(){
        return loan;
    }

    //functions
    public void borrowBook(int book_id, int borrower_id){
        Library library = new Library();
        Users borrower = library.getUserByID(borrower_id);
        checkFine(borrower);
        state.borrowBook(this, book_id, borrower_id);
    }
    public void returnBook(String ret_date ,int book_id, int borrower_id){
        state.checkLoan(this, book_id, borrower_id);
        state.returnBook(this, ret_date ,book_id, borrower_id);
    }
    public void renewDueTime(Date newDate){
        state.renewDueTime(this,newDate);
    }
    public void checkFine(Users user){
        state.checkFine(this, user);
    }
    public void payFine(int user_id){
        Library library = new Library();
        Users user = library.getUserByID(user_id);
        state.checkFine(this, user);
        state.payFine(this, user_id);
    }
    public void leave(){
        state.leave(this);
    }
}
