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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author lenovo
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    private String username; 
    private String password; 
    private String warn; 
    private BigDecimal balance; 
    private BigDecimal balance1; 
    /**
     * Creates a new instance of Login
     */
    public LoginBean() {
    }
    
    
    public String account(){
        AccountHelper at=new AccountHelper();
        List <Account> account1=new ArrayList<Account>();
        account1=at.getdetail(username);
        Iterator it=account1.iterator();
        Account a=(Account)it.next();
        this.setBalance1( a.getBalacnce()); 
        return "LoginAccount";
    }
    
    
    public String Login(){
        this.setUsername(this.getUsername());
        this.setPassword(this.getPassword());
        String user,pass;
        user=this.getUsername();
        pass=this.getPassword(); 
        RegisterHelper rh=new  RegisterHelper();
        List <Register> register=rh.getRegister(user);
        Iterator it=register.iterator();
        Register t=(Register)it.next();
        if(t.getUsername().equals(user)&&t.getPassword().equals(pass)){
            
            //通过username获得账户信息
             AccountHelper a=new AccountHelper();
             List <Account> account=new ArrayList<Account>();
             account=a.getdetail(user);
             Iterator its=account.iterator();
             if(its.hasNext()){
             Account at=(Account)its.next();       
             this.setBalance(at.getBalacnce());
             }
             
            //设定余额
             AccountHelper at=new AccountHelper();
             List <Account> account1=new ArrayList<Account>();
             account1=at.getdetail(username);
             Iterator itss=account1.iterator();
             Account aa=(Account)itss.next();
             this.setBalance1( aa.getBalacnce()); 
        return "LoginAccount";}
        else {
            this.setWarn("账号或者密码错误");
            return "Login";
        }
    }

    public BigDecimal getBalance1() {
        return balance1;
    }

    public void setBalance1(BigDecimal balance1) {
        this.balance1 = balance1;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
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
    
}
