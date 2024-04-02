package library;
import java.util.ArrayList;

public class Borrower extends Users{

    private int book_num_allowed = 5;
    private ArrayList<String> borrowed_book_ids= new ArrayList<>();
    private double fine = 0;
    private String address;
    private ArrayList<Loan> BookLoans;

    Borrower(BorrowerBuilder builder){
        super(builder);
        this.book_num_allowed = builder.book_num_allowed;
        this.borrowed_book_ids = builder.borrowed_book_ids;
        this.fine = builder.fine;
    }

    public static class BorrowerBuilder extends Users.Builder<BorrowerBuilder>{
        private int book_num_allowed = 5;
        private ArrayList<String> borrowed_book_ids = new ArrayList<>();
        private double fine = 0.0;
        private String address;


        public Borrower build(){
            return new Borrower(this);
        }

        protected BorrowerBuilder self(){
            return this;
        }

        public BorrowerBuilder borrowed_books_id(ArrayList a){
            this.borrowed_book_ids = a;
            return self();
        }

        public BorrowerBuilder book_num_allowed(int i){
            this.book_num_allowed = i;
            return self();
        }

        public BorrowerBuilder fine(double d){
            this.fine = d;
            return self();
        }

        public BorrowerBuilder address(String s){
            this.address = s;
            return self();
        }
    }

    public String getAddress(){return this.address;}

    public boolean GetFineStatus() {
        dbConnectivity db = new dbConnectivity();
        return (db.GetFineStatus(this.getId()));
    }

    public boolean SetAddress(String Addr) {
        this.address = Addr;
        dbConnectivity db = new dbConnectivity();
        boolean result = db.SetAddress(this.getId(), this.address);
        return result;
    }

    public void SetFineAmount(double user_fine) {
        this.fine = user_fine;
        dbConnectivity db = new dbConnectivity();
        boolean result = db.SetFineAmount(this.getId(), user_fine);
    }

    public boolean AddLoanInfo(Loan LoanInfo) {
        BookLoans.add(LoanInfo);
        return true;
    }

    public void AllLoansofUser(ArrayList<Loan> LoansofUser) {

        // this.BookLoans=LoansofUser;
        dbConnectivity db = new dbConnectivity();
        this.BookLoans = db.LoadLoanListofSpecificUser(this.getId());
    }


    public void UpdateLoanInfo(Loan Update, int book_id) {

        for (int counter = BookLoans.size()-1 ; counter >=0; counter--){
            Loan L = BookLoans.get(counter);
            if ((L.GetaBookId() == book_id)) {
                L.SetLoan(Update);
                break;
            }
        }
    }


    public String toString(){
        String result = super.toString() +
                "Address: "+ this.address + "\n"+
                "Number of books allowed to borrow: "+ this.book_num_allowed + "\n"+
                "Borrowed books: " + this.borrowed_book_ids + "\n"+
                "Unpaid fine: " + this.fine + "\n";
        return result;
    }
}
