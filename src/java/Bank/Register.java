package Bank;
// Generated 2017-1-9 16:25:43 by Hibernate Tools 4.3.1



/**
 * Register generated by hbm2java
 */
public class Register  implements java.io.Serializable {


     private String username;
     private String password;
     private String address;
     private String bankaccountnumber;
     private String email;
     private String contactnumber;
     private String cumstomername;

    public Register() {
    }

    public Register(String username, String password, String address, String bankaccountnumber, String email, String contactnumber, String cumstomername) {
       this.username = username;
       this.password = password;
       this.address = address;
       this.bankaccountnumber = bankaccountnumber;
       this.email = email;
       this.contactnumber = contactnumber;
       this.cumstomername = cumstomername;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBankaccountnumber() {
        return this.bankaccountnumber;
    }
    
    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactnumber() {
        return this.contactnumber;
    }
    
    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
    public String getCumstomername() {
        return this.cumstomername;
    }
    
    public void setCumstomername(String cumstomername) {
        this.cumstomername = cumstomername;
    }




}


