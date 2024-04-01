package library;

public class Books {
    private int book_id;
    private String type;
    private String title;
    private String author;
    private String subject;
    private int quantity;

    Books() {
        this.book_id = 0;
        this.type = "  ";
        this.title = "  ";
        this.author = "  ";
        this.subject = "  ";
        this.quantity = 0;
    }

    Books(int book_id, String type, String title, String author, String subject, int quantity) {
        this.book_id = book_id;
        this.type = type;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.quantity = quantity;
    }

    public Books GetaBook() {
        dbConnectivity db = new dbConnectivity();
        Books MyBook = db.GetaBookbyId(this.book_id);
        return MyBook;
    }
    
    public int getBook_id() {
        return this.book_id;
    }
    public void setBook_Id(int book_id) {
        this.book_id = book_id;
    }

    public String getType() {
        dbConnectivity db = new dbConnectivity();
        String typeofbook = db.GetTitleofBook(this.book_id);
        return typeofbook;
    }
    public void setType(String type) {
        this.type = type;
        ChangeBookInformation.ChangeBookInformation(book_id, type, 1);
    }

    public String getTitle() {
        dbConnectivity db = new dbConnectivity();
        String titleofbook = db.GetTitleofBook(this.book_id);
        return titleofbook;
    }
    public void setTitle(String title) {
        this.title = title;
        ChangeBookInformation.ChangeBookInformation(book_id, title, 1);
    }

    public String getAuthor() {
        dbConnectivity db = new dbConnectivity();
        String authorofbook = db.GetAuthorofBook(this.book_id);
        return authorofbook;
    }
    public void setAuthor(String author) {
        this.author = author;
        ChangeBookInformation.ChangeBookInformation(book_id, author, 2);
    }

    public String getSubject() {
        dbConnectivity db = new dbConnectivity();
        String subjectofbook = db.GetSubjectofBook(this.book_id);
        return subjectofbook;
    }
    public void setSubject(String subject) {
        this.subject = subject;
        ChangeBookInformation.ChangeBookInformation(book_id, subject, 3);
    }

    public int getQuantity() {
        dbConnectivity db = new dbConnectivity();
        int quantity_available = db.GetQuantityofBook(this.book_id);
        return quantity_available;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        dbConnectivity db = new dbConnectivity();
        db.UpdateBookQuantity(this.quantity, this.book_id);
    }

    public void IncreaseQuantity() {
        this.quantity = this.quantity + 1;
        dbConnectivity db = new dbConnectivity();
        db.UpdateBookQuantity(this.quantity, book_id);
    }

    public void DecreaseQuantity() {
        if (quantity > 0) {
            this.quantity = this.quantity - 1;
            dbConnectivity db = new dbConnectivity();
            db.UpdateBookQuantity(this.quantity, book_id);
        }
    }

    public boolean CheckAvailability(int book_id) {
        dbConnectivity db = new dbConnectivity();
        int quantity_available = db.GetQuantityofBook(this.book_id);
        if (quantity_available <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public String PrintInformation() {
        boolean available = CheckAvailability(this.book_id);
        String status;
        if (available == true) {
            status = "available";
        } else {
            status = "not available";
        }
        String Resultant=" ";
        Resultant =Resultant+"    "+this.book_id + "\t" + this.type + "\t" + this.title + "\t" + this.author + "\t" + this.subject + "\t" + this.quantity + "\t" + status + "\n";
        return Resultant;
    }
}