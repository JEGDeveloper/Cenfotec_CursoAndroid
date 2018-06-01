package test.yespinoza.androidproject.Model;

import java.io.Serializable;

public class User  extends Person  implements Serializable{
    private String userName;
    private String password;

    public User(String pUserName, String pPassword){
        this.userName = pUserName;
        this.password = pPassword;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
