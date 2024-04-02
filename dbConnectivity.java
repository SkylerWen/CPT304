package library;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;

public class dbConnectivity {
    Connection con;
    Statement stmt;
    dbConnectivity(){
        try {
            
            String s = "jdbc:sqlserver://localhost:49862;databaseName=Library";
            con = DriverManager.getConnection(s, "new_user", "123");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void closeConnection()
    {
        try
        {
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    ArrayList<Books> LoadAllBooks() {
        ArrayList<Books> CurrentBooks = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("select * from Books");
            while (rs.next()) {
                int book_id, quantity;
                String type, title, author, subject;
                book_id = rs.getInt(1);
                type = rs.getString(2);
                title = rs.getString(3);
                author = rs.getString(4);
                subject = rs.getString(5);
                quantity = rs.getInt(6);
                Books NewBook = new Books(book_id, type, title, author, subject, quantity);
                CurrentBooks.add(NewBook);
            }
        } catch (Exception e) { System.out.println(e); }
        return CurrentBooks;
    }

    String GetTypeofBook(int book_id) {
        String type = "  ";
        try {
            ResultSet rs = stmt.executeQuery("select type from Books where book_id='" + book_id + "'");
            while (rs.next()) {
                type = rs.getString(1);
            }
        } catch (Exception e) { System.out.println(e); }
        return type;
    }

    String GetTitleofBook(int book_id) {
        String title = "  ";
        try {
            ResultSet rs = stmt.executeQuery("select title from Books where book_id='" + book_id + "'");
            while (rs.next()) {
                title = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return title;

    }

    String GetAuthorofBook(int book_id) {
        String author = " ";
        try {
            ResultSet rs = stmt.executeQuery("select author from Books where book_id='" + book_id + "'");
            while (rs.next()) {
                author = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return author;
    }

    String GetSubjectofBook(int book_id) {
        String subject = "  ";
        try {
            ResultSet rs = stmt.executeQuery("select subject from Books where book_id='" + book_id + "'");
            while (rs.next()) {
                subject = rs.getString(1);
            }
        } catch (Exception e) { System.out.println(e); }
        return subject;
    }

    int GetQuantityofBook(int book_id) {
        int quantity = -1;
        try {
            ResultSet rs = stmt.executeQuery("select quantity from Books where book_id='" + book_id + "'");
            while (rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (Exception e) { System.out.println(e); }
        return quantity;
    }

    void UpdateBookQuantity(int new_quantity, int id) {
        try {
            int i = stmt.executeUpdate("UPDATE Books  SET quantity='" + new_quantity + "'Where book_id='" + id + "'");
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    ArrayList<Loan> LoadLoanList() {

        ArrayList<Loan> LoanList = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("Select * from (Borrower join Loan as L on Borrower.borrower_id=L.borrower_id)  join Books on Books.book_id=L.book_id");
            while (rs.next()) {
                int borrower_id;
                String borrower_name, borrower_address, borrower_number;
                char borrower_gender;
                boolean fine_defaulter;
                double fine;
                int loan_id;
                Date issue_date, due_date, returned_date;
                String fine_status;
                boolean returned_status;
                int book_id, quantity;
                String type, title, author, subject;

                borrower_id = rs.getInt(1);
                borrower_name = rs.getString(2);
                borrower_gender = rs.getString(3).charAt(0);
                borrower_address = rs.getString(4);
                borrower_number = rs.getString(5);
                fine_defaulter = rs.getBoolean(6);
                fine = rs.getFloat(7);
                loan_id = rs.getInt(8);
                issue_date = rs.getDate(9);
                due_date = rs.getDate(10);
                returned_date = rs.getDate(11);
                fine_status = rs.getString(12);
                returned_status = rs.getBoolean(13);
                book_id = rs.getInt(16);
                type = rs.getString(17);
                title = rs.getString(18);
                author = rs.getString(19);
                subject = rs.getString(20);
                quantity = rs.getInt(21);

                //Borrower Loanee = new Borrower(borrower_id, borrower_name, borrower_gender, borrower_number, borrower_address, fine_defaulter, fine);
                Borrower Loanee = new Borrower.BorrowerBuilder().build();
                Books LoanedBook = new Books(book_id, type, title, author, subject, quantity);
                Loan LoanTempObj = new Loan(loan_id, Loanee, LoanedBook, fine_status, returned_status, issue_date, due_date, returned_date);
                LoanList.add(LoanTempObj);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return LoanList;

    }

    ArrayList<Loan> LoadLoanListofSpecificUser(int user_id) {

        ArrayList<Loan> LoanListofUser = new ArrayList<>();
        try {
            ResultSet ds = stmt.executeQuery("Select * from (Borrower join Loan as L on Borrower.borrower_id=L.borrower_id)  join Books on Books.book_id=L.book_id where Borrower.borrower_id='" + user_id + "'");
            while (ds.next()) {
                int borrower_id;
                String borrower_name, borrower_address, borrower_number;
                char borrower_gender;
                boolean fine_defaulter;
                double fine;
                int loan_id;
                Date issue_date, due_date, returned_date;
                String fine_status;
                boolean returned_status;
                int book_id, quantity;
                String type, title, author, subject;

                borrower_id = ds.getInt(1);
                borrower_name = ds.getString(2);
                borrower_gender = ds.getString(3).charAt(0);
                borrower_address = ds.getString(4);
                borrower_number = ds.getString(5);
                fine_defaulter = ds.getBoolean(6);
                fine = ds.getFloat(7);
                loan_id = ds.getInt(8);
                issue_date = ds.getDate(9);
                due_date = ds.getDate(10);
                returned_date = ds.getDate(11);
                fine_status = ds.getString(12);
                returned_status = ds.getBoolean(13);
                book_id = ds.getInt(16);
                type = ds.getString(17);
                title = ds.getString(18);
                author = ds.getString(19);
                subject = ds.getString(20);
                quantity = ds.getInt(21);

                //Borrower Loanee = new Borrower(borrower_id, borrower_name, borrower_gender, borrower_number, borrower_address, fine_defaulter, fine);
                Borrower Loanee = new Borrower.BorrowerBuilder().build();
                Books LoanedBook = new Books(book_id, type, title, author, subject, quantity);
                Loan LoanTempObj = new Loan(loan_id, Loanee, LoanedBook, fine_status, returned_status, issue_date, due_date, returned_date);
                LoanListofUser.add(LoanTempObj);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return LoanListofUser;

    }

    static Books GetaBookbyId(int book_id) {
        Books CurrentBook = new Books();
        dbConnectivity dbConnectivity = new dbConnectivity();
        try {
            ResultSet rs = dbConnectivity.stmt.executeQuery("select * from Books where book_id='" + book_id + "'");
            while (rs.next()) {
                int quantity;
                String type, title, author, subject;
                book_id = rs.getInt(1);
                type = rs.getString(2);
                title = rs.getString(3);
                author = rs.getString(4);
                subject = rs.getString(5);
                quantity = rs.getInt(6);
                Books NewBook = new Books(book_id, type, title, author, subject, quantity);
                CurrentBook = NewBook;
            }
        } catch (Exception e) { System.out.println(e); }
        return CurrentBook;
    }


    Borrower GetaBorrowerObjectByUserId(int id) {

        Borrower BorrowerObject = new Borrower.BorrowerBuilder().build();
        try {
            ResultSet rs = stmt.executeQuery("select * from Borrower where borrower_id='" + id + "'");
            while (rs.next()) {
                int borrower_id;
                String borrower_name, borrower_address, borrower_number;
                char borrower_gender;
                boolean fine_defaulter;
                float fine;

                borrower_id = rs.getInt(1);
                borrower_name = rs.getString(2);
                borrower_gender = rs.getString(3).charAt(0);
                borrower_address = rs.getString(4);
                borrower_number = rs.getString(5);
                fine_defaulter = rs.getBoolean(6);
                fine = rs.getFloat(7);
                //remains to be modified!
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
                Borrower TempBorrowerObject = new Borrower.BorrowerBuilder().id(borrower_id).name(borrower_name).gender(borrower_gender).address(borrower_address).build();
                //origin below
                //Borrower(borrower_id, borrower_name, borrower_gender, borrower_address, borrower_number, fine_defaulter, fine);
                BorrowerObject = TempBorrowerObject;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return BorrowerObject;

    }

    ArrayList<Users> LoadAllBorrowers() {

        ArrayList<Borrower> Borrowers = new ArrayList<>();
        ArrayList<Users> CurrentUsers = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("select * from Borrower");

            while (rs.next()) {

                int borrower_id;
                String borrower_name, borrower_address, borrower_number;
                char borrower_gender;
                boolean fine_defaulter;
                float fine;

                borrower_id = rs.getInt(1);
                borrower_name = rs.getString(2);
                borrower_gender = rs.getString(3).charAt(0);
                borrower_address = rs.getString(4);
                borrower_number = rs.getString(5);
                fine_defaulter = rs.getBoolean(6);
                fine = rs.getFloat(7);

                Borrower TempBorrowerObj = new Borrower.BorrowerBuilder().id(borrower_id).name(borrower_name).gender(borrower_gender).address(borrower_address).build();

                Borrowers.add(TempBorrowerObj);

                // System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getInt(5));
            }

            for (Borrower B : Borrowers) {
                int borrower_id = B.getId();
                ArrayList<Loan> UserLoanArray = LoadLoanListofSpecificUser(borrower_id);
                B.AllLoansofUser(UserLoanArray);

            }

            for (Borrower B : Borrowers) {
                CurrentUsers.add(B);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return CurrentUsers;

    }

    boolean SetFineStatus(int borrower_id, boolean fine_defaulter) {

        try {
            stmt.executeUpdate("Update Borrower Set fine_defaulter='" + fine_defaulter + "' Where borrower_id='" + borrower_id + "'");

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }

    boolean SetTelephone(int borrower_id, String telnum) {

        try {
            stmt.executeUpdate("Update Borrower Set borrower_num='" + telnum + "' Where borrower_id='" + borrower_id + "'");

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }


    boolean SetAddress(int borrower_id, String Address) {

        try {
            stmt.executeUpdate("Update Borrower Set borrower_address='" + Address + "' Where borrower_id='" + borrower_id + "'");

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }

    boolean SetName(int borrower_id, String name) {

        try {
            stmt.executeUpdate("Update Borrower Set borrower_name='" + name+ "' Where borrower_id='" + borrower_id + "'");

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }


    boolean SetGender(int borrower_id, char g) {

        try {
            stmt.executeUpdate("Update Borrower Set borrower_gender='" + g+ "' Where borrower_id='" + borrower_id + "'");

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }

    boolean SetFineAmount(int borrower_id, double fine_amount) {

        try {
            stmt.executeUpdate("Update Borrower Set fine='" + fine_amount + "' Where borrower_id='" + borrower_id + "'");

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;

    }

    boolean GetFineStatus(int borrower_id) {
        boolean result = false;
        try {
            ResultSet rs = stmt.executeQuery("select fine_defaulter from Borrower where borrower_id='" + borrower_id + "'");

            while (rs.next()) {

                result = rs.getBoolean(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    double GetFineAmount(int borrower_id) {

        double amount = 0.0;
        try {
            ResultSet rs = stmt.executeQuery("select fine from Borrower where borrower_id='" + borrower_id + "'");

            while (rs.next()) {

                amount = rs.getDouble(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return amount;

    }

    //Librarian Functions implemented here
    ArrayList<Librarian> LoadAllLibrarians() {

        ArrayList<Librarian> CurrentLibrarians = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("Select * From Staff Where rank='librarian'");
            while (rs.next()) {
                int staff_id;
                String staff_name;
                char staff_gender;

                staff_id = rs.getInt(1);
                staff_name = rs.getString(2);
                staff_gender = rs.getString(3).charAt(0);

                Librarian TempObj = new Librarian.LibrarianBuilder().id(staff_id).build();
                CurrentLibrarians.add(TempObj);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return CurrentLibrarians;

    }




    boolean AddBorrower(int user_id, String user_name, char user_gender, String add, String telnum, String pw) {

        try {

            boolean fine = false;
            double fineamount = 0.0;

            stmt.executeUpdate("Insert into Borrower (borrower_id, borrower_name ,borrower_gender, borrower_address , borrower_number , fine_defaulter ,fine, password) values('" + user_id + "','" + user_name + "','" + user_gender + "','" + add + "','" + telnum + "','" + fine + "','" + fineamount + "','" +pw+"')");

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }


    boolean AddNewLoan(Loan LoanObj) {

        try {
            int loanid = LoanObj.GetLoanId();
            Date issue_date = LoanObj.getIssueDate();
            Date due_date = LoanObj.getDueDate();
            Date return_date = LoanObj.getReturnDate();
            String fine_status = LoanObj.GetFineStatus();
            boolean returned_status = LoanObj.GetStatus();
            int borrower_id = LoanObj.GetaBorrowerId();
            int book_id = LoanObj.GetaBookId();

            java.sql.Date issue_date1 = new java.sql.Date(issue_date.getTime());
            java.sql.Date due_date1 = new java.sql.Date(due_date.getTime());
            java.sql.Date return_date1 = new java.sql.Date(return_date.getTime());

            stmt.executeUpdate("Insert into Loan (loanid , issue_date ,due_date ,  return_date , fine_status , returned_status ,borrower_id ,book_id ) values('" + loanid + "','" + issue_date1 + "','" + due_date1 + "','" + return_date1 + "','" + fine_status + "','" + returned_status + "','" + borrower_id + "','" + book_id + "')");

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;

    }

    int GetLoanedBookId(int loanId) {
        int bookId = -1;
        try {
            ResultSet rs = stmt.executeQuery("select book_id from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                bookId = rs.getInt(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return bookId;

    }

    String GetLoanFineStatus(int loanId) {
        String LoanFineStatus = "";
        try {
            ResultSet rs = stmt.executeQuery("select fine_status from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                LoanFineStatus = rs.getString(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return LoanFineStatus;

    }

    Date GetReturnDate(int loanId) {

        Date ReturnDate = new Date();
        try {
            ResultSet rs = stmt.executeQuery("select return_date from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                ReturnDate = rs.getDate(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return ReturnDate;

    }

    Date GetIssueDate(int loanId) {

        Date IssueDate = new Date();
        try {
            ResultSet rs = stmt.executeQuery("select issue_date from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                IssueDate = rs.getDate(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return IssueDate;

    }

    Date GetDueDate(int loanId) {

        Date DueDate = new Date();
        try {
            ResultSet rs = stmt.executeQuery("select due_date from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                DueDate = rs.getDate(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return DueDate;

    }

    Books GetLoanedBook(int loanId) {

        Books LoanedBook = new Books();
        try {
            ResultSet rs = stmt.executeQuery("select book_id from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                int book_id = rs.getInt(1);
                LoanedBook = GetaBookbyId(book_id);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return LoanedBook;

    }

    int GetLoaneeId(int loanId) {

        int LoaneeId = -1;
        try {
            ResultSet rs = stmt.executeQuery("select borrower_id from Loan where loanid='"+loanId+"'");

            while (rs.next()) {

                LoaneeId = rs.getInt(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return LoaneeId;

    }

    boolean GetLoanReturnedStatus(int loanId) {

        boolean result = false;
        try {
            ResultSet rs = stmt.executeQuery("select returned_status from Loan where loanid='" + loanId + "'");

            while (rs.next()) {

                result = rs.getBoolean(1);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;

    }

    void SetLoanReturnedDate(int loanId, Date Ret_date) {
        int i = 0;
        try {

            java.sql.Date retdate = new java.sql.Date(Ret_date.getTime());
            i = stmt.executeUpdate("UPDATE Loan SET return_date='" + retdate + "'Where loanid='" + loanId + "'");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void SetLoanedBook(int loanId, int book_id) {
        int i = 0;
        try {

            i = stmt.executeUpdate("UPDATE Loan SET book_id='" + book_id + "'Where loanid='" + loanId + "'");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void SetLoaneeObject(int loanId, int borrower) {
        int i = 0;
        try {

            i = stmt.executeUpdate("UPDATE Loan SET borrower_id='" + borrower + "'Where loanid='" + loanId + "'");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void SetReturnStatus(int loanId, boolean status) {
        int i = 0;
        try {

            i = stmt.executeUpdate("UPDATE Loan SET returned_status='" + status + "'Where loanid='" + loanId + "'");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void SetLoanFineStatus(int loanId, String status) {

        int i = 0;
        try {

            i = stmt.executeUpdate("UPDATE Loan SET fine_status='" + status + "'Where loanid='" + loanId + "'");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void SetLoan(int loanID, Loan LoanObj) {

        try {
            int loanid = LoanObj.GetLoanId();
            Date issue_date = LoanObj.getIssueDate();
            Date due_date = LoanObj.getDueDate();
            Date return_date = LoanObj.getReturnDate();
            String fine_status = LoanObj.GetFineStatus();
            boolean returned_status = LoanObj.GetStatus();
            int borrower_id = LoanObj.GetaBorrowerId();
            int book_id = LoanObj.GetaBookId();

            java.sql.Date issue_date1 = new java.sql.Date(issue_date.getTime());
            java.sql.Date due_date1 = new java.sql.Date(due_date.getTime());
            java.sql.Date return_date1 = new java.sql.Date(return_date.getTime());

            stmt.executeUpdate("Update Loan SET issue_date='" + issue_date1 + "',due_date='" + due_date1 + "' ,return_date='" + return_date1 + "' , fine_status='" + fine_status + "', returned_status= '" + returned_status + "',borrower_id='" + borrower_id + "' ,book_id='" + book_id + "'  Where loanid=' " + loanid + "'");
        } catch (Exception e) {
            System.out.println(e);
        }

    }



    boolean CheckUserId (int ID)
    {
        boolean flag=false;

        try {

            ResultSet rs = stmt.executeQuery("select * from Borrower where borrower_id='" + ID+ "'");
            if(rs.next())
            {
                flag=true;
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return flag;

    }
    boolean CheckUserPw (String pw)
    {
        boolean flag=false;

        try {

            ResultSet rs = stmt.executeQuery("select * from Borrower where borrower_password='" + pw+ "'");
            if(rs.next())
            {
                flag=true;
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return flag;

    }

    boolean CheckIsBookLoaned(int book_id)
    {

        boolean flag=false;

        try {

            ResultSet rs = stmt.executeQuery("select * from Loan where book_id='" +book_id+ " 'and returned_status ='" +0+"'");
            if(rs.next())
            {

                flag=true;
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return flag;

    }

}
