package library;
import java.util.ArrayList;

abstract class Users {
    private final String name;
    private final int id;
    private final char gender;
    private String pw;
    //optional paras;
    private String phone;


    protected Users(Builder<?> builder){
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
    public String getPw(){return this.pw;}

    //setters
    public void setPhone(String s){
        this.phone = s;
        dbConnectivity db = new dbConnectivity();
        boolean result = db.SetTelephone(this.getId(), this.phone);
    }

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
        public abstract Users build();


    }


    public void SetFineAmount(double fine) {
        dbConnectivity db= new dbConnectivity ();
        db.SetFineAmount(id, fine);
    }
    public boolean SetFineStatus(boolean ispaid){
        dbConnectivity db= new dbConnectivity ();
        boolean result= db.SetFineStatus(id, ispaid);
        return result;
    }

    public void UpdateLoanInfo(Loan L, int i ) {}

    public void display_user_info(){
        String info = this.toString();
        System.out.println(info);
    }

    public interface LoginHandler{
        boolean handle(Users u);
        void setNext(LoginHandler nextHandler);
        LoginHandler getNextHandler();
    }

    public class IDHandler implements LoginHandler{
        private LoginHandler next;

        @Override
        public LoginHandler getNextHandler(){return next;}

        @Override
        public boolean handle(Users u){
            dbConnectivity db = new dbConnectivity();
            boolean flag = db.CheckUserId(u.getId());
            if (flag == true){
                this.setNext(next);
            }else{
                System.out.println("Cannot find user with ID:" + u.getId());
            }
            return flag;
        }


        public void setNext(LoginHandler nextHandler){
            this.next = nextHandler;
        }
    }

    public class PwHandler implements LoginHandler{
        private LoginHandler next;
        @Override
        public LoginHandler getNextHandler(){return next;}
        @Override
        public boolean handle(Users u){
            dbConnectivity db = new dbConnectivity();
            boolean flag = db.CheckUserPw(u.getPw());
            if (flag == true){
                this.setNext(next);
            }else{
                System.out.println("Cannot find user with ID:" + u.getId());
            }
            return flag;
        }


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
            return chain.handle(Users.this);
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
    public boolean GetFineStatus() {
        dbConnectivity db = new dbConnectivity();
        return (db.GetFineStatus(this.getId()));
    }

}
