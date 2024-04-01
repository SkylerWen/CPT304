package library;
import java.sql.Connection;
import java.sql.Statement;

public class AddBooks {

    static void AddNewBook(int book_id, String type, String title, String author, String subject, int quantity) {
        try {
            dbConnectivity dbConnectivity = new dbConnectivity();
            dbConnectivity.stmt.executeUpdate("Insert into Books (book_id, type, title, author, subject, quantity) values('" + book_id + "','" + type + "','" + title + "','" + author + "','" + subject + "','" + quantity + "')");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Add the book successfully!");
    }
}