package library;

public class DeleteBooks {
    static dbConnectivity dbConnectivity = new dbConnectivity();

    static boolean DeleteABook(int book_id) {
        boolean flag = false;
        boolean CheckIsBookLoaned = dbConnectivity.CheckIsBookLoaned(book_id);
        if (CheckIsBookLoaned == false) {
            try {
                dbConnectivity.stmt.executeUpdate("Delete From Books Where book_id ='" + book_id + "'");
                flag = true;
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Delete the book successfully!");
        }
        return flag;
    }
}