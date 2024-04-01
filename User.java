import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


abstract class User {
    //compulsory paras;
    private final String name;
    private final int id;
    private final char gender;
    private String pw;
    //optional paras;
    private String phone;


    protected User(Builder<?> builder){
        this.name = builder.name;
        this.id = builder.id;
        this.gender = builder.gender;
    }

    //getters
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public char getGender(){return gender;}
    public String getPhone(){return phone;}

    //setters
    public void setPhone(String s){
        this.phone = s;
        dbConnectivity db = new dbConnectivity();
        boolean result = db.SetTelephone(this.GetId(), this.phone);
    }

    public int GetId() { return this.id;
    }
    public String getPw(){return this.pw;}

    //Builder Pattern for constructor;

    public static abstract class Builder<T extends Builder<T>>{
        //compulsory para;
        private  String name;
        private int id;
        private char gender;
        //optional para;
        private String phone;

        public T name(String s){
            this.name = s;
            return self();
        }///s

        public T id(int i){
            this.id = i;
            return self();
        }
        public T gender(char c){
            this.gender= c;
            return self();
        }
        public T phone(String s){
            this.phone = s;
            return self();
        }

        protected abstract T self();
        public abstract User build();


    }



    public ArrayList<Books> SearchBookbyTitle(String s){
        Books[] bl;
        for (Books b : bl){
            String title = b.getTitle();
            if (title.contains(s) == true){
                b.PrintInformation();
            }else{
                System.out.println("No book named " + s + "is found. \n");
            }
        }

        ArrayList<Books> bookList = new ArrayList<Books>();
        dbConnectivity db = new dbConnectivity();
        bookList = db.SearchBookbyTitle(s);
        if (bookList.isEmpty() == false){
            for (Books b : bookList){
                b.PrintInformation();
            }
        }else{
            System.out.println("No book named " + s + "is found. \n");
        }
        return bookList;
    }


    public ArrayList<Books> SearchBookbySubject(String s){
        ArrayList<Books> bookList = new ArrayList<>();
        dbConnectivity db = new dbConnectivity();
        bookList = db.SearchBookbySubject(s);
        if (bookList.isEmpty() == false){
            for (Books b : bookList){
                b.PrintInformation();
            }
        }else{
            System.out.println("No book subjected " + s + "is found. \n");
        }

        return bookList;
    }

    public ArrayList<Books> SearchBookbyAuthor(String s){
        ArrayList<Books> bookList = new ArrayList<Books>();
        dbConnectivity db = new dbConnectivity();
        bookList = db.SearchBookbyAuthor(s);
        if (bookList.isEmpty() == false){
            for (Books b : bookList){
                b.PrintInformation();
            }
        }else{
            System.out.println("No book written by " + s + "is found. \n");
        }

        return bookList;
    }
    public void SetFineAmount(double fine) {
        dbConnectivity db= new dbConnectivity ();
        db.SetFineAmount(id, fine);
    }
    public boolean SetFineStatus(boolean ispaied){
        dbConnectivity db= new dbConnectivity ();
        boolean result= db.SetFineStatus(id, ispaid);
        return result;
    }
//??????????????????????????????????????????????????????
    public void UpdateLoanInfo(Loan L, int i ) {}

    //abstract String ViewInformation(ArrayList<Loan> LoanLoanList, int user_id);
    //上一行会导致Borrower报错（因为没有实例化该抽象方法）
//?????????????????????????????????????????????????????????

    public void display_user_info(){
        String info = this.toString();
        System.out.println(info);
    }

    public interface LoginHandler{
        boolean handle(User u);
        void setNext(LoginHandler nextHandler);
        LoginHandler getNextHandler();
    }

    public class IDHandler implements LoginHandler{
        private LoginHandler next;

        @Override
        public LoginHandler getNextHandler(){return next;}

        @Override
        public boolean handle(User u){
            dbConnectivity db = new dbConnectivity();
            boolean flag = db.CheckUserId(u.getId());
            if (flag == true){
                this.setNext(next);
            }else{
                System.out.println("Cannot find user with ID:" + u.getId());
            }
            return flag;
        }

        @Override
        public void setNext(LoginHandler nextHandler){
            this.next = nextHandler;
        }
    }

    public class PwHandler implements LoginHandler{
        private LoginHandler next;
        @Override
        public LoginHandler getNextHandler(){return next;}
        @Override
        public boolean handle(User u){
            dbConnectivity db = new dbConnectivity();
            boolean flag = db.CheckUserPw(u.getPw());
            if (flag == true){
                this.setNext(next);
            }else{
                System.out.println("Cannot find user with ID:" + u.getId());
            }
            return flag;
        }

        @Override
        public void setNext(LoginHandler nextHandler){
            this.next = nextHandler;
        }
    }

    public class LoginProcessor {
        private LoginHandler chain;
        public void addHandler(LoginHandler h){
            if (chain == null){
                chain = h;
            }else{
                LoginHandler currentHandler = chain;
                while (currentHandler.getNextHandler()!= null){
                    currentHandler = currentHandler.getNextHandler();
                }
                currentHandler.setNext(h);
            }
        }

        public boolean logIn(String name, int id, String pw){
            return chain.handle(User.this);
        }
    }

    @Override
    public String toString(){
        String info = "Name: " + this.name + "/n" +
                "ID: " + this.id + "/n" +
                "Phone number: " + this.phone + "/n"
                ;
        return info;
    }





}
