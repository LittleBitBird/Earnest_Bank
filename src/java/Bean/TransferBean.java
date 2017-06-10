/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Bank.Account;
import Bank.Banktransaction;
import Bank.helper.AccountHelper;
import Bank.helper.TransactionHelper;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@ManagedBean
@RequestScoped
public class TransferBean {
     private String accountid;
     private String accountgetter;
     private String accountsender;
     private String banktransaction;
     private String warn;
     private Date transactiondate;
     private BigDecimal transactiondmoney;
     private BigDecimal leftmoney;
    /**
     * Creates a new instance of TransferBean
     */
    public TransferBean() {
    }
    
    public void sun1(){
        //在这个bean里获得LoignBean的信息
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        LoginBean LoginBean = (LoginBean) facesContext.getApplication()  
      .getVariableResolver().resolveVariable(facesContext, "loginBean");  
        String sender= LoginBean.getUsername();
        //从自己账户里面扣钱
        AccountHelper ah=new AccountHelper();
        List <Account> a=ah.getdetail(sender);
        Iterator it=a.iterator();
        Account account=(Account)it.next();
        BigDecimal a1=account.getBalacnce().subtract(this.getTransactiondmoney());
        int r=a1.compareTo(BigDecimal.ZERO);
        if(r==1||r==0){
        account.setBalacnce(a1);
        ah.updatedetail(account);
        //向收款人的账户加钱
        String getter=this.getAccountgetter();
        List <Account> a2=ah.getdetail(getter);
        if(a2.size()>0){
            //收款人存在
           
        Iterator it1=a2.iterator();
        Account account1=(Account)it1.next();
        BigDecimal a3=account1.getBalacnce().add(this.getTransactiondmoney());
        account1.setBalacnce(a3);
        ah.updatedetail(account1);
        //建立银行事件
        Banktransaction b=new Banktransaction();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	b.setTransactiondate(currentDate); 
        b.setAccountid("1"+calendar.getTime().getTime());
        b.setAccountgetter(getter);
        b.setAccountsender(sender);
        b.setBanktransaction("转账");
        b.setTransactiondmoney(this.getTransactiondmoney());
        TransactionHelper t=new TransactionHelper();
        t.savedetail(b);
        }else{
            //收款人不存在
            account.setBalacnce(a1.add(this.getTransactiondmoney()));
            ah.updatedetail(account);
            this.setWarn("收款人不存在");
        } 
        }
        else{
            this.setWarn("余额不足");
        }
        this.setLeftmoney(a1);
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public BigDecimal getLeftmoney() {
        return leftmoney;
    }

    public void setLeftmoney(BigDecimal leftmoney) {
        this.leftmoney = leftmoney;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getAccountgetter() {
        return accountgetter;
    }

    public void setAccountgetter(String accountgetter) {
        this.accountgetter = accountgetter;
    }

    public String getAccountsender() {
        return accountsender;
    }

    public void setAccountsender(String accountsender) {
        this.accountsender = accountsender;
    }

    public String getBanktransaction() {
        return banktransaction;
    }

    public void setBanktransaction(String banktransaction) {
        this.banktransaction = banktransaction;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public BigDecimal getTransactiondmoney() {
        return transactiondmoney;
    }

    public void setTransactiondmoney(BigDecimal transactiondmoney) {
        this.transactiondmoney = transactiondmoney;
    }
    
}
