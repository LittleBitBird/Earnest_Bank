/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Bank.Account;
import Bank.Register;
import Bank.helper.AccountHelper;
import Bank.helper.RegisterHelper;
import java.math.BigDecimal;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lenovo
 */
@ManagedBean
@RequestScoped
public class RegisterBean {
     private String username;
     private String password;
     private String address;
     private String bankaccountnumber;
     private String email;
     private String contactnumber;
     private String cumstomername;
     private String AccountType;
     private String warm;
    /**
     * Creates a new instance of Register
     */
    public RegisterBean() {
    }
    
    public void saveRegister(){
        Register register =new Register();
        register.setUsername(this.getUsername().toString());
        register.setPassword(this.getPassword().toString());
        register.setAddress(this.getAddress().toString());
        register.setBankaccountnumber(this.getBankaccountnumber().toString());
        register.setEmail(this.getEmail().toString());
        register.setContactnumber(this.getContactnumber().toString());
        register.setCumstomername(this.getCumstomername().toString());
        RegisterHelper rh=new RegisterHelper();
       
            System.out.println(1);
            int a=rh.saveRegister(register,this.getUsername().toString());
            if(a==0){
                this.setWarm("该用户名已经存在");
            }
            else{
        Account account =new Account ();  
        if(this.getAccountType().equals("d")){
             account.setAccounttype("定期");
             account.setRate("0.002");
        }
        else {
            account.setAccounttype("活期");
            account.setRate("0.0012");
        }
        account.setBalacnce(new BigDecimal(0));
        account.setUsername(this.getUsername().toString());
        AccountHelper ah=new AccountHelper();
        ah.savedetail(account);
        this.setWarm("注册成功，您现在可以登录了");
        }
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    
    
    public String getWarm() {
        return warm;
    }

    public void setWarm(String warm) {
        this.warm = warm;
    }
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getCumstomername() {
        return cumstomername;
    }

    public void setCumstomername(String cumstomername) {
        this.cumstomername = cumstomername;
    }
    
}
