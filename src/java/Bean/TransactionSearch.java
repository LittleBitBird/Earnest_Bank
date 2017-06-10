/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Bank.Banktransaction;
import Bank.Register;
import Bank.Transaction;
import Bank.helper.RegisterHelper;
import Bank.helper.TransactionHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class TransactionSearch {
     private String accountid;
     private String accountgetter;
     private String accountsender;
     private String banktransaction;
     private Date transactiondate;
     private BigDecimal transactiondmoney;
     private List <Transaction> tran=new ArrayList<Transaction> ();
    /**
     * Creates a new instance of TransactionSearch
     */
     
    public void transaction(ValueChangeEvent vce){
        String a=vce.getNewValue().toString();
         
          TransactionHelper th= new TransactionHelper();
          List <Banktransaction> transaction2=th.getgetter(a);
          List <Banktransaction> transaction1=th.getsender(a);
         for(Iterator it=transaction2.iterator();it.hasNext();){
          Banktransaction tx=(Banktransaction)it.next();
            Transaction t= new Transaction();
            String getname,sendername;
            getname=tx.getAccountgetter();//收款人姓名
            sendername=tx.getAccountsender();//发款人姓名

            //收款人信息
            RegisterHelper rh=new RegisterHelper();
            Iterator it1=rh.getRegister(getname).iterator();
            Register r=(Register)it1.next();
            t.setBankaccountnumberGet(r.getBankaccountnumber());
            t.setCustomergetname(r.getCumstomername());
                                 
            //发款人信息
            Iterator it2=rh.getRegister(sendername).iterator();
            Register r1=(Register)it2.next();
            t.setBankaccountnumberSend(r1.getBankaccountnumber());
            t.setCustomersendname(r1.getCumstomername());
            
            t.setAccountid(tx.getAccountid());
            t.setBanktransaction(tx.getBanktransaction());
            t.setTransactiondate(tx.getTransactiondate());
            t.setTransactiondmoney(tx.getTransactiondmoney());
            tran.add(t);
            System.out.println(t.toString());
         }
         for(Iterator it=transaction1.iterator();it.hasNext();){
          Banktransaction tx=(Banktransaction)it.next();
            Transaction t= new Transaction();
            String getname,sendername;
            getname=tx.getAccountgetter();//收款人姓名
            sendername=tx.getAccountsender();//发款人姓名

            //收款人信息
            RegisterHelper rh=new RegisterHelper();
            Iterator it1=rh.getRegister(getname).iterator();
            Register r=(Register)it1.next();
            t.setBankaccountnumberGet(r.getBankaccountnumber());
            t.setCustomergetname(r.getCumstomername());
                                 
            //发款人信息
            Iterator it2=rh.getRegister(sendername).iterator();
            Register r1=(Register)it2.next();
            t.setBankaccountnumberSend(r1.getBankaccountnumber());
            t.setCustomersendname(r1.getCumstomername());
            
            t.setAccountid(tx.getAccountid());
            t.setBanktransaction(tx.getBanktransaction());
            t.setTransactiondate(tx.getTransactiondate());
            t.setTransactiondmoney(tx.getTransactiondmoney());
            tran.add(t);
            System.out.println(t.toString());
             System.out.println(tran.toString());}
}

    public List<Transaction> getTran() {
        return tran;
    }

    public void setTran(List<Transaction> tran) {
        this.tran = tran;
    }
   
      
    public TransactionSearch() {
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
