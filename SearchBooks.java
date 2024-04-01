package library;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchBooks {

    Books GetaBookbyId(int book_id) {
        Books CurrentBook = new Books();
        dbConnectivity dbConnectivity = new dbConnectivity();
        try {
            ResultSet rs = dbConnectivity.stmt.executeQuery("select * from Books where book_id ='" + book_id + "'");
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return CurrentBook;
    }

    static ArrayList<Books> SearchBookbyType(String input) {
        ArrayList<Books> BooksList = new ArrayList<>();
        dbConnectivity dbConnectivity = new dbConnectivity();
        try {
            ResultSet rs = dbConnectivity.stmt.executeQuery("select book_id from Books where type ='" + input + "'");
            while (rs.next()) {
                int book_id = rs.getInt(1);
                Books NewBook = dbConnectivity.GetaBookbyId(book_id);
                BooksList.add(NewBook);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return BooksList;
    }

    static ArrayList<Books> SearchBookbyTitle(String input) {
        ArrayList<Books> BooksList = new ArrayList<>();
        dbConnectivity dbConnectivity = new dbConnectivity();
        try {
            ResultSet rs = dbConnectivity.stmt.executeQuery("select book_id from Books where title ='" + input + "'");
            while (rs.next()) {
                int book_id = rs.getInt(1);
                Books NewBook = dbConnectivity.GetaBookbyId(book_id);
                BooksList.add(NewBook);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return BooksList;

    }

    static ArrayList<Books> SearchBookbyAuthor(String input) {
        ArrayList<Books> BooksList = new ArrayList<>();
        dbConnectivity dbConnectivity = new dbConnectivity();
        try {
            ResultSet rs = dbConnectivity.stmt.executeQuery("select book_id from Books where author ='" + input + "'");
            while (rs.next()) {

                int book_id = rs.getInt(1);
                Books NewBook = dbConnectivity.GetaBookbyId(book_id);
                BooksList.add(NewBook);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return BooksList;

    }

    static ArrayList<Books> SearchBookbySubject(String input) {
        ArrayList<Books> BooksList = new ArrayList<>();
        dbConnectivity dbConnectivity = new dbConnectivity();
        try {

            ResultSet rs = dbConnectivity.stmt.executeQuery("select book_id from Books where subject ='" + input + "'");
            while (rs.next()) {
                int book_id = rs.getInt(1);
                Books NewBook = dbConnectivity.GetaBookbyId(book_id);
                BooksList.add(NewBook);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return BooksList;

    }

}