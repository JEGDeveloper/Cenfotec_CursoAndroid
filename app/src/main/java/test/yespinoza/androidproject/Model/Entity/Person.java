package test.yespinoza.androidproject.Model.Entity;

import java.io.Serializable;

public class  Person implements Serializable {
    private String id;
    private String idNumber;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String birthday;
    private String address;

    public Person(){
        id = "";
        idNumber = "";
        name = "";
        lastName = "";
        email = "";
        phone = "";
        birthday = "";
        address = "";
    }

    public Person(String pId, String pName, String pLastName, String pEmail, String pPhone, String pDateOfBirth, String pAddress){
        id = pId;
        name = pName;
        lastName = pLastName;
        email = pEmail;
        phone = pPhone;
        birthday = pDateOfBirth;
        address = pAddress;
    }

    public String getFullName(){
        try {
            return name.concat(" ").concat(lastName);
        }catch (Exception oException){
            return "";
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return birthday;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.birthday = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
