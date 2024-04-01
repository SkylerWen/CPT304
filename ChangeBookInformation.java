package library;

public class ChangeBookInformation{
        static void ChangeBookInformation(int book_id, String UpdatedInfo, int i) {
                dbConnectivity dbConnectivity = new dbConnectivity();
                try {
                        if (i == 1) {
                                int j = dbConnectivity.stmt.executeUpdate("UPDATE Books  SET type ='" + UpdatedInfo + "'Where book_id ='" + book_id + "'");
                        } else if (i == 2) {
                                int j = dbConnectivity.stmt.executeUpdate("UPDATE Books  SET title ='" + UpdatedInfo + "'Where book_id ='" + book_id + "'");
                        } else if (i == 3) {
                                int j = dbConnectivity.stmt.executeUpdate("UPDATE Books  SET author ='" + UpdatedInfo + "'Where book_id ='" + book_id + "'");
                        } else if (i == 4) {
                                int j = dbConnectivity.stmt.executeUpdate("UPDATE Books  SET subject ='" + UpdatedInfo + "'Where book_id ='" + book_id + "'");
                        }
                } catch (Exception e) {
                        System.out.println(e);
                }
        }
}