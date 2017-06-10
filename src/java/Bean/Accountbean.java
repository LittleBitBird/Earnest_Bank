/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Bank.Account;
import Bank.helper.AccountHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author lenovo
 */
@ManagedBean
@RequestScoped
public class Accountbean {
     private String username;
     private String accounttype;
     private BigDecimal balacnce;
     private String rate;
     private String a="wedr";
    /**
     * Creates a new instance of AccountDetail
     */
    public Accountbean() {
        
    }

    public String getUsername() {
        return username;
    }
    
    public void autochange(ValueChangeEvent vce){
        String s=vce.getNewValue().toString();
        AccountHelper a=new AccountHelper();
        List <Account> account=new ArrayList<Account>();
        account=a.getdetail(s);
        for(Iterator it=account.iterator();it.hasNext();){
            Account at=(Account)it.next();
            this.setAccounttype(at.getAccounttype());
            this.setBalacnce(at.getBalacnce());
            this.setRate(at.getRate());
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public BigDecimal getBalacnce() {
        return balacnce;
    }

    public void setBalacnce(BigDecimal balacnce) {
        this.balacnce = balacnce;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
    
}
